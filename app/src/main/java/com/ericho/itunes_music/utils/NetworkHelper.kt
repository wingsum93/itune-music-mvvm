package com.ericho.itunes_music.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object NetworkHelper {
    fun isNetworkAvailable(context: Context): Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

}