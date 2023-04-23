package com.night.dialog.ui.picker

import android.os.Bundle
import android.view.Gravity
import android.view.View
import com.night.dialog.R
import com.night.dialog.base.EaseSafeDialogFragment
import com.night.dialog.tools.DialogHelp

class DateTimePickerDialog: EaseSafeDialogFragment<DateTimePickerViewModel>() {
    override fun initLayout(): Int {
        return R.layout.ease_layout_dialog_date_picker
    }

    override fun initViewModel(): Class<DateTimePickerViewModel> {
        return DateTimePickerViewModel::class.java
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {

    }

    override fun initAdapter(savedInstanceState: Bundle?) {

    }

    override fun initListener(savedInstanceState: Bundle?) {

    }

    override fun initData(savedInstanceState: Bundle?) {

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