package com.night.dialog

import android.view.Gravity
import android.widget.Toast
import com.hjq.toast.ToastParams
import com.hjq.toast.Toaster
import com.night.dialog.callback.IBindView
import com.night.dialog.tools.ToastHelp

object DialogTools {





    /**
     * 弹出自定义Toast
     * @param msg 提示内容
     *            自定义布局中，提示内容的TextView需要设置ID为"android:id="@android:id/message""
     * @param bindView IBindView [IBindView]
     * @param gravity 弹出位置 [Gravity.BOTTOM]
     * @param duration 弹出时长 [Toast.LENGTH_SHORT]
     * @param xOffset x轴偏移
     * @param yOffset y轴偏移
     * @param delay 延时弹出时间 毫秒
     */
    fun showToast(
        msg: String?,
        bindView: IBindView,
        gravity: Int = Gravity.BOTTOM,
        duration: Int = Toast.LENGTH_SHORT,
        xOffset: Int = 0,
        yOffset: Int = 0,
        delay: Long = 0
    ) {
        val mToastStyle = ToastHelp(gravity, xOffset, yOffset, bindView)
        val mToastParams = ToastParams()
        mToastParams.style = mToastStyle
        mToastParams.text = msg
        if (delay > 0) {
            Toaster.delayedShow(mToastParams, delay)
        } else {
            if (duration == Toast.LENGTH_LONG) {
                Toaster.showLong(mToastParams)
            } else {
                Toaster.show(mToastParams)
            }
        }
    }
    fun dismissToast() {
        Toaster.cancel()
    }

    fun getDialogBuilder(): DialogBuilder {
        return DialogBuilder()
    }

    fun getPickerBuilder(): PickerBuilder {
        return PickerBuilder()
    }

    fun getToastBuilder(): ToastBuilder {
        return ToastBuilder()
    }
}