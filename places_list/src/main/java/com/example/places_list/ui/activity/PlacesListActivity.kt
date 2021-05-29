package com.example.places_list.ui.activity

import android.os.Bundle
import com.example.places_list.R
import com.examples.core.base.activity.PermissionsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlacesListActivity : PermissionsActivity() {
    override var permissions: Array<String> = arrayOf(
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )
    override var navGraphResourceId: Int = R.navigation.places_nav

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissionsLauncher.launch(permissions)
    }

}