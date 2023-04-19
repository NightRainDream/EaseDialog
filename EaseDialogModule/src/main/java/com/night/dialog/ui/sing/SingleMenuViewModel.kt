package com.night.dialog.ui.sing

import androidx.lifecycle.MutableLiveData
import com.night.dialog.base.EaseBaseViewModel
import com.night.dialog.entity.MenuEntity

class SingleMenuViewModel : EaseBaseViewModel() {
    /**
     * 单选菜单数据
     */
    private val mMenuList = mutableListOf<MenuEntity>()

    val mNotifyPosition = MutableLiveData<Int>()

    /**
     * 设置菜单数据
     *
     * @param menu 菜单数据
     */
    fun setMenuList(menu: MutableList<String>) {
        for (title in menu) {
            val mEntity = MenuEntity(title, false)
            mMenuList.add(mEntity)
        }
    }

    /**
     * 获取单选菜单数据
     */
    fun getMenuList(): MutableList<MenuEntity> {
        return mMenuList
    }

    /**
     * 设置Item状态
     */
    fun setItemState(position: Int): Boolean {
        if (position >= mMenuList.size || position < 0) {
            return false
        }
        //全部置位
        for ((index, item) in mMenuList.withIndex()) {
            if (item.isSelect) {
                item.isSelect = false
                //通知刷新Adapter
                mNotifyPosition.value = index
                break
            }
        }
        //选中点击
        mMenuList[position].isSelect = true
        //通知刷新Adapter
        mNotifyPosition.value = position
        return true
    }

    /**
     * 获取已选中下标
     */
    fun getSelectPositions(): Int {
        for ((index, item) in mMenuList.withIndex()) {
            if (item.isSelect) {
                return index
            }
        }
        return -1
    }
}