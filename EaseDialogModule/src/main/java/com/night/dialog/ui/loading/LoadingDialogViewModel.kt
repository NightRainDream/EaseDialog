package com.night.dialog.ui.loading

import com.night.dialog.base.EaseBaseViewModel
import com.night.dialog.callback.ILoadingCallback

class LoadingDialogViewModel : EaseBaseViewModel() {
    private var mCallback: ILoadingCallback? = null

    /**
     * 设置回调事件
     */
    fun setCallback(callback: ILoadingCallback?) {
        if (callback == null) {
            return
        }
        this.mCallback = callback
    }

    override fun onCleared() {
        super.onCleared()
        mCallback?.onDismiss()
    }
}