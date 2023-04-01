package com.night.dialog.entity

import java.util.*

data class DateEntity(val year: Int, val month: Int, val day: Int) {
    companion object {
        /**
         * 获取今天日期
         */
        fun getToday(): DateEntity {
            val cal = Calendar.getInstance()
            return DateEntity(
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH) + 1,
                cal.get(Calendar.DATE)
            )
        }

        /**
         * 默认最小日期
         */
        fun getDefaultMinDate(): DateEntity {
            val cal = Calendar.getInstance()
            return DateEntity(
                cal.get(Calendar.YEAR) - 10,
                1,
                1
            )
        }

        /**
         * 默认最大日期
         */
        fun getDefaultMaxDate(): DateEntity {
            val cal = Calendar.getInstance()
            return DateEntity(
                cal.get(Calendar.YEAR) + 10,
                12,
                31
            )
        }
    }
}
