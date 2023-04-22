package com.night.dialog.ui.sing

import com.night.dialog.base.EaseViewModel
import com.night.dialog.entity.MenuEntity

class SingleMenuViewModel : EaseViewModel() {
    /**
     * 单选菜单数据
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
     * 获取单选菜单数据
     */
    fun getMenuList(): MutableList<MenuEntity> {
        return mMenuList
    }
}