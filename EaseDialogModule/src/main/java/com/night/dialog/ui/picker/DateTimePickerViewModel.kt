package com.night.dialog.ui.picker

import androidx.lifecycle.MutableLiveData
import com.night.dialog.base.EaseViewModel
import com.night.dialog.callback.IDateTimeSelectCallback
import com.night.dialog.entity.DateTimeEntity
import com.night.dialog.entity.DateTimePickerEntity

class DateTimePickerViewModel : EaseViewModel() {
    private var minDateTime: DateTimePickerEntity? = null
    private var maxDateTime: DateTimePickerEntity? = null
    private var selDateTime: DateTimePickerEntity? = null

    //年
    val mYearData = MutableLiveData<List<String>>()
    var mSelYear = MutableLiveData<String>()
    private var mYear: Int = 0

    //月
    val mMonthData = MutableLiveData<List<String>>()
    var mSelMonth = MutableLiveData<String>()
    private var mMonth: Int = 0

    //日
    val mDayData = MutableLiveData<List<String>>()
    var mSelDay = MutableLiveData<String>()
    private var mDay: Int = 0

    //时
    val mHourData = MutableLiveData<List<String>>()
    var mSelHour = MutableLiveData<String>()
    private var mHour: Int = 0

    //分
    val mMinuteData = MutableLiveData<List<String>>()
    var mSelMinute = MutableLiveData<String>()
    private var mMinute: Int = 0

    //秒
    val mSecondData = MutableLiveData<List<String>>()
    var mSelSecond = MutableLiveData<String>()
    private var mSecond: Int = 0

    private var mPickerCallback: IDateTimeSelectCallback? = null

     val mLabel = MutableLiveData<List<Int>>()

    /**
     * 设置选择器最小时间
     */
    fun setMinDateTime(entity: DateTimePickerEntity?) {
        if (entity == null) {
            return
        }
        this.minDateTime = entity
    }

    /**
     * 设置选择器最小时间
     */
    fun setMaxDateTime(entity: DateTimePickerEntity?) {
        if (entity == null) {
            return
        }
        this.maxDateTime = entity
    }

    /**
     * 设置选择器最小时间
     */
    fun setSelDateTime(entity: DateTimePickerEntity?) {
        if (entity == null) {
            return
        }
        this.selDateTime = entity
    }

    /**
     * 初始化年数据
     */
    fun initYearData() {
        if (maxDateTime == null || minDateTime == null || selDateTime == null) {
            return
        }
        if (mYearData.value != null) {
            return
        }
        val mList = mutableListOf<String>()
        val max = maxDateTime!!.year
        val min = minDateTime!!.year
        for (year in min..max) {
            mList.add(year.toString() + "年")
        }
        mYearData.value = mList
        mSelYear.value = initNumberDisplay(selDateTime!!.year) + "年"
        mYear = selDateTime!!.year
    }

    fun initMonthData() {
        if (maxDateTime == null || minDateTime == null || selDateTime == null) {
            return
        }
        if (mMonthData.value != null) {
            return
        }
        val mList = mutableListOf<String>()
        val max = maxDateTime!!.month
        val min = minDateTime!!.month
        for (i in min..max) {
            mList.add(initNumberDisplay(i) + "月")
        }
        mMonthData.value = mList
        mSelMonth.value = initNumberDisplay(selDateTime!!.month) + "月"
        mMonth = selDateTime!!.month
    }

    fun initDayData() {
        if (maxDateTime == null || minDateTime == null || selDateTime == null) {
            return
        }
        if (mDayData.value != null) {
            return
        }
        val mList = mutableListOf<String>()
        val max = maxDateTime!!.day
        val min = minDateTime!!.day
        for (i in min..max) {
            mList.add(initNumberDisplay(i) + "日")
        }
        mDayData.value = mList
        mSelDay.value = initNumberDisplay(selDateTime!!.day) + "日"
        mDay = selDateTime!!.day
    }

    fun initHourData() {
        if (maxDateTime == null || minDateTime == null || selDateTime == null) {
            return
        }
        if (mHourData.value != null) {
            return
        }
        val mList = mutableListOf<String>()
        val max = maxDateTime!!.hour
        val min = minDateTime!!.hour
        for (i in min..max) {
            mList.add(initNumberDisplay(i) + "时")
        }
        mHourData.value = mList
        mSelHour.value = initNumberDisplay(selDateTime!!.hour) + "时"
        mHour = selDateTime!!.hour
    }

    fun initMinuteData() {
        if (maxDateTime == null || minDateTime == null || selDateTime == null) {
            return
        }
        if (mMinuteData.value != null) {
            return
        }
        val mList = mutableListOf<String>()
        val max = maxDateTime!!.minute
        val min = minDateTime!!.minute
        for (year in min..max) {
            mList.add(year.toString() + "分")
        }
        mMinuteData.value = mList
        mSelMinute.value = initNumberDisplay(selDateTime!!.minute) + "分"
        mMinute = selDateTime!!.minute
    }

    fun initSecondData() {
        if (maxDateTime == null || minDateTime == null || selDateTime == null) {
            return
        }
        if (mSecondData.value != null) {
            return
        }
        val mList = mutableListOf<String>()
        val max = maxDateTime!!.second
        val min = minDateTime!!.second
        for (i in min..max) {
            mList.add(initNumberDisplay(i) + "秒")
        }
        mSecondData.value = mList
        mSelSecond.value = initNumberDisplay(selDateTime!!.second) + "秒"
        mSecond = selDateTime!!.second
    }

    fun setSelectYear(index: Int) {
        val mSelectYear = mYearData.value!![index]
        val year = mSelectYear.removeRange(mSelectYear.length - 1, mSelectYear.length).toIntOrNull()
        year ?: return
        var min = 0
        var max = 12
        val mMinYear = minDateTime?.year ?: 2013
        val mMaxYear = maxDateTime?.year ?: 2033
        if (mMinYear == mMaxYear) {
            min = mMinYear.coerceAtMost(mMaxYear)
            max = mMinYear.coerceAtLeast(mMaxYear)
        } else if (year == mMinYear) {
            min = minDateTime?.month ?: 1
            max = 12
        } else if (year == mMaxYear) {
            min = 1
            max = maxDateTime?.month ?: 12
        } else {
            min = 1
            max = 12
        }
        val mList = mutableListOf<String>()
        for (i in min..max) {
            mList.add(initNumberDisplay(i) + "月")
        }
        mSelYear.value = mSelectYear
        mYear = year
        mMonthData.value = mList
        var position = mList.indexOf(mSelMonth.value)
        if (position == -1) {
            position = 0
        }
        setSelectMonth(position)
    }

    fun setSelectMonth(index: Int) {
        val mSelectMonth = mMonthData.value!![index]
        val month = mSelectMonth.removeRange(mSelectMonth.length - 1, mSelectMonth.length).toIntOrNull()
        month ?: return
        val mMinYear = minDateTime?.year ?: 2013
        val mMaxYear = maxDateTime?.year ?: 2033
        val mMinMonth = minDateTime?.month ?: 1
        val mMaxMonth = maxDateTime?.month ?: 12
        var min = 0
        var max = 31
        if (mYear == mMinYear && month == mMinMonth && mYear == mMaxYear && month == mMaxMonth) {
            min = minDateTime!!.day
            max = maxDateTime!!.day
        } else if (mYear == mMinYear && month == mMinMonth) {
            min = minDateTime!!.day
            max = initTotalDaysInMonth(month)
        } else if (mYear == mMaxYear && month == mMaxMonth) {
            min = 1
            max = maxDateTime!!.day
        } else {
            min = 1
            max = initTotalDaysInMonth(month)
        }
        val mList = mutableListOf<String>()
        for (i in min..max) {
            mList.add(initNumberDisplay(i) + "日")
        }
        mSelMonth.value = mSelectMonth
        mMonth = month
        mDayData.value = mList
        var position = mList.indexOf(mSelDay.value)
        if (position == -1) {
            position = 0
        }
        setSelectDay(position)
    }

    fun setSelectDay(index: Int) {
        val mSelectDay = mDayData.value!![index]
        val day = mSelectDay.removeRange(mSelectDay.length - 1, mSelectDay.length).toIntOrNull()
        day ?: return
        val mMinYear = minDateTime?.year ?: 2013
        val mMaxYear = maxDateTime?.year ?: 2033
        val mMinMonth = minDateTime?.month ?: 1
        val mMaxMonth = maxDateTime?.month ?: 12
        val mMinDay = minDateTime?.day ?: 1
        val mMaxDay = maxDateTime?.day ?: 31
        var min = 0
        var max = 23
        if (mYear == mMinYear && mMonth == mMinMonth && day == mMinDay && mYear == mMaxYear && mMonth == mMaxMonth && day == mMaxDay) {
            min = minDateTime!!.hour
            max = maxDateTime!!.hour
        } else if (mYear == mMinYear && mMonth == mMinMonth && day == mMinDay) {
            min = minDateTime!!.hour
            max = 23
        } else if (mYear == mMaxYear && mMonth == mMaxMonth && day == mMaxDay) {
            min = 0
            max = maxDateTime!!.hour
        } else {
            min = 0
            max = 23
        }
        val mList = mutableListOf<String>()
        for (i in min..max) {
            mList.add(initNumberDisplay(i) + "时")
        }
        mSelDay.value = mSelectDay
        mDay = day
        mHourData.value = mList
        var position = mList.indexOf(mSelHour.value)
        if (position == -1) {
            position = 0
        }
        setSelectHour(position)
    }

    fun setSelectHour(index: Int) {
        val mSelectHour = mHourData.value!![index]
        val hour = mSelectHour.removeRange(mSelectHour.length - 1, mSelectHour.length).toIntOrNull()
        hour ?: return
        val mMinYear = minDateTime?.year ?: 2013
        val mMaxYear = maxDateTime?.year ?: 2033
        val mMinMonth = minDateTime?.month ?: 1
        val mMaxMonth = maxDateTime?.month ?: 12
        val mMinDay = minDateTime?.day ?: 1
        val mMaxDay = maxDateTime?.day ?: 31
        val mMinHour = minDateTime?.hour ?: 0
        val mMaxHour = maxDateTime?.hour ?: 23
        var min = 0
        var max = 59
        if (mYear == mMinYear && mMonth == mMinMonth && mDay == mMinDay && hour == mMinHour && mYear == mMaxYear && mMonth == mMaxMonth && mDay == mMaxDay && hour == mMaxHour) {
            min = minDateTime!!.minute
            max = maxDateTime!!.minute
        } else if (mYear == mMinYear && mMonth == mMinMonth && mDay == mMinDay && hour == mMinHour) {
            min = minDateTime!!.minute
            max = 59
        } else if (mYear == mMaxYear && mMonth == mMaxMonth && mDay == mMaxDay && hour == mMaxHour) {
            min = 0
            max = maxDateTime!!.hour
        } else {
            min = 0
            max = 59
        }
        val mList = mutableListOf<String>()
        for (i in min..max) {
            mList.add(initNumberDisplay(i) + "分")
        }
        mSelHour.value = mSelectHour
        mHour = hour
        mMinuteData.value = mList
        var position = mList.indexOf(mSelMinute.value)
        if (position == -1) {
            position = 0
        }
        setSelectMinute(position)
    }

    fun setSelectMinute(index: Int) {
        val mSelectMinute = mMinuteData.value!![index]
        val minute = mSelectMinute.removeRange(mSelectMinute.length - 1, mSelectMinute.length).toIntOrNull()
        minute ?: return
        val mMinYear = minDateTime?.year ?: 2013
        val mMaxYear = maxDateTime?.year ?: 2033
        val mMinMonth = minDateTime?.month ?: 1
        val mMaxMonth = maxDateTime?.month ?: 12
        val mMinDay = minDateTime?.day ?: 1
        val mMaxDay = maxDateTime?.day ?: 31
        val mMinHour = minDateTime?.hour ?: 0
        val mMaxHour = maxDateTime?.hour ?: 23
        val mMinMinute = minDateTime?.minute ?: 0
        val mMaxMinute = maxDateTime?.minute ?: 59
        var min = 0
        var max = 59

        if (mYear == mMinYear && mMonth == mMinMonth && mDay == mMinDay && mHour == mMinHour && minute == mMinMinute && mYear == mMaxYear && mMonth == mMaxMonth && mDay == mMaxDay && mHour == mMaxHour && minute == mMaxMinute) {
            min = minDateTime!!.minute
            max = maxDateTime!!.minute
        } else if (mYear == mMinYear && mMonth == mMinMonth && mDay == mMinDay && mHour == mMinHour && minute == mMinMinute) {
            min = minDateTime!!.second
            max = 59
        } else if (mYear == mMaxYear && mMonth == mMaxMonth && mDay == mMaxDay && mHour == mMaxHour && minute == mMaxMinute) {
            min = 0
            max = maxDateTime!!.second
        } else {
            min = 0
            max = 59
        }
        val mList = mutableListOf<String>()
        for (i in min..max) {
            mList.add(initNumberDisplay(i) + "秒")
        }
        mSelMinute.value = mSelectMinute
        mMinute = minute
        mSecondData.value = mList
        var position = mList.indexOf(mSelSecond.value)
        if (position == -1) {
            position = 0
        }
        setSelectSecond(position)
    }

    fun setSelectSecond(index: Int) {
        val mSelectSecond = mSecondData.value!![index]
        val second = mSelectSecond.removeRange(mSelectSecond.length - 1, mSelectSecond.length).toIntOrNull()
        second ?: return
        mSelSecond.value = mSelectSecond
        mSecond = second
    }

    fun setCallback(callback: IDateTimeSelectCallback?) {
        this.mPickerCallback = callback
    }


    fun setLabel(label: List<Int>) {
        if(mLabel.value == null){
            mLabel.value = label
        }
    }


    override fun onCancelEvent() {
        mPickerCallback?.onCancel()
    }

    fun onPositiveEvent() {
        val mDateTimeEntity = DateTimeEntity(
            mYear.toString(),
            mMonth.toString(),
            mDay.toString(),
            mHour.toString(),
            mMinute.toString(),
            mSecond.toString()
        )
        mPickerCallback?.onSelectDate(mDateTimeEntity)
    }

    override fun onCleared() {
        mPickerCallback?.onDismiss()
    }


    private fun initTotalDaysInMonth(month: Int): Int {
        return when (month) {
            1 -> {
                31
            }
            3 -> {
                31
            }
            5 -> {
                31
            }
            7 -> {
                31
            }
            8 -> {
                31
            }
            10 -> {
                31
            }
            12 -> {
                31
            }
            4 -> {
                30
            }
            6 -> {
                30
            }
            9 -> {
                30
            }
            11 -> {
                30
            }
            2 -> {
                val isLeap = (mYear % 4 == 0 && mYear % 100 != 0) || mYear % 400 == 0
                if (isLeap) {
                    return 29
                } else {
                    return 28
                }
            }
            else -> {
                30
            }
        }
    }

}