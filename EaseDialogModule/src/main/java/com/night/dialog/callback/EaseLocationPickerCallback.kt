package com.night.dialog.callback

import androidx.annotation.MainThread
import com.night.dialog.entity.EaseLocationEntity
import com.night.dialog.entity.EaseProvinceEntity

/**
 * 地址选择回调
 */
interface ILocationCallback {
    fun onAddressSelected(province: EaseLocationEntity, city: EaseLocationEntity, county: EaseLocationEntity)
}

/**
 * 地址选择器变化回调
 *
 */
interface ILocationChangeListener {
    /**
     * @param province 省
     * @param city 市
     * @param county 县
     */
    fun onLocationChange(province: EaseLocationEntity, city: EaseLocationEntity, county: EaseLocationEntity)
}

/**
 * 地址数据加载回调
 */
interface EaseILoadLocationCallback {
    @MainThread
    fun onLoadAddress(address: ArrayList<EaseProvinceEntity>)
}