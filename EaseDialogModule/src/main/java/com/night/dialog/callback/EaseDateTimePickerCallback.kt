package com.night.dialog.callback

import com.night.dialog.base.EaseBaseCallback
import com.night.dialog.entity.EaseDateTimeEntity


/**
 * 时间日期选择器变化
 */
interface IDateTimeChanceListener {
    fun onDateTimeChange(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int)
}

/**
 * 时间日期选择回调
 */
interface IDateTimeCallback:EaseBaseCallback {
    fun onPositive(result: EaseDateTimeEntity)
}