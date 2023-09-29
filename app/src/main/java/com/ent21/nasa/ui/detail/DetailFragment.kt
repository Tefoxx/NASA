package com.ent21.nasa.ui.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ent21.nasa.R
import com.ent21.nasa.core.BaseFragment
import com.ent21.nasa.databinding.FragmentDetailBinding
import com.ent21.nasa.ui.viewBinding
import com.ent21.nasa.utils.getDp
import com.ent21.nasa.utils.load

private const val CONTENT_TITLE_MARGIN = 16

class DetailFragment : BaseFragment(R.layout.fragment_detail) {
    private val viewBinding by viewBinding(FragmentDetailBinding::bind)
    private val args by navArgs<DetailFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.title = args.apod.title
        descriptionTextView.text = args.apod.explanation
        imageView.load(args.apod.hdUrl ?: args.apod.url)

        toolbar.context.getDp(CONTENT_TITLE_MARGIN).let {
            toolbar.setContentInsetsAbsolute(it, it)
            toolbar.contentInsetStartWithNavigation= 0
        }

        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
    }
}