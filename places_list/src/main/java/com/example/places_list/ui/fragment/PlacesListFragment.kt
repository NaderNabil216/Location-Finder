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
import com.examples.core.base.activity.PermissionsActivity
import com.examples.core.base.adapter.diffutilsAdapter.BaseRecyclerAdapter
import com.examples.core.base.fragment.BaseFragment
import com.examples.entities.explore_places.local.ExploredPlace
import com.google.android.gms.location.LocationRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_places_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class PlacesListFragment : BaseFragment<PlacesListViewModel>(),SwipeRefreshLayout.OnRefreshListener , ActivityCompat.OnRequestPermissionsResultCallback{
    private val TAG = "####"

    override var layoutResourceId: Int = R.layout.fragment_places_list
    override val viewModel: PlacesListViewModel by viewModels()

    private val adapter: BaseRecyclerAdapter<ExploredPlace> = PlacesListAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideActivityToolbar()
//        swipeContainer.setOnRefreshListener(this)
//        observePlacesResults()
//        onRefresh()
        initLocationFetching()
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

    private fun initLocationFetching(){
        val request = LocationRequest.create()
        Intent(requireActivity(), PlacesListActivity::class.java).apply {
            putExtra("request", request)
        }
        Locus.setLogging(true)
        startUpdates()
    }

    private fun getSingleUpdate() {
        Locus.getCurrentLocation(requireActivity()) { result ->
            result.location?.let {
//                tvSingleUpdate.text = "${it.latitude}, ${it.longitude}"
//                tvSingleUpdate.visibility = View.VISIBLE
//                tvErrors.visibility = View.INVISIBLE
                Log.e(TAG,"${it.latitude}, ${it.longitude}")
            } ?: run {
//                tvSingleUpdate.visibility = View.INVISIBLE
//                tvErrors.text = result.error?.message
//                tvErrors.visibility = View.VISIBLE
            }
        }
    }

    private fun onLocationUpdate(location: Location) {
//        btnStart.isEnabled = false
//        btnStop.isEnabled = true
//        llLocationData.visibility = View.VISIBLE
//        tvNoLocation.visibility = View.GONE
//        tvLatitude.text = location.latitude.toString()
//        tvLongitude.text = location.longitude.toString()
//        tvTime.text = getCurrentTimeString()
//        tvErrors.visibility = View.INVISIBLE


        Log.e(TAG, "Latitude: ${location.latitude}\tLongitude: ${location.longitude}")
    }

    private fun onError(error: Throwable?) {
//        btnStart.isEnabled = true
//        tvLatitude.text = ""
//        tvLongitude.text = ""
//        llLocationData.visibility = View.INVISIBLE
        Log.e(TAG, "Error: ${error?.message}")
//        tvErrors.text = error?.message
//        tvErrors.visibility = View.VISIBLE
    }

    fun startUpdates() {
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

    fun stopUpdates() {
        Locus.stopLocationUpdates()
//        btnStop.isEnabled = true
//        btnStart.isEnabled = true
//        llLocationData.visibility = View.INVISIBLE
//        tvNoLocation.visibility = View.VISIBLE
//        tvSingleUpdate.visibility = View.INVISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        stopUpdates()
    }

}