package com.night.dialog.entity

import androidx.annotation.ColorInt
import com.night.dialog.tools.DialogHelp

data class TextInfoEntity(var textSize: Int,@ColorInt var textColor: Int, var text: CharSequence = "", var isBold: Boolean = false) {
    val mTextSize get() = run { DialogHelp.spToPx(textSize.toFloat()) }
}