package com.night.easedialog

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.night.dialog.DialogTools
import com.night.dialog.callback.IDateTimeSelectCallback
import com.night.dialog.callback.IDialogActionCallback
import com.night.dialog.entity.DateTimeEntity
import com.night.dialog.entity.DateTimePickerEntity
import com.night.dialog.tools.EaseConstantTools

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
            .setLabel(
                mutableListOf(
                    EaseConstantTools.PICKER_LABEL_YEAR,
                    EaseConstantTools.PICKER_LABEL_MONTH,
                    EaseConstantTools.PICKER_LABEL_DAY,
                    EaseConstantTools.PICKER_LABEL_HOUR,
                    EaseConstantTools.PICKER_LABEL_MINUTE,
                    EaseConstantTools.PICKER_LABEL_SECOND
                )
            )
            .toDateTimePicker(this, object : IDateTimeSelectCallback {
                override fun onSelectDate(result: DateTimeEntity) {
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
}