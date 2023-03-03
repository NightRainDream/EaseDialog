package com.night.dialog.base

import android.app.Activity
import android.text.TextUtils
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.night.dialog.DialogHelp
import com.night.dialog.callback.IDialogActionCallback
import com.night.dialog.entity.MenuInfoEntity
import com.night.dialog.entity.TextInfoEntity

abstract class BaseBuilderDialog {
    private var mTitleTextInfo: TextInfoEntity = DialogHelp.getTitleTextInfo()
    private var mContentTextInfo: TextInfoEntity = DialogHelp.getContentTextInfo()
    private var mCancelTextInfo: TextInfoEntity = DialogHelp.getCancelTextInfo()
    private var mPositiveTextInfo: TextInfoEntity = DialogHelp.getPositiveTextInfo()
    private var mCancelText = DialogHelp.getCancelText()
    private var mPositiveText = DialogHelp.getPositiveText()
    protected var isCancel = false

    /**
     * 设置标题属性
     */
    fun setTitleTextInfo(title: TextInfoEntity): BaseBuilderDialog {
        this.mTitleTextInfo = title
        return this
    }

    /**
     * 设置内容属性
     */
    fun setContentTextInfo(content: TextInfoEntity): BaseBuilderDialog {
        this.mContentTextInfo = content
        return this
    }

    /**
     * 设置取消按钮属性
     */
    fun setCancelTextInfo(cancel: TextInfoEntity): BaseBuilderDialog {
        this.mCancelTextInfo = cancel
        return this
    }

    /**
     * 设置积极按钮属性
     */
    fun setPositiveTextInfo(positive: TextInfoEntity): BaseBuilderDialog {
        this.mPositiveTextInfo = positive
        return this
    }

    /**
     * 设置取消按钮文本
     */
    fun setCancelText(cancel: String): BaseBuilderDialog {
        this.mCancelText = cancel
        return this
    }

    /**
     * 设置积极按钮文本
     */
    fun setPositiveText(positive: String): BaseBuilderDialog {
        this.mPositiveText = positive
        return this
    }

    /**
     * 设置点击外部可关闭
     */
    fun setCancelable(isCancel: Boolean): BaseBuilderDialog {
        this.isCancel = isCancel
        return this
    }

    protected fun initTitleTextView(titleView: AppCompatTextView, title: String) {
        titleView.setTextColor(mTitleTextInfo.textColor)
        titleView.textSize = mTitleTextInfo.textSize
        titleView.text = title
    }

    protected fun initContentTextView(msgView: AppCompatTextView, msg: String) {
        msgView.setTextColor(mContentTextInfo.textColor)
        msgView.textSize = mContentTextInfo.textSize
        msgView.text = msg
    }

    protected fun initCancelTextView(cancelView: AppCompatTextView) {
        if (TextUtils.isEmpty(mCancelText)) {
            cancelView.isInvisible = true
        } else {
            cancelView.isVisible = true
            cancelView.text = mCancelText
            cancelView.setTextColor(mCancelTextInfo.textColor)
            cancelView.textSize = mCancelTextInfo.textSize
            cancelView.background = DialogHelp.getButtonPress()
        }
    }

    protected fun initPositiveTextView(positiveView: TextView) {
        positiveView.text = mPositiveText
        positiveView.setTextColor(mPositiveTextInfo.textColor)
        positiveView.textSize = mPositiveTextInfo.textSize
        positiveView.background = DialogHelp.getButtonPress()
    }

    /**
     * 构建提示性质Dialog
     */
    abstract fun buildTipDialog(activity: Activity, title: String, msg: String, callback: IDialogActionCallback? = null)

    /**
     * 构建警告性质Dialog
     */
    abstract fun buildWarnDialog(activity: Activity, msg: String, callback: IDialogActionCallback? = null)

    /**
     * 构建底部单选Dialog
     */
    abstract fun buildSingleDialog(activity: Activity, title: String, menu: MutableList<String>, defIndex: Int = -1, callback: IDialogActionCallback?=null)

    /**
     * 构建底部多选Dialog
     */
    abstract fun buildMultipleDialog(activity: Activity, title: String, menu: MutableList<String>, defSelect: MutableList<Int>, callback: IDialogActionCallback?=null)

    /**
     * 构建底部选择菜单
     */
    abstract fun buildMenuDialog(activity: Activity,menu: MutableList<MenuInfoEntity>,callback: IDialogActionCallback?)
}