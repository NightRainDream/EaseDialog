package com.night.dialog.ui.multiple

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.night.dialog.R
import com.night.dialog.adapter.MultipleMenuAdapter
import com.night.dialog.entity.MenuEntity
import com.night.dialog.tools.DialogHelp
import com.night.dialog.tools.LogcatToos
import com.night.dialog.tools.SmallDividerItem
import com.night.dialog.widget.EaseFragmentDialog

/**
 * ---------------------------------------------------
 * 说    明: 多选对话框
 * 作    者: Night
 * 时    间: 2023/4/19
 * ---------------------------------------------------
 */
class MultipleMenuDialog : EaseFragmentDialog<MultipleMenuViewModel>() {
    private lateinit var mTitleView: AppCompatTextView
    private lateinit var mCancelView: AppCompatTextView
    private lateinit var mPositiveView: AppCompatTextView
    private lateinit var mContentView: RecyclerView
    private lateinit var mMultipleMenuAdapter: MultipleMenuAdapter

    override fun initLayoutView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.ease_laoyut_dialog_multiple_menu, container, false)
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
        mMultipleMenuAdapter = MultipleMenuAdapter(requireContext(), mViewModel.getMenuList())
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


    fun setMenuList(menu: MutableList<String>) {
        mViewModel.setMenuList(menu)
        mMultipleMenuAdapter.notifyItemRangeInserted(0, menu.size)
        DialogHelp.setDialogMaxSize(view)
    }

    fun setSelectIndex(index: MutableList<Int>?) {
        if (index == null) {
            return
        }
        for (position in index) {
            if (mViewModel.setItemState(position)) {
                mMultipleMenuAdapter.notifyItemChanged(position, "multiple_state")
            }
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
}