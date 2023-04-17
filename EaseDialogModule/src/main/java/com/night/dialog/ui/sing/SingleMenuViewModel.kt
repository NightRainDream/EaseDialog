package com.night.dialog.ui.sing

import androidx.lifecycle.MutableLiveData
import com.night.dialog.base.EaseBaseViewModel

class SingleMenuViewModel : EaseBaseViewModel() {
    /**
     * 单选菜单数据
     */
    val mMenuList = MutableLiveData<MutableList<String>>()

    /**
     * 默认选中菜单
     */
    val mSelectIndex = MutableLiveData<Int>()


    /**
     * 设置菜单数据
     *
     * @param menu 菜单数据
     */
    fun setMenuList(menu: MutableList<String>) {
        mMenuList.value = menu
    }


    /**
     * 设置选中位置
     *
     * @param index 选中位置
     */
    fun setSelectIndex(index: Int) {
        if ((mMenuList.value?.size ?: 0) <= index) {
            return
        }
        mSelectIndex.value = index
    }
}