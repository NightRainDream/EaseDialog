package com.night.dialog.callback

import com.night.dialog.base.EaseBaseCallback

/**
 * 提示性质对话框回调
 */
interface ITipsModalCallback : EaseBaseCallback {
    /**
     * 点击确定
     */
    fun onPositive()
}

/**
 * 警告性质对话框回调
 */
interface IWarnModalCallback : EaseBaseCallback {
    /**
     * 点击确定
     */
    fun onPositive()
}

/**
 * 底部单选菜单回调
 */
interface ISingleMenuCallback : EaseBaseCallback {
    /**
     * 点击确定
     *
     * @param title 所选中标题
     * @param menuIndex 所选中下标
     */
    fun onPositive(title: String, menuIndex: Int)
}

/**
 * 底部多选菜单回调
 */
interface IMultipleMenuCallback : EaseBaseCallback {
    /**
     * 点击确定
     *
     * @param title 所选中标题
     * @param menuIndex 所选中下标
     */
    fun onPositive(title: List<String>, menuIndex: List<Int>)
}

/**
 * 多选对话框回调
 */
interface ILoadingCallback {
    /**
     * 对话框关闭
     * <p>
     *    点击取消、确定、外部使DialogDismiss，都会回调
     * </p>
     */
    fun onDismiss() {}
}

/**
 * PopMenu回调
 */
interface IPopMenuCallback {
    /**
     * 点击确定
     *
     * @param title 所选中标题
     * @param menuIndex 所选中下标
     */
    fun onPositive(title: String, menuIndex: Int)

    /**
     * 对话框关闭
     * <p>
     *    点击取消、确定、外部使DialogDismiss，都会回调
     * </p>
     */
    fun onDismiss() {}
}

