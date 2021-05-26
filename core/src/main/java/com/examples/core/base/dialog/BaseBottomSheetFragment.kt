package com.examples.core.base.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import com.examples.data.source.prefrences.StorageManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.examples.core.R
import com.examples.core.base.view_model.BaseViewModel
import com.examples.core.utils.LoadingListener
import com.examples.entities.base.ErrorStatus
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


@ExperimentalCoroutinesApi
abstract class BaseBottomSheetFragment<VM : BaseViewModel> : BottomSheetDialogFragment() {

    private val TAG = BaseBottomSheetFragment::class.java.simpleName

    abstract var layoutResourceId: Int
    private var mLoader: LoadingListener? = null

    abstract val viewModel: VM

    open var isRoundedCorners = true

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
                    ErrorStatus.NO_CONNECTION -> NoInternetDialog.showDialog(requireContext(),it.errorStatus)
                    ErrorStatus.UNAUTHORIZED -> handleUnAuthError()
                    ErrorStatus.INTERNAL_SERVER_ERROR -> ServerErrorDialog.showDialog(requireContext(),it.errorStatus)
                    else -> Toast.makeText(context, "${it?.message}", Toast.LENGTH_LONG).show()
                }
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


    open fun handleUnAuthError() {
        storageManager.clearUserData()
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            window?.attributes?.windowAnimations = R.style.dialog_animation
            setOnShowListener { setupBottomSheet(it) }
        }
    }

    open fun setupBottomSheet(dialogInterface: DialogInterface) {
        val bottomSheetDialog = dialogInterface as BottomSheetDialog
        val bottomSheet = bottomSheetDialog.findViewById<View>(
            com.google.android.material.R.id.design_bottom_sheet
        ) ?: return

        if (isRoundedCorners)
            bottomSheet.setBackgroundResource(R.drawable.bg_rounded_sheet_white)
    }

    override fun onStop() {
        super.onStop()
        with(viewModel) {
            errorLiveData.removeObservers(viewLifecycleOwner)
            cancellationMsgLiveData.removeObservers(viewLifecycleOwner)
            showLoading(false)
        }
    }


    open fun showLoading(show: Boolean) {
        mLoader?.showLoading(show)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.let {
            if (it is LoadingListener) mLoader = it
        }
    }

    override fun onDetach() {
        super.onDetach()
        mLoader = null
    }
}
