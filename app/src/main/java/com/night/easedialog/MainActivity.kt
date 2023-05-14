package com.night.easedialog

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.night.dialog.DialogTools
import com.night.dialog.callback.*
import com.night.dialog.entity.EaseDateTimeEntity
import com.night.dialog.entity.EaseLocationEntity
import com.night.dialog.tools.PICKER_ADDRESS_PROVINCE_CITY
import com.night.dialog.tools.PICKER_ALL

class MainActivity : AppCompatActivity() {
    private lateinit var mTipsView: AppCompatTextView
    private lateinit var mWarnView: AppCompatTextView
    private lateinit var mSingleView: AppCompatTextView
    private lateinit var mMultipleView: AppCompatTextView
    private lateinit var mSingleMenuleView: AppCompatTextView
    private lateinit var mDateTimePickerleView: AppCompatTextView
    private lateinit var mAddressPickerleView: AppCompatTextView
    private lateinit var mColorPickerleView: AppCompatTextView
    private lateinit var mLoadingView: AppCompatTextView
    private lateinit var mToastView: AppCompatTextView
    private var mProvince: EaseLocationEntity? = null
    private var mCity: EaseLocationEntity? = null
    private var mCounty: EaseLocationEntity? = null

    private var mStartDateTime = EaseDateTimeEntity.getDefaultMin()
    private var mEndDateTime = EaseDateTimeEntity.getDefaultMax()
    private var mSelectDateTime = EaseDateTimeEntity.getToday()

    private val mSingleList = mutableListOf(
        "仅一次", "星期一到星期五", "法定工作日(智能跳过节假日)", "每天", "自定义"
    )
    private var mSelectSingleIndex = -1

    private val mMultipleList = mutableListOf(
        "周日", "周一", "周二", "周三", "周四", "周五", "周六", "黑色星期一"
    )
    private val mSelectMultipleIndex = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTipsView = findViewById(R.id.btn_tips_dialog)
        mWarnView = findViewById(R.id.btn_warn_dialog)
        mSingleView = findViewById(R.id.btn_single_dialog)
        mMultipleView = findViewById(R.id.btn_multiple_dialog)
        mSingleMenuleView = findViewById(R.id.btn_single_menu)
        mDateTimePickerleView = findViewById(R.id.btn_date_time_picker)
        mAddressPickerleView = findViewById(R.id.btn_address_picker)
        mColorPickerleView = findViewById(R.id.btn_color_picker)
        mLoadingView = findViewById(R.id.btn_loading)
        mToastView = findViewById(R.id.btn_toast)

        //提示性质模态框
        mTipsView.setOnClickListener {
            DialogTools.getDialogBuilder()
                .setTitleText("允许日历联网")
                .setMainText("下载或更新节假日、班休、历法、订阅等信息。")
                .setPositiveTextColor(Color.RED)
                .toTipsDialog(this, object : ITipsModalCallback {
                    override fun onPositive() {
                        DialogTools.getToastBuilder().toToast("点击确定")
                    }

                    override fun onCancel() {
                        DialogTools.getToastBuilder().toToast("点击取消")
                    }
                })
        }
        //警告性质模态框
        mWarnView.setOnClickListener {
            DialogTools.getDialogBuilder()
                .setMainText("确定删除当前联系人?")
                .setPositiveTextColor(Color.RED)
                .setPositiveText("删除")
                .toWarnDialog(this, object : IWarnModalCallback {
                    override fun onPositive() {
                        DialogTools.getToastBuilder().toToast("点击确定")
                    }

                    override fun onCancel() {
                        DialogTools.getToastBuilder().toToast("点击取消")
                    }
                })
        }
        //底部单选菜单
        mSingleView.setOnClickListener {
            DialogTools.getDialogBuilder()
                .setTitleText("重复")
                .toSingleMenu(
                    this,
                    mSingleList,
                    mSelectSingleIndex,
                    object : ISingleMenuCallback {
                        override fun onPositive(title: String, menuIndex: Int) {
                            mSelectSingleIndex = menuIndex
                            DialogTools.getToastBuilder().toToast("选中内容:$title")
                        }

                        override fun onCancel() {
                            DialogTools.getToastBuilder().toToast("点击取消")
                        }
                    })
        }
        //底部多选菜单
        mMultipleView.setOnClickListener {
            DialogTools.getDialogBuilder()
                .setTitleText("重复")
                .toMultipleMenu(this, mMultipleList, mSelectMultipleIndex,
                    object : IMultipleMenuCallback {
                        override fun onPositive(title: List<String>, menuIndex: List<Int>) {
                            mSelectMultipleIndex.clear()
                            mSelectMultipleIndex.addAll(menuIndex)
                            DialogTools.getToastBuilder().toToast("选中内容:${menuIndex.size}个")
                        }

                        override fun onCancel() {
                            DialogTools.getToastBuilder().toToast("点击取消")
                        }
                    })
        }
        //时间日期选择器
        mDateTimePickerleView.setOnClickListener {
            DialogTools.getPickerBuilder()
                .setTitleText("请选择日期和时间")
                .setMinDateTime(mStartDateTime)
                .setMaxDateTime(mEndDateTime)
                .setSelDateTime(mSelectDateTime)
                .toDateTimePicker(this, PICKER_ALL, object : IDateTimeCallback {
                    override fun onPositive(result: EaseDateTimeEntity) {
                        DialogTools.getToastBuilder()
                            .toToast(result.getDateTime())
                    }

                    override fun onCancel() {
                        super.onCancel()
                        DialogTools.getToastBuilder()
                            .toToast("取消")
                    }
                })
        }
        //地址选择器
        mAddressPickerleView.setOnClickListener {
            DialogTools.getPickerBuilder()
                .setTitleText("请选择地址")
                .setSelectProvince(mProvince)
                .setSelectCity(mCity)
                .setSelectCounty(mCounty)
                .toAddressPicker(this, PICKER_ADDRESS_PROVINCE_CITY, object : ILocationCallback {
                    override fun onPositive(
                        province: EaseLocationEntity,
                        city: EaseLocationEntity,
                        county: EaseLocationEntity
                    ) {
                        mProvince = province
                        mCity = city
                        mCounty = county
                        DialogTools.getToastBuilder().toToast(province.name + city.name + county.name)
                    }
                })
        }
        //颜色选择器
        mColorPickerleView.setOnClickListener {
            DialogTools.getPickerBuilder()
                .setTitleText("请选择颜色")
                .toColorPicker(this, object : IColorSelectCallback {
                    override fun onPositive(red: Int, green: Int, blue: Int) {
                        DialogTools.getToastBuilder()
                            .toToast("R:".plus(red).plus("|G:").plus(green).plus("|B:").plus(blue))
                    }
                })
        }
        //加载框
        mLoadingView.setOnClickListener {
            DialogTools.getDialogBuilder()
                .toLoadingDialog(this, "正在加载中...")
        }
        //Toast
        mToastView.setOnClickListener {
            DialogTools.getToastBuilder()
                .toToast("文件删除成功~")
        }
        //单选菜单
        mSingleMenuleView.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                DialogTools.getDialogBuilder()
                    .setTouchCoordinate(event.x, event.y, event.rawX, event.rawY)
                    .toPopMenu(this, v, mutableListOf("删除", "编辑", "复制"), object : IPopMenuCallback {
                        override fun onPositive(title: String, menuIndex: Int) {
                            DialogTools.getToastBuilder().toToast(title)
                        }
                    })
            }
            true
        }
    }
}