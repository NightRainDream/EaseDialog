package com.night.dialog.ui.sing

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.night.dialog.R
import com.night.dialog.adapter.MultipleMenuAdapter
import com.night.dialog.adapter.SingleMenuAdapter
import com.night.dialog.tools.DialogHelp
import com.night.dialog.tools.SmallDividerItem
import com.night.dialog.widget.EaseFragmentDialog

class SingleMenuDialog : EaseFragmentDialog<SingleMenuViewModel>() {
    private lateinit var mTitleView: AppCompatTextView
    private lateinit var mContentView: RecyclerView
    private lateinit var mCancelView: AppCompatTextView
    private lateinit var mSingleMenuAdapter: SingleMenuAdapter

    override fun initLayoutView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.ease_layout_dialog_single_menu, container, false)
    }

    override fun initViewModel(): Class<SingleMenuViewModel> {
        return SingleMenuViewModel::class.java
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
                DialogHelp.getColor(R.color.colorDivider)
            )
        )
        DialogHelp.setDialogMaxSize(view)
    }

    override fun initListener(savedInstanceState: Bundle?) {
        mCancelView.setOnClickListener {
            dismiss()
            mViewModel.onCancelEvent()
        }
        mSingleMenuAdapter.setOnItemClickListener(object : SingleMenuAdapter.OnItemClickListener {
            override fun onItemClick(text: String, menuIndex: Int) {
                dismiss()
                mViewModel.onPositiveEvent(text, mutableListOf(menuIndex))
            }
        })
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.mCancelTextInfo.observe(this) {
            setViewParameter(mCancelView, it)
        }
        mViewModel.mTitleTextInfo.observe(this) {
            setViewParameter(mTitleView, it)
        }

        mViewModel.mNotifyPosition.observe(this) {
            mSingleMenuAdapter.notifyItemChanged(it, "single_state")
        }
    }


    fun setMenuList(menu: MutableList<String>) {
        mViewModel.setMenuList(menu)
        mSingleMenuAdapter.notifyItemRangeInserted(0, menu.size)
        DialogHelp.setDialogMaxSize(view)
    }

    fun setSelectIndex(index: Int) {
        mViewModel.setItemState(index)
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