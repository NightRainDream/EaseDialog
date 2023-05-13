package com.night.dialog.entity

/**
 * 地址数据
 *
 * @param code 行政区代码
 * @param name 行政区名称
 */
data class EaseLocationEntity(val name: String, val code: String)

/**
 * 省级数据
 */
data class EaseProvinceEntity(val code: String, val name: String, val city: List<EaseCityEntity>)

/**
 * 市级数据
 */
data class EaseCityEntity(val code: String, val name: String, val county: List<EaseCountyEntity>)

/**
 * 县级数据
 */
data class EaseCountyEntity(val code: String, val name: String)