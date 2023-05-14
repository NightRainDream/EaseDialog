package com.night.dialog.ui.picker

import com.night.dialog.base.EaseBaseViewModel
import com.night.dialog.callback.ILocationCallback
import com.night.dialog.entity.*
import com.night.dialog.tools.AddressMode
import com.night.dialog.tools.PICKER_ADDRESS_ALL

class LocationPickerViewModel : EaseBaseViewModel() {
    @AddressMode
    private var mLabel: Int = PICKER_ADDRESS_ALL
    private var mCallback: ILocationCallback? = null
    private var mDefaultProvince = EaseLocationEntity("北京市", "110000")
    private var mDefaultCity = EaseLocationEntity("北京市", "110000")
    private var mDefaultCounty = EaseLocationEntity("朝阳区", "110105")
    fun setLocalChange(province: EaseLocationEntity, city: EaseLocationEntity, county: EaseLocationEntity) {
        this.mDefaultProvince = province
        this.mDefaultCity = city
        this.mDefaultCounty = county
    }

    fun setSelectLocation(province: EaseLocationEntity?, city: EaseLocationEntity?, county: EaseLocationEntity?) {
        if (province == null || city == null || county == null) {
            return
        }
        mDefaultProvince = province
        mDefaultCity = city
        mDefaultCounty = county
    }

    fun setLabel(@AddressMode label: Int) {
        this.mLabel = label
    }

    fun setCallback(callback: ILocationCallback?) {
        this.mCallback = callback
    }

    @AddressMode
    fun getLabel(): Int {
        return mLabel
    }

    fun getSelectProvince(): EaseLocationEntity {
        return mDefaultProvince
    }


    fun getSelectCity(): EaseLocationEntity {
        return mDefaultCity
    }


    fun getSelectCounty(): EaseLocationEntity {
        return mDefaultCounty
    }

    fun onPositiveEvent() {
        mCallback?.onPositive(mDefaultProvince, mDefaultCity, mDefaultCounty)
    }

    fun onCancelEvent() {
        mCallback?.onCancel()
    }

    override fun onCleared() {
        super.onCleared()
        mCallback?.onDismiss()
    }


}