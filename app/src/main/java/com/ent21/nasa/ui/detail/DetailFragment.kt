package com.ent21.nasa.ui.detail

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ent21.nasa.R
import com.ent21.nasa.core.BaseFragment
import com.ent21.nasa.databinding.FragmentDetailBinding
import com.ent21.nasa.ui.viewBinding
import com.ent21.nasa.utils.applyHtml
import com.ent21.nasa.utils.getDp
import com.ent21.nasa.utils.load
import com.ent21.nasa.utils.showPicture
import com.ent21.nasa.utils.toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.Locale

private const val CONTENT_TITLE_MARGIN = 16

class DetailFragment : BaseFragment(R.layout.fragment_detail) {
    private val args by navArgs<DetailFragmentArgs>()
    private val viewBinding by viewBinding(FragmentDetailBinding::bind)
    private val viewModel: DetailViewModel by viewModel { parametersOf(args) }
    private val imgUrl by lazy { args.apod.hdUrl ?: args.apod.url }
    private val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted -> viewModel.onPermissionResult(isGranted) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.title = args.apod.title
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        descriptionTextView.text =
            getString(R.string.explanation, args.apod.explanation).applyHtml()
        dateTextView.text = getString(R.string.date, dateFormat.format(args.apod.date)).applyHtml()

        imageView.load(imgUrl)
        imageView.setOnClickListener { showPicture(imgUrl) }

        toolbar.context.getDp(CONTENT_TITLE_MARGIN).let {
            toolbar.setContentInsetsAbsolute(it, it)
        }

        downloadButton.setOnClickListener {
            viewModel.saveImageInGallery()
        }

        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is DetailAction.ShowToast -> toast(action.textResId)
                is DetailAction.PermissionRequest -> requestPermissionLauncher.launch(action.permission)
            }
        }
    }
}