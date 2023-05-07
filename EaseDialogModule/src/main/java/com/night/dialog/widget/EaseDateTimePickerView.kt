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
import com.night.dialog.entity.DateTimePickerEntity
import com.night.dialog.tools.*

class EaseDateTimePickerView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayoutCompat(context, attrs, defStyleAttr) {
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

    //年
    private val mYearData = mutableListOf<String>()
    private var mSelYear = ""
    private var mYear: Int = 0

    //月
    private val mMonthData = mutableListOf<String>()
    private var mSelMonth = ""
    private var mMonth: Int = 0

    //日
    private val mDayData = mutableListOf<String>()
    private var mSelDay = ""
    private var mDay: Int = 0

    //时
    private val mHourData = mutableListOf<String>()
    private var mSelHour = ""
    private var mHour: Int = 0

    //分
    private val mMinuteData = mutableListOf<String>()
    private var mSelMinute = ""
    private var mMinute: Int = 0

    //秒
    private val mSecondData = mutableListOf<String>()
    private var mSelSecond = ""
    private var mSecond: Int = 0

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
        //初始化基础设置
        initLabel()
        //初始化监听
        initListener()
    }


    @ColorInt
    private fun getColor(context: Context, @ColorRes id: Int): Int {
        return ContextCompat.getColor(context, id)
    }

    @Px
    private fun dpToPx(context: Context, dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)
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
        //年
        if (mYearData.isNotEmpty()) {
            mYearData.clear()
        }
        val maxYear = mEndDateTime!!.year
        val minYear = mStartDateTime!!.year
        for (year in minYear..maxYear) {
            mYearData.add(year.toString() + "年")
        }
        mSelYear = initNumberDisplay(mDefDateTime!!.year) + "年"
        mYear = mDefDateTime!!.year
        //月
        if (mMonthData.isNotEmpty()) {
            mMonthData.clear()
        }
        val maxMonth = mEndDateTime!!.month
        val minMonth = mStartDateTime!!.month
        for (i in minMonth..maxMonth) {
            mMonthData.add(initNumberDisplay(i) + "月")
        }
        mSelMonth = initNumberDisplay(mDefDateTime!!.month) + "月"
        mMonth = mDefDateTime!!.month
        //日
        if (mDayData.isNotEmpty()) {
            mDayData.clear()
        }
        val maxDay = mEndDateTime!!.day
        val minDay = mStartDateTime!!.day
        for (i in minDay..maxDay) {
            mDayData.add(initNumberDisplay(i) + "日")
        }
        mSelDay = initNumberDisplay(mDefDateTime!!.day) + "日"
        mDay = mDefDateTime!!.day
        //时
        if (mHourData.isNotEmpty()) {
            mHourData.clear()
        }
        val maxHour = mEndDateTime!!.hour
        val minHour = mStartDateTime!!.hour
        for (i in minHour..maxHour) {
            mHourData.add(initNumberDisplay(i) + "时")
        }
        mSelHour = initNumberDisplay(mDefDateTime!!.hour) + "时"
        mHour = mDefDateTime!!.hour
        //分
        if (mMinuteData.isNotEmpty()) {
            mMinuteData.clear()
        }
        val maxMinute = mEndDateTime!!.minute
        val minMinute = mStartDateTime!!.minute
        for (i in minMinute..maxMinute) {
            mMinuteData.add(initNumberDisplay(i) + "分")
        }
        mSelMinute = initNumberDisplay(mDefDateTime!!.minute) + "分"
        mMinute = mDefDateTime!!.minute
        //秒
        if (mSecondData.isNotEmpty()) {
            mSecondData.clear()
        }
        val maxSecond = mEndDateTime!!.second
        val minSecond = mStartDateTime!!.second
        for (i in minSecond..maxSecond) {
            mSecondData.add(initNumberDisplay(i) + "秒")
        }
        mSelSecond = initNumberDisplay(mDefDateTime!!.second) + "秒"
        mSecond = mDefDateTime!!.second
    }

    /**
     * 初始化监听
     */
    private fun initListener(){
        mYearView.setOnWheelChangedListener(object : OnWheelChangedListener {
            override fun onWheelScrolled(view: WheelView?, offset: Int) {

            }

            override fun onWheelSelected(view: WheelView?, position: Int) {

            }

            override fun onWheelScrollStateChanged(view: WheelView?, state: Int) {

            }

            override fun onWheelLoopFinished(view: WheelView?) {

            }
        })
    }
    private fun initNumberDisplay(num: Int): String {
        if (num <= 9) {
            return "0".plus(num)
        }
        return num.toString()
    }
}