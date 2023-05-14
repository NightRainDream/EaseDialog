package com.night.dialog.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.night.dialog.R
import com.night.dialog.entity.TextInfoEntity
import com.night.dialog.tools.DialogHelp

open class EaseBaseViewModel : ViewModel() {

    /**
     * 默认标题属性
     */
    val mTitleTextInfo = MutableLiveData(
        TextInfoEntity(
            20,
            -1,
            isBold = true
        )
    )

    /**
     * 默认主文字属性
     */
    val mMainTextInfo = MutableLiveData(
        TextInfoEntity(
            15,
            -1
        )
    )

    /**
     * 默认取消按钮属性
     */
    val mCancelTextInfo = MutableLiveData(
        TextInfoEntity(
            16,
            -1,
            DialogHelp.getString(R.string.cancel)
        )
    )

    /**
     * 默认确定按钮属性
     */
    val mPositiveTextInfo =
        MutableLiveData(
            TextInfoEntity(
                16,
                -1,
                DialogHelp.getString(R.string.define)
            )
        )


    /**
     * 设置标题文字属性
     *
     * @param info 标题文字属性
     */
    fun setTitleTextInfo(info: TextInfoEntity?) {
        if (info == null) {
            return
        }
        mTitleTextInfo.value = info
    }

    /**
     * 设置主文字属性
     *
     * @param info 主文字属性
     */
    fun setMainTextInfo(info: TextInfoEntity?) {
        if (info == null) {
            return
        }
        mMainTextInfo.value = info
    }

    /**
     * 设置取消按钮文字属性
     *
     * @param info 消按钮文字属性
     */
    fun setCancelTextInfo(info: TextInfoEntity?) {
        if (info == null) {
            return
        }
        mCancelTextInfo.value = info
    }

    /**
     * 设置确定文字属性
     *
     * @param info 确定文字属性
     */
    fun setPositiveTextInfo(info: TextInfoEntity?) {
        if (info == null) {
            return
        }
        mPositiveTextInfo.value = info
    }

    fun initNumberDisplay(num: Int): String{
        if(num <= 9){
            return "0".plus(num)
        }
        return num.toString()
    }
}