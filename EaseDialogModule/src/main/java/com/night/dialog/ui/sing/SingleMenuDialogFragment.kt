package com.night.dialog.ui.sing

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.night.dialog.R
import com.night.dialog.adapter.SingleMenuAdapter
import com.night.dialog.base.EaseSafeDialogFragment
import com.night.dialog.entity.MenuEntity
import com.night.dialog.tools.DialogHelp
import com.night.dialog.tools.SmallDividerItem

/**
 * ---------------------------------------------------
 * 说    明: 单选对话框
 * 作    者: Night
 * 时    间: 2023/4/22
 * ---------------------------------------------------
 */
class SingleMenuDialogFragment : EaseSafeDialogFragment<SingleMenuBaseViewModel>() {
    private lateinit var mTitleView: AppCompatTextView
    private lateinit var mContentView: RecyclerView
    private lateinit var mCancelView: AppCompatTextView
    private lateinit var mSingleMenuAdapter: SingleMenuAdapter
    private var mMenuData = mutableListOf<MenuEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.setMenuList(mMenuData)
    }

    override fun initLayout(): Int {
        return R.layout.ease_layout_dialog_single_menu
    }

    override fun initViewModel(): Class<SingleMenuBaseViewModel> {
        return SingleMenuBaseViewModel::class.java
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mTitleView = view.findViewById(R.id.tv_menu_title)
        mContentView = view.findViewById(R.id.rv_menu_title)
        mCancelView = view.findViewById(R.id.rv_menu_cancel)
    }

    override fun initAdapter(savedInstanceState: Bundle?) {
        mSingleMenuAdapter = SingleMenuAdapter(mViewModel.getMenuList())
        mContentView.layoutManager = LinearLayoutManager(activity)
        mContentView.adapter = mSingleMenuAdapter
        mContentView.addItemDecoration(
            SmallDividerItem(
                DialogHelp.dpToPx(0.5F),
                DialogHelp.getColor(R.color.EaseColorDivider)
            )
        )
        DialogHelp.setDialogMaxSize(view)
    }

    override fun initListener(savedInstanceState: Bundle?) {
        mCancelView.setOnClickListener {
            mViewModel.onCancelEvent()
            dismiss()
        }
        mSingleMenuAdapter.setOnItemClickListener(object : SingleMenuAdapter.OnItemClickListener {
            override fun onItemClick(text: String, menuIndex: Int) {
                mViewModel.onPositiveEvent(text, mutableListOf(menuIndex))
                dismiss()
            }
        })
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.mTitleTextInfo.observe(this) {
            setViewParameter(mTitleView, it)
        }
        mViewModel.mCancelTextInfo.observe(this) {
            setViewParameter(mCancelView, it)
        }
    }

    override fun initGravity(): Int {
        return if (DialogHelp.isLandscape()) Gravity.CENTER else Gravity.BOTTOM
    }

    override fun initAnimations(): Int {
        return if (DialogHelp.isLandscape()) R.style.CenterAnimation else R.style.BottomAnimation
    }

    override fun isCancel(): Boolean {
        return true
    }

    /**
     * @param menu 菜单数据
     * @param def 默认选中位置
     */
    fun setMenuData(menu: MutableList<String>, def: Int) {
        for ((i, title) in menu.withIndex()) {
            val mEntity = MenuEntity(title, i == def)
            mMenuData.add(mEntity)
        }
    }
}