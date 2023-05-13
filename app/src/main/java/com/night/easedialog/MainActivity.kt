package com.night.easedialog

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.night.dialog.DialogTools
import com.night.dialog.callback.IColorSelectCallback
import com.night.dialog.callback.IDateTimeCallback
import com.night.dialog.callback.IDialogActionCallback
import com.night.dialog.callback.ILocationCallback
import com.night.dialog.entity.EaseDateTimeEntity
import com.night.dialog.entity.EaseLocationEntity
import com.night.dialog.tools.PICKER_ADDRESS_PROVINCE_CITY
import com.night.dialog.tools.PICKER_ALL

class MainActivity : AppCompatActivity() {

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
        findViewById<View>(R.id.tv_pop_menu).setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                DialogTools.getDialogBuilder()
                    .setTouchCoordinate(event.x, event.y, event.rawX, event.rawY)
                    .toPopMenu(this, v, mutableListOf("删除", "编辑", "复制"), object : IDialogActionCallback {
                        override fun onPositive(content: String, index: MutableList<Int>) {
                            DialogTools.getToastBuilder().toToast(content)
                        }
                    })
            }
            true
        }
    }

    fun onTipDialog(view: View) {
        DialogTools.getDialogBuilder()
            .setTitleText("允许日历联网")
            .setMainText("下载或更新节假日、班休、历法、订阅等信息。")
            .setPositiveTextColor(Color.RED)
            .toTipsDialog(this, object : IDialogActionCallback {
                override fun onPositive(content: String, index: MutableList<Int>) {
                    DialogTools.getToastBuilder()
                        .toToast("确定")
                }

                override fun onCancel() {
                    DialogTools.getToastBuilder()
                        .toToast("取消")
                }
            })
    }

    fun onTipWarnDialog(view: View) {
        DialogTools.getDialogBuilder()
            .setMainText("确定删除当前联系人确定删除当前联系人确定删除当前联系人?")
            .setPositiveTextColor(Color.RED)
            .setPositiveText("删除")
            .toWarnDialog(this, object : IDialogActionCallback {
                override fun onPositive(content: String, index: MutableList<Int>) {
                    DialogTools.getToastBuilder()
                        .toToast("确定")
                }

                override fun onCancel() {
                    DialogTools.getToastBuilder()
                        .toToast("取消")
                }
            })
    }

    fun onMenuDialog(view: View) {
        DialogTools.getDialogBuilder()
            .toLoadingDialog(this)
    }

    fun onTipSingleDialog(view: View) {
        DialogTools.getDialogBuilder()
            .setTitleText("重复")
            .toSingleMenu(
                this,
                mSingleList,
                mSelectSingleIndex,
                object : IDialogActionCallback {
                    override fun onPositive(content: String, index: MutableList<Int>) {
                        mSelectSingleIndex = index.first()
                        DialogTools.getToastBuilder()
                            .toToast(content)
                    }

                    override fun onCancel() {
                        super.onCancel()
                        DialogTools.getToastBuilder()
                            .toToast("取消")
                    }
                })
    }

    fun onMultipleDialog(view: View) {
        DialogTools.getDialogBuilder()
            .setTitleText("重复")
            .toMultipleMenu(this, mMultipleList, mSelectMultipleIndex,
                object : IDialogActionCallback {
                    override fun onPositive(content: String, index: MutableList<Int>) {
                        mSelectMultipleIndex.clear()
                        mSelectMultipleIndex.addAll(index)
                        DialogTools.getToastBuilder()
                            .toToast("确定==>" + index.size)
                    }

                    override fun onCancel() {
                        super.onCancel()
                        DialogTools.getToastBuilder()
                            .toToast("取消")
                    }
                })
    }

    fun onDatePicker(view: View) {
        DialogTools.getPickerBuilder()
            .setTitleText("请选择日期和时间")
            .toDateTimePicker(this, PICKER_ALL, object : IDateTimeCallback {
                override fun onSelectDate(result: EaseDateTimeEntity) {
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

    fun onToast(view: View) {
        DialogTools.getToastBuilder()
            .toToast("文件删除成功~")
    }

    private var mProvince: EaseLocationEntity? = null
    private var mCity: EaseLocationEntity? = null
    private var mCounty: EaseLocationEntity? = null
    fun onAddressPicker(view: View) {
        DialogTools.getPickerBuilder()
            .setTitleText("请选择地址")
            .setSelectProvince(mProvince)
            .setSelectCity(mCity)
            .setSelectCounty(mCounty)
            .toAddressPicker(this, PICKER_ADDRESS_PROVINCE_CITY, object : ILocationCallback {
                override fun onAddressSelected(
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

    fun onColorPicker(view: View) {
        DialogTools.getPickerBuilder()
            .setTitleText("请选择颜色")
            .toColorPicker(this, object : IColorSelectCallback {
                override fun onSelected(red: Int, green: Int, blue: Int) {
                    DialogTools.getToastBuilder().toToast("R:".plus(red).plus("|G:").plus(green).plus("|B:").plus(blue))
                }
            })
    }
}