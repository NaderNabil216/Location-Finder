package com.examples.core.base.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.examples.core.R
import com.examples.core.base.adapter.diffutilsAdapter.BaseRecyclerAdapter
import com.examples.core.base.view_model.BasePagingViewModel
import com.examples.core.utils.EndlessRecyclerViewScrollListener
import com.examples.core.utils.getVerticalLayoutManager
import com.examples.entities.base.ResponsePagingResultModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Nader Nabil on 30/10/2020.
 */
@ExperimentalCoroutinesApi
abstract class BasePagingFragment<T, VM> : BaseFragment<VM>(),
    SwipeRefreshLayout.OnRefreshListener where VM : BasePagingViewModel<T> {

    private val TAG = BasePagingFragment::class.java.simpleName

    abstract val pagingAdapter: BaseRecyclerAdapter<T>


    /*
    all paging fragment layout must include @layout/paging_view, just include and forget it.

    OR if u want to use custom layout for paging with different ids use methods to provide views.
     */

    open fun getPagingRecycler(): RecyclerView = requireView().findViewById(R.id.rvPaging)
        ?: throw Throwable("Can't find view with id rvPaging, Include paging_view or override getPagingRecycler")

    open fun getSwipeToRefreshView(): SwipeRefreshLayout =
        requireView().findViewById(R.id.swipeContainer)
            ?: throw Throwable("Can't find view with id swipeContainer, Include paging_view or override getSwipeToRefreshView")

    open fun getLoadMoreIndicator(): View = requireView().findViewById(R.id.loadMore)
        ?: throw Throwable("Can't find view with id loadMore, Include paging_view or override getLoadMoreIndicator")

    open fun getEmptyView(): View = requireView().findViewById(R.id.emptyView)
        ?: throw Throwable("Can't find view with id emptyView, Include paging_view or override getEmptyView")

    open fun getEmptyViewText(): TextView = requireView().findViewById(R.id.tv_empty_list)
        ?: throw Throwable("Can't find view with id tv_empty_list, Include paging_view or override getEmptyViewText")

    open fun getEmptyViewImg(): ImageView = requireView().findViewById(R.id.img_empty_state)
        ?: throw Throwable("Can't find view with id img_empty_state, Include paging_view or override getEmptyViewImg")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        initEmptyView()
        viewModel.pagingList.observe(viewLifecycleOwner){

            showOrHideLoadMoreView(false)
            cancelSwipeToRefresh()
            showOrHideEmptyView(it.data.isEmpty())

            pagingResponseObserver(it)
        }

        getSwipeToRefreshView().setOnRefreshListener(this)
        viewModel.initPageNumber()
    }

    open fun initRecycler() {

        getPagingRecycler().run {
            setHasFixedSize(true)
            adapter = pagingAdapter

            if (layoutManager == null)
                layoutManager = getVerticalLayoutManager(requireContext())
        }

        getPagingRecycler().addOnScrollListener(object :
            EndlessRecyclerViewScrollListener(getPagingRecycler().layoutManager!! as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if (viewModel.loadMore) {
                    viewModel.incrementAndFetchPage()
                    showOrHideLoadMoreView(viewModel.pageNumber > 1)
                }
            }
        })
    }


    open fun pagingResponseObserver(response: ResponsePagingResultModel<T>) {

        if (viewModel.pageNumber == 1) {
            pagingAdapter.submitList(response.data)
        } else if (viewModel.pageNumber > 1) {
            pagingAdapter.addToList(response.data)
        }

        /** Loading More Condition**/
        viewModel.loadMore = response.totalCount > viewModel.pageNumber * viewModel.pageSize
    }



    override fun onViewModelError() {
        super.onViewModelError()
        cancelSwipeToRefresh()
        showOrHideLoadMoreView(false)
    }


    fun showOrHideLoadMoreView(isVisible: Boolean) {
        getLoadMoreIndicator().visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    fun cancelSwipeToRefresh() {
        if (getSwipeToRefreshView().isRefreshing)
            getSwipeToRefreshView().isRefreshing = false
    }

    override fun onRefresh() {
        getSwipeToRefreshView().isRefreshing = true
        getLoadMoreIndicator().visibility = View.GONE
        viewModel.resetPageNumber()
    }


    fun showOrHideEmptyView(isEmptyStateVisible: Boolean) {
        if (isEmptyStateVisible) {
            getPagingRecycler().visibility = View.GONE
            getEmptyView().visibility = View.VISIBLE
        } else {
            getEmptyView().visibility = View.GONE
            getPagingRecycler().visibility = View.VISIBLE
        }
    }

    private fun initEmptyView() {
        setEmptyViewTxt(emptyViewTxtRes)
        setEmptyViewIcon(emptyViewIcon)
    }

    open val emptyViewIcon = R.drawable.ic_general_empty_view

    abstract val emptyViewTxtRes: Int

    fun setEmptyViewTxt(@StringRes str: Int) {
        getEmptyViewText().setText(str)
    }

    fun setEmptyViewIcon(@DrawableRes icon: Int) {
        getEmptyViewImg().setImageResource(icon)
    }

}
