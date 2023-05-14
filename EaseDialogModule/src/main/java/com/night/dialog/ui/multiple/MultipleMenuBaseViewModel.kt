package com.night.dialog.ui.multiple

import com.night.dialog.base.EaseBaseViewModel
import com.night.dialog.callback.IMultipleMenuCallback
import com.night.dialog.entity.MenuEntity

class MultipleMenuBaseViewModel : EaseBaseViewModel() {
    /**
     * 多选菜单数据
     */
    private val mMenuList = mutableListOf<MenuEntity>()

    /**
     * 回调事件
     */
    private var mCallback: IMultipleMenuCallback? = null


    /**
     * 设置回调事件
     */
    fun setCallback(callback: IMultipleMenuCallback?) {
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
     * 获取多选菜单数据
     */
    fun getMenuList(): MutableList<MenuEntity> {
        return mMenuList
    }

    /**
     * 设置Item状态
     */
    fun setItemState(position: Int): Boolean {
        if (position >= mMenuList.size) {
            return false
        }
        val mState = mMenuList[position].isSelect
        mMenuList[position].isSelect = !mState
        return true
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
    fun onPositiveEvent() {
        val mIndexList = mutableListOf<Int>()
        val mTitleList = mutableListOf<String>()
        for ((i, item) in mMenuList.withIndex()) {
            if (item.isSelect) {
                mIndexList.add(i)
                mTitleList.add(item.title)
            }
        }
        mCallback?.onPositive(mTitleList, mIndexList)
    }


    override fun onCleared() {
        super.onCleared()
        mCallback?.onDismiss()
    }
}