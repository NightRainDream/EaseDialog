package com.night.dialog.ui.picker

import com.night.dialog.base.EaseBaseViewModel
import com.night.dialog.callback.IAddressPickerCallback
import com.night.dialog.entity.*
import com.night.dialog.tools.AddressMode
import com.night.dialog.tools.PICKER_ADDRESS_ALL

class AddressPickerViewModel : EaseBaseViewModel() {
    @AddressMode
    private var mLabel: Int = PICKER_ADDRESS_ALL
    private var mCallback: IAddressPickerCallback? = null
    private var mDefaultProvince = EaseLocationEntity("北京市", "110000")
    private var mDefaultCity = EaseLocationEntity("北京市", "110000")
    private var mDefaultCounty = EaseLocationEntity("朝阳区", "110105")
    fun setLocalChange(province: EaseLocationEntity, city: EaseLocationEntity, county: EaseLocationEntity) {
        this.mDefaultProvince = province
        this.mDefaultCity = city
        this.mDefaultCounty = county
    }

    fun setLabel(@AddressMode label: Int) {
        this.mLabel = label
    }

    fun setCallback(callback: IAddressPickerCallback?) {
        this.mCallback = callback
    }

    @AddressMode
    fun getLabel(): Int {
        return mLabel
    }

    fun onPositiveEvent() {
        mCallback?.onAddressSelected(mDefaultProvince, mDefaultCity, mDefaultCounty)
    }


}