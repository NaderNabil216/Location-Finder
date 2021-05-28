package com.example.places_list.ui.activity

import com.example.places_list.R
import com.examples.core.base.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlacesListActivity : BaseActivity(){
    override var navGraphResourceId: Int = R.navigation.places_nav

}