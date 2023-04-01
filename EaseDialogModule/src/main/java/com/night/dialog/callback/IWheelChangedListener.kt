package com.night.dialog.callback

import com.night.dialog.base.WheelView

internal interface IWheelChangedListener {
    fun onChanged(wheel: WheelView?, oldValue: Int, newValue: Int)
}