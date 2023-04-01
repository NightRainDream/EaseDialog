package com.night.dialog.entity

import androidx.annotation.ColorInt
import com.night.dialog.tools.DialogHelp

data class TextInfoEntity(val textSize: Int,@ColorInt val textColor: Int, val text: CharSequence = "", val isBold: Boolean = false) {
    val mTextSize get() = run { DialogHelp.spToPx(textSize.toFloat()) }
}