package com.night.dialog.ui.multiple

import com.night.dialog.base.EaseViewModel
import com.night.dialog.entity.MenuEntity

class MultipleMenuViewModel : EaseViewModel() {
    /**
     * 多选菜单数据
     */
    private val mMenuList = mutableListOf<MenuEntity>()

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
     * 获取已选中下标
     */
    fun getSelectPositions(): MutableList<Int> {
        val mList = mutableListOf<Int>()
        for ((index, item) in mMenuList.withIndex()) {
            if (item.isSelect) {
                mList.add(index)
            }
        }
        return mList
    }
}