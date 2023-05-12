package com.night.dialog.ui.picker

import android.content.res.Resources
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.night.dialog.R
import com.night.dialog.base.EaseSafeDialogFragment
import com.night.dialog.callback.IAddressPickerCallback
import com.night.dialog.callback.ILocationChangeListener
import com.night.dialog.entity.EaseLocationEntity
import com.night.dialog.tools.*
import com.night.dialog.tools.DialogHelp
import com.night.dialog.widget.EaseAddressPickerView

class AddressPickerDialogFragment : EaseSafeDialogFragment<AddressPickerViewModel>() {
    private lateinit var mTitleView: AppCompatTextView
    private lateinit var mCancelView: AppCompatTextView
    private lateinit var mPositiveView: AppCompatTextView
    private lateinit var mAddressView: EaseAddressPickerView
    @AddressMode
    private var mLabel = PICKER_ADDRESS_ALL
    private var mIAddressPickerCallback: IAddressPickerCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(mLabel != -1){
            mViewModel.setLabel(mLabel)
        }
        mIAddressPickerCallback?.let {
            mViewModel.setCallback(it)
        }
    }

    override fun initLayout(): Int {
        return R.layout.ease_layout_dialog_address_picker
    }

    override fun initViewModel(): Class<AddressPickerViewModel> {
        return AddressPickerViewModel::class.java
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mTitleView = view.findViewById(R.id.tv_picker_title)
        mCancelView = view.findViewById(R.id.tv_picker_cancel)
        mPositiveView = view.findViewById(R.id.tv_picker_positive)
        mAddressView = view.findViewById(R.id.ease_address)
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
        mAddressView.setOnLocalChangeListener(object : ILocationChangeListener {
            override fun onLocationChange(
                province: EaseLocationEntity,
                city: EaseLocationEntity,
                county: EaseLocationEntity
            ) {
                mViewModel.setLocalChange(province, city, county)
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
        mAddressView.setMode(mViewModel.getLabel())

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

    fun setLabel(@AddressMode mode: Int){
        this.mLabel = mode
    }

    fun setCallback(callback: IAddressPickerCallback){
        this.mIAddressPickerCallback = callback
    }

}