package com.night.dialog.tools

import android.content.res.Configuration
import android.content.res.Resources
import android.text.TextUtils
import android.util.TypedValue
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.night.dialog.EaseDialog
import com.night.dialog.R
import com.night.dialog.entity.TextInfoEntity

internal object DialogHelp {

    fun setTextViewInfo(view: TextView?,info: TextInfoEntity?){
        if(view == null || info == null){
            return
        }
        view.paint.isFakeBoldText = info.isBold
        if(!TextUtils.isEmpty(info.text)){
            view.text =info.text
        }
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX,info.mTextSize)
        view.setTextColor(info.textColor)
    }

    /**
     * 是否为横屏
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

    @ColorInt
    fun getColor(@ColorRes id: Int): Int {
        return ContextCompat.getColor(EaseDialog.getContext(), id)
    }

    fun dpToPx(dp: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, EaseDialog.getContext().resources.displayMetrics).toInt()
    }

    fun spToPx(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp, EaseDialog.getContext().resources.displayMetrics)
    }

    /**
     * 获取Menu最大高度
     */
    fun getMaxMenuHeight(): Float {
        val mMaxHeight = if (isLandscape()) {
            Resources.getSystem().displayMetrics.heightPixels * 0.8F
        } else {
            Resources.getSystem().displayMetrics.heightPixels * 0.7F
        }
        val mDecorate = EaseDialog.getContext().resources.getDimension(R.dimen.dp_120)
        return mMaxHeight - mDecorate
    }
}