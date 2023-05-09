package com.night.dialog.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.Px
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.github.gzuliyujiang.wheelview.contract.OnWheelChangedListener
import com.github.gzuliyujiang.wheelview.widget.WheelView
import com.night.dialog.R
import com.night.dialog.callback.IDateTimePickerListener
import com.night.dialog.entity.DateTimePickerEntity
import com.night.dialog.tools.*

class EaseDateTimePickerView(context: Context, attrs: AttributeSet?) :
    LinearLayoutCompat(context, attrs) {
    private var mYearView: WheelView
    private var mMonthView: WheelView
    private var mDayView: WheelView
    private var mHourView: WheelView
    private var mMinuteView: WheelView
    private var mSecondView: WheelView
    private var mLabel = PICKER_DATE_HOUR_MINUTE
    private var mDefTextColor = Color.GRAY
    private var mSelTextColor = Color.BLACK
    private var mDefTextSize = 14F
    private var mSelTextSize = 15F
    private var mIndicatorColor = Color.GRAY

    private var mStartDateTime: DateTimePickerEntity? = null
    private var mEndDateTime: DateTimePickerEntity? = null
    private var mDefDateTime: DateTimePickerEntity? = null
    private var mCallback: IDateTimePickerListener? = null

    //年
    private val mYearData = mutableListOf<String>()
    private var mSelectYear = ""

    //月
    private val mMonthData = mutableListOf<String>()
    private var mSelectMonth = ""

    //日
    private val mDayData = mutableListOf<String>()
    private var mSelectDay = ""

    //时
    private val mHourData = mutableListOf<String>()
    private var mSelectHour = ""

    //分
    private val mMinuteData = mutableListOf<String>()
    private var mSelectMinute = ""

    //秒
    private val mSecondData = mutableListOf<String>()
    private var mSelectSecond = ""

    init {
        //设置方向
        orientation = HORIZONTAL
        LayoutInflater.from(context).inflate(R.layout.ease_widget_date_time, this, true)
        mYearView = this.findViewById(R.id.id_year)
        mMonthView = this.findViewById(R.id.id_month)
        mDayView = this.findViewById(R.id.id_day)
        mHourView = this.findViewById(R.id.id_hour)
        mMinuteView = this.findViewById(R.id.id_minute)
        mSecondView = this.findViewById(R.id.id_second)
        //获取自定义属性
        val arrayType = context.obtainStyledAttributes(attrs, R.styleable.EaseDateTimePickerView)
        mDefTextColor = arrayType.getColor(
            R.styleable.EaseDateTimePickerView_customDefTextColor,
            getColor(context, R.color.EaseColorMainText)
        )
        mSelTextColor = arrayType.getColor(
            R.styleable.EaseDateTimePickerView_customSelTextColor,
            getColor(context, R.color.EaseColorButtonTextColor)
        )
        mIndicatorColor = arrayType.getColor(
            R.styleable.EaseDateTimePickerView_customIndicatorColor,
            getColor(context, R.color.EaseColorMainText)
        )
        mDefTextSize =
            arrayType.getDimension(R.styleable.EaseDateTimePickerView_customDefTextSize, dpToPx(context, 14F))
        mSelTextSize =
            arrayType.getDimension(R.styleable.EaseDateTimePickerView_customSelTextSize, dpToPx(context, 15F))
        arrayType.recycle()
        //初始化自定义属性
        mYearView.textColor = mDefTextColor
        mYearView.selectedTextColor = mSelTextColor
        mYearView.textSize = mDefTextSize
        mYearView.selectedTextSize = mSelTextSize
        mYearView.indicatorColor = mIndicatorColor

        mMonthView.textColor = mDefTextColor
        mMonthView.selectedTextColor = mSelTextColor
        mMonthView.textSize = mDefTextSize
        mMonthView.selectedTextSize = mSelTextSize
        mMonthView.indicatorColor = mIndicatorColor

        mDayView.textColor = mDefTextColor
        mDayView.selectedTextColor = mSelTextColor
        mDayView.textSize = mDefTextSize
        mDayView.selectedTextSize = mSelTextSize
        mDayView.indicatorColor = mIndicatorColor

        mHourView.textColor = mDefTextColor
        mHourView.selectedTextColor = mSelTextColor
        mHourView.textSize = mDefTextSize
        mHourView.selectedTextSize = mSelTextSize
        mHourView.indicatorColor = mIndicatorColor

        mMinuteView.textColor = mDefTextColor
        mMinuteView.selectedTextColor = mSelTextColor
        mMinuteView.textSize = mDefTextSize
        mMinuteView.selectedTextSize = mSelTextSize
        mMinuteView.indicatorColor = mIndicatorColor

        mSecondView.textColor = mDefTextColor
        mSecondView.selectedTextColor = mSelTextColor
        mSecondView.textSize = mDefTextSize
        mSecondView.selectedTextSize = mSelTextSize
        mSecondView.indicatorColor = mIndicatorColor
        //初始化监听
        initListener()
    }

    /**
     * 设置组件数据
     *
     * @param start 开始日期时间数据
     * @param end 结束日期时间数据
     * @param def 默认选中时间数据
     */
    fun setDateTime(start: DateTimePickerEntity?, end: DateTimePickerEntity?, def: DateTimePickerEntity?) {
        if (start == null || end == null || def == null) {
            return
        }
        this.mStartDateTime = start
        this.mEndDateTime = end
        this.mDefDateTime = def
        initData()
    }

    /**
     * 设置选中Label
     *
     * @param mode
     */
    fun setLabel(@DateTimeMode mode: Int) {
        this.mLabel = mode
        initLabel()
    }

    /**
     * 设置监听
     */
    fun setListener(callback: IDateTimePickerListener) {
        this.mCallback = callback
    }

    /**
     * 初始化显示项目
     */
    private fun initLabel() {
        when (mLabel) {
            PICKER_ALL -> {
                //全部
                mYearView.isVisible = true
                mMonthView.isVisible = true
                mDayView.isVisible = true
                mHourView.isVisible = true
                mMinuteView.isVisible = true
                mSecondView.isVisible = true
            }
            PICKER_DATE_HOUR -> {
                //年月日时
                mYearView.isVisible = true
                mMonthView.isVisible = true
                mDayView.isVisible = true
                mHourView.isVisible = true
            }
            PICKER_DATE_HOUR_MINUTE -> {
                //年月日时分
                mYearView.isVisible = true
                mMonthView.isVisible = true
                mDayView.isVisible = true
                mHourView.isVisible = true
                mMinuteView.isVisible = true
            }
            PICKER_DATE -> {
                //年月日
                mYearView.isVisible = true
                mMonthView.isVisible = true
                mDayView.isVisible = true
            }
            PICKER_YEAR -> {
                //年
                mYearView.isVisible = true
            }
            PICKER_YEAR_MONTH -> {
                //年月
                mYearView.isVisible = true
                mMonthView.isVisible = true
            }
            PICKER_MONTH_DAY -> {
                //年日
                mYearView.isVisible = true
                mDayView.isVisible = true
            }
            PICKER_DAY -> {
                //日
                mDayView.isVisible = true
            }
            PICKER_TIME -> {
                //时分秒
                mHourView.isVisible = true
                mMinuteView.isVisible = true
                mSecondView.isVisible = true
            }
            PICKER_HOUR -> {
                //时
                mHourView.isVisible = true
            }
            PICKER_HOUR_MINUTE -> {
                //时分
                mHourView.isVisible = true
                mMinuteView.isVisible = true
            }
            PICKER_MINUTE_SECOND -> {
                //分秒
                mMinuteView.isVisible = true
                mSecondView.isVisible = true
            }
            PICKER_SECOND -> {
                //秒
                mSecondView.isVisible = true
            }
        }
    }

    /**
     * 初始化数据
     */
    private fun initData() {
        if (mStartDateTime == null || mEndDateTime == null || mDefDateTime == null) {
            return
        }
        this.mSelectYear = initNumberDisplay(mDefDateTime!!.year).plus("年")
        this.mSelectMonth = initNumberDisplay(mDefDateTime!!.month).plus("月")
        this.mSelectDay = initNumberDisplay(mDefDateTime!!.day).plus("日")
        this.mSelectHour = initNumberDisplay(mDefDateTime!!.hour).plus("时")
        this.mSelectMinute = initNumberDisplay(mDefDateTime!!.minute).plus("分")
        this.mSelectSecond = initNumberDisplay(mDefDateTime!!.second).plus("秒")
        setYearData(mSelectYear)
        setMonthData(mSelectYear, mSelectMonth)
        setDayDate(mSelectYear, mSelectMonth, mSelectDay)
        setHourDate(mSelectYear, mSelectMonth, mSelectDay, mSelectHour)
        setMinuteDate(mSelectYear, mSelectMonth, mSelectDay, mSelectHour, mSelectMinute)
        setSecondDate(mSelectYear, mSelectMonth, mSelectDay, mSelectHour, mSelectMinute, mSelectSecond)
    }

    /**
     * 初始化监听
     */
    private fun initListener() {
        mYearView.setOnWheelChangedListener(object : OnWheelChangedListener {
            override fun onWheelScrolled(view: WheelView?, offset: Int) {

            }

            override fun onWheelSelected(view: WheelView?, position: Int) {
                mSelectYear = mYearData[position]
                mCallback?.onSelected(
                    initStrInt(mSelectYear),
                    initStrInt(mSelectMonth),
                    initStrInt(mSelectDay),
                    initStrInt(mSelectHour),
                    initStrInt(mSelectMinute),
                    initStrInt(mSelectSecond)
                )
                setMonthData(mSelectYear, mSelectMonth)
                setDayDate(mSelectYear, mSelectMonth, mSelectDay)
                setHourDate(mSelectYear, mSelectMonth, mSelectDay, mSelectHour)
                setMinuteDate(mSelectYear, mSelectMonth, mSelectDay, mSelectHour, mSelectMinute)
                setSecondDate(mSelectYear, mSelectMonth, mSelectDay, mSelectHour, mSelectMinute, mSelectSecond)
            }

            override fun onWheelScrollStateChanged(view: WheelView?, state: Int) {

            }

            override fun onWheelLoopFinished(view: WheelView?) {

            }
        })

        mMonthView.setOnWheelChangedListener(object : OnWheelChangedListener {
            override fun onWheelScrolled(view: WheelView?, offset: Int) {

            }

            override fun onWheelSelected(view: WheelView?, position: Int) {
                mSelectMonth = mMonthData[position]
                mCallback?.onSelected(
                    initStrInt(mSelectYear),
                    initStrInt(mSelectMonth),
                    initStrInt(mSelectDay),
                    initStrInt(mSelectHour),
                    initStrInt(mSelectMinute),
                    initStrInt(mSelectSecond)
                )
                setDayDate(mSelectYear, mSelectMonth, mSelectDay)
                setHourDate(mSelectYear, mSelectMonth, mSelectDay, mSelectHour)
                setMinuteDate(mSelectYear, mSelectMonth, mSelectDay, mSelectHour, mSelectMinute)
                setSecondDate(mSelectYear, mSelectMonth, mSelectDay, mSelectHour, mSelectMinute, mSelectSecond)
            }

            override fun onWheelScrollStateChanged(view: WheelView?, state: Int) {

            }

            override fun onWheelLoopFinished(view: WheelView?) {

            }
        })

        mDayView.setOnWheelChangedListener(object : OnWheelChangedListener {
            override fun onWheelScrolled(view: WheelView?, offset: Int) {

            }

            override fun onWheelSelected(view: WheelView?, position: Int) {
                mSelectDay = mDayData[position]
                mCallback?.onSelected(
                    initStrInt(mSelectYear),
                    initStrInt(mSelectMonth),
                    initStrInt(mSelectDay),
                    initStrInt(mSelectHour),
                    initStrInt(mSelectMinute),
                    initStrInt(mSelectSecond)
                )
                setHourDate(mSelectYear, mSelectMonth, mSelectDay, mSelectHour)
                setMinuteDate(mSelectYear, mSelectMonth, mSelectDay, mSelectHour, mSelectMinute)
                setSecondDate(mSelectYear, mSelectMonth, mSelectDay, mSelectHour, mSelectMinute, mSelectSecond)
            }

            override fun onWheelScrollStateChanged(view: WheelView?, state: Int) {

            }

            override fun onWheelLoopFinished(view: WheelView?) {

            }
        })

        mHourView.setOnWheelChangedListener(object : OnWheelChangedListener {
            override fun onWheelScrolled(view: WheelView?, offset: Int) {

            }

            override fun onWheelSelected(view: WheelView?, position: Int) {
                mSelectHour = mHourData[position]
                mCallback?.onSelected(
                    initStrInt(mSelectYear),
                    initStrInt(mSelectMonth),
                    initStrInt(mSelectDay),
                    initStrInt(mSelectHour),
                    initStrInt(mSelectMinute),
                    initStrInt(mSelectSecond)
                )
                setMinuteDate(mSelectYear, mSelectMonth, mSelectDay, mSelectHour, mSelectMinute)
                setSecondDate(mSelectYear, mSelectMonth, mSelectDay, mSelectHour, mSelectMinute, mSelectSecond)
            }

            override fun onWheelScrollStateChanged(view: WheelView?, state: Int) {

            }

            override fun onWheelLoopFinished(view: WheelView?) {

            }
        })

        mMinuteView.setOnWheelChangedListener(object : OnWheelChangedListener {
            override fun onWheelScrolled(view: WheelView?, offset: Int) {

            }

            override fun onWheelSelected(view: WheelView?, position: Int) {
                mSelectMinute = mMinuteData[position]
                mCallback?.onSelected(
                    initStrInt(mSelectYear),
                    initStrInt(mSelectMonth),
                    initStrInt(mSelectDay),
                    initStrInt(mSelectHour),
                    initStrInt(mSelectMinute),
                    initStrInt(mSelectSecond)
                )
                setSecondDate(mSelectYear, mSelectMonth, mSelectDay, mSelectHour, mSelectMinute, mSelectSecond)
            }

            override fun onWheelScrollStateChanged(view: WheelView?, state: Int) {

            }

            override fun onWheelLoopFinished(view: WheelView?) {

            }
        })

        mSecondView.setOnWheelChangedListener(object : OnWheelChangedListener {
            override fun onWheelScrolled(view: WheelView?, offset: Int) {

            }

            override fun onWheelSelected(view: WheelView?, position: Int) {
                mSelectSecond = mSecondData[position]
                mCallback?.onSelected(
                    initStrInt(mSelectYear),
                    initStrInt(mSelectMonth),
                    initStrInt(mSelectDay),
                    initStrInt(mSelectHour),
                    initStrInt(mSelectMinute),
                    initStrInt(mSelectSecond)
                )
            }

            override fun onWheelScrollStateChanged(view: WheelView?, state: Int) {

            }

            override fun onWheelLoopFinished(view: WheelView?) {

            }
        })
    }

    /**
     * 设置年数据
     *
     * @param defaultYear 默认选中数据
     */
    private fun setYearData(defaultYear: String) {
        if (mStartDateTime == null || mEndDateTime == null) {
            return
        }
        if (mYearData.isNotEmpty()) {
            mYearData.clear()
        }
        for (i in mStartDateTime!!.year..mEndDateTime!!.year) {
            mYearData.add(i.toString().plus("年"))
        }
        var mPosition = mYearData.indexOf(defaultYear)
        if (mPosition == -1) {
            mPosition = 0
        }
        mSelectYear = mYearData[mPosition]
        mYearView.setData(mYearData, mPosition)
    }

    /**
     * 设置月数据
     * @param defaultYear 当前年
     * @param defaultMonth 默认选中月
     */
    private fun setMonthData(defaultYear: String, defaultMonth: String) {
        if (mStartDateTime == null || mEndDateTime == null) {
            return
        }
        if (mMonthData.isNotEmpty()) {
            mMonthData.clear()
        }
        val intYear = initStrInt(defaultYear)
        val min: Int
        val max: Int
        if (mStartDateTime!!.year == intYear) {
            min = mStartDateTime!!.month
            max = 12
        } else if (mEndDateTime!!.year == intYear) {
            min = 1
            max = mEndDateTime!!.month
        } else {
            min = 1
            max = 12
        }
        for (i in min..max) {
            mMonthData.add(initNumberDisplay(i).plus("月"))
        }
        var mPosition = mMonthData.indexOf(defaultMonth)
        if (mPosition == -1) {
            mPosition = 0
        }
        mSelectMonth = mMonthData[mPosition]
        mMonthView.setData(mMonthData, mPosition)
    }

    /**
     * 设置日数据
     * @param defaultYear 当前年
     * @param defaultMonth 当前月
     * @param defaultDay 默认选中日
     */
    private fun setDayDate(defaultYear: String, defaultMonth: String, defaultDay: String) {
        if (mStartDateTime == null || mEndDateTime == null) {
            return
        }
        if (mDayData.isNotEmpty()) {
            mDayData.clear()
        }
        val intYear = initStrInt(defaultYear)
        val intMonth = initStrInt(defaultMonth)
        val min: Int
        val max: Int
        if (mStartDateTime!!.year == intYear && mStartDateTime!!.month == intMonth) {
            min = mStartDateTime!!.day
            max = initTotalDaysInMonth(intYear, intMonth)
        } else if (mEndDateTime!!.year == intYear && mEndDateTime!!.month == intMonth) {
            min = 1
            max = mEndDateTime!!.day
        } else {
            min = 1
            max = initTotalDaysInMonth(intYear, intMonth)
        }
        for (i in min..max) {
            mDayData.add(initNumberDisplay(i).plus("日"))
        }
        var mPosition = mDayData.indexOf(defaultDay)
        if (mPosition == -1) {
            mPosition = 0
        }
        mSelectDay = mDayData[mPosition]
        mDayView.setData(mDayData, mPosition)
    }

    /**
     * 设置小时数据
     * @param defaultYear 当前年
     * @param defaultMonth 当前月
     * @param defaultDay 当前日
     * @param defaultHour 默认选中小时
     */
    private fun setHourDate(defaultYear: String, defaultMonth: String, defaultDay: String, defaultHour: String) {
        if (mStartDateTime == null || mEndDateTime == null) {
            return
        }
        if (mHourData.isNotEmpty()) {
            mHourData.clear()
        }
        val intYear = initStrInt(defaultYear)
        val intMonth = initStrInt(defaultMonth)
        val intDay = initStrInt(defaultDay)
        val min: Int
        val max: Int
        if (mStartDateTime!!.year == intYear && mStartDateTime!!.month == intMonth && mStartDateTime!!.day == intDay) {
            min = mStartDateTime!!.hour
            max = 23
        } else if (mEndDateTime!!.year == intYear && mEndDateTime!!.month == intMonth && mEndDateTime!!.day == intDay) {
            min = 0
            max = mEndDateTime!!.hour
        } else {
            min = 0
            max = 23
        }
        for (i in min..max) {
            mHourData.add(initNumberDisplay(i).plus("时"))
        }
        var mPosition = mHourData.indexOf(defaultHour)
        if (mPosition == -1) {
            mPosition = 0
        }
        mSelectHour = mHourData[mPosition]
        mHourView.setData(mHourData, mPosition)
    }

    /**
     * 设置分钟数据
     * @param defaultYear 当前年
     * @param defaultMonth 当前月
     * @param defaultDay 当前日
     * @param defaultHour 当前小时
     * @param defaultMinute 默认选中分钟
     */
    private fun setMinuteDate(
        defaultYear: String,
        defaultMonth: String,
        defaultDay: String,
        defaultHour: String,
        defaultMinute: String
    ) {
        if (mStartDateTime == null || mEndDateTime == null) {
            return
        }
        if (mMinuteData.isNotEmpty()) {
            mMinuteData.clear()
        }
        val intYear = initStrInt(defaultYear)
        val intMonth = initStrInt(defaultMonth)
        val intDay = initStrInt(defaultDay)
        val intHour = initStrInt(defaultHour)
        val min: Int
        val max: Int
        if (mStartDateTime!!.year == intYear && mStartDateTime!!.month == intMonth && mStartDateTime!!.day == intDay && mStartDateTime!!.hour == intHour) {
            min = mStartDateTime!!.minute
            max = 59
        } else if (mEndDateTime!!.year == intYear && mEndDateTime!!.month == intMonth && mEndDateTime!!.day == intDay && mEndDateTime!!.hour == intHour) {
            min = 0
            max = mEndDateTime!!.minute
        } else {
            min = 0
            max = 59
        }
        for (i in min..max) {
            mMinuteData.add(initNumberDisplay(i).plus("分"))
        }
        var mPosition = mMinuteData.indexOf(defaultMinute)
        if (mPosition == -1) {
            mPosition = 0
        }
        mSelectMinute = mMinuteData[mPosition]
        mMinuteView.setData(mMinuteData, mPosition)
    }

    /**
     * 设置秒数据
     * @param defaultYear 当前年
     * @param defaultMonth 当前月
     * @param defaultDay 当前日
     * @param defaultHour 当前小时
     * @param defaultMinute 当前分钟
     * @param defaultSecond 默认选中秒
     */
    private fun setSecondDate(
        defaultYear: String,
        defaultMonth: String,
        defaultDay: String,
        defaultHour: String,
        defaultMinute: String,
        defaultSecond: String
    ) {
        if (mStartDateTime == null || mEndDateTime == null) {
            return
        }
        if (mSecondData.isNotEmpty()) {
            mSecondData.clear()
        }
        val intYear = initStrInt(defaultYear)
        val intMonth = initStrInt(defaultMonth)
        val intDay = initStrInt(defaultDay)
        val intHour = initStrInt(defaultHour)
        val intMinute = initStrInt(defaultMinute)
        val min: Int
        val max: Int
        if (mStartDateTime!!.year == intYear && mStartDateTime!!.month == intMonth && mStartDateTime!!.day == intDay && mStartDateTime!!.hour == intHour && mStartDateTime!!.minute == intMinute) {
            min = mStartDateTime!!.minute
            max = 59
        } else if (mEndDateTime!!.year == intYear && mEndDateTime!!.month == intMonth && mEndDateTime!!.day == intDay && mEndDateTime!!.hour == intHour && mEndDateTime!!.minute == intMinute) {
            min = 0
            max = mEndDateTime!!.minute
        } else {
            min = 0
            max = 59
        }
        for (i in min..max) {
            mSecondData.add(initNumberDisplay(i).plus("秒"))
        }
        var mPosition = mSecondData.indexOf(defaultSecond)
        if (mPosition == -1) {
            mPosition = 0
        }
        mSelectSecond = mSecondData[mPosition]
        mSecondView.setData(mSecondData, mPosition)
    }


    private fun initStrInt(data: String): Int {
        return data.removeRange(data.length - 1, data.length).toInt()
    }


    private fun initNumberDisplay(num: Int): String {
        if (num <= 9) {
            return "0".plus(num)
        }
        return num.toString()
    }

    private fun initTotalDaysInMonth(year: Int, month: Int): Int {
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
                val isLeap = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0
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

    @ColorInt
    private fun getColor(context: Context, @ColorRes id: Int): Int {
        return ContextCompat.getColor(context, id)
    }

    @Px
    private fun dpToPx(context: Context, dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)
    }
}