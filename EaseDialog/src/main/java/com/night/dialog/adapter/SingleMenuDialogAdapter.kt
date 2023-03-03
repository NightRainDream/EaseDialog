package com.night.dialog.adapter

import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.util.set
import androidx.recyclerview.widget.RecyclerView
import com.night.dialog.DialogHelp
import com.night.dialog.R

class SingleMenuDialogAdapter(context: Context, menu: MutableList<String>) : RecyclerView.Adapter<SingleMenuDialogAdapter.MenuViewHolder>() {
    private val mMenuDate = menu
    private val mLayoutInflater = LayoutInflater.from(context)
    private val mSelectState = SparseBooleanArray(mMenuDate.size)
    private var mSelectIndex = -1
    private val mTitleTextInfo = DialogHelp.getTitleTextInfo()
    private val mMainTextInfo = DialogHelp.getContentTextInfo()
    private lateinit var mRoomView: RecyclerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val mItemView = mLayoutInflater.inflate(R.layout.item_single_menu, parent, false)
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

    fun setSelectPosition(index: Int) {
        if (index == -1) {
            return
        }
        if (index >= mMenuDate.size) {
            return
        }
        if (mSelectIndex == -1) {
            mSelectState.put(index, true)
        } else {
            mSelectState[mSelectIndex] = false
            notifyItemChanged(mSelectIndex,"change")
            mSelectState[index] = true
        }
        mSelectIndex = index
        notifyItemChanged(index,"change")
    }

    fun getSelectPosition(): Int {
        return mSelectIndex
    }

    fun getSelectDate(): String {
        return mMenuDate[mSelectIndex]
    }
}