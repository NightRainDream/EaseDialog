package com.night.dialog.base

import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import com.night.dialog.EaseDialog
import com.night.dialog.R
import com.night.dialog.entity.TextInfoEntity
import com.night.dialog.tools.DialogHelp

open class BaseDialogBuilder {
    protected var isCancel = false
    protected var mTitleTextInfo = DialogHelp.sDefaultTitleTextInfo
    protected var mMainTextInfo = DialogHelp.sDefaultMainTextInfo
    protected var mCancelTextInfo = DialogHelp.sDefaultCancelTextInfo
    protected var mPositiveTextInfo = DialogHelp.sDefaultPositiveTextInfo
    protected var mStyle = if(DialogHelp.isLandscape()) R.style.CustomDialog else R.style.CustomBottomDialog
    protected var mGravity = if(DialogHelp.isLandscape()) Gravity.CENTER else Gravity.BOTTOM


    fun initTextInfo(view: TextView, info: TextInfoEntity, special: String? = null) {
        DialogHelp.setTextViewInfo(view, info)
        if (!TextUtils.isEmpty(special)) {
            view.text = special
        }
    }

    protected fun getMaxMenuSize(menuHeight: Float): Int {
        val mItemHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,49F,EaseDialog.getContext().resources.displayMetrics)
        return (menuHeight / mItemHeight).toInt()
    }
}