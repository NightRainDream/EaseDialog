package com.night.dialog.adapter

import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.util.forEach
import androidx.core.util.set
import androidx.recyclerview.widget.RecyclerView
import com.night.dialog.DialogHelp
import com.night.dialog.R

class MultipleMenuDialogAdapter(context: Context, menu: MutableList<String>, defaultSelect: MutableList<Int>) : RecyclerView.Adapter<MultipleMenuDialogAdapter.MenuViewHolder>() {
    private val mMenuDate: MutableList<String> = menu
    private val mLayoutInflater: LayoutInflater
    private val mSelectState: SparseBooleanArray
    private lateinit var mRoomView: RecyclerView
    private val mTitleTextInfo = DialogHelp.getTitleTextInfo()
    private val mMainTextInfo = DialogHelp.getContentTextInfo()

    init {
        mLayoutInflater = LayoutInflater.from(context)
        mSelectState = SparseBooleanArray(mMenuDate.size)
        val mMenuSize = menu.size
        for (i in defaultSelect) {
            if (i < mMenuSize) {
                mSelectState[i] = true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val mItemView = mLayoutInflater.inflate(R.layout.item_multiple_menu, parent, false)
        mItemView.setOnClickListener {
            setSelectPosition(mRoomView.getChildLayoutPosition(it))
        }
        return MenuViewHolder(mItemView)
    }

    override fun getItemCount(): Int {
        return mMenuDate.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.mTextView.setTextColor(mTitleTextInfo.textColor)
        holder.mImageView.setColorFilter(mMainTextInfo.textColor)
        holder.itemView.background = DialogHelp.getItemPressBackground()
        //数据内容设置
        holder.mTextView.text = mMenuDate[position]
        holder.mImageView.isSelected = mSelectState.get(position, false)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        holder.mImageView.setColorFilter(mMainTextInfo.textColor)
        for (tag in payloads){
            if("change" == tag){
                holder.mImageView.isSelected = mSelectState.get(position, false)
            }
        }
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.mRoomView = recyclerView
    }

    class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mTextView = view.findViewById<AppCompatTextView>(R.id.tv_item_menu)
        val mImageView = view.findViewById<AppCompatImageView>(R.id.iv_item_menu)
    }
    private fun setSelectPosition(index: Int) {
        if (index == -1) {
            return
        }
        if (index >= mMenuDate.size) {
            return
        }
        val mOld = mSelectState.get(index, false)
        mSelectState[index] = !mOld
        notifyItemChanged(index,"")
    }

    fun getSelectIndex(): MutableList<Int> {
        val mSelectIndex = mutableListOf<Int>()
        mSelectState.forEach { key, value ->
            if (value) {
                mSelectIndex.add(key)
            }
        }
        return mSelectIndex
    }
}