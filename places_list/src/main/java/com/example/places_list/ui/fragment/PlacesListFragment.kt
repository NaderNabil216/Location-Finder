package com.example.places_list.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.places_list.R
import com.example.places_list.ui.fragment.adapter.PlacesListAdapter
import com.examples.core.base.adapter.diffutilsAdapter.BaseRecyclerAdapter
import com.examples.core.base.fragment.BaseFragment
import com.examples.entities.explore_places.local.ExploredPlace
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_places_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class PlacesListFragment : BaseFragment<PlacesListViewModel>(),SwipeRefreshLayout.OnRefreshListener {
    override var layoutResourceId: Int = R.layout.fragment_places_list
    override val viewModel: PlacesListViewModel by viewModels()

    private val adapter: BaseRecyclerAdapter<ExploredPlace> = PlacesListAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideActivityToolbar()
        swipeContainer.setOnRefreshListener(this)
        observeSearchResults()
        onRefresh()
    }

    private fun observeSearchResults() {
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


}