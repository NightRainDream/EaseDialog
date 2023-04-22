package com.night.dialog.ui.multiple

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.night.dialog.R
import com.night.dialog.adapter.MultipleMenuAdapter
import com.night.dialog.base.EaseSafeDialogFragment
import com.night.dialog.entity.MenuEntity
import com.night.dialog.tools.DialogHelp
import com.night.dialog.tools.LogcatToos
import com.night.dialog.tools.SmallDividerItem

/**
 * ---------------------------------------------------
 * 说    明: 多选对话框
 * 作    者: Night
 * 时    间: 2023/4/19
 * ---------------------------------------------------
 */
class MultipleMenuDialogFragment : EaseSafeDialogFragment<MultipleMenuViewModel>() {
    private lateinit var mTitleView: AppCompatTextView
    private lateinit var mCancelView: AppCompatTextView
    private lateinit var mPositiveView: AppCompatTextView
    private lateinit var mContentView: RecyclerView
    private lateinit var mMultipleMenuAdapter: MultipleMenuAdapter
    private val mMenuData = mutableListOf<MenuEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.setMenuList(mMenuData)
    }

    override fun initLayout(): Int {
        return R.layout.ease_laoyut_dialog_multiple_menu
    }

    override fun initViewModel(): Class<MultipleMenuViewModel> {
        return MultipleMenuViewModel::class.java
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mTitleView = view.findViewById(R.id.tv_menu_title)
        mContentView = view.findViewById(R.id.rv_menu_title)
        mCancelView = view.findViewById(R.id.tv_multiple_cancel)
        mPositiveView = view.findViewById(R.id.tv_multiple_positive)
    }

    override fun initAdapter(savedInstanceState: Bundle?) {
        mMultipleMenuAdapter = MultipleMenuAdapter(mViewModel.getMenuList())
        mContentView.layoutManager = LinearLayoutManager(activity)
        mContentView.adapter = mMultipleMenuAdapter
        mContentView.addItemDecoration(
            SmallDividerItem(
                DialogHelp.dpToPx(0.5F),
                DialogHelp.getColor(R.color.colorDivider)
            )
        )
        DialogHelp.setDialogMaxSize(view)
    }

    override fun initListener(savedInstanceState: Bundle?) {
        mPositiveView.setOnClickListener {
            dismiss()
            LogcatToos.e(mViewModel.getSelectPositions().size.toString())
            mViewModel.onPositiveEvent("", mViewModel.getSelectPositions())
        }
        mCancelView.setOnClickListener {
            dismiss()
            mViewModel.onCancelEvent()
        }
        mMultipleMenuAdapter.setOnItemClickListener(object : MultipleMenuAdapter.OnItemClickListener {
            override fun onItemClick(text: String, menuIndex: Int) {
                if (mViewModel.setItemState(menuIndex)) {
                    mMultipleMenuAdapter.notifyItemChanged(menuIndex, "multiple_state")
                }
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
        mViewModel.mPositiveTextInfo.observe(this) {
            setViewParameter(mPositiveView, it)
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

    fun setMenuData(menu: MutableList<String>, def: MutableList<Int>?) {
        for ((i, title) in menu.withIndex()) {
            val mEntity = MenuEntity(title, def?.contains(i) ?: false)
            mMenuData.add(mEntity)
        }
    }
}