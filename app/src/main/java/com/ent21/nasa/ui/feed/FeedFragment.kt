package com.ent21.nasa.ui.feed

import android.os.Bundle
import android.view.View
import com.ent21.nasa.R
import com.ent21.nasa.core.BaseFragment
import com.ent21.nasa.databinding.FragmentFeedBinding
import com.ent21.nasa.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedFragment : BaseFragment(R.layout.fragment_feed) {
    private val viewBinding by viewBinding(FragmentFeedBinding::bind)
    private val viewModel: FeedViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)


    }
}