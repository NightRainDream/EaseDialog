package com.night.dialog.callback

import com.night.dialog.base.WheelView

internal interface IWheelClickedListener {
    fun onItemClicked(wheel: WheelView?, itemIndex: Int)
}