package com.night.dialog.base

import android.app.Activity
import android.app.Dialog
import android.content.res.Configuration
import android.content.res.Resources
import com.night.dialog.EaseDialog
import com.night.dialog.R
import java.lang.ref.WeakReference
import kotlin.coroutines.Continuation

class BaseDialog(activity: Activity) : Dialog(activity, R.style.KitCustomDialog) {
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
        super.dismiss()
    }

    fun getActivity(): Activity? {
        return mWeakActivity.get()
    }

    /**
     * 设置Dialog宽度
     *
     * @param ratio 宽屏宽度的百分比
     */
    fun setDialogWidth(ratio: Float = 0.8F) {
        var screenWidth : Float =    Resources.getSystem().displayMetrics.widthPixels.toFloat()
        if (EaseDialog.getContext().resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            screenWidth *= 0.6F
        }
        val attrs = window?.attributes
        attrs?.width = (screenWidth * ratio).toInt()
        window?.attributes = attrs
    }

    /**
     * 设置弹出位置
     */
    fun setGravity(gravity: Int) {
        window?.setGravity(gravity)
    }
}