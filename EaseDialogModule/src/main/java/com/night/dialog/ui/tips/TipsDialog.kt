package com.night.dialog.ui.tips

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.night.dialog.R
import com.night.dialog.tools.DialogHelp
import com.night.dialog.widget.EaseFragmentDialog

class TipsDialog : EaseFragmentDialog<TipsViewModel>() {
    private lateinit var mTitleView: AppCompatTextView
    private lateinit var mContentView: AppCompatTextView
    private lateinit var mCancelView: AppCompatTextView
    private lateinit var mPositiveView: AppCompatTextView

    override fun initLayoutView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.ease_layout_dialog_tip, container, false)
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

    override fun initViewModel(): Class<TipsViewModel> {
        return TipsViewModel::class.java
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mTitleView = view.findViewById(R.id.tv_tip_title)
        mContentView = view.findViewById(R.id.tv_tip_content)
        mCancelView = view.findViewById(R.id.tv_tip_cancel)
        mPositiveView = view.findViewById(R.id.tv_tip_positive)
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.mTitleTextInfo.observe(this) {
            setViewParameter(mTitleView, it)
        }
        mViewModel.mMainTextInfo.observe(this) {
            setViewParameter(mContentView, it)
        }
        mViewModel.mCancelTextInfo.observe(this) {
            setViewParameter(mCancelView, it)
        }
        mViewModel.mPositiveTextInfo.observe(this) {
            setViewParameter(mPositiveView, it)
        }
    }

    override fun initListener(savedInstanceState: Bundle?) {
        mCancelView.setOnClickListener {
            dismiss()
            mViewModel.onCancelEvent()
        }

        mPositiveView.setOnClickListener {
            dismiss()
            mViewModel.onPositiveEvent("", mutableListOf())
        }
    }
}