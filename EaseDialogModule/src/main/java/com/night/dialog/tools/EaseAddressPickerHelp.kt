package com.night.dialog.tools

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.annotation.WorkerThread
import com.night.dialog.callback.EaseILoadAddressCallback
import com.night.dialog.entity.EaseCityEntity
import com.night.dialog.entity.EaseCountyEntity
import com.night.dialog.entity.EaseProvinceEntity
import org.json.JSONArray
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.concurrent.Executors

object EaseAddressPickerHelp {
    private val mAddressList = ArrayList<EaseProvinceEntity>()
    private val mHandler = Handler(Looper.getMainLooper())

    /**
     * 获取地址数据
     *
     * @param context 上下文
     * @param callback 数据回调[EaseILoadAddressCallback]
     */
    fun getAddressData(context: Context, callback: EaseILoadAddressCallback) {
        if (mAddressList.isEmpty()) {
            Executors.newSingleThreadExecutor().execute {
                val text = loadFromAssets(context)
                val provinceArray = JSONArray(text)
                for (i in 0 until provinceArray.length()) {
                    val provinceObject = provinceArray.optJSONObject(i)
                    val code = provinceObject.optString("code", "")
                    val name = provinceObject.optString("name", "")
                    val provinceEntity = EaseProvinceEntity(
                        code,
                        name,
                        parseCity(provinceObject.optJSONArray("cityList"))
                    )
                    mAddressList.add(provinceEntity)
                }
                mHandler.post {
                    callback.onLoadAddress(mAddressList)
                }
            }
        } else {
            callback.onLoadAddress(mAddressList)
        }
    }

    private fun parseCity(cityArray: JSONArray?): ArrayList<EaseCityEntity> {
        if (cityArray == null || cityArray.length() == 0) {
            return arrayListOf()
        }
        val mCityList = arrayListOf<EaseCityEntity>()
        for (i in 0 until cityArray.length()) {
            val countyObject = cityArray.optJSONObject(i)
            val code = countyObject.optString("code", "")
            val name = countyObject.optString("name", "")
            val entity =
                EaseCityEntity(code, name, parseCounty(countyObject.optJSONArray("areaList")))
            mCityList.add(entity)
        }
        return mCityList
    }


    /**
     * 解析县级数据
     *
     * @param countyArray 县级JSON
     */
    private fun parseCounty(countyArray: JSONArray?): ArrayList<EaseCountyEntity> {
        if (countyArray == null || countyArray.length() == 0) {
            return arrayListOf()
        }
        val mCountList = arrayListOf<EaseCountyEntity>()
        for (i in 0 until countyArray.length()) {
            val countyObject = countyArray.optJSONObject(i)
            val code = countyObject.optString("code", "")
            val name = countyObject.optString("name", "")
            val entity = EaseCountyEntity(code, name)
            mCountList.add(entity)
        }
        return mCountList
    }


    @WorkerThread
    private fun loadFromAssets(context: Context): String {
        val stringBuilder = StringBuilder()
        val am = context.assets
        return try {
            val bf = BufferedReader(InputStreamReader(am.open("china_address.json")))
            var line: String?
            while (bf.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            stringBuilder.toString()
        } catch (e: IOException) {
            ""
        }
    }
}