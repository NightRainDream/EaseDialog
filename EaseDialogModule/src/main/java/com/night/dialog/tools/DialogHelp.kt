package com.night.dialog.tools

import android.content.res.Configuration
import android.content.res.Resources
import android.text.TextUtils
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.Px
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.night.dialog.EaseDialog
import com.night.dialog.entity.TextInfoEntity

internal object DialogHelp {


    /**
     * 设置View属性
     *
     * @param view TextView
     * @param info 属性实体
     */
    fun setTextViewInfo(view: TextView?, info: TextInfoEntity?) {
        if (view == null || info == null) {
            return
        }
        view.paint.isFakeBoldText = info.isBold
        if (!TextUtils.isEmpty(info.text)) {
            view.text = info.text
        }
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, info.mTextSize)
        if (info.textColor != -1) {
            view.setTextColor(info.textColor)
        }
    }

    /**
     * 是否为横屏
     * @return 是否为横屏
     */
    fun isLandscape(): Boolean {
        val configuration = EaseDialog.getContext().resources.configuration.orientation
        return Configuration.ORIENTATION_LANDSCAPE == configuration
    }

    /**
     * 获取资源文件下内容
     * @param id 资源文件ID
     * @return 对应内容
     */
    fun getString(@StringRes id: Int): String {
        return EaseDialog.getContext().getString(id)
    }

    /**
     * 获取资源文件下内容
     * @param id 资源文件ID
     * @return 对应内容
     */
    @ColorInt
    fun getColor(@ColorRes id: Int): Int {
        return ContextCompat.getColor(EaseDialog.getContext(), id)
    }

    /**
     * dp转px
     *
     * @param dp dp值
     * @return px值
     */
    @Px
    fun dpToPx(dp: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            EaseDialog.getContext().resources.displayMetrics
        ).toInt()
    }

    /**
     * sp转px
     *
     * @param dp sp值
     * @return px值
     */
    @Px
    fun spToPx(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            dp,
            EaseDialog.getContext().resources.displayMetrics
        )
    }

    fun setDialogMaxSize(view: View?) {
        if (view == null) {
            return
        }
        //制定测量规则 参数表示size + mode
        val width = View.MeasureSpec.makeMeasureSpec(
            0,
            View.MeasureSpec.UNSPECIFIED
        )
        val height = View.MeasureSpec.makeMeasureSpec(
            0,
            View.MeasureSpec.UNSPECIFIED
        )
        //调用measure方法之后就可以获取宽高
        view.measure(width, height)

        //Dialog最大高度
        val mMaxHeight = if (isLandscape()) {
            Resources.getSystem().displayMetrics.heightPixels * HEIGHT_RATIO_LANDSCAPE
        } else {
            Resources.getSystem().displayMetrics.heightPixels * HEIGHT_RATIO_PORTRAIT
        }
        //内容高度
        val mContentHeight = view.measuredHeight
        if (mContentHeight > mMaxHeight) {
            val mParams = view.layoutParams
            mParams.height = mMaxHeight.toInt()
            view.layoutParams = mParams
        }
    }
}