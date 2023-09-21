package com.ent21.nasa.ui.feed

import android.os.Bundle
import android.view.View
import com.ent21.nasa.R
import com.ent21.nasa.core.BaseFragment
import com.ent21.nasa.databinding.FragmentFeedBinding
import com.ent21.nasa.ui.PagingAdapter
import com.ent21.nasa.ui.viewBinding
import com.ent21.nasa.utils.SpaceItemDecorator
import com.ent21.nasa.utils.getDp
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val SPACE = 16

class FeedFragment : BaseFragment(R.layout.fragment_feed) {
    private val viewBinding by viewBinding(FragmentFeedBinding::bind)
    private val viewModel: FeedViewModel by viewModel()
    private val adapter: PagingAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = adapter
        view.context.getDp(SPACE).let {
            recyclerView.addItemDecoration(SpaceItemDecorator(it, it, it, it, it))
        }

        viewModel.action.observe(viewLifecycleOwner) {

        }

        viewModel.feed.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }
    }
}