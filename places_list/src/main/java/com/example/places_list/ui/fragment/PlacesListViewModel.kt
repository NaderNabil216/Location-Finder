package com.example.places_list.ui.fragment

import android.location.Location
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.examples.core.base.view_model.BaseViewModel
import com.examples.domain.usecases.ExplorePlacesUsecase
import com.examples.entities.explore_places.local.ExploredPlace
import com.examples.entities.explore_places.query.ExplorePlacesQuery
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class PlacesListViewModel @ViewModelInject constructor(private val explorePlacesUsecase: ExplorePlacesUsecase) :
    BaseViewModel() {

    var latestSavedLocation: Location? = null

    private val placesListResultsLiveData = MutableLiveData<List<ExploredPlace>>()
    val placesListResult: LiveData<List<ExploredPlace>> = placesListResultsLiveData

    fun getPlacesList() {
        callApi(placesListResultsLiveData) {
            explorePlacesUsecase.execute(
                ExplorePlacesQuery(
                    "${latestSavedLocation?.latitude ?: 0.0},${latestSavedLocation?.longitude ?: 0.0}",
                    "1000",
                    "1000"
                ), it
            )
        }
    }
}