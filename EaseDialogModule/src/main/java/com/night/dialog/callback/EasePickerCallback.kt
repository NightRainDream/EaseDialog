package com.night.dialog.callback

import com.night.dialog.entity.EaseLocationEntity

interface IAddressPickerCallback {
    fun onAddressSelected(province: EaseLocationEntity?, city: EaseLocationEntity?, county: EaseLocationEntity?)
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