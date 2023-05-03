package com.night.dialog.callback

import com.night.dialog.entity.DateTimeEntity

interface IDateTimeSelectCallback {
    fun onSelectDate(result: DateTimeEntity)

    fun onCancel(){}

    fun onDismiss(){}
}