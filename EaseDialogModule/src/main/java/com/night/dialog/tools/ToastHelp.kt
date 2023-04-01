package com.night.dialog.tools

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.hjq.toast.config.IToastStyle
import com.night.dialog.callback.IBindView

class ToastHelp(gravity: Int, xOffset: Int, yOffset: Int, bind: IBindView) : IToastStyle<View> {
    private val mGravity = gravity
    private val mXOffset = xOffset
    private val mYOffset = yOffset
    private val mBindCallback = bind
    override fun createView(context: Context?): View {
        val mBindView = LayoutInflater.from(context).inflate(mBindCallback.mLayoutId, null)
        mBindCallback.onBind(mBindView)
        return mBindView
    }

    override fun getGravity(): Int {
        return mGravity
    }

    override fun getXOffset(): Int {
        return mXOffset
    }

    override fun getYOffset(): Int {
        return mYOffset
    }
}