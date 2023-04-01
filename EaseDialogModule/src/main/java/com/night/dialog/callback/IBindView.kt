package com.night.dialog.callback

import android.view.View
import androidx.annotation.LayoutRes

abstract class IBindView(@LayoutRes id: Int) {
    val mLayoutId = id
    abstract fun onBind(bindView: View)
}