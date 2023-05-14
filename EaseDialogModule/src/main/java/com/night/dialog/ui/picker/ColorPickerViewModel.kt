package com.night.dialog.ui.picker

import com.night.dialog.base.EaseBaseViewModel
import com.night.dialog.callback.IColorSelectCallback

class ColorPickerViewModel:EaseBaseViewModel() {
    private var mCallback: IColorSelectCallback? = null
    private var mSelectR: Int = 0
    private var mSelectG: Int = 0
    private var mSelectB: Int = 0
    fun setCallback(callback:IColorSelectCallback?){
        if(callback == null){
            return
        }
        mCallback = callback
    }

    fun setSelectRGB(red: Int, green: Int, blue: Int){
        this.mSelectR = red
        this.mSelectG = green
        this.mSelectB = blue
    }

    fun onCancelEvent() {
        mCallback?.onCancel()
    }

    fun onPositiveEvent() {
        mCallback?.onPositive(mSelectR, mSelectG, mSelectB)
    }

    override fun onCleared() {
        super.onCleared()
        mCallback?.onDismiss()
    }
}