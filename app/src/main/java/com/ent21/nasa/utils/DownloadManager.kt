package com.ent21.nasa.utils

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

private const val APP_IMAGE_DIRECTORY = "NasaApp"
private const val TAG = "DownloadManager"
class DownloadManager(private val context: Context, private val scope: CoroutineScope) {

    private val compressFormat
        get() = Bitmap.CompressFormat.PNG

    fun saveImageInGallery(
        title: String,
        url: String,
        onError: (Throwable) -> Unit,
        permissionRequest: (permission: String) -> Unit
    ) = scope.launch(Dispatchers.IO) {
        runCatching {
            val bitmap = withContext(Dispatchers.IO) {
                Glide.with(context).asBitmap().load(url).submit().get()
            }
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q && checkPermission()) {
                permissionRequest(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            } else {
                saveBitmapInGallery(bitmap, title)
            }
        }.onFailure {
            Log.e(TAG, "Failed to save image in gallery", it)
            onError(it)
        }
    }

    private fun checkPermission() = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    ) != PackageManager.PERMISSION_GRANTED

    private fun saveBitmapInGallery(bitmap: Bitmap, title: String) {
        val values = getContentValues(title)
        var uri: Uri? = null
        with(context.contentResolver) {
            runCatching {
                insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)?.also {
                    uri = it
                    openOutputStream(it)?.use { stream ->
                        if (!bitmap.compress(compressFormat, 100, stream))
                            throw IOException("Failed to save bitmap.")
                    } ?: throw IOException("Failed to open output stream.")
                } ?: throw IOException("Failed to create new MediaStore record.")
                uri?.takeIf { Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q }?.let {
                    val updateValues = ContentValues()
                    updateValues.put(MediaStore.Images.ImageColumns.IS_PENDING, false)
                    update(it, updateValues, null, null)
                }
            }.getOrElse {
                uri?.let { uri -> delete(uri, null, null) }
                throw it
            }
        }
    }

    private fun getContentValues(title: String) = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, title)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/${compressFormat.name}")
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            put(
                MediaStore.MediaColumns.DATA,
                StringBuilder()
                    .append(Environment.getExternalStorageDirectory()).appendSeparator()
                    .append(Environment.DIRECTORY_PICTURES).appendSeparator()
                    .append(APP_IMAGE_DIRECTORY).appendSeparator()
                    .append(title)
                    .toString()
            )
        } else {
            put(
                MediaStore.MediaColumns.RELATIVE_PATH,
                StringBuilder()
                    .append(Environment.DIRECTORY_PICTURES).appendSeparator()
                    .append(APP_IMAGE_DIRECTORY)
                    .toString()
            )
            put(MediaStore.MediaColumns.IS_PENDING, true)
        }
    }
}