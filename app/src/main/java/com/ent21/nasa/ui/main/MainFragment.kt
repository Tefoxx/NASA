package com.ent21.nasa.ui.main

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.ent21.nasa.BottomNavGraphDirections
import com.ent21.nasa.R
import com.ent21.nasa.core.BaseFragment
import com.ent21.nasa.databinding.FragmentMainBinding
import com.ent21.nasa.ui.viewBinding

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val viewBinding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(viewBinding) {
        super.onViewCreated(view, savedInstanceState)

        val navController = fragmentContainer.getFragment<NavHostFragment>().navController

        bottomNavigationView.setOnItemReselectedListener { }
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.feed -> {
                    navController.navigate(BottomNavGraphDirections.toFeedFragment())
                    true
                }

                R.id.earth -> {
                    navController.navigate(BottomNavGraphDirections.toEarthFragment())
                    true
                }

                else -> false
            }
        }
    }
}