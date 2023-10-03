package com.ent21.nasa.ui

import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ent21.nasa.R
import com.ent21.nasa.core.BaseActivity

class MainActivity : BaseActivity(R.layout.activity_main), MainNavigation {

    override fun getMainNavController(): NavController = findNavController(R.id.fragmentContainer)
}