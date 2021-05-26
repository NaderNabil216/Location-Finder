package com.examples.core.base.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.examples.core.R
import com.examples.entities.base.ErrorStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Nader Nabil on 5/1/2021.
 */
abstract class BaseNetworkingDialog {

    private lateinit var dialog: Dialog
    private lateinit var dialogImage: ImageView
    private lateinit var dialogTitle: TextView
    private lateinit var dialogDetails: TextView
    private lateinit var btnOk: TextView
    var isShown = false

    open fun showDialog(context: Context, errorStatus: ErrorStatus) {

        if (isShown)
            return

        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_networking_errors)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)

        initViews()

        dialog.window?.apply {
            setLayout(
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT
            )
            setFlags(
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
            )
            setGravity(Gravity.CENTER)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        isShown = true

        when (errorStatus) {
            ErrorStatus.NO_CONNECTION -> updateNoInternetConnectionUi()
            ErrorStatus.INTERNAL_SERVER_ERROR -> updateServerErrorUi()
            ErrorStatus.UNKNOWN_HOST -> updateUnknownHostUi()
            else -> updateNoInternetConnectionUi()
        }
        dialog.show()
    }


    fun dismiss(withDelay: Boolean = true) {
        isShown = false
        if (withDelay) CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            dialog.dismiss()
        }
        else dialog.dismiss()
    }

    private fun initViews() {
        btnOk = dialog.findViewById(R.id.btn_ok)
        dialogImage = dialog.findViewById(R.id.dialogImg)
        dialogTitle = dialog.findViewById(R.id.tvTitle)
        dialogDetails = dialog.findViewById(R.id.tvDetails)
        btnOk.setOnClickListener {
            dismiss(false)
        }
    }

    private fun updateNoInternetConnectionUi() {
        dialogTitle.text = dialog.context.getString(R.string.no_internet_connection)
        dialogDetails.text =
            dialog.context.getString(R.string.please_check_your_internet_connection_and_try_again)
        dialogImage.setImageResource(R.drawable.ic_no_internet)
    }

    private fun updateServerErrorUi() {
        dialogTitle.text = dialog.context.getString(R.string.internal_server_error)
        dialogDetails.text =
            dialog.context.getString(R.string.please_check_your_internet_connection_and_try_again)
        dialogImage.setImageResource(R.drawable.ic_server_error)
    }

    private fun updateUnknownHostUi() {
        dialogTitle.text = dialog.context.getString(R.string.unknown_host)
        dialogDetails.text =
            dialog.context.getString(R.string.please_check_your_internet_connection_and_try_again)
        dialogImage.setImageResource(R.drawable.ic_server_error)
    }

}