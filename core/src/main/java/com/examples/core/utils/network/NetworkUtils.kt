package com.examples.core.utils.network

/**
 * Created by Nader Nabil.
 */
class NetworkUtils {

    companion object{
        fun isNetworkConnected(state : ConnectivityProvider.NetworkState): Boolean {
            return (state as? ConnectivityProvider.NetworkState.ConnectedState)?.hasInternet == true
        }
    }
}