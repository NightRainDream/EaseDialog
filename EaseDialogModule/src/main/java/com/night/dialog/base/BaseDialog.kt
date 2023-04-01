package com.night.dialog.base

import android.app.Activity
import android.app.Dialog
import android.view.Gravity
import android.view.ViewGroup
import androidx.annotation.StyleRes
import java.lang.ref.WeakReference

class BaseDialog(activity: Activity, @StyleRes style: Int) : Dialog(activity, style) {
    private val mWeakActivity = WeakReference(activity)
    override fun show() {
        val mActivity = mWeakActivity.get()
        if (mActivity == null) {
            return
        }
        if (mActivity.isFinishing || mActivity.isDestroyed) {
            return
        }
        if (isShowing) {
            return
        }
        val mAttributes = window?.attributes
        mAttributes?.width = ViewGroup.LayoutParams.MATCH_PARENT
        mAttributes?.height = ViewGroup.LayoutParams.WRAP_CONTENT
        super.show()
    }

    override fun dismiss() {
        val mActivity = mWeakActivity.get()
        if (mActivity == null) {
            return
        }
        if (mActivity.isFinishing || mActivity.isDestroyed) {
            return
        }
        if (!isShowing) {
            return
        }
        super.dismiss()
    }

    fun getActivity(): Activity? {
        return mWeakActivity.get()
    }

    /**
     * 设置居中
     *
     * @param gravity  位置[Gravity]
     */
    fun setGravity(gravity: Int) {
        val mAttributes = window?.attributes
        mAttributes?.gravity = gravity
        window?.attributes = mAttributes
    }

    fun setDialogWidth(width: Float){
        val mAttributes = window?.attributes
        mAttributes?.width = width.toInt()
        window?.attributes = mAttributes
    }
}