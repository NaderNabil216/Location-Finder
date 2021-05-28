package com.example.places_list.ui.fragment.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.example.places_list.R
import com.examples.core.base.adapter.diffutilsAdapter.BaseRecyclerAdapter
import com.examples.entities.explore_places.local.ExploredPlace
import kotlinx.android.synthetic.main.item_place.view.*

class PlacesListAdapter() :
    BaseRecyclerAdapter<ExploredPlace>({ oldItem, newItem -> oldItem.placeName == newItem.placeName }) {
    override val itemLayoutRes: Int
        get() = R.layout.item_place

    override fun bind(view: View, item: ExploredPlace, position: Int) {
        view.run {

            if (item.placeIcon.isNotEmpty()) Glide.with(context).load(item.placeIcon).circleCrop().into(ivIcon)
            tvPlaceTitle.text = item.placeName
            tvAddress.text = item.placeAddress
        }
    }
}