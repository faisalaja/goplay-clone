package com.goplay.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.goplay.app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var navigation: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupNavigation()
    }

    private fun setupNavigation() {
        navigation = findNavController(R.id.nav_host_fragment)
        navigation.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splash_screen -> setVisibilityBottomNav(isShow = false)
                else -> setVisibilityBottomNav(isShow = true)
            }
        }
    }

    override fun onNavigateUp(): Boolean {
        return navigation.navigateUp()
    }

    private fun setVisibilityBottomNav(isShow: Boolean) {
        mainBinding.isShowComponentView = isShow
    }
}