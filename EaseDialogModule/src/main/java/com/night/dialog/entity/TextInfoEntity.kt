package com.night.dialog.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.ColorInt
import com.night.dialog.tools.DialogHelp

data class TextInfoEntity(var textSize: Int,@ColorInt var textColor: Int, var text: CharSequence?="", var isBold: Boolean = false): Parcelable {
    val mTextSize get() = run { DialogHelp.spToPx(textSize.toFloat()) }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(textSize)
        parcel.writeInt(textColor)
        parcel.writeString(text.toString())
        parcel.writeByte(if (isBold) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TextInfoEntity> {
        override fun createFromParcel(parcel: Parcel): TextInfoEntity {
            return TextInfoEntity(parcel)
        }

        override fun newArray(size: Int): Array<TextInfoEntity?> {
            return arrayOfNulls(size)
        }
    }
}