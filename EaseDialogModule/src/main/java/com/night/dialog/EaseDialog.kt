package com.night.dialog

import android.app.Application
import android.content.Context
import com.hjq.toast.Toaster

object EaseDialog {
    private lateinit var mApplication: Application
    /**
     * 初始化EaseDialog
     *
     * @param application Application
     */
    fun initialize(application: Application) {
        mApplication = application
        Toaster.init(mApplication)
    }

    /**
     * 获取应用上下文
     */
    fun getContext(): Context {
        if (!this::mApplication.isInitialized) {
            throw NullPointerException("Not Initialize EaseDialog")
        }
        return mApplication.applicationContext
    }

}