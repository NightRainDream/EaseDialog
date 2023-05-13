package com.night.dialog.ui.picker

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.night.dialog.R
import com.night.dialog.base.EaseSafeDialogFragment
import com.night.dialog.callback.EaseColorSelectListener
import com.night.dialog.callback.IColorSelectCallback
import com.night.dialog.tools.DialogHelp
import com.night.dialog.widget.EaseColorPickerView

class ColorPickerDialogFragment : EaseSafeDialogFragment<ColorPickerViewModel>() {
    private lateinit var mTitleView: AppCompatTextView
    private lateinit var mCancelView: AppCompatTextView
    private lateinit var mPositiveView: AppCompatTextView
    private lateinit var mColorView: EaseColorPickerView
    private lateinit var mShowView: View
    private var mCallback: IColorSelectCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.setCallback(mCallback)
    }

    override fun initLayout(): Int {
        return R.layout.ease_layout_dialog_color_picker
    }

    override fun initViewModel(): Class<ColorPickerViewModel> {
        return ColorPickerViewModel::class.java
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mTitleView = view.findViewById(R.id.tv_picker_title)
        mCancelView = view.findViewById(R.id.tv_picker_cancel)
        mPositiveView = view.findViewById(R.id.tv_picker_positive)
        mColorView = view.findViewById(R.id.ease_color)
        mShowView = view.findViewById(R.id.v_show)
    }

    override fun initAdapter(savedInstanceState: Bundle?) {
        DialogHelp.setDialogMaxSize(view)
    }

    override fun initListener(savedInstanceState: Bundle?) {
        mColorView.setOnColorSelectedListener(object : EaseColorSelectListener {
            override fun onColorSelecting(red: Int, green: Int, blue: Int) {
                mShowView.setBackgroundColor(Color.rgb(red, green, blue))
            }

            override fun onColorSelected(red: Int, green: Int, blue: Int) {
                mViewModel.setSelectRGB(red, green, blue)
            }
        })

        mCancelView.setOnClickListener {
            mViewModel.onCancelEvent()
            dismiss()
        }

        mPositiveView.setOnClickListener {
            mViewModel.onPositiveEvent()
            dismiss()
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
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

    fun setCallback(callback: IColorSelectCallback?) {
        this.mCallback = callback
    }
}