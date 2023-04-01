package com.night.dialog.entity


data class DateTimeEntity(val year: String,val month: String, val day: String, val hour: String, val minute: String, val second: String) {
    fun getDate(): String {
        return year.plus("-").plus(month).plus("-").plus(day)
    }

    fun getTime(): String {
        return hour.plus(":").plus(minute).plus(":")
            .plus(second)
    }

    fun getDateTime(): String {
        return year.plus("-").plus(month).plus("-").plus(day).plus(" ").plus(hour).plus(":").plus(minute).plus(":").plus(second)
    }
}