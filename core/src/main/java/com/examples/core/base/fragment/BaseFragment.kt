package com.examples.core.base.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.examples.core.base.activity.ToolbarListener
import com.examples.core.base.dialog.NoInternetDialog
import com.examples.core.base.dialog.ServerErrorDialog
import com.examples.core.base.dialog.UnknownHostDialog
import com.examples.core.base.view_model.BaseViewModel
import com.examples.core.utils.LoadingListener
import com.examples.data.source.prefrences.StorageManager
import com.examples.entities.base.ErrorStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**
 * Created by Nader Nabil
 */
@ExperimentalCoroutinesApi
abstract class BaseFragment<VM : BaseViewModel> : NetworkBaseFragment() {

    private val TAG = BaseFragment::class.java.simpleName

    abstract var layoutResourceId: Int
    private var mLoader: LoadingListener? = null

    abstract val viewModel: VM

    protected var toolbarListener: ToolbarListener? = null


    @Inject
    lateinit var storageManager: StorageManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResourceId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            errorLiveData.observe(viewLifecycleOwner, Observer {
                when (it.errorStatus) {
                    ErrorStatus.NO_CONNECTION -> NoInternetDialog.showDialog(
                        requireContext(),
                        it.errorStatus
                    )
                    ErrorStatus.UNAUTHORIZED -> handleUnAuthError()
                    ErrorStatus.INTERNAL_SERVER_ERROR -> ServerErrorDialog.showDialog(
                        requireContext(),
                        it.errorStatus
                    )
                    ErrorStatus.UNKNOWN_HOST -> UnknownHostDialog.showDialog(
                        requireContext(),
                        it.errorStatus
                    )
                    ErrorStatus.FORRBIDEN, ErrorStatus.NOT_FOUND -> Toast.makeText(
                        context,
                        "Something went wrong",
                        Toast.LENGTH_LONG
                    ).show()
                    else -> Toast.makeText(context, "${it?.message}", Toast.LENGTH_LONG).show()
                }
                onViewModelError()
            })

            cancellationMsgLiveData.observe(viewLifecycleOwner, Observer {
                Log.d(TAG, it)
            })

            isLoadingLiveData.observe(viewLifecycleOwner, Observer {
                showLoading(it)
                Log.d(TAG, "Loading observer is called")
            })
        }

    }


    open fun onViewModelError() {

    }

    open fun handleUnAuthError() {
        storageManager.clearUserData()
    }

    fun setActivityToolbarTitle(title: String, gravity: Int? = null) {
        toolbarListener?.setActivityToolbarTitle(title, gravity)
    }

    fun hideActivityToolbar() {
        toolbarListener?.hideActivityToolbar()
    }


    override fun onDestroyView() {
        with(viewModel) {
            errorLiveData.removeObservers(viewLifecycleOwner)
            cancellationMsgLiveData.removeObservers(viewLifecycleOwner)
            showLoading(false)
        }
        super.onDestroyView()
    }


    open fun showLoading(show: Boolean) {
        mLoader?.showLoading(show)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.let {
            if (it is LoadingListener) mLoader = it

            if (it is ToolbarListener) toolbarListener = it

        }
    }

    override fun onDetach() {
        super.onDetach()
        mLoader = null
        toolbarListener = null
    }
}
