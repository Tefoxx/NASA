package com.ent21.nasa.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(private val layoutResId: Int) : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
    }
}