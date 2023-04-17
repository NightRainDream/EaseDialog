package com.night.dialog.tools

import android.util.Log
import com.night.dialog.BuildConfig

internal object LogcatToos {
    private const val TAG = "EaseDialog"
    fun i(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, msg)
        }
    }

    fun w(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.w(TAG, msg)
        }
    }

    fun e(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, msg)
        }
    }
}