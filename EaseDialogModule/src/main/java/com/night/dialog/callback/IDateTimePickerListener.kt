package com.night.dialog.callback

interface IDateTimePickerListener {
    fun onSelected(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int)
}