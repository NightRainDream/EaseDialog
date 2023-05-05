package com.night.dialog

import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import com.night.dialog.base.BaseDialogBuilder
import com.night.dialog.callback.IDateTimeSelectCallback
import com.night.dialog.entity.DateTimePickerEntity
import com.night.dialog.entity.TextInfoEntity
import com.night.dialog.ui.picker.DateTimePickerDialogFragment

class PickerBuilder : BaseDialogBuilder() {
    private var mMinDateTime: DateTimePickerEntity? = null
    private var mMaxDateTime: DateTimePickerEntity? = null
    private var mSelDateTime: DateTimePickerEntity? = null
    private var mLabel: List<Int>? = null

    /**
     * 设置标题TextView属性
     *
     * @param title TextView属性[TextInfoEntity]
     * @return DialogBuilder
     */
    fun setTitleTextInfo(title: TextInfoEntity): PickerBuilder {
        this.mTitleTextInfo = title
        return this
    }

    /**
     * 设置标题内容
     *
     * @param title 标题内容
     */
    fun setTitleText(title: String?): PickerBuilder {
        if (title != null) {
            this.mTitleTextInfo.text = title
        }
        return this
    }

    /**
     * 设置标题内容颜色
     *
     * @param title 标题内容颜色
     */
    fun setTitleTextColor(@ColorInt title: Int): PickerBuilder {
        this.mTitleTextInfo.textColor = title
        return this
    }

    /**
     * 设置内容TextView属性
     *
     * @param main TextView属性[TextInfoEntity]
     * @return DialogBuilder
     */
    fun setMainTextInfo(main: TextInfoEntity): PickerBuilder {
        this.mMainTextInfo = main
        return this
    }

    /**
     * 设置主要内容
     *
     * @param main 主要内容
     */
    fun setMainText(main: String?): PickerBuilder {
        if (main != null) {
            this.mMainTextInfo.text = main
        }
        return this
    }

    /**
     * 设置主要内容颜色
     *
     * @param main 主要内容颜色
     */
    fun setMainTextColor(@ColorInt main: Int): PickerBuilder {
        this.mMainTextInfo.textColor = main
        return this
    }

    /**
     * 设置取消按钮TextView属性
     *
     * @param cancel TextView属性[TextInfoEntity]
     * @return DialogBuilder
     */
    fun setCancelTextInfo(cancel: TextInfoEntity): PickerBuilder {
        this.mCancelTextInfo = cancel
        return this
    }

    /**
     * 设置取消按钮文本
     *
     * @param cancel 取消按钮文本
     */
    fun setCancelText(cancel: String?): PickerBuilder {
        if (cancel != null) {
            this.mCancelTextInfo.text = cancel
        }
        return this
    }

    /**
     * 设置取消按钮文本颜色
     *
     * @param cancel 取消按钮文本颜色
     */
    fun setCancelTextColor(@ColorInt cancel: Int): PickerBuilder {
        this.mCancelTextInfo.textColor = cancel
        return this
    }

    /**
     * 设置确定按钮TextView属性
     *
     * @param positive TextView属性[TextInfoEntity]
     * @return DialogBuilder
     */
    fun setPositiveTextInfo(positive: TextInfoEntity): PickerBuilder {
        this.mPositiveTextInfo = positive
        return this
    }

    /**
     * 设置确认按钮文本
     *
     * @param positive 确认按钮文本
     */
    fun setPositiveText(positive: String?): PickerBuilder {
        if (null != positive) {
            this.mPositiveTextInfo.text = positive
        }
        return this
    }

    /**
     * 设置确认按钮文本颜色
     *
     * @param positive 确认按钮文本颜色
     */
    fun setPositiveTextColor(@ColorInt positive: Int): PickerBuilder {
        this.mPositiveTextInfo.textColor = positive
        return this
    }

    fun setMinDateTime(entity: DateTimePickerEntity): PickerBuilder {
        this.mMinDateTime = entity
        return this
    }

    fun setMaxDateTime(entity: DateTimePickerEntity): PickerBuilder {
        this.mMaxDateTime = entity
        return this
    }

    fun setSelDateTime(entity: DateTimePickerEntity): PickerBuilder {
        this.mSelDateTime = entity
        return this
    }

    fun setLabel(label: List<Int>?): PickerBuilder {
        this.mLabel = label
        return this
    }

    fun toDateTimePicker(activity: AppCompatActivity, callback: IDateTimeSelectCallback) {
        val mFragmentManage = activity.supportFragmentManager
        val mHistoryDialog = mFragmentManage.findFragmentByTag("DateTimePicker")
        if (mHistoryDialog != null && mHistoryDialog is DateTimePickerDialogFragment) {
            mHistoryDialog.dismiss()
        }
        val mDateTimePickerDialogFragment = DateTimePickerDialogFragment()
        mDateTimePickerDialogFragment.setTitleTextInfo(mTitleTextInfo)
        mDateTimePickerDialogFragment.setMainTextInfo(mMainTextInfo)
        mDateTimePickerDialogFragment.setCancelTextInfo(mCancelTextInfo)
        mDateTimePickerDialogFragment.setPositiveTextInfo(mPositiveTextInfo)
        mDateTimePickerDialogFragment.setCallback(callback)
        mDateTimePickerDialogFragment.setMinDateTime(mMinDateTime)
        mDateTimePickerDialogFragment.setMaxDateTime(mMaxDateTime)
        mDateTimePickerDialogFragment.setSelDateTime(mSelDateTime)
        if (mLabel != null) {
            mDateTimePickerDialogFragment.setLabel(mLabel!!)
        }
        mDateTimePickerDialogFragment.show(mFragmentManage, "DateTimePicker")
    }
}