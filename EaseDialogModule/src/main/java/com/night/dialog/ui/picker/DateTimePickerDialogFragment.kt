package com.night.dialog.ui.picker

import android.content.res.Resources
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import com.github.gzuliyujiang.wheelview.contract.OnWheelChangedListener
import com.github.gzuliyujiang.wheelview.widget.WheelView
import com.night.dialog.R
import com.night.dialog.base.EaseSafeDialogFragment
import com.night.dialog.callback.IDateTimeSelectCallback
import com.night.dialog.entity.DateTimePickerEntity
import com.night.dialog.tools.*
import com.night.dialog.tools.DialogHelp
import com.night.dialog.tools.LogcatToos

class DateTimePickerDialogFragment : EaseSafeDialogFragment<DateTimePickerBaseViewModel>() {
    private lateinit var mYearView: WheelView
    private lateinit var mMonthView: WheelView
    private lateinit var mDayView: WheelView
    private lateinit var mHourView: WheelView
    private lateinit var mMinuteView: WheelView
    private lateinit var mSecondView: WheelView
    private lateinit var mTitleView: AppCompatTextView
    private lateinit var mCancelView: AppCompatTextView
    private lateinit var mPositiveView: AppCompatTextView
    private var mMinData = DateTimePickerEntity.getDefaultMin()
    private var mMaxData = DateTimePickerEntity.getDefaultMax()
    private var mSelData = DateTimePickerEntity.getToday()
    private var mCallback: IDateTimeSelectCallback? = null
    private var mLabel = PICKER_DATE_HOUR_MINUTE


    override fun initLayout(): Int {
        return R.layout.ease_layout_dialog_date_picker
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.setMinDateTime(mMinData)
        mViewModel.setMaxDateTime(mMaxData)
        mViewModel.setSelDateTime(mSelData)
        mViewModel.setLabel(mLabel)
        mCallback?.let {
            mViewModel.setCallback(it)
        }
    }

    override fun initViewModel(): Class<DateTimePickerBaseViewModel> {
        return DateTimePickerBaseViewModel::class.java
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mYearView = view.findViewById(R.id.id_year)
        mMonthView = view.findViewById(R.id.id_month)
        mDayView = view.findViewById(R.id.id_day)
        mHourView = view.findViewById(R.id.id_hour)
        mMinuteView = view.findViewById(R.id.id_minute)
        mSecondView = view.findViewById(R.id.id_second)
        mTitleView = view.findViewById(R.id.tv_picker_title)
        mCancelView = view.findViewById(R.id.tv_picker_cancel)
        mPositiveView = view.findViewById(R.id.tv_picker_positive)
    }

    override fun initAdapter(savedInstanceState: Bundle?) {
        //Dialog最大高度
        if (DialogHelp.isLandscape()) {
            val mMaxHeight =
                Resources.getSystem().displayMetrics.heightPixels * HEIGHT_RATIO_LANDSCAPE
            val mParams = view?.layoutParams
            mParams?.height = mMaxHeight.toInt()
            view?.layoutParams = mParams
        }

    }

    override fun initListener(savedInstanceState: Bundle?) {
        mCancelView.setOnClickListener {
            mViewModel.onCancelEvent()
            dismiss()
        }

        mPositiveView.setOnClickListener {
            mViewModel.onPositiveEvent()
            dismiss()
        }

        mYearView.setOnWheelChangedListener(object : OnWheelChangedListener {
            override fun onWheelScrolled(view: WheelView?, offset: Int) {

            }

            override fun onWheelSelected(view: WheelView?, position: Int) {
                LogcatToos.w("选中年份: " + position)
                mViewModel.setSelectYear(position)
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
                LogcatToos.w("选中月份: " + position)
                mViewModel.setSelectMonth(position)
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
                LogcatToos.w("选中日: " + position)
                mViewModel.setSelectDay(position)
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
                LogcatToos.w("选中小时: " + position)
                mViewModel.setSelectHour(position)
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
                LogcatToos.w("选中分钟: " + position)
                mViewModel.setSelectMinute(position)
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
                LogcatToos.w("选中秒: " + position)
                mViewModel.setSelectSecond(position)
            }

            override fun onWheelScrollStateChanged(view: WheelView?, state: Int) {

            }

            override fun onWheelLoopFinished(view: WheelView?) {

            }
        })
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.mLabel.observe(this) {
            when (it) {
                PICKER_TIME -> {
                    //时-分-秒
                    mHourView.isVisible = true
                    mMinuteView.isVisible = true
                    mSecondView.isVisible = true
                }
                PICKER_MINUTE_SECOND -> {
                    //分-秒
                    mMinuteView.isVisible = true
                    mSecondView.isVisible = true
                }
                PICKER_SECOND -> {
                    //秒
                    mSecondView.isVisible = true
                }
                PICKER_DATE -> {
                    //年-月-日
                    mYearView.isVisible = true
                    mMonthView.isVisible = true
                    mDayView.isVisible = true
                }
//                PICKER_DATE_MONTH_DAY -> {
//                    //月-日
//                    mMonthView.isVisible = true
//                    mDayView.isVisible = true
//                }
//                PICKER_DATE_DAY -> {
//                    //日
//                    mDayView.isVisible = true
//                }
//                PICKER_DATE_YEAR ->{
//                    //年
//                    mYearView.isVisible = true
//                }
//                PICKER_DATE_YEAR_MONTH ->{
//                    //年
//                    mYearView.isVisible = true
//                    mMonthView.isVisible = true
//                }
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
            }
        }
        mViewModel.mTitleTextInfo.observe(this) {
            setViewParameter(mTitleView, it)
        }
        mViewModel.mCancelTextInfo.observe(this) {
            setViewParameter(mCancelView, it)
        }
        mViewModel.mPositiveTextInfo.observe(this) {
            setViewParameter(mPositiveView, it)
        }
        mViewModel.mYearData.observe(this) {
            if (mYearView.isVisible) {
                mYearView.data = it
            }
        }
        mViewModel.mSelYear.observe(this) {
            if (mYearView.isVisible) {
                mYearView.setDefaultValue(it)
            }
        }

        mViewModel.mMonthData.observe(this) {
            if (mMonthView.isVisible) {
                mMonthView.data = it
            }
        }
        mViewModel.mSelMonth.observe(this) {
            if (mMonthView.isVisible) {
                mMonthView.setDefaultValue(it)
            }
        }

        mViewModel.mDayData.observe(this) {
            if (mDayView.isVisible) {
                mDayView.data = it
            }
        }
        mViewModel.mSelDay.observe(this) {
            if (mDayView.isVisible) {
                mDayView.setDefaultValue(it)
            }
        }

        mViewModel.mHourData.observe(this) {
            if (mHourView.isVisible) {
                mHourView.data = it
            }
        }
        mViewModel.mSelHour.observe(this) {
            if (mHourView.isVisible) {
                mHourView.setDefaultValue(it)
            }
        }

        mViewModel.mMinuteData.observe(this) {
            if (mMinuteView.isVisible) {
                mMinuteView.data = it
            }
        }
        mViewModel.mSelMinute.observe(this) {
            if (mMinuteView.isVisible) {
                mMinuteView.setDefaultValue(it)
            }
        }

        mViewModel.mSecondData.observe(this) {
            if (mSecondView.isVisible) {
                mSecondView.data = it
            }
        }
        mViewModel.mSelSecond.observe(this) {
            if (mSecondView.isVisible) {
                mSecondView.setDefaultValue(it)
            }
        }
        mViewModel.initYearData()
        mViewModel.initMonthData()
        mViewModel.initDayData()
        mViewModel.initHourData()
        mViewModel.initMinuteData()
        mViewModel.initSecondData()
    }

    override fun initGravity(): Int {
        return if (DialogHelp.isLandscape()) Gravity.CENTER else Gravity.BOTTOM
    }

    override fun initAnimations(): Int {
        return if (DialogHelp.isLandscape()) R.style.CenterAnimation else R.style.BottomAnimation
    }

    override fun isCancel(): Boolean {
        return true
    }

    fun setMinDateTime(entity: DateTimePickerEntity?) {
        if (entity == null) {
            return
        }
        this.mMinData = entity
    }

    fun setMaxDateTime(entity: DateTimePickerEntity?) {
        if (entity == null) {
            return
        }
        this.mMaxData = entity
    }

    fun setSelDateTime(entity: DateTimePickerEntity?) {
        if (entity == null) {
            return
        }
        this.mSelData = entity
    }

    fun setLabel(mode: Int) {
        this.mLabel = mode
    }

    fun setCallback(callback: IDateTimeSelectCallback?) {
        this.mCallback = callback
    }
}