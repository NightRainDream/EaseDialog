package com.night.dialog

import android.app.Activity
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.StyleRes
import com.hjq.toast.ToastParams
import com.hjq.toast.Toaster
import com.night.dialog.base.BaseDialog
import com.night.dialog.callback.IBindDialogView
import com.night.dialog.callback.IBindView
import com.night.dialog.tools.ToastHelp
import java.lang.ref.WeakReference

object DialogTools {
    private var mShowDialog: WeakReference<BaseDialog>? = null

    /**
     * 弹出Dialog
     *
     * @param styleId Dialog样式
     * @param gravity 弹出位置
     * @param isCancel 是否可取消
     */
    fun showDialog(
        activity: Activity,
        bindView: IBindDialogView,
        @StyleRes styleId: Int,
        gravity: Int,
        isCancel: Boolean
    ) {
        if (mShowDialog != null && mShowDialog!!.get() != null) {
            mShowDialog!!.get()!!.dismiss()
        }
        val mBaseDialog = BaseDialog(activity, styleId)
        mBaseDialog.setContentView(bindView.mLayoutId)
        mBaseDialog.setGravity(gravity)
        mBaseDialog.setCancelable(isCancel)
        mShowDialog = WeakReference(mBaseDialog)
        bindView.onBind(mBaseDialog)
        mBaseDialog.show()

    }



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

    fun dismissDialog() {
        if (mShowDialog != null && mShowDialog!!.get() != null) {
            mShowDialog!!.get()!!.dismiss()
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