package com.night.dialog.adapter

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.util.set
import androidx.recyclerview.widget.RecyclerView
import com.night.dialog.R

class SingleMenuAdapter(menu: MutableList<String>) :
    RecyclerView.Adapter<SingleMenuAdapter.SingleMenuHolder>() {
    private val mMenuList = menu
    private lateinit var mLayoutInflater: LayoutInflater
    private lateinit var mRoomView: RecyclerView
    private val mState = SparseBooleanArray(mMenuList.size)
    private var mNowSelectPosition = -1
    private var mOnItemClickListener: OnItemClickListener? = null

    class SingleMenuHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTitleView = itemView.findViewById<AppCompatTextView>(R.id.tv_item_single_menu)
        val mIconView = itemView.findViewById<AppCompatImageView>(R.id.iv_item_single_menu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleMenuHolder {
        val mItemView = mLayoutInflater.inflate(R.layout.item_single_menu, parent, false)
        mItemView.setOnClickListener {
            val touchIndex = mRoomView.getChildAdapterPosition(it)
            setSelectPosition(touchIndex)
            mOnItemClickListener?.onItemClick(mMenuList[touchIndex], touchIndex)
        }
        return SingleMenuHolder(mItemView)
    }

    override fun getItemCount(): Int {
        return mMenuList.size
    }

    override fun onBindViewHolder(holder: SingleMenuHolder, position: Int) {
        holder.mTitleView.text = mMenuList[position]
        holder.mTitleView.textSize = 16F
        holder.mIconView.isSelected = mState.get(position)
    }

    override fun onBindViewHolder(
        holder: SingleMenuHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        for (key in payloads) {
            if ("select" == key) {
                holder.mIconView.isSelected = mState.get(position)
            }
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mLayoutInflater = LayoutInflater.from(recyclerView.context)
        mRoomView = recyclerView
    }

    /**
     * 设置选中位置
     */
    fun setSelectPosition(index: Int) {
        if (index == mNowSelectPosition) {
            return
        }
        if (index >= mMenuList.size) {
            return
        }
        mState[mNowSelectPosition] = false
        notifyItemChanged(mNowSelectPosition)
        mState[index] = true
        notifyItemChanged(mNowSelectPosition)
        this.mNowSelectPosition = index
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(text: String, menuIndex: Int)
    }
}