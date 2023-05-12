package com.night.dialog.entity


data class EaseProvinceEntity(val code: String, val name: String?, val city: List<EaseCityEntity>)

data class EaseCityEntity(val code: String, val name: String?, val county: List<EaseCountyEntity>)

data class EaseCountyEntity(val code: String, val name: String?)

data class AddressEntity(val code: String, val name: String)


