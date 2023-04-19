package com.night.dialog.adapter

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.util.set
import androidx.core.util.size
import androidx.recyclerview.widget.RecyclerView
import com.night.dialog.R
import com.night.dialog.entity.MenuEntity
import com.night.dialog.tools.LogcatToos

class SingleMenuAdapter(menu: MutableList<MenuEntity>) :
    RecyclerView.Adapter<SingleMenuAdapter.SingleMenuHolder>() {
    private val mMenuList = menu
    private lateinit var mLayoutInflater: LayoutInflater
    private lateinit var mRoomView: RecyclerView
    private var mOnItemClickListener: OnItemClickListener? = null

    class SingleMenuHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTitleView = itemView.findViewById<AppCompatTextView>(R.id.tv_item_single_menu)
        val mIconView = itemView.findViewById<AppCompatImageView>(R.id.iv_item_single_menu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleMenuHolder {
        val mItemView = mLayoutInflater.inflate(R.layout.ease_layout_item_single_menu, parent, false)
        mItemView.setOnClickListener {
            val touchIndex = mRoomView.getChildAdapterPosition(it)
            mOnItemClickListener?.onItemClick(mMenuList[touchIndex].title, touchIndex)
        }
        return SingleMenuHolder(mItemView)
    }

    override fun getItemCount(): Int {
        return mMenuList.size
    }

    override fun onBindViewHolder(holder: SingleMenuHolder, position: Int) {
        holder.mTitleView.text = mMenuList[position].title
        holder.mTitleView.textSize = 16F

        holder.mIconView.isSelected = mMenuList[position].isSelect
    }

    override fun onBindViewHolder(
        holder: SingleMenuHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        for (key in payloads) {
            if ("single_state" == key) {
                holder.mIconView.isSelected = mMenuList[position].isSelect
            }
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mLayoutInflater = LayoutInflater.from(recyclerView.context)
        mRoomView = recyclerView
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(text: String, menuIndex: Int)
    }
}