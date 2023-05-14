package com.night.dialog.ui.picker

import com.night.dialog.base.EaseBaseViewModel
import com.night.dialog.callback.IDateTimeCallback
import com.night.dialog.entity.EaseDateTimeEntity
import com.night.dialog.tools.DateTimeMode
import com.night.dialog.tools.PICKER_DATE_HOUR_MINUTE

class DateTimePickerViewModel : EaseBaseViewModel() {
    private var minDateTime: EaseDateTimeEntity? = null
    private var maxDateTime: EaseDateTimeEntity? = null
    private var selDateTime: EaseDateTimeEntity? = null
    private var mPickerCallback: IDateTimeCallback? = null
    private var mLabel: Int = PICKER_DATE_HOUR_MINUTE

    /**
     * 设置选择器最小时间
     */
    fun initMinDateTime(entity: EaseDateTimeEntity?) {
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
    fun initMaxDateTime(entity: EaseDateTimeEntity?) {
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
    fun initSelDateTime(entity: EaseDateTimeEntity?) {
        if (entity == null) {
            return
        }
        if (selDateTime != null) {
            return
        }
        this.selDateTime = entity
    }

    fun setSelDateTime(entity: EaseDateTimeEntity?) {
        if (entity == null) {
            return
        }
        this.selDateTime = entity
    }

    fun setLabel(mode: Int) {
        mLabel = mode
    }

    fun setCallback(callback: IDateTimeCallback?) {
        this.mPickerCallback = callback
    }

    fun getStartDateTime(): EaseDateTimeEntity? {
        return minDateTime
    }

    fun getEndDateTime(): EaseDateTimeEntity? {
        return maxDateTime
    }

    fun getDefDateTime(): EaseDateTimeEntity? {
        return selDateTime
    }

    @DateTimeMode
    fun getLabel(): Int {
        return mLabel
    }

    fun onCancelEvent() {
        mPickerCallback?.onCancel()
    }

    fun onPositiveEvent() {
        val mDateTimeEntity = EaseDateTimeEntity(
            selDateTime!!.year,
            selDateTime!!.month,
            selDateTime!!.day,
            selDateTime!!.hour,
            selDateTime!!.minute,
            selDateTime!!.second
        )
        mPickerCallback?.onPositive(mDateTimeEntity)
    }

    override fun onCleared() {
        mPickerCallback?.onDismiss()
    }
}