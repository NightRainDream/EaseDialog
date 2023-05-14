package com.night.dialog.ui.tips

import com.night.dialog.base.EaseBaseViewModel
import com.night.dialog.callback.ITipsModalCallback

class TipsViewModel : EaseBaseViewModel() {
    private var mCallback: ITipsModalCallback? = null

    /**
     * 设置回调事件
     */
    fun setCallback(callback: ITipsModalCallback?) {
        if (callback == null) {
            return
        }
        this.mCallback = callback
    }

    /**
     * 点击取消按钮事件
     */
    fun onCancelEvent() {
        mCallback?.onCancel()
    }

    /**
     * 点击确定按钮事件
     */
    fun onPositiveEvent() {
        mCallback?.onPositive()
    }


    override fun onCleared() {
        super.onCleared()
        mCallback?.onDismiss()
    }
}