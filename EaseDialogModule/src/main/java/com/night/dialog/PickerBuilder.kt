package com.night.dialog

import android.app.Activity
import android.view.Gravity
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import com.night.dialog.base.BaseDialog
import com.night.dialog.base.BaseDialogBuilder
import com.night.dialog.base.WheelView
import com.night.dialog.callback.IBindDialogView
import com.night.dialog.callback.IDateTimeSelectCallback
import com.night.dialog.callback.IWheelChangedListener
import com.night.dialog.entity.DateEntity
import com.night.dialog.entity.DateTimeEntity
import com.night.dialog.entity.TextInfoEntity
import com.night.dialog.entity.TimeEntity
import com.night.dialog.tools.DatePickerHelp
import com.night.dialog.tools.DialogHelp
import com.night.dialog.tools.TimePickerHelp

class PickerBuilder : BaseDialogBuilder() {
//    private val mDatePickerHelp: DatePickerHelp by lazy {
//        DatePickerHelp()
//    }
//    private val mTimePickerHelp: TimePickerHelp by lazy {
//        TimePickerHelp()
//    }
//
//    /**
//     * 设置标题TextView属性
//     *
//     * @param title TextView属性[TextInfoEntity]
//     * @return DialogBuilder
//     */
//    fun setTitleTextInfo(title: TextInfoEntity): PickerBuilder {
//        this.mTitleTextInfo = title
//        return this
//    }
//
////    /**
////     * 设置标题内容
////     *
////     * @param title 标题内容
////     */
////    fun setTitleText(title: String?): PickerBuilder {
////        if (title != null) {
////            this.mTitleText = title
////        }
////        return this
////    }
//
//    /**
//     * 设置标题内容颜色
//     *
//     * @param title 标题内容颜色
//     */
//    fun setTitleTextColor(@ColorInt title: Int): PickerBuilder {
//        this.mTitleTextColor = title
//        return this
//    }
//
//    /**
//     * 设置取消按钮TextView属性
//     *
//     * @param cancel TextView属性[TextInfoEntity]
//     * @return DialogBuilder
//     */
//    fun setCancelTextInfo(cancel: TextInfoEntity): PickerBuilder {
//        this.mCancelTextInfo = cancel
//        return this
//    }
//
//    /**
//     * 设置取消按钮文本
//     *
//     * @param cancel 取消按钮文本
//     */
//    fun setCancelText(cancel: String?): PickerBuilder {
//        if (cancel != null) {
//            this.mCancelText = cancel
//        }
//        return this
//    }
//
//    /**
//     * 设置取消按钮文本颜色
//     *
//     * @param cancel 取消按钮文本颜色
//     */
//    fun setCancelTextColor(@ColorInt cancel: Int): PickerBuilder {
//        this.mCancelTextColor = cancel
//        return this
//    }
//
//    /**
//     * 设置确定按钮TextView属性
//     *
//     * @param positive TextView属性[TextInfoEntity]
//     * @return DialogBuilder
//     */
//    fun setPositiveTextInfo(positive: TextInfoEntity): PickerBuilder {
//        this.mPositiveTextInfo = positive
//        return this
//    }
//
//    /**
//     * 设置确认按钮文本
//     *
//     * @param positive 确认按钮文本
//     */
//    fun setPositiveText(positive: String?): PickerBuilder {
//        if (null != positive) {
//            this.mPositionText = positive
//        }
//        return this
//    }
//
//    /**
//     * 设置确认按钮文本颜色
//     *
//     * @param positive 确认按钮文本颜色
//     */
//    fun setPositiveTextColor(positive: Int): PickerBuilder {
//        this.mPositionTextColor = positive
//        return this
//    }
//
//    /**
//     * 设置最大日期
//     *
//     * @param maxDate 最大日期[DateEntity]
//     */
//    fun setMaxDate(maxDate: DateEntity): PickerBuilder {
//        mDatePickerHelp.setMaxDate(maxDate)
//        return this
//    }
//
//    /**
//     * 设置最小日期
//     *
//     * @param minDate 最小日期[DateEntity]
//     */
//    fun setMinDate(minDate: DateEntity): PickerBuilder {
//        mDatePickerHelp.setMinDate(minDate)
//        return this
//    }
//
//    /**
//     * 设置默认选中日期
//     *
//     * @param defDate 默认选中日期[DateEntity]
//     */
//    fun setDefaultDate(defDate: DateEntity): PickerBuilder {
//        mDatePickerHelp.setDefault(defDate)
//        return this
//    }
//
//    /**
//     * 设置最大时间
//     *
//     * @param maxTime 最大时间[TimeEntity]
//     */
//    fun setMaxTime(maxTime: TimeEntity): PickerBuilder {
//        mTimePickerHelp.setMaxTime(maxTime)
//        return this
//    }
//
//    /**
//     * 设置最小时间
//     *
//     * @param minTime 最小时间[TimeEntity]
//     */
//    fun setMinTime(minTime: TimeEntity): PickerBuilder {
//        mTimePickerHelp.setMinTime(minTime)
//        return this
//    }
//
//    /**
//     * 设置默认选中时间
//     *
//     * @param defTime 默认选中时间[TimeEntity]
//     */
//    fun setDefaultTime(defTime: TimeEntity): PickerBuilder {
//        mTimePickerHelp.setDefaultTime(defTime)
//        return this
//    }
//
//    /**
//     * 设置点击外部可关闭
//     */
//    fun setCancelable(isCancel: Boolean): PickerBuilder {
//        this.isCancel = isCancel
//        return this
//    }
//
//
//    /**
//     * 日期选择器
//     *
//     * @param activity Activity
//     * @param callback 状态回调[IDateTimeSelectCallback]
//     */
//    fun toDatePicker(activity: Activity, callback: IDateTimeSelectCallback) {
//        mDatePickerHelp.initDefaultIndex()
//        DialogTools.showDialog(activity, object : IBindDialogView(R.layout.ease_layout_dialog_date_picker) {
//            override fun onBind(dialog: BaseDialog) {
//                dialog.setGravity(if (DialogHelp.isLandscape()) Gravity.CENTER else Gravity.BOTTOM)
//                val mViewGroup = dialog.findViewById<ViewGroup>(R.id.ll_picker)
//                val idYear = dialog.findViewById<WheelView>(R.id.id_year)
//                val idMonth = dialog.findViewById<WheelView>(R.id.id_month)
//                val idDay = dialog.findViewById<WheelView>(R.id.id_day)
//                val mTitleView = dialog.findViewById<AppCompatTextView>(R.id.tv_picker_title)
//                val mPositiveView = dialog.findViewById<AppCompatTextView>(R.id.tv_picker_positive)
//                val mCancelView = dialog.findViewById<AppCompatTextView>(R.id.tv_picker_cancel)
//                if (DialogHelp.isLandscape()) {
//                    val mLayoutParams = mViewGroup.layoutParams
//                    mLayoutParams.height = DialogHelp.getMaxMenuHeight().toInt()
//                    mViewGroup.layoutParams = mLayoutParams
//                }
//                //设置标题参数
//                setTitleParameter(mTitleView)
//                //设置取消按钮参数
//                setCancelParameter(mCancelView)
//                //设置确认按钮参数
//                setPositiveParameter(mPositiveView)
//
//                idYear?.addChangingListener(object : IWheelChangedListener {
//                    override fun onChanged(wheel: WheelView?, oldValue: Int, newValue: Int) {
//                        mDatePickerHelp.setSelectYear(newValue)
//                        mDatePickerHelp.initMonthLabel(activity, idMonth)
//                        mDatePickerHelp.initDayLabel(activity, idDay)
//                    }
//                })
//
//                idMonth?.addChangingListener(object : IWheelChangedListener {
//                    override fun onChanged(wheel: WheelView?, oldValue: Int, newValue: Int) {
//                        mDatePickerHelp.setSelectMonth(newValue)
//                        mDatePickerHelp.initDayLabel(activity, idDay)
//                    }
//                })
//
//                idDay?.addChangingListener(object : IWheelChangedListener {
//                    override fun onChanged(wheel: WheelView?, oldValue: Int, newValue: Int) {
//                        mDatePickerHelp.setSelectDay(newValue)
//                    }
//                })
//                mDatePickerHelp.initYearLabel(activity, idYear)
//                if (mDatePickerHelp.isSelectFirst()) {
//                    mDatePickerHelp.initMonthLabel(activity, idMonth)
//                    mDatePickerHelp.initDayLabel(activity, idDay)
//                }
//                mPositiveView.setOnClickListener {
//                    dialog.dismiss()
//                    val result = DateTimeEntity(
//                        mDatePickerHelp.getSelectYear(),
//                        mDatePickerHelp.getSelectMonth(),
//                        mDatePickerHelp.getSelectDay(),
//                        "00",
//                        "00",
//                        "00",
//                    )
//                    callback.onSelectDate(result)
//                }
//                mCancelView.setOnClickListener {
//                    dialog.dismiss()
//                    callback.onCancel()
//                }
//            }
//        }, mStyle, mGravity, isCancel)
//    }
//
//    /**
//     * 时间选择器
//     *
//     * @param activity Activity
//     * @param callback 状态回调[IDateTimeSelectCallback]
//     */
//    fun toTimePicker(activity: Activity, callback: IDateTimeSelectCallback) {
//        mTimePickerHelp.initDefaultIndex()
//        DialogTools.showDialog(activity, object : IBindDialogView(R.layout.ease_layout_dialog_time_picker) {
//            override fun onBind(dialog: BaseDialog) {
//                dialog.setGravity(if (DialogHelp.isLandscape()) Gravity.CENTER else Gravity.BOTTOM)
//                val mViewGroup = dialog.findViewById<ViewGroup>(R.id.ll_picker)
//                val idHour = dialog.findViewById<WheelView>(R.id.id_hour)
//                val idMinute = dialog.findViewById<WheelView>(R.id.id_minute)
//                val idSecond = dialog.findViewById<WheelView>(R.id.id_second)
//                val mTitleView = dialog.findViewById<AppCompatTextView>(R.id.tv_picker_title)
//                val mPositiveView = dialog.findViewById<AppCompatTextView>(R.id.tv_picker_positive)
//                val mCancelView = dialog.findViewById<AppCompatTextView>(R.id.tv_picker_cancel)
//                if (DialogHelp.isLandscape()) {
//                    val mLayoutParams = mViewGroup.layoutParams
//                    mLayoutParams.height = DialogHelp.getMaxMenuHeight().toInt()
//                    mViewGroup.layoutParams = mLayoutParams
//                }
//                //设置标题参数
//                setTitleParameter(mTitleView)
//                //设置取消按钮参数
//                setCancelParameter(mCancelView)
//                //设置确认按钮参数
//                setPositiveParameter(mPositiveView)
//
//                idHour?.addChangingListener(object : IWheelChangedListener {
//                    override fun onChanged(wheel: WheelView?, oldValue: Int, newValue: Int) {
//                        mTimePickerHelp.setSelectHour(newValue)
//                        mTimePickerHelp.initMinuteLabel(activity, idMinute)
//                        mTimePickerHelp.initSecondLabel(activity, idSecond)
//                    }
//                })
//
//                idMinute?.addChangingListener(object : IWheelChangedListener {
//                    override fun onChanged(wheel: WheelView?, oldValue: Int, newValue: Int) {
//                        mTimePickerHelp.setSelectMinute(newValue)
//                        mTimePickerHelp.initSecondLabel(activity, idSecond)
//                    }
//                })
//
//                idSecond?.addChangingListener(object : IWheelChangedListener {
//                    override fun onChanged(wheel: WheelView?, oldValue: Int, newValue: Int) {
//                        mTimePickerHelp.setSelectSecond(newValue)
//                    }
//                })
//                mTimePickerHelp.initHourLabel(activity, idHour)
//                if (mTimePickerHelp.isSelectFirst()) {
//                    mTimePickerHelp.initMinuteLabel(activity, idMinute)
//                    mTimePickerHelp.initSecondLabel(activity, idSecond)
//                }
//                mPositiveView.setOnClickListener {
//                    dialog.dismiss()
//                    val result = DateTimeEntity(
//                        "1970", "01", "01",
//                        mTimePickerHelp.getSelectHour(),
//                        mTimePickerHelp.getSelectMinute(),
//                        mTimePickerHelp.getSelectSecond()
//
//                    )
//                    callback.onSelectDate(result)
//                }
//                mCancelView.setOnClickListener {
//                    dialog.dismiss()
//                    callback.onCancel()
//                }
//            }
//        }, mStyle, mGravity, isCancel)
//    }
}