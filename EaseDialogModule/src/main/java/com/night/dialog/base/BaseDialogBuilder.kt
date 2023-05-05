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
}