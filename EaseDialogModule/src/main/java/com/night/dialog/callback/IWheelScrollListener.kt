 package com.night.dialog.callback

import com.night.dialog.base.WheelView

internal interface IWheelScrollListener {
    fun onScrollingStarted(wheel: WheelView)
    fun onScrollingFinished(wheel: WheelView)
}