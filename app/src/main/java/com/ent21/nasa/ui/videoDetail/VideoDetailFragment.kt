package com.ent21.nasa.ui.videoDetail

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.ent21.nasa.R
import com.ent21.nasa.core.BaseFragment
import com.ent21.nasa.databinding.FragmentVideoDetailBinding
import com.ent21.nasa.ui.viewBinding
import com.ent21.nasa.utils.applyHtml
import com.ent21.nasa.utils.load
import com.ent21.nasa.utils.toast
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import java.text.SimpleDateFormat
import java.util.Locale

class VideoDetailFragment : BaseFragment(R.layout.fragment_video_detail) {
    private val args: VideoDetailFragmentArgs by navArgs()
    private val viewBinding by viewBinding(FragmentVideoDetailBinding::bind)
    private val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)
    private val videoId: String? by lazy { Uri.parse(args.apod.url).lastPathSegment }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)

        descriptionTextView.text =
            getString(R.string.explanation, args.apod.explanation).applyHtml()
        dateTextView.text = getString(R.string.date, dateFormat.format(args.apod.date)).applyHtml()

        args.apod.thumbnailUrl?.let { thumbnailImageView.load(it) }

        lifecycle.addObserver(playerView)
        playerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                videoId?.let { youTubePlayer.loadVideo(it, 0f) } ?: toast(R.string.video_error)
            }

            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
                super.onStateChange(youTubePlayer, state)
                if (state == PlayerConstants.PlayerState.PLAYING) {
                    placeholderGroup.isVisible = false
                }
            }

            override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                super.onError(youTubePlayer, error)
                placeholderGroup.isVisible = false
                errorGroup.isVisible = true
            }
        })
    }
}