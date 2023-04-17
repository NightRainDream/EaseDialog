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
import com.night.dialog.adapter.SingleMenuAdapter
import com.night.dialog.tools.DialogHelp
import com.night.dialog.widget.EaseFragmentDialog

class SingleMenuDialog : EaseFragmentDialog<SingleMenuViewModel>() {
    private lateinit var mTitleView: AppCompatTextView
    private lateinit var mContentView: RecyclerView
    private lateinit var mCancelView: AppCompatTextView
    private lateinit var mSingleMenuAdapter: SingleMenuAdapter
    private val mData = mutableListOf<String>()

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
        mSingleMenuAdapter = SingleMenuAdapter(mData)
        mContentView.layoutManager = LinearLayoutManager(activity)
        mContentView.adapter = mSingleMenuAdapter
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.mCancelTextInfo.observe(this) {
            setViewParameter(mCancelView, it)
        }
        mViewModel.mTitleTextInfo.observe(this) {
            setViewParameter(mTitleView, it)
        }
        mViewModel.mMenuList.observe(this) {
            mData.clear()
            mData.addAll(it)
            mSingleMenuAdapter.notifyItemRangeChanged(0, mData.size)
        }

        mViewModel.mSelectIndex.observe(this) {
            mSingleMenuAdapter.setSelectPosition(it)
        }

    }

    override fun initListener(savedInstanceState: Bundle?) {
        mSingleMenuAdapter.setOnItemClickListener(object : SingleMenuAdapter.OnItemClickListener {
            override fun onItemClick(text: String, menuIndex: Int) {
                dismiss()
                mViewModel.onPositiveEvent(text, mutableListOf(menuIndex))
            }
        })
        mCancelView.setOnClickListener {
            dismiss()
            mViewModel.onCancelEvent()
        }
    }

    fun setMenuList(menu: MutableList<String>) {
        mViewModel.setMenuList(menu)
    }

    fun setSelectIndex(index: Int) {
        mViewModel.setSelectIndex(index)
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