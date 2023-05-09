package com.night.dialog.ui.picker

import com.night.dialog.base.EaseBaseViewModel
import com.night.dialog.callback.IDateTimeSelectCallback
import com.night.dialog.entity.DateTimeEntity
import com.night.dialog.entity.DateTimePickerEntity
import com.night.dialog.tools.DateTimeMode
import com.night.dialog.tools.PICKER_DATE_HOUR_MINUTE

class DateTimePickerBaseViewModel : EaseBaseViewModel() {
    private var minDateTime: DateTimePickerEntity? = null
    private var maxDateTime: DateTimePickerEntity? = null
    private var selDateTime: DateTimePickerEntity? = null
    private var mPickerCallback: IDateTimeSelectCallback? = null
    private var mLabel: Int = PICKER_DATE_HOUR_MINUTE

    /**
     * 设置选择器最小时间
     */
    fun initMinDateTime(entity: DateTimePickerEntity?) {
        if (entity == null) {
            return
        }
        if (minDateTime != null) {
            return
        }
        this.minDateTime = entity
    }

    /**
     * 设置选择器最小时间
     */
    fun initMaxDateTime(entity: DateTimePickerEntity?) {
        if (entity == null) {
            return
        }
        if (maxDateTime != null) {
            return
        }
        this.maxDateTime = entity
    }

    /**
     * 设置选择器最小时间
     */
    fun initSelDateTime(entity: DateTimePickerEntity?) {
        if (entity == null) {
            return
        }
        if(selDateTime != null){
            return
        }
        this.selDateTime = entity
    }
    fun setSelDateTime(entity: DateTimePickerEntity?) {
        if (entity == null) {
            return
        }
        this.selDateTime = entity
    }

    fun setLabel(mode: Int) {
        mLabel = mode
    }

    fun setCallback(callback: IDateTimeSelectCallback?) {
        this.mPickerCallback = callback
    }

    fun getStartDateTime(): DateTimePickerEntity? {
        return minDateTime
    }

    fun getEndDateTime(): DateTimePickerEntity? {
        return maxDateTime
    }

    fun getDefDateTime(): DateTimePickerEntity? {
        return selDateTime
    }

    @DateTimeMode
    fun getLabel(): Int {
        return mLabel
    }

    override fun onCancelEvent() {
        mPickerCallback?.onCancel()
    }

    fun onPositiveEvent() {
        val mDateTimeEntity = DateTimeEntity(
            selDateTime!!.year.toString(),
            selDateTime!!.month.toString(),
            selDateTime!!.day.toString(),
            selDateTime!!.hour.toString(),
            selDateTime!!.minute.toString(),
            selDateTime!!.second.toString()
        )
        mPickerCallback?.onSelectDate(mDateTimeEntity)
    }

    override fun onCleared() {
        mPickerCallback?.onDismiss()
    }
}