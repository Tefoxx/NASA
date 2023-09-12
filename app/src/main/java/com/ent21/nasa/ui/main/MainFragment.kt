package com.ent21.nasa.ui.main

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.ent21.nasa.R
import com.ent21.nasa.base.BaseFragment
import com.ent21.nasa.databinding.FragmentMainBinding
import com.ent21.nasa.viewBinding

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val viewBinding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)

        val navController = fragmentContainer.getFragment<NavHostFragment>().navController

        bottomNavigationView.setOnItemReselectedListener { }
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.feed -> {
                    navController.navigate(R.id.toFeedFragment)
                    true
                }
                R.id.earth -> {
                    navController.navigate(R.id.toEarthFragment)
                    true
                }
                else -> false
            }
        }
    }
}