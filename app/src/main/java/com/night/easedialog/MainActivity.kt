package com.night.easedialog

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.night.dialog.DialogTools
import com.night.dialog.ToastBuilder
import com.night.dialog.callback.IDateTimeSelectCallback
import com.night.dialog.callback.IDialogActionCallback
import com.night.dialog.entity.DateEntity
import com.night.dialog.entity.DateTimeEntity
import com.night.dialog.entity.TextInfoEntity
import com.night.dialog.entity.TimeEntity

class MainActivity : AppCompatActivity() {

    private val mSingleList = mutableListOf(
        "仅一次", "星期一到星期五", "法定工作日(智能跳过节假日)", "每天", "自定义"
    )
    private var mSelectSingleIndex = -1

    private val mMultipleList = mutableListOf(
        "周日", "周一", "周二", "周三", "周四", "周五", "周六"
    )

    private val mSelectMultipleIndex = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.tv_pop_menu).setOnTouchListener { v, event ->
            if(event.action == MotionEvent.ACTION_UP){
                DialogTools.getDialogBuilder()
                    .setTouchCoordinate(event.x,event.y,event.rawX,event.rawY)
                    .toPopMenu(this,v, mutableListOf("删除","编辑","复制"), object : IDialogActionCallback {
                        override fun onPositive(content: String, index: MutableList<Int>) {
                            DialogTools.getToastBuilder().toToast(content)
                        }

                    })
            }
            true }
    }

    fun onTipDialog(view: View) {
        DialogTools.getDialogBuilder()
            .toTipsDialog(this, "允许日历联网", "下载或更新节假日、班休、历法、订阅等信息。")
    }

    fun onTipWarnDialog(view: View) {
        DialogTools.getDialogBuilder()
            .toWarnDialog(this, "确定删除当前联系人?")
    }

    fun onMenuDialog(view: View) {
        DialogTools.getDialogBuilder()
            .setCancelable(true)
            .toLoadingDialog(this)
    }

    fun onTipSingleDialog(view: View) {
        DialogTools.getDialogBuilder()
            .toSingleMenu(
                this,
                "重复",
                mSingleList,
                mSelectSingleIndex,
                object : IDialogActionCallback {
                    override fun onPositive(content: String, index: MutableList<Int>) {
                        mSelectSingleIndex = index.first()
                        Toast.makeText(this@MainActivity, content, Toast.LENGTH_SHORT).show()
                    }
                })
    }

    fun onMultipleDialog(view: View) {
        DialogTools.getDialogBuilder()
            .toMultipleMenu(this, "重复", mMultipleList, mSelectMultipleIndex,
                object : IDialogActionCallback {
                    override fun onPositive(content: String, index: MutableList<Int>) {
                        mSelectMultipleIndex.clear()
                        mSelectMultipleIndex.addAll(index)
                    }
                })
    }

    fun closeDialog(view: View) {
        DialogTools.dismissDialog()
    }

    fun onDatePicker(view: View) {
        DialogTools.getPickerBuilder()
            .toDatePicker(this, "请选择日期", object : IDateTimeSelectCallback {
                override fun onSelectDate(result: DateTimeEntity) {
                    Toast.makeText(this@MainActivity, result.getDate(), Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun onTimePicker(view: View) {
        DialogTools.getPickerBuilder()
            .toTimePicker(this, "请选择时间", object : IDateTimeSelectCallback {
                override fun onSelectDate(result: DateTimeEntity) {
                    Toast.makeText(this@MainActivity, result.getTime(), Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun onToast(view: View) {
        DialogTools.getToastBuilder()
            .toToast("文件删除成功~")
    }
}