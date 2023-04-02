package com.night.dialog.tools

import android.app.Activity
import androidx.core.view.isGone
import com.night.dialog.R
import com.night.dialog.adapter.ArrayWheelAdapter
import com.night.dialog.base.WheelView
import com.night.dialog.entity.DateEntity
import java.util.*

internal class DatePickerHelp {
    //最大可选择日期
    private var maxDate = DateEntity.getDefaultMaxDate()

    //最小可选择日期
    private var minDate = DateEntity.getDefaultMinDate()

    //默认选中日期
    private var defaultDate = DateEntity.getToday()

    //选中年的位置
    private var selectYearIndex = 0

    //选中月的位置
    private var selectMonthIndex = 0

    //选中日的位置
    private var selectDayIndex = 0

    //年选项数据
    private val yearList = mutableListOf<String>()

    //月选项数据
    private val monthList = mutableListOf<String>()

    //日选项数据
    private val dayList = mutableListOf<String>()

    //展示选择项
    private val mLabel = mutableListOf("年", "月", "日")

    //当前选中的年份
    private var mSelectYear = defaultDate.year

    //当前选中的月份
    private var mSelectMonth = defaultDate.month

    //当前选中的日期
    private var mSelectDay = defaultDate.day


    fun setMaxDate(entity: DateEntity) {
        this.maxDate = entity
    }

    fun setMinDate(entity: DateEntity) {
        this.minDate = entity
    }

    fun setDefault(entity: DateEntity) {
        this.defaultDate = entity
    }

    /**
     * 设置选中年的位置
     *
     * @param index 选中位置
     */
    fun setSelectYear(index: Int) {
        selectYearIndex = index
        mSelectYear = yearList[selectYearIndex].replace(mLabel[0], "").toInt()
    }

    /**
     * 设置选中月的位置
     *
     * @param index 选中位置
     */
    fun setSelectMonth(index: Int) {
        selectMonthIndex = index
        mSelectMonth = monthList[selectMonthIndex].replace(mLabel[1], "").toInt()
    }

    /**
     * 设置选中日的位置
     *
     * @param index 选中位置
     */
    fun setSelectDay(index: Int) {
        selectDayIndex = index
        mSelectDay = dayList[selectDayIndex].replace(mLabel[2], "").toInt()
    }

    /**
     * 获取选中年份
     *
     * @return 选中年份
     */
    fun getSelectYear(): String {
        return initNumber(mSelectYear)
    }

    /**
     * 获取选中月份
     *
     * @return 选中月份
     */
    fun getSelectMonth(): String {
        return initNumber(mSelectMonth)
    }

    /**
     * 获取选中日期
     *
     * @return 选中日期
     */
    fun getSelectDay(): String {
        return initNumber(mSelectDay)
    }

    /**
     * 初始化数据展示方式
     */
    private fun initNumber(num: Int): String {
        return if (num < 10) {
            "0".plus(num)
        } else {
            num.toString()
        }
    }

    /**
     * 获取年份数据
     *
     * @param title 单位名称
     */
    private fun getYearLabelData(title: String = ""): MutableList<String> {
        val minYear = minDate.year
        val maxYear = maxDate.year
        return initLabelData(minYear, maxYear, title)
    }

    /**
     * 获取月份数据
     */
    private fun getMonthLabelData(title: String = ""): MutableList<String> {
        val minMonth = minDate.month
        val maxMonth = maxDate.month
        return when (selectYearIndex) {
            0 -> {
                //选中最小年
                initLabelData(minMonth, 12, title)
            }
            yearList.lastIndex -> {
                //选中最大年
                initLabelData(1, maxMonth, title)
            }
            else -> {
                initLabelData(1, 12, title)
            }
        }
    }

    /**
     * 获取天数据
     */
    private fun getDayData(title: String = ""): MutableList<String> {
        val minDay: Int
        val maxDay: Int
        val mNowYear = minDate.year + selectYearIndex
        if (selectYearIndex == 0 && selectMonthIndex == 0) {
            //如果选中了最小年，最小月
            val mNowMonth = minDate.month
            val mLastDay = getLastDayOfMonth(mNowYear, mNowMonth)
            minDay = if (minDate.day > mLastDay) {
                mLastDay
            } else {
                minDate.day
            }
            maxDay = mLastDay
        } else if (selectYearIndex == yearList.lastIndex && selectMonthIndex == monthList.lastIndex) {
            //选中了最大年和最大月
            val mNowMonth = maxDate.month
            val mLastDay = getLastDayOfMonth(mNowYear, mNowMonth)
            minDay = 1
            maxDay = if (maxDate.day > mLastDay) {
                mLastDay
            } else {
                maxDate.day
            }
        } else {
            val mNowMonth = selectMonthIndex + 1
            val mLastDay = getLastDayOfMonth(mNowYear, mNowMonth)
            minDay = 1
            maxDay = mLastDay
        }
        return initLabelData(minDay, maxDay, title)
    }

    /**
     * 初始化年分组数据
     *
     * @param activity Activity
     * @param idYear WheelView
     */
    fun initYearLabel(activity: Activity, idYear: WheelView?) {
        if (idYear == null) {
            return
        }
        if (mLabel.size <= 0) {
            idYear.isGone = true
            return
        }
        yearList.clear()
        yearList.addAll(getYearLabelData(mLabel[0]))
        val yearAdapter = getLabelAdapter(activity, yearList)
        idYear.viewAdapter = yearAdapter
        idYear.currentItem = if (selectYearIndex < yearList.size) selectYearIndex else 0
    }

    /**
     * 初始化月分组数据
     *
     * @param activity Activity
     * @param idMonth WheelView
     */
    fun initMonthLabel(activity: Activity, idMonth: WheelView?) {
        if (idMonth == null) {
            return
        }
        if (mLabel.size <= 1) {
            idMonth.isGone = true
            return
        }
        monthList.clear()
        monthList.addAll(getMonthLabelData(mLabel[1]))
        val monthAdapter = getLabelAdapter(activity, monthList)
        idMonth.viewAdapter = monthAdapter
        idMonth.currentItem = if (selectMonthIndex < monthList.size) selectMonthIndex else 0
    }

    /**
     * 初始化日分组数据
     *
     * @param activity Activity
     * @param idDay WheelView
     */
    fun initDayLabel(activity: Activity, idDay: WheelView?) {
        if (idDay == null) {
            return
        }
        if (mLabel.size <= 2) {
            idDay.isGone = true
            return
        }
        dayList.clear()
        dayList.addAll(getDayData(mLabel[2]))
        val dayAdapter = getLabelAdapter(activity, dayList)
        idDay.viewAdapter = dayAdapter
        idDay.currentItem = if (selectDayIndex < dayList.size) selectDayIndex else 0
    }

    /**
     * 初始化分组数据
     *
     * @param min 最大值
     * @param max 最小值
     * @param title 后缀
     */
    private fun initLabelData(min: Int, max: Int, title: String): MutableList<String> {
        val mData = mutableListOf<String>()
        for (i in min..max) {
            mData.add(initNumber(i).plus(title))
        }
        return mData
    }

    /**
     * 获取本年本月最后一天的日期
     *
     * @param year 当前年
     * @param month 当前月
     */
    private fun getLastDayOfMonth(year: Int, month: Int): Int {
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.MONTH, month - 1)
        cal.set(Calendar.DAY_OF_MONTH, 1)
        cal.add(Calendar.MONTH, 1)
        cal.set(Calendar.DAY_OF_MONTH, 0)
        return cal.get(Calendar.DAY_OF_MONTH)
    }

    /**
     * 获取Label适配器
     *
     * @param activity Activity
     * @param data 适配器数据
     */
    private fun getLabelAdapter(
        activity: Activity,
        data: MutableList<String>
    ): ArrayWheelAdapter<String> {
        val mLabelAdapter = ArrayWheelAdapter(activity, data)
        mLabelAdapter.setItemResource(R.layout.ease_layout_default_item_date)
        mLabelAdapter.setItemTextResource(R.id.default_item_date_name_tv)
        return mLabelAdapter
    }

    /**
     * 是否全部选中第一个
     *
     * @return select index == 0
     */
    fun isSelectFirst(): Boolean {
        return (selectYearIndex == 0 && selectMonthIndex == 0 && selectDayIndex == 0)
    }

    /**
     * 初始化默认显示数据
     */
    fun initDefaultIndex() {
        val mYearData = getYearLabelData()
        for ((index, year) in mYearData.withIndex()) {
            if (year.toInt() == defaultDate.year) {
                //年位置
                selectYearIndex = index
                break
            }
        }
        val mMonthData = getMonthLabelData()
        for ((index, month) in mMonthData.withIndex()) {
            if (month.toInt() == defaultDate.month) {
                //月默认位置
                selectMonthIndex = index
                break
            }
        }
        val mDayData = getDayData()
        for ((index, day) in mDayData.withIndex()) {
            if (day.toInt() == defaultDate.day) {
                //日默认位置
                selectDayIndex = index
                break
            }
        }
    }
}