package com.night.dialog.entity

import java.util.*

/**
 * 时间日期
 *
 * @param year 年
 * @param month 月
 * @param day 日
 * @param hour 时
 * @param minute 分
 * @param second 秒
 */
data class EaseDateTimeEntity(
    val year: Int,
    val month: Int,
    val day: Int,
    val hour: Int,
    val minute: Int,
    val second: Int
) {
    companion object {
        fun getToday(): EaseDateTimeEntity {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH) + 1
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val hour = cal.get(Calendar.HOUR_OF_DAY)
            val minute = cal.get(Calendar.MINUTE)
            val second = cal.get(Calendar.SECOND)
            return EaseDateTimeEntity(year, month, day, hour, minute, second)
        }

        fun getDefaultMin(): EaseDateTimeEntity {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR) - 10
            return EaseDateTimeEntity(year, 1, 1, 0, 0, 0)
        }

        fun getDefaultMax(): EaseDateTimeEntity {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR) + 10
            return EaseDateTimeEntity(year, 12, 31, 23, 59, 59)
        }
    }

    fun getDate(): String {
        return year.toString().plus("年").plus(month).plus("月").plus(day)
    }

    fun getTime(): String {
        return hour.toString().plus(":").plus(minute).plus(":").plus(second)
    }

    fun getDateTime(): String {
        return year.toString().plus("年").plus(month).plus("月").plus(day).plus(" ").plus(hour).plus(":").plus(minute)
            .plus(":")
            .plus(second)
    }
}