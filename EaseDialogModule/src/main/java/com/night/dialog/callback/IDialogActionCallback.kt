package com.night.dialog.callback

interface IDialogActionCallback {
    /**
     * Dialog点击积极按钮
     */
    fun onPositive(content: String, index: MutableList<Int>)

    /**
     * Dialog点击取消按钮
     */
    fun onCancel() {}

    /**
     * Dialog关闭
     */
    fun onDismiss() {}
}