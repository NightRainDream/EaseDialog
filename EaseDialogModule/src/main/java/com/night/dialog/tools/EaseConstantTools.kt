package com.night.dialog.tools

import androidx.annotation.IntDef

/**
 * 横屏Dialog高度占比
 */
const val HEIGHT_RATIO_LANDSCAPE = 0.8F

/**
 * 竖屏Dialog高度占比
 */
const val HEIGHT_RATIO_PORTRAIT = 0.5F

//------------------------> 日期时间选择器 开始 <------------------------
const val PICKER_DATE = 1000
const val PICKER_YEAR = 1001
const val PICKER_YEAR_MONTH = 1002
const val PICKER_MONTH_DAY = 1003
const val PICKER_DAY = 1004

const val PICKER_TIME = 1005
const val PICKER_HOUR = 1006
const val PICKER_HOUR_MINUTE = 1007
const val PICKER_MINUTE_SECOND = 1008
const val PICKER_SECOND = 1009

const val PICKER_ALL = 1010
const val PICKER_DATE_HOUR = 1011
const val PICKER_DATE_HOUR_MINUTE = 1012

@IntDef(
    PICKER_DATE, PICKER_YEAR, PICKER_YEAR_MONTH, PICKER_MONTH_DAY, PICKER_DAY,
    PICKER_TIME, PICKER_HOUR, PICKER_HOUR_MINUTE, PICKER_MINUTE_SECOND, PICKER_SECOND,
    PICKER_ALL, PICKER_DATE_HOUR, PICKER_DATE_HOUR_MINUTE
)
@Retention(AnnotationRetention.SOURCE)
annotation class DateTimeMode
//------------------------> 日期时间选择器 结束 <------------------------

const val PICKER_ADDRESS_ALL = 2000
const val PICKER_ADDRESS_PROVINCE_CITY = 2001
const val PICKER_ADDRESS_CITY_COUNTY = 2002

@IntDef(
    PICKER_ADDRESS_ALL,
    PICKER_ADDRESS_PROVINCE_CITY,
    PICKER_ADDRESS_CITY_COUNTY
)
@Retention(AnnotationRetention.SOURCE)
annotation class AddressMode