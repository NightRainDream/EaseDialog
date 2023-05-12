package com.night.dialog.callback
import androidx.annotation.MainThread
import com.night.dialog.entity.EaseProvinceEntity

interface EaseILoadAddressCallback {
    @MainThread
    fun onLoadAddress(address: ArrayList<EaseProvinceEntity>)
}