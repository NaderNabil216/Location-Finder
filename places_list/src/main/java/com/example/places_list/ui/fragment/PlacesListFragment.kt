package com.example.places_list.ui.fragment

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.birjuvachhani.locus.Locus
import com.example.places_list.R
import com.example.places_list.ui.activity.PlacesListActivity
import com.example.places_list.ui.fragment.adapter.PlacesListAdapter
import com.examples.core.base.adapter.diffutilsAdapter.BaseRecyclerAdapter
import com.examples.core.base.fragment.BaseFragment
import com.examples.core.utils.showToast
import com.examples.entities.explore_places.local.ExploredPlace
import com.google.android.gms.location.LocationRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_places_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class PlacesListFragment : BaseFragment<PlacesListViewModel>(),
    SwipeRefreshLayout.OnRefreshListener, ActivityCompat.OnRequestPermissionsResultCallback {
    private val TAG = "####"

    override var layoutResourceId: Int = R.layout.fragment_places_list
    override val viewModel: PlacesListViewModel by viewModels()

    private val adapter: BaseRecyclerAdapter<ExploredPlace> = PlacesListAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideActivityToolbar()
        swipeContainer.setOnRefreshListener(this)
        observePlacesResults()
        onRefresh()
        initLocationFetching()
        initViews()
    }

    private fun initViews() {
        locationSwitcher.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> {
                    startUpdates()
                }
                false -> {
                    stopUpdates()
                    getSingleUpdate()
                }
            }
        }
    }

    private fun observePlacesResults() {
        viewModel.placesListResult.observe(viewLifecycleOwner, {
            cancelSwipeToRefresh()
            showOrHideEmptyView(it.isEmpty())
            rvPaging.adapter = adapter
            adapter.submitList(it)
        })
    }

    override fun onRefresh() {
        swipeContainer.isRefreshing = true
        adapter.clear()
        viewModel.getPlacesList()
    }

    private fun cancelSwipeToRefresh() {
        if (swipeContainer.isRefreshing)
            swipeContainer.isRefreshing = false
    }

    private fun showOrHideEmptyView(isEmptyStateVisible: Boolean) {
        if (isEmptyStateVisible) {
            rvPaging.visibility = View.GONE
            emptyState.visibility = View.VISIBLE
        } else {
            emptyState.visibility = View.GONE
            rvPaging.visibility = View.VISIBLE
        }
    }

    private fun initLocationFetching() {
        val request = LocationRequest.create()
        Intent(requireActivity(), PlacesListActivity::class.java).apply {
            putExtra("request", request)
        }
        Locus.setLogging(true)
        getSingleUpdate()
    }

    private fun getSingleUpdate() {
        Locus.getCurrentLocation(requireActivity()) { result ->
            result.location?.let {
                Log.e(TAG, "${it.latitude}, ${it.longitude}")
                onLocationUpdate(it)
            }
        }
    }

    private fun startUpdates() {
        Locus.configure {
            enableBackgroundUpdates = false
            forceBackgroundUpdates = true
            shouldResolveRequest = true
        }

        Locus.startLocationUpdates(this) { result ->
            result.location?.let(::onLocationUpdate)
            result.error?.let(::onError)
        }
    }

    private fun stopUpdates() {
        Locus.stopLocationUpdates()
    }

    private fun onLocationUpdate(location: Location) {
        Log.e(TAG, "Latitude: ${location.latitude}\tLongitude: ${location.longitude}")
        when (viewModel.latestSavedLocation) {
            null -> {
                viewModel.latestSavedLocation = location
                onRefresh()
            }
            else -> {
                viewModel.latestSavedLocation?.distanceTo(location)?.let {
                    if (it >= 500.0f) {
                        viewModel.latestSavedLocation = location
                        onRefresh()
                    }
                }
            }

        }
    }

    private fun onError(error: Throwable?) {
        Log.e(TAG, "Error: ${error?.message}")
        requireContext().showToast("Error: ${error?.message}")
    }

    override fun onDestroy() {
        super.onDestroy()
        stopUpdates()
    }

}