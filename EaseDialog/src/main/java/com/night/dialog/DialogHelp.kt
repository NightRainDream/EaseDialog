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
     * ??????BottomDialog????????????
     */
    fun getBottomDialogBackground(): Drawable {
        return initBackgroundDrawable(mBackgroundColor, mRadius, mRadius, 0F, 0F)
    }

    /**
     * ????????????Dialog????????????
     */
    fun getDialogBackground(): Drawable {
        return initBackgroundDrawable(mBackgroundColor, mRadius, mRadius, mRadius, mRadius)
    }

    /**
     * ??????Item??????????????????
     *
     * @return ??????????????????
     */
    fun getItemPressBackground(): Drawable {
        //????????????????????????
        val mItemRadius = getRadius(4F)
        //????????????????????????
        val mPressDrawable = initBackgroundDrawable(mPressBackgroundColor, mItemRadius, mItemRadius, mItemRadius, mItemRadius)
        return initPressBackgroundDrawable(mPressDrawable,null)
    }

    /**
     * ??????????????????
     *
     * @return ????????????
     */
    fun getButtonPress(): Drawable {
        //????????????????????????
        val mButtonRadius = getRadius(4F)
        //????????????????????????
        val mPressDrawable = initBackgroundDrawable(mPressBackgroundColor, mButtonRadius, mButtonRadius, mButtonRadius, mButtonRadius)
        val mDefDrawable = ColorDrawable(mBackgroundColor)
        return initPressBackgroundDrawable(mPressDrawable,mDefDrawable)
    }

    /**
     * ?????????????????????????????????
     */
    fun getString(@StringRes id: Int): String {
        return EaseDialog.getContext().getString(id)
    }

    /**
     * ????????????????????????
     */
    fun getCancelText(): String {
        return getString(R.string.cancel)
    }

    /**
     * ????????????????????????
     */
    fun getPositiveText(): String {
        return getString(R.string.define)
    }

    /**
     * ????????????????????????
     */
    fun getTitleTextInfo(): TextInfoEntity {
        return TextInfoEntity(mTitleTextColor, 18F)
    }

    /**
     * ????????????????????????
     */
    fun getContentTextInfo(): TextInfoEntity {
        return TextInfoEntity(mMainTextColor, 15F)
    }

    /**
     * ??????????????????????????????
     */
    fun getCancelTextInfo(): TextInfoEntity {
        return TextInfoEntity(mCancelTextColor, 15F)
    }

    /**
     * ??????????????????????????????
     */
    fun getPositiveTextInfo(): TextInfoEntity {
        return TextInfoEntity(mPositiveTextColor, 15F)
    }

    /**
     * ????????????
     *
     * @param id ColorResId
     */
    private fun getColor(@ColorRes id: Int): Int {
        return ContextCompat.getColor(EaseDialog.getContext(), id)
    }

    /**
     * ????????????
     *
     * @param dp ???
     */
    private fun getRadius(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, EaseDialog.getContext().resources.displayMetrics)
    }

    /**
     * ???????????????Drawable
     *
     * @param topLeft ???????????????
     * @param topRight ???????????????
     * @param bottomRight ???????????????
     * @param bottomLeft ???????????????
     *
     * @return ?????????Drawable
     */
    private fun initBackgroundDrawable(@ColorInt color: Int, topLeft: Float, topRight: Float, bottomRight: Float, bottomLeft: Float): Drawable {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.shape = GradientDrawable.RECTANGLE
        gradientDrawable.setColor(color)
        gradientDrawable.cornerRadii = floatArrayOf(topLeft, topLeft, topRight, topRight, bottomRight, bottomRight, bottomLeft, bottomLeft)
        return gradientDrawable
    }

    /**
     * ????????????????????????
     *
     * @return Item????????????
     */
    private fun initPressBackgroundDrawable(drawable: Drawable,def: Drawable?): Drawable {
        val state = StateListDrawable()
        val pressed = android.R.attr.state_pressed
        state.addState(intArrayOf(-pressed), def)
        state.addState(intArrayOf(pressed), drawable)
        return state
    }
}