package com.night.dialog.widget

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.Px
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.github.gzuliyujiang.wheelview.contract.OnWheelChangedListener
import com.github.gzuliyujiang.wheelview.widget.WheelView
import com.night.dialog.R
import com.night.dialog.callback.EaseILoadLocationCallback
import com.night.dialog.callback.ILocationChangeListener
import com.night.dialog.entity.EaseCityEntity
import com.night.dialog.entity.EaseCountyEntity
import com.night.dialog.entity.EaseLocationEntity
import com.night.dialog.entity.EaseProvinceEntity
import com.night.dialog.tools.*


class EaseLocationPickerView(context: Context, attrs: AttributeSet? = null) : LinearLayoutCompat(context, attrs, 0) {
    private var mProvinceView: WheelView
    private var mCityView: WheelView
    private var mCountyView: WheelView
    private var mDefTextColor = Color.GRAY
    private var mSelTextColor = Color.BLACK
    private var mDefTextSize = 14F
    private var mSelTextSize = 15F
    private var mIndicatorColor = Color.GRAY
    private var mAddressData: ArrayList<EaseProvinceEntity>? = null

    private val mProvinceData = mutableListOf<String>()
    private var mProvinceEntity = EaseLocationEntity("北京市", "110000")

    private val mCityData = mutableListOf<String>()
    private var mCityEntity = EaseLocationEntity("北京市", "110000")

    private val mCountyData = mutableListOf<String>()
    private var mCountyEntity = EaseLocationEntity("朝阳区", "110105")
    private var mListener: ILocationChangeListener? = null


    init {
        LayoutInflater.from(context).inflate(R.layout.ease_widget_address, this, true)
        mProvinceView = this.findViewById(R.id.id_province)
        mCityView = this.findViewById(R.id.id_city)
        mCountyView = this.findViewById(R.id.id_county)
        //获取自定义属性
        val arrayType = context.obtainStyledAttributes(attrs, R.styleable.EaseAddressPickerView)
        mDefTextColor = arrayType.getColor(
            R.styleable.EaseAddressPickerView_addressDefTextColor,
            getColor(context, R.color.EaseColorMainText)
        )
        mSelTextColor = arrayType.getColor(
            R.styleable.EaseAddressPickerView_addressSelTextColor,
            getColor(context, R.color.EaseColorButtonTextColor)
        )
        mIndicatorColor = arrayType.getColor(
            R.styleable.EaseAddressPickerView_addressIndicatorColor,
            getColor(context, R.color.EaseColorMainText)
        )
        mDefTextSize =
            arrayType.getDimension(
                R.styleable.EaseAddressPickerView_addressDefTextSize,
                dpToPx(context, 14F)
            )
        mSelTextSize =
            arrayType.getDimension(
                R.styleable.EaseAddressPickerView_addressSelTextSize,
                dpToPx(context, 15F)
            )
        arrayType.recycle()
        //初始化自定义属性
        mProvinceView.textColor = mDefTextColor
        mProvinceView.selectedTextColor = mSelTextColor
        mProvinceView.textSize = mDefTextSize
        mProvinceView.selectedTextSize = mSelTextSize
        mProvinceView.indicatorColor = mIndicatorColor

        mCityView.textColor = mDefTextColor
        mCityView.selectedTextColor = mSelTextColor
        mCityView.textSize = mDefTextSize
        mCityView.selectedTextSize = mSelTextSize
        mCityView.indicatorColor = mIndicatorColor

        mCountyView.textColor = mDefTextColor
        mCountyView.selectedTextColor = mSelTextColor
        mCountyView.textSize = mDefTextSize
        mCountyView.selectedTextSize = mSelTextSize
        mCountyView.indicatorColor = mIndicatorColor
        //初始化监听
        initListener()
    }

    /**
     * 设置模式
     *
     * @param mode Mode
     */
    fun setMode(@AddressMode mode: Int) {
        when (mode) {
            PICKER_ADDRESS_PROVINCE_CITY -> {
                mProvinceView.isVisible = true
                mCityView.isVisible = true
            }
            PICKER_ADDRESS_CITY_COUNTY -> {
                mCityView.isVisible = true
                mCountyView.isVisible = true
            }
            PICKER_ADDRESS_ALL -> {
                mProvinceView.isVisible = true
                mCityView.isVisible = true
                mCountyView.isVisible = true
            }
        }
    }

    /**
     * 设置默认数据
     *
     * @param province 省
     * @param city 市
     * @param county 县
     */
    fun setDefaultData(province: EaseLocationEntity, city: EaseLocationEntity, county: EaseLocationEntity) {
        this.mProvinceEntity = province
        this.mCityEntity = city
        this.mCountyEntity = county
        //初始化数据
        initAddressData(context)
    }

    fun setOnLocalChangeListener(listener: ILocationChangeListener) {
        this.mListener = listener
    }

    private fun initListener() {
        mProvinceView.setOnWheelChangedListener(object : OnWheelChangedListener {
            override fun onWheelScrolled(view: WheelView?, offset: Int) {

            }

            override fun onWheelSelected(view: WheelView?, position: Int) {
                val mSelectProvince = mProvinceData[position]
                setProvinceEntity(mSelectProvince)
                val mCityList = getCityList(mSelectProvince)
                val mCityFirst = mCityList.first()
                initCityData(mCityList, mCityFirst.name)
                val mCountyList = getCountyList(mSelectProvince, mCityFirst.name)
                val mCountyFirst = mCountyList.first()
                initCountyData(mCountyList, mCountyFirst.name)
                mListener?.onLocationChange(mProvinceEntity, mCityEntity, mCountyEntity)
            }

            override fun onWheelScrollStateChanged(view: WheelView?, state: Int) {

            }

            override fun onWheelLoopFinished(view: WheelView?) {

            }
        })
        mCityView.setOnWheelChangedListener(object : OnWheelChangedListener {
            override fun onWheelScrolled(view: WheelView?, offset: Int) {

            }

            override fun onWheelSelected(view: WheelView?, position: Int) {
                val mSelectCity = mCityData[position]
                setCityEntity(mSelectCity)
                val mCountyList = getCountyList(mProvinceEntity.name, mSelectCity)
                val mCountyFirst = mCountyList.first()
                initCountyData(mCountyList, mCountyFirst.name)
                mListener?.onLocationChange(mProvinceEntity, mCityEntity, mCountyEntity)
            }

            override fun onWheelScrollStateChanged(view: WheelView?, state: Int) {

            }

            override fun onWheelLoopFinished(view: WheelView?) {

            }
        })
        mCountyView.setOnWheelChangedListener(object : OnWheelChangedListener {
            override fun onWheelScrolled(view: WheelView?, offset: Int) {

            }

            override fun onWheelSelected(view: WheelView?, position: Int) {
                val mSelectCounty = mCountyData[position]
                setCountyEntity(mSelectCounty)
                mListener?.onLocationChange(mProvinceEntity, mCityEntity, mCountyEntity)
            }

            override fun onWheelScrollStateChanged(view: WheelView?, state: Int) {

            }

            override fun onWheelLoopFinished(view: WheelView?) {

            }
        })
    }

    private fun initAddressData(context: Context) {
        EaseAddressPickerHelp.getAddressData(context, object : EaseILoadLocationCallback {
            override fun onLoadAddress(address: ArrayList<EaseProvinceEntity>) {
                this@EaseLocationPickerView.mAddressData = address
                val mDefaultProvince = mProvinceEntity.name
                val mDefaultCity = mCityEntity.name
                val mDefaultCounty = mCountyEntity.name
                initProvinceData(mAddressData!!, mDefaultProvince)
                initCityData(getCityList(mDefaultProvince), mDefaultCity)
                initCountyData(getCountyList(mDefaultProvince, mDefaultCity), mDefaultCounty)
                mListener?.onLocationChange(mProvinceEntity, mCityEntity, mCountyEntity)
            }
        })
    }


    private fun initProvinceData(provinces: List<EaseProvinceEntity>, defaultProvince: String) {
        if (mProvinceData.isNotEmpty()) {
            mProvinceData.clear()
        }
        for (entity in provinces) {
            val mTitle = entity.name ?: ""
            mProvinceData.add(mTitle)
            //有默认选中的数据
            if (mTitle == defaultProvince) {
                mProvinceEntity = EaseLocationEntity(mTitle, entity.code)
                val mCityFirst = entity.city.first()
                mCityEntity = EaseLocationEntity(mCityFirst.name ?: "", mCityFirst.code)
                val mCountyFirst = mCityFirst.county.first()
                mCountyEntity = EaseLocationEntity(mCountyFirst.name ?: "", mCountyFirst.code)
            }
        }

        var mPosition = mProvinceData.indexOf(defaultProvince)
        if (mPosition == -1) {
            mPosition = 0
        }
        mProvinceView.setData(mProvinceData, mPosition)
    }

    private fun initCityData(citys: List<EaseCityEntity>, defaultCity: String) {
        if (mCityData.isNotEmpty()) {
            mCityData.clear()
        }
        for (entity in citys) {
            val mTitle = entity.name ?: ""
            mCityData.add(mTitle)
            if (mTitle == defaultCity) {
                mCityEntity = EaseLocationEntity(mTitle, entity.code)
                val mCountyFirst = entity.county.first()
                mCountyEntity = EaseLocationEntity(mCountyFirst.name ?: "", mCountyFirst.code)
            }
        }
        var mPosition = mCityData.indexOf(defaultCity)
        if (mPosition == -1) {
            mPosition = 0
        }
        mCityView.setData(mCityData, mPosition)
    }

    private fun initCountyData(countys: List<EaseCountyEntity>, defaultCounty: String) {
        if (mCountyData.isNotEmpty()) {
            mCountyData.clear()
        }
        for (entity in countys) {
            val mTitle = entity.name ?: ""
            mCountyData.add(mTitle)
            if (mTitle == defaultCounty) {
                mCountyEntity = EaseLocationEntity(mTitle, entity.code)
            }
        }
        var mPosition = mCountyData.indexOf(defaultCounty)
        if (mPosition == -1) {
            mPosition = 0
        }
        mCountyView.setData(mCountyData, mPosition)
    }

    fun setProvinceEntity(name: String) {
        for (entity in mAddressData!!) {
            if (name == entity.name) {
                mProvinceEntity = EaseLocationEntity(entity.name, entity.code)
            }
        }
    }

    fun setCityEntity(name: String) {
        val citys = getCityList(mProvinceEntity.name)
        for (entity in citys) {
            if (name == entity.name) {
                mCityEntity = EaseLocationEntity(entity.name, entity.code)
            }
        }
    }

    fun setCountyEntity(name: String) {
        val countys = getCountyList(mProvinceEntity.name, mCityEntity.name)
        for (entity in countys) {
            if (name == entity.name) {
                mCountyEntity = EaseLocationEntity(entity.name, entity.code)
            }
        }
    }

    /**
     * 获取城市数据
     *
     * @param province 当前省
     */
    private fun getCityList(province: String?): List<EaseCityEntity> {
        if (mAddressData == null) {
            return listOf()
        }
        if (TextUtils.isEmpty(province)) {
            return listOf()
        }
        for (entity in mAddressData!!) {
            if (province == entity.name) {
                return entity.city
            }
        }
        return listOf()
    }

    /**
     * 获取县级数据
     *
     * @param province 当前省
     * @param city 当前县
     */
    private fun getCountyList(province: String?, city: String?): List<EaseCountyEntity> {
        if (mAddressData == null) {
            return listOf()
        }
        if (TextUtils.isEmpty(province)) {
            return listOf()
        }
        if (TextUtils.isEmpty(city)) {
            return listOf()
        }
        for (entity in mAddressData!!) {
            if (province == entity.name) {
                for (cityEntity in entity.city) {
                    if (city == cityEntity.name) {
                        return cityEntity.county
                    }
                }
            }
        }
        return listOf()
    }


    @ColorInt
    private fun getColor(context: Context, @ColorRes id: Int): Int {
        return ContextCompat.getColor(context, id)
    }

    @Px
    private fun dpToPx(context: Context, dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        )
    }

}