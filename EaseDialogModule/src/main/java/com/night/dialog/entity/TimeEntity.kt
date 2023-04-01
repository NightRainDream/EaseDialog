package com.night.dialog.entity

import java.util.*

data class TimeEntity(val hour: Int, val minute: Int, val second: Int) {
    companion object{
        fun getToday(): TimeEntity {
            val cal = Calendar.getInstance()
            return TimeEntity(
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                cal.get(Calendar.SECOND)
            )
        }

        fun getDefaultMinDate(): TimeEntity {
            val cal = Calendar.getInstance()
            return TimeEntity(
                0,
                0,
                0
            )
        }

        fun getDefaultMaxDate(): TimeEntity {
            val cal = Calendar.getInstance()
            return TimeEntity(
                23,
                59,
                59
            )
        }
    }
}
