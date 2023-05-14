package com.night.dialog.base

interface EaseBaseCallback {
    /**
     * 点击去掉
     */
    fun onCancel() {}

    /**
     * 对话框关闭
     * <p>
     *    点击取消、确定、外部使DialogDismiss，都会回调
     * </p>
     */
    fun onDismiss() {}
}