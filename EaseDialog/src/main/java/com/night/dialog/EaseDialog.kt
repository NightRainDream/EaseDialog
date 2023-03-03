package com.night.dialog

import android.app.Application
import android.content.Context
import androidx.annotation.StyleRes

object EaseDialog {
    private lateinit var mApplication: Application
    private var mDebug: Boolean = true
    private var mDialogStyle: Int = R.style.EaseDialog


    /**
     * 初始化EaseDialog
     *
     * @param application Application
     * @param style Dialog样式[R.style.EaseDialog]
     * @param isDebug 是否运行在Debug模式下
     */
    fun initialize(application: Application, @StyleRes style: Int = R.style.EaseDialog, isDebug: Boolean = true) {
        mApplication = application
        mDebug = isDebug
        mDialogStyle = style
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

    /**
     * 获取自定义Dialog样式
     */
    fun getDialogStyle(): Int {
        return mDialogStyle
    }
}