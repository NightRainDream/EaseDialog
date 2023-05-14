package com.night.dialog.ui.single

import com.night.dialog.base.EaseBaseViewModel
import com.night.dialog.callback.ISingleMenuCallback
import com.night.dialog.entity.MenuEntity

class SingleMenuViewModel : EaseBaseViewModel() {
    /**
     * 单选菜单数据
     */
    private val mMenuList = mutableListOf<MenuEntity>()

    /**
     * 回调事件
     */
    private var mCallback: ISingleMenuCallback? = null

    /**
     * 设置回调事件
     */
    fun setCallback(callback: ISingleMenuCallback?) {
        if (callback == null) {
            return
        }
        this.mCallback = callback
    }

    /**
     * 设置菜单数据
     *
     * @param menu 菜单数据
     */
    fun setMenuList(menu: MutableList<MenuEntity>) {
        if (menu.isEmpty()) {
            return
        }
        mMenuList.clear()
        mMenuList.addAll(menu)
    }

    /**
     * 获取单选菜单数据
     */
    fun getMenuList(): MutableList<MenuEntity> {
        return mMenuList
    }

    /**
     * 点击取消按钮事件
     */
    fun onCancelEvent() {
        mCallback?.onCancel()
    }

    /**
     * 点击确定按钮事件
     */
    fun onPositiveEvent(title: String, index: Int) {
        mCallback?.onPositive(title, index)
    }


    override fun onCleared() {
        super.onCleared()
        mCallback?.onDismiss()
    }
}