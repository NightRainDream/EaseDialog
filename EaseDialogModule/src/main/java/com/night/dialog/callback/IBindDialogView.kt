package com.night.dialog.callback

import androidx.annotation.LayoutRes
import com.night.dialog.base.BaseDialog

abstract class IBindDialogView(@LayoutRes id: Int) {
    val mLayoutId = id
    abstract fun onBind(dialog: BaseDialog)
}