package com.night.dialog.base

import android.app.Activity
import android.view.View
import android.widget.PopupWindow
import java.lang.ref.WeakReference

class BasePopupWindow(activity: Activity) : PopupWindow(activity) {
    private val mWeakActivity = WeakReference(activity)
    init {
        isFocusable = true
        isTouchable = true
        isOutsideTouchable = true
    }
    override fun showAtLocation(parent: View?, gravity: Int, x: Int, y: Int) {
        if (isReady()) {
            super.showAtLocation(parent, gravity, x, y)
        }
    }

    override fun showAsDropDown(anchor: View?, xoff: Int, yoff: Int, gravity: Int) {
        if (isReady()) {
            super.showAsDropDown(anchor, xoff, yoff, gravity)
        }
    }

    override fun dismiss() {
        if (isReady()) {
            super.dismiss()
        }
    }


    private fun isReady(): Boolean {
        val mActivity = mWeakActivity.get()
        mActivity ?: return false
        if (mActivity.isDestroyed || mActivity.isFinishing) {
            return false
        }
        return true
    }
}