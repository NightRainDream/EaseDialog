package com.night.dialog.adapter

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.util.forEach
import androidx.core.util.set
import androidx.recyclerview.widget.RecyclerView
import com.night.dialog.R

class MultipleMenuAdapter(menu: MutableList<String>, defaultSelect: MutableList<Int>?) :
    RecyclerView.Adapter<MultipleMenuAdapter.SingleMenuHolder>() {
    private val mMenuList = menu
    private lateinit var mLayoutInflater: LayoutInflater
    private lateinit var mRoomView: RecyclerView
    private val mState = SparseBooleanArray(mMenuList.size)

    init {
        if (defaultSelect != null) {
            for (i in defaultSelect) {
                if (i < mMenuList.size) {
                    mState[i] = true
                }
            }
        }
    }

    class SingleMenuHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTitleView = itemView.findViewById<AppCompatTextView>(R.id.tv_item_multiple_menu)
        val mIconView = itemView.findViewById<AppCompatImageView>(R.id.iv_item_multiple_menu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleMenuHolder {
        val mItemView = mLayoutInflater.inflate(R.layout.ease_layout_item_mulltiple_menu, parent, false)
        mItemView.setOnClickListener {
            val touchIndex = mRoomView.getChildAdapterPosition(it)
            setTouchIndex(touchIndex)
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

    fun setTouchIndex(index: Int) {
        if (index >= mMenuList.size) {
            return
        }
        mState[index] = !mState.get(index)
        notifyItemChanged(index, "select")
    }

    fun getSelectData(): MutableList<Int> {
        val mSel = mutableListOf<Int>()
        mState.forEach { key, value ->
            if (value) {
                mSel.add(key)
            }
        }
        return mSel
    }

}