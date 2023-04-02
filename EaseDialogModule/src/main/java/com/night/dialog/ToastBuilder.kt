package com.night.dialog

import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.night.dialog.callback.IBindView
import com.night.dialog.tools.DialogHelp

class ToastBuilder {
    private var mDelayTime = 0L
    private var mGravity = Gravity.BOTTOM
    private var mXOffset = 0
    private var mYOffset = 0
    private var mDuration = Toast.LENGTH_SHORT

    /**
     * 设置延时弹出时间
     *
     * @param millisecond 延时时间-毫秒
     */
    fun setDelayTime(millisecond: Long): ToastBuilder {
        this.mDelayTime = millisecond
        return this
    }

    /**
     * 设置展示时长
     * @param duration 长度[Toast.LENGTH_SHORT]
     */
    fun setDuration(duration: Int): ToastBuilder {
        this.mDuration = duration
        return this
    }

    /**
     * 设置弹出位置
     *
     * @param gravity Gravity[Gravity]
     */
    fun setGravity(gravity: Int): ToastBuilder {
        this.mGravity = gravity
        return this
    }

    /**
     * 设置X轴偏移像素
     *
     * @param x 偏移像素
     */
    fun setXOffset(x: Int): ToastBuilder {
        this.mXOffset = x
        return this
    }

    /**
     * 设置Y轴偏移像素
     *
     * @param y 偏移像素
     */
    fun setYOffset(y: Int): ToastBuilder {
        this.mYOffset = y
        return this
    }

    /**
     * 弹出普通Toast
     *
     * @param msg 提示内容
     */
    fun toToast(msg: String?) {
        if (msg == null || msg == "") {
            return
        }
        DialogTools.showToast(msg, object : IBindView(R.layout.ease_layout_toast) {
            override fun onBind(bindView: View) {

            }
        }, mGravity, mDuration, mXOffset, getDefaultY(mGravity,mYOffset), mDelayTime)
    }

    private fun getDefaultY(gravity: Int, yOffset: Int): Int {
        if (yOffset > 0) {
            return yOffset
        }
        if (gravity == Gravity.TOP || gravity == Gravity.BOTTOM) {
            val offset = if (DialogHelp.isLandscape()) {
                EaseDialog.getContext().resources.getDimension(R.dimen.dp_68).toInt()
            } else {
                EaseDialog.getContext().resources.getDimension(R.dimen.dp_128).toInt()
            }
            return offset
        }
        return yOffset
    }
}