package com.night.dialog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.night.dialog.R
import com.night.dialog.entity.MenuEntity

class MultipleMenuAdapter(menu: MutableList<MenuEntity>) :
    RecyclerView.Adapter<MultipleMenuAdapter.MultipleMenuHolder>() {
    private val mMenuList = menu
    private lateinit var mLayoutInflater: LayoutInflater
    private lateinit var mRoomView: RecyclerView
    private var mOnItemClickListener: OnItemClickListener? = null


    class MultipleMenuHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTitleView: AppCompatTextView = itemView.findViewById(R.id.tv_item_multiple_menu)
        val mIconView: AppCompatImageView = itemView.findViewById(R.id.iv_item_multiple_menu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultipleMenuHolder {
        val mItemView = mLayoutInflater.inflate(R.layout.ease_layout_item_mulltiple_menu, parent, false)
        mItemView.setOnClickListener {
            val touchIndex = mRoomView.getChildAdapterPosition(mItemView)
            mOnItemClickListener?.onItemClick(mMenuList[touchIndex].title, touchIndex)
        }
        return MultipleMenuHolder(mItemView)
    }

    override fun getItemCount(): Int {
        return mMenuList.size
    }

    override fun onBindViewHolder(holder: MultipleMenuHolder, position: Int) {
        val mItemEntity = mMenuList[position]
        holder.mTitleView.text = mItemEntity.title
        holder.mTitleView.textSize = 16F
        holder.mIconView.isSelected = mItemEntity.isSelect
    }

    override fun onBindViewHolder(
        holder: MultipleMenuHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        val mItemEntity = mMenuList[position]
        for (key in payloads) {
            if ("multiple_state" == key) {
                holder.mIconView.isSelected = mItemEntity.isSelect
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