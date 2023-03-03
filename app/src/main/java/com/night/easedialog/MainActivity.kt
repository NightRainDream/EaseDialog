package com.night.easedialog

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.night.dialog.DialogTools
import com.night.dialog.callback.IDialogActionCallback
import com.night.dialog.entity.MenuInfoEntity
import com.night.dialog.entity.TextInfoEntity

class MainActivity : AppCompatActivity() {
    private val mMenuList = mutableListOf<MenuInfoEntity>(
        MenuInfoEntity(R.drawable.baseline_edit_24, "编辑卡片"),
        MenuInfoEntity(R.drawable.baseline_content_copy_24, "复制卡号"),
        MenuInfoEntity(R.drawable.baseline_delete_24, "删除卡片")
    )

    private val mSingleList = mutableListOf(
        "三合一模式", "四合一模式", "八合一模式", "多合一模式"
    )
    private var mSelectSingleIndex = -1

    private val mMultipleList = mutableListOf(
        "小写字母", "大写字母", "阿拉伯数字", "英文符号"
    )

    private val mSelectMultipleIndex = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onTip(view: View) {
        DialogTools.Builder()
            .setCancelText("")
            .setPositiveText("知道了")
            .buildTipDialog(this, "温馨提示", "3天后是您的生日，祝您生日快乐~", object : IDialogActionCallback {
                override fun onPositive(content: String, index: MutableList<Int>) {
                    Toast.makeText(this@MainActivity, "知道了", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun onWarn(view: View) {
        DialogTools.Builder()
            .setPositiveTextInfo(TextInfoEntity(Color.RED, 15F))
            .setPositiveText("删除")
            .buildWarnDialog(this, "确定删除当前记录？", object : IDialogActionCallback {
                override fun onPositive(content: String, index: MutableList<Int>) {
                    Toast.makeText(this@MainActivity, "删除", Toast.LENGTH_SHORT).show()
                }

                override fun onCancel() {
                    Toast.makeText(this@MainActivity, "取消", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun onBottomMenu(view: View) {
        DialogTools.Builder()
            .buildMenuDialog(this, mMenuList, object : IDialogActionCallback {
                override fun onPositive(content: String, index: MutableList<Int>) {
                    Toast.makeText(this@MainActivity, content, Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun onBottomSingle(view: View) {
        DialogTools.Builder()
            .buildSingleDialog(this, "请选择游戏模式", mSingleList, mSelectSingleIndex, object : IDialogActionCallback {
                override fun onPositive(content: String, index: MutableList<Int>) {
                    mSelectSingleIndex = index.first()
                    Toast.makeText(this@MainActivity, content, Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun onBottomMultiple(view: View) {
        DialogTools.Builder()
            .buildMultipleDialog(this, "请选择合成元素", mMultipleList, mSelectMultipleIndex, object : IDialogActionCallback {
                override fun onPositive(content: String, index: MutableList<Int>) {
                    mSelectMultipleIndex.clear()
                    mSelectMultipleIndex.addAll(index)
                    Toast.makeText(this@MainActivity, "已选择:" + index.size, Toast.LENGTH_SHORT).show()
                }
            })
    }
}