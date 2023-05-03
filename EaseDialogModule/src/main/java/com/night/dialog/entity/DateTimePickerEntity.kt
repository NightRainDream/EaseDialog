package com.night.dialog.entity

import java.util.*

data class DateTimePickerEntity(
    val year: Int,
    val month: Int,
    val day: Int,
    val hour: Int,
    val minute: Int,
    val second: Int
) {
    companion object {
        fun getToday(): DateTimePickerEntity {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)+1
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val hour = cal.get(Calendar.HOUR_OF_DAY)
            val minute = cal.get(Calendar.MINUTE)
            val second = cal.get(Calendar.SECOND)
            return DateTimePickerEntity(year, month, day, hour, minute, second)
        }

        fun getDefaultMin(): DateTimePickerEntity {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR) - 10
            return DateTimePickerEntity(year, 1, 1, 0, 0, 0)
        }

        fun getDefaultMax(): DateTimePickerEntity{
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR) + 10
            return DateTimePickerEntity(year, 12, 31, 23, 59, 59)
        }
    }
}
