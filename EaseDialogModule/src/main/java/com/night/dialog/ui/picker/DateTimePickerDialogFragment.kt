package com.night.dialog.ui.picker

import android.content.res.Resources
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.night.dialog.R
import com.night.dialog.base.EaseSafeDialogFragment
import com.night.dialog.callback.IDateTimePickerListener
import com.night.dialog.callback.IDateTimeSelectCallback
import com.night.dialog.entity.DateTimePickerEntity
import com.night.dialog.tools.*
import com.night.dialog.tools.DialogHelp
import com.night.dialog.widget.EaseDateTimePickerView

class DateTimePickerDialogFragment : EaseSafeDialogFragment<DateTimePickerBaseViewModel>() {
    private lateinit var mTitleView: AppCompatTextView
    private lateinit var mCancelView: AppCompatTextView
    private lateinit var mPositiveView: AppCompatTextView
    private lateinit var mDateTimeView : EaseDateTimePickerView
    private var mMinData = DateTimePickerEntity.getDefaultMin()
    private var mMaxData = DateTimePickerEntity.getDefaultMax()
    private var mSelData = DateTimePickerEntity.getToday()
    private var mCallback: IDateTimeSelectCallback? = null
    private var mLabel = -1


    override fun initLayout(): Int {
        return R.layout.ease_layout_dialog_date_picker
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.initMinDateTime(mMinData)
        mViewModel.initMaxDateTime(mMaxData)
        mViewModel.initSelDateTime(mSelData)
        if(mLabel != -1){
            mViewModel.setLabel(mLabel)
        }
        mCallback?.let {
            mViewModel.setCallback(it)
        }
    }

    override fun initViewModel(): Class<DateTimePickerBaseViewModel> {
        return DateTimePickerBaseViewModel::class.java
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mTitleView = view.findViewById(R.id.tv_picker_title)
        mCancelView = view.findViewById(R.id.tv_picker_cancel)
        mPositiveView = view.findViewById(R.id.tv_picker_positive)
        mDateTimeView = view.findViewById(R.id.ease_date_time)
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

        mDateTimeView.setListener(object : IDateTimePickerListener {
            override fun onSelected(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int) {
                mViewModel.setSelDateTime(DateTimePickerEntity(year, month, day, hour, minute, second))
            }
        })
    }

    override fun initData(savedInstanceState: Bundle?) {
        mDateTimeView.setLabel(mViewModel.getLabel())

        mDateTimeView.setDateTime(mViewModel.getStartDateTime(),mViewModel.getEndDateTime(),mViewModel.getDefDateTime())

        mViewModel.mTitleTextInfo.observe(this) {
            setViewParameter(mTitleView, it)
        }
        mViewModel.mCancelTextInfo.observe(this) {
            setViewParameter(mCancelView, it)
        }
        mViewModel.mPositiveTextInfo.observe(this) {
            setViewParameter(mPositiveView, it)
        }
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