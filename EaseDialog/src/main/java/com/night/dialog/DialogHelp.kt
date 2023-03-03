package com.night.dialog

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.util.TypedValue
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.night.dialog.entity.TextInfoEntity

internal object DialogHelp {
    private var mTitleTextColor: Int = getColor(R.color.colorTitleText)
    private var mMainTextColor: Int = getColor(R.color.colorMainText)
    private var mPositiveTextColor: Int = getColor(R.color.colorPositiveTextColor)
    private var mCancelTextColor: Int = getColor(R.color.colorMainText)
    private var mBackgroundColor: Int = getColor(R.color.colorBackground)
    private var mPressBackgroundColor: Int = getColor(R.color.colorPress)
    private var mRadius: Float = getRadius(4F)

    init {
        val colorArray = intArrayOf(
            R.attr.dialogTitleTextColor,
            R.attr.dialogMainTextColor,
            R.attr.dialogPositiveTextColor,
            R.attr.dialogCancelTextColor,
            R.attr.dialogBackgroundColor,
            R.attr.dialogPressBackgroundColor,
        )
        val colorTyped = EaseDialog.getContext().theme.obtainStyledAttributes(EaseDialog.getDialogStyle(), colorArray)
        mTitleTextColor = colorTyped.getColor(colorArray.indexOf(R.attr.dialogTitleTextColor), mTitleTextColor)
        mMainTextColor = colorTyped.getColor(colorArray.indexOf(R.attr.dialogMainTextColor), mMainTextColor)
        mPositiveTextColor = colorTyped.getColor(colorArray.indexOf(R.attr.dialogPositiveTextColor), mPositiveTextColor)
        mCancelTextColor = colorTyped.getColor(colorArray.indexOf(R.attr.dialogCancelTextColor), mCancelTextColor)
        mBackgroundColor = colorTyped.getColor(colorArray.indexOf(R.attr.dialogBackgroundColor), mBackgroundColor)
        mPressBackgroundColor = colorTyped.getColor(colorArray.indexOf(R.attr.dialogPressBackgroundColor), mPressBackgroundColor)
        colorTyped.recycle()
        val sizeArray = intArrayOf(R.attr.dialogRadius)
        val sizeTyped = EaseDialog.getContext().theme.obtainStyledAttributes(EaseDialog.getDialogStyle(), sizeArray)
        mRadius = sizeTyped.getDimension(sizeArray.indexOf(R.attr.dialogRadius), mRadius)
        sizeTyped.recycle()
    }

    /**
     * 获取BottomDialog背景资源
     */
    fun getBottomDialogBackground(): Drawable {
        return initBackgroundDrawable(mBackgroundColor, mRadius, mRadius, 0F, 0F)
    }

    /**
     * 获取普通Dialog背景资源
     */
    fun getDialogBackground(): Drawable {
        return initBackgroundDrawable(mBackgroundColor, mRadius, mRadius, mRadius, mRadius)
    }

    /**
     * 获取Item按下背景资源
     *
     * @return 按下背景资源
     */
    fun getItemPressBackground(): Drawable {
        //按钮按下圆角角度
        val mItemRadius = getRadius(4F)
        //按钮按下背景资源
        val mPressDrawable = initBackgroundDrawable(mPressBackgroundColor, mItemRadius, mItemRadius, mItemRadius, mItemRadius)
        return initPressBackgroundDrawable(mPressDrawable,null)
    }

    /**
     * 获取按钮背景
     *
     * @return 按钮背景
     */
    fun getButtonPress(): Drawable {
        //按钮按下圆角角度
        val mButtonRadius = getRadius(4F)
        //按钮按下背景资源
        val mPressDrawable = initBackgroundDrawable(mPressBackgroundColor, mButtonRadius, mButtonRadius, mButtonRadius, mButtonRadius)
        val mDefDrawable = ColorDrawable(mBackgroundColor)
        return initPressBackgroundDrawable(mPressDrawable,mDefDrawable)
    }

    /**
     * 通过资源文件获取字符串
     */
    fun getString(@StringRes id: Int): String {
        return EaseDialog.getContext().getString(id)
    }

    /**
     * 获取取消按钮文本
     */
    fun getCancelText(): String {
        return getString(R.string.cancel)
    }

    /**
     * 获取确定按钮文本
     */
    fun getPositiveText(): String {
        return getString(R.string.define)
    }

    /**
     * 获取标题文本信息
     */
    fun getTitleTextInfo(): TextInfoEntity {
        return TextInfoEntity(mTitleTextColor, 18F)
    }

    /**
     * 获取内容文本信息
     */
    fun getContentTextInfo(): TextInfoEntity {
        return TextInfoEntity(mMainTextColor, 15F)
    }

    /**
     * 获取取消按钮文本信息
     */
    fun getCancelTextInfo(): TextInfoEntity {
        return TextInfoEntity(mCancelTextColor, 15F)
    }

    /**
     * 获取取消积极文本信息
     */
    fun getPositiveTextInfo(): TextInfoEntity {
        return TextInfoEntity(mPositiveTextColor, 15F)
    }

    /**
     * 获取颜色
     *
     * @param id ColorResId
     */
    private fun getColor(@ColorRes id: Int): Int {
        return ContextCompat.getColor(EaseDialog.getContext(), id)
    }

    /**
     * 获取圆角
     *
     * @param dp 值
     */
    private fun getRadius(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, EaseDialog.getContext().resources.displayMetrics)
    }

    /**
     * 初始化背景Drawable
     *
     * @param topLeft 左上角圆角
     * @param topRight 右上角圆角
     * @param bottomRight 右下角圆角
     * @param bottomLeft 左下角圆角
     *
     * @return 处理后Drawable
     */
    private fun initBackgroundDrawable(@ColorInt color: Int, topLeft: Float, topRight: Float, bottomRight: Float, bottomLeft: Float): Drawable {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.shape = GradientDrawable.RECTANGLE
        gradientDrawable.setColor(color)
        gradientDrawable.cornerRadii = floatArrayOf(topLeft, topLeft, topRight, topRight, bottomRight, bottomRight, bottomLeft, bottomLeft)
        return gradientDrawable
    }

    /**
     * 获取按下背景资源
     *
     * @return Item按下背景
     */
    private fun initPressBackgroundDrawable(drawable: Drawable,def: Drawable?): Drawable {
        val state = StateListDrawable()
        val pressed = android.R.attr.state_pressed
        state.addState(intArrayOf(-pressed), def)
        state.addState(intArrayOf(pressed), drawable)
        return state
    }
}