package com.night.dialog.base

import com.night.dialog.R
import com.night.dialog.entity.TextInfoEntity
import com.night.dialog.tools.DialogHelp

open class BaseDialogBuilder {
    /**
     * 默认标题文字详情
     */
    protected var mTitleTextInfo = TextInfoEntity(20, -1, isBold = true)

    /**
     * 默认主文字详情
     */
    protected var mMainTextInfo = TextInfoEntity(15,-1)

    /**
     * 默认取消按钮文字详情
     */
    protected var mCancelTextInfo = TextInfoEntity(
        16,
        -1,
        DialogHelp.getString(R.string.cancel)
    )

    /**
     * 默认确定按钮文字详情
     */
    protected var mPositiveTextInfo = TextInfoEntity(
        16,
        -1,
        DialogHelp.getString(R.string.define)
    )



//    protected fun copyParams(easeFragmentDialog: EaseFragmentDialog){
//        easeFragmentDialog.setTitleTextInfo(mTitleTextInfo)
//        easeFragmentDialog.setMainTextInfo(mMainTextInfo)
//        easeFragmentDialog.setCancelTextInfo(mCancelTextInfo)
//        easeFragmentDialog.setPositiveTextInfo(mPositiveTextInfo)
//    }


//    protected var isCancel = false
//
//    /**
//     * 默认标题文字详情
//     */
//    protected var mTitleTextInfo = TextInfoEntity(20, DialogHelp.getColor(R.color.colorTitleText), isBold = true)
//
//    /**
//     * 默认主文字详情
//     */
//    protected var mMainTextInfo = TextInfoEntity(15, DialogHelp.getColor(R.color.colorMainText))
//
//    /**
//     * 默认取消按钮文字详情
//     */
//    protected var mCancelTextInfo = TextInfoEntity(
//        16,
//        DialogHelp.getColor(R.color.colorButtonTextColor),
//        DialogHelp.getString(R.string.cancel)
//    )
//
//    /**
//     * 默认确定按钮文字详情
//     */
//    protected var mPositiveTextInfo = TextInfoEntity(
//        16,
//        DialogHelp.getColor(R.color.colorButtonTextColor),
//        DialogHelp.getString(R.string.define)
//    )
//
//    /**
//     * 默认样式
//     */
//    protected var mStyle = if (DialogHelp.isLandscape()) R.style.CustomDialog else R.style.CustomBottomDialog
//
//    /**
//     * 默认弹出位置
//     */
//    protected var mGravity = if (DialogHelp.isLandscape()) Gravity.CENTER else Gravity.BOTTOM
//    protected var mCancelText: String = ""
//    protected var mCancelTextColor: Int = -1
//    protected var mPositionText: String = ""
//    protected var mPositionTextColor: Int = -1
//    protected var mTitleText: String = ""
//    protected var mTitleTextColor: Int = -1
//    protected var mMainText: String = ""
//    protected var mMainTextColor: Int = -1
//
//    /**
//     * 设置对话框标题参数
//     *
//     * @param view 标题View
//     */
//    fun setTitleParameter(view: TextView?) {
//        if (view == null) {
//            return
//        }
//        if (!TextUtils.isEmpty(mTitleText)) {
//            mTitleTextInfo.text = mTitleText
//        }
//        if (mTitleTextColor != -1) {
//            mTitleTextInfo.textColor = mTitleTextColor
//        }
//        DialogHelp.setTextViewInfo(view, mTitleTextInfo)
//    }
//
//    /**
//     * 设置对话框内容参数
//     *
//     * @param view 内容View
//     */
//    fun setMainParameter(view: TextView?) {
//        if (view == null) {
//            return
//        }
//        if (!TextUtils.isEmpty(mMainText)) {
//            mMainTextInfo.text = mMainText
//        }
//        if (mMainTextColor != -1) {
//            mMainTextInfo.textColor = mMainTextColor
//        }
//        DialogHelp.setTextViewInfo(view, mMainTextInfo)
//    }
//
//    /**
//     * 设置对话框取消按钮参数
//     *
//     * @param view 取消按钮View
//     */
//    fun setCancelParameter(view: TextView?) {
//        if (view == null) {
//            return
//        }
//        if (!TextUtils.isEmpty(mCancelText)) {
//            mCancelTextInfo.text = mCancelText
//        }
//        if (mCancelTextColor != -1) {
//            mCancelTextInfo.textColor = mCancelTextColor
//        }
//        DialogHelp.setTextViewInfo(view, mCancelTextInfo)
//    }
//
//    /**
//     * 设置对话框取确认钮参数
//     *
//     * @param view 确认按钮View
//     */
//    fun setPositiveParameter(view: TextView?) {
//        if (view == null) {
//            return
//        }
//        if (!TextUtils.isEmpty(mPositionText)) {
//            mPositiveTextInfo.text = mPositionText
//        }
//        if (mPositionTextColor != -1) {
//            mPositiveTextInfo.textColor = mPositionTextColor
//        }
//        DialogHelp.setTextViewInfo(view, mPositiveTextInfo)
//    }
//
//    protected fun getMaxMenuSize(menuHeight: Float): Int {
//        val mItemHeight = TypedValue.applyDimension(
//            TypedValue.COMPLEX_UNIT_DIP,
//            49F,
//            EaseDialog.getContext().resources.displayMetrics
//        )
//        return (menuHeight / mItemHeight).toInt()
//    }
}