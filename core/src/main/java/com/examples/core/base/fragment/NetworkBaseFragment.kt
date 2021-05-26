package com.examples.core.base.fragment

import android.util.Log
import androidx.fragment.app.Fragment
import com.examples.entities.base.ErrorStatus
import com.examples.core.base.dialog.NoInternetDialog
import com.examples.core.utils.network.ConnectivityProvider
import com.examples.core.utils.network.NetworkUtils

/**
 * Created by Nader Nabil
 */
open class NetworkBaseFragment : Fragment(), ConnectivityProvider.ConnectivityStateListener {

    private val TAG = NetworkBaseFragment::class.java.simpleName

    companion object {
        var isNetworkConnected = true
    }

    private val provider: ConnectivityProvider by lazy { ConnectivityProvider.createProvider(requireContext()) }


    override fun onResume() {
        super.onResume()
        provider.addListener(this)
    }

    override fun onStop() {
        super.onStop()
        if (NoInternetDialog.isShown) NoInternetDialog.dismiss()
        provider.removeListener(this)
    }

    override fun onNetworkStateChange(state: ConnectivityProvider.NetworkState) {
        val hasInternet = NetworkUtils.isNetworkConnected(state)
        if (!hasInternet) {
            NoInternetDialog.showDialog(requireContext(), ErrorStatus.NO_CONNECTION)
            isNetworkConnected = false
        } else {
            if (NoInternetDialog.isShown) NoInternetDialog.dismiss()
            isNetworkConnected = true
        }
        Log.d(TAG, "Is Network connected: $hasInternet")
    }
}