package com.night.dialog.tools

import android.app.Activity
import com.night.dialog.R
import com.night.dialog.adapter.ArrayWheelAdapter
import com.night.dialog.base.WheelView
import com.night.dialog.entity.TimeEntity

internal class TimePickerHelp {
    //选中时的位置
    private var selectHourIndex = 0

    //选中分的位置
    private var selectMinuteIndex = 0

    //选中秒的位置
    private var selectSecondIndex = 0

    //最大可选择日期
    private var maxTime = TimeEntity.getDefaultMaxDate()

    //最小可选择日期
    private var minTime = TimeEntity.getDefaultMinDate()

    //默认选中日期
    private var defaultTime = TimeEntity.getToday()

    //时选项数据
    private val hourList = mutableListOf<String>()

    //分选项数据
    private val minuteList = mutableListOf<String>()

    //秒选项数据
    private val secondList = mutableListOf<String>()

    //展示选择项
    private val mLabel = mutableListOf("时", "分", "秒")

    //当前选中的时间
    private var mSelectHour = defaultTime.hour

    //当前选中的分钟
    private var mSelectMinute = defaultTime.minute

    //当前选中的秒
    private var mSelectSecond = defaultTime.second

    fun setMaxTime(entity: TimeEntity) {
        this.maxTime = entity
    }

    fun setMinTime(entity: TimeEntity) {
        this.minTime = entity
    }

    fun setDefaultTime(entity: TimeEntity) {
        this.defaultTime = entity
    }

    /**
     * 设置选中小时的位置
     *
     * @param index 选中位置
     */
    fun setSelectHour(index: Int) {
        selectHourIndex = index
        mSelectHour = hourList[selectHourIndex].replace(mLabel[0], "").toInt()
    }

    /**
     * 设置选中分钟的位置
     *
     * @param index 选中位置
     */
    fun setSelectMinute(index: Int) {
        selectMinuteIndex = index
        mSelectMinute = minuteList[selectMinuteIndex].replace(mLabel[1], "").toInt()
    }

    /**
     * 设置选中秒的位置
     *
     * @param index 选中位置
     */
    fun setSelectSecond(index: Int) {
        selectSecondIndex = index
        mSelectSecond = secondList[selectSecondIndex].replace(mLabel[2], "").toInt()
    }


    /**
     * 获取选中小时
     *
     * @return 选中小时
     */
    fun getSelectHour(): String {
        return initNumber(mSelectHour)
    }

    /**
     * 获取选中分钟
     *
     * @return 选中分钟
     */
    fun getSelectMinute(): String {
        return initNumber(mSelectMinute)
    }

    /**
     * 获取选中秒
     *
     * @return 选中秒
     */
    fun getSelectSecond(): String {
        return initNumber(mSelectSecond)
    }

    /**
     * 是否全部选中第一个
     *
     * @return select index == 0
     */
    fun isSelectFirst(): Boolean {
        return (selectHourIndex == 0 && selectMinuteIndex == 0 && selectSecondIndex == 0)
    }

    /**
     * 初始化小时分组数据
     *
     * @param activity Activity
     * @param dialog BottomDialog
     * @param idHour WheelView
     */
    fun initHourLabel(activity: Activity, idHour: WheelView?) {
        if (idHour == null) {
            return
        }
        hourList.clear()
        hourList.addAll(getHourData(mLabel[0]))
        val hourAdapter = getLabelAdapter(activity, hourList)
        idHour.viewAdapter = hourAdapter
        idHour.currentItem = if (selectHourIndex < hourList.size) selectHourIndex else 0
    }

    /**
     * 初始化分钟分组数据
     *
     * @param activity Activity
     * @param dialog BottomDialog
     * @param idMinute WheelView
     */
    fun initMinuteLabel(activity: Activity, idMinute: WheelView?) {
        if (idMinute == null) {
            return
        }
        minuteList.clear()
        minuteList.addAll(getMinuteData(mLabel[1]))
        val minuteAdapter = getLabelAdapter(activity, minuteList)
        idMinute.viewAdapter = minuteAdapter
        idMinute.currentItem = if (selectMinuteIndex < minuteList.size) selectMinuteIndex else 0
    }

    /**
     * 初始化秒分组数据
     *
     * @param activity Activity
     * @param dialog BottomDialog
     * @param idSecond WheelView
     */
    fun initSecondLabel(activity: Activity, idSecond: WheelView?) {
        if (idSecond == null) {
            return
        }
        secondList.clear()
        secondList.addAll(getSecondData(mLabel[2]))
        val secondAdapter = getLabelAdapter(activity, secondList)
        idSecond.viewAdapter = secondAdapter
        idSecond.currentItem = if (selectSecondIndex < secondList.size) selectSecondIndex else 0
    }

    /**
     * 获取小时数据
     */
    private fun getHourData(title: String = ""): MutableList<String> {
        val minHour = minTime.hour
        val maxHour = maxTime.hour
        return initLabelData(minHour, maxHour, title)
//        return if (selectYearIndex == 0 && selectMonthIndex == 0 && selectDayIndex == 0) {
//            //如果选中了最小年，最小月,最小天
//            initLabelData(minHour, 23, title)
//        } else if (selectYearIndex == yearList.lastIndex && selectMonthIndex == monthList.lastIndex && selectDayIndex == dayList.lastIndex) {
//            initLabelData(0, maxHour, title)
//        } else {
//            initLabelData(0, 23, title)
//        }
    }

    /**
     * 获取分钟数据
     */
    private fun getMinuteData(title: String = ""): MutableList<String> {
        val minMinute = minTime.minute
        val maxMinute = maxTime.minute
        return if (selectHourIndex == 0) {
            initLabelData(minMinute, 59, title)
        } else if (selectHourIndex == hourList.lastIndex) {
            initLabelData(0, maxMinute, title)
        } else {
            initLabelData(0, 59, title)
        }
    }

    /**
     * 获取秒数据
     */
    private fun getSecondData(title: String = ""): MutableList<String> {
        val minSecond = minTime.second
        val maxSecond = maxTime.second
        return if (selectHourIndex == 0 && selectMinuteIndex == 0) {
            //最大
            initLabelData(minSecond, 59, title)
        } else if (selectHourIndex == hourList.lastIndex && selectMinuteIndex == minuteList.lastIndex) {
            initLabelData(0, maxSecond, title)
        } else {
            initLabelData(0, 59, title)
        }
    }

    /**
     * 初始化默认显示数据
     */
    fun initDefaultIndex() {
        val mHourData = getHourData()
        for ((index, hour) in mHourData.withIndex()) {
            if (hour.toInt() == defaultTime.hour) {
                //时默认位置
                selectHourIndex = index
                break
            }
        }
        val mMinute = getMinuteData()
        for ((index, minute) in mMinute.withIndex()) {
            if (minute.toInt() == defaultTime.minute) {
                //分默认位置
                selectMinuteIndex = index
                break
            }
        }
        val mSecond = getSecondData()
        for ((index, second) in mSecond.withIndex()) {
            if (second.toInt() == defaultTime.second) {
                //分默认位置
                selectSecondIndex = index
                break
            }
        }
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
     * 获取Label适配器
     *
     * @param activity Activity
     * @param dialog BottomDialog
     * @param data 适配器数据
     */
    private fun getLabelAdapter(
        activity: Activity,
        data: MutableList<String>
    ): ArrayWheelAdapter<String> {
        val mLabelAdapter = ArrayWheelAdapter(activity, data)
        mLabelAdapter.setItemResource(R.layout.default_item_date)
        mLabelAdapter.setItemTextResource(R.id.default_item_date_name_tv)
        return mLabelAdapter
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
}