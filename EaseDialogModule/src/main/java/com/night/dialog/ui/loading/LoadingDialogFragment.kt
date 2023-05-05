package com.night.dialog.ui.loading

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.night.dialog.R
import com.night.dialog.base.EaseSafeDialogFragment

/**
 * ---------------------------------------------------
 * 说    明: LoadingDialog
 * 作    者: Night
 * 时    间: 2023/4/22
 * ---------------------------------------------------
 */
class LoadingDialogFragment : EaseSafeDialogFragment<LoadingDialogBaseViewModel>() {
    private lateinit var mLoadingTextView: AppCompatTextView
    override fun initLayout(): Int {
        return R.layout.ease_layout_dialog_loading
    }

    override fun initViewModel(): Class<LoadingDialogBaseViewModel> {
        return LoadingDialogBaseViewModel::class.java
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mLoadingTextView = view.findViewById(R.id.tv_loading)
    }

    override fun initAdapter(savedInstanceState: Bundle?) {

    }

    override fun initListener(savedInstanceState: Bundle?) {

    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.mMainTextInfo.observe(this) {
            setViewParameter(mLoadingTextView, it)
        }
    }

    override fun initGravity(): Int {
        return Gravity.CENTER
    }

    override fun initAnimations(): Int {
        return R.style.CenterAnimation
    }

    override fun isCancel(): Boolean {
        dialog?.setCanceledOnTouchOutside(false)
        return true
    }
}