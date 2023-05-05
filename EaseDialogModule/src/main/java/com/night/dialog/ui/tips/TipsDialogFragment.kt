package com.night.dialog.ui.tips

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.night.dialog.R
import com.night.dialog.base.EaseSafeDialogFragment
import com.night.dialog.tools.DialogHelp

/**
 * ---------------------------------------------------
 * 说    明: 提示性质对话框
 * 作    者: Night
 * 时    间: 2023/4/22
 * ---------------------------------------------------
 */
class TipsDialogFragment : EaseSafeDialogFragment<TipsBaseViewModel>() {
    private lateinit var mTitleView: AppCompatTextView
    private lateinit var mContentView: AppCompatTextView
    private lateinit var mCancelView: AppCompatTextView
    private lateinit var mPositiveView: AppCompatTextView
    override fun initLayout(): Int {
        return R.layout.ease_layout_dialog_tip
    }

    override fun initViewModel(): Class<TipsBaseViewModel> {
        return TipsBaseViewModel::class.java
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mTitleView = view.findViewById(R.id.tv_tip_title)
        mContentView = view.findViewById(R.id.tv_tip_content)
        mCancelView = view.findViewById(R.id.tv_tip_cancel)
        mPositiveView = view.findViewById(R.id.tv_tip_positive)
    }

    override fun initAdapter(savedInstanceState: Bundle?) {

    }

    override fun initListener(savedInstanceState: Bundle?) {
        mCancelView.setOnClickListener {
            mViewModel.onCancelEvent()
            dismiss()
        }
        mPositiveView.setOnClickListener {
            mViewModel.onPositiveEvent("", mutableListOf())
            dismiss()
        }
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

    override fun initGravity(): Int {
        return if (DialogHelp.isLandscape()) Gravity.CENTER else Gravity.BOTTOM
    }

    override fun initAnimations(): Int {
        return if (DialogHelp.isLandscape()) R.style.CenterAnimation else R.style.BottomAnimation
    }

    override fun isCancel(): Boolean {
        return true
    }
}