package com.night.dialog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.night.dialog.R

class PopupMenuAdapter(context: Context, data: MutableList<String>) :RecyclerView.Adapter<PopupMenuAdapter.MenuViewHolder>() {
    private val mMenuDate = data
    private val mLayoutInflater = LayoutInflater.from(context)
    private var mOnItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val mItemView = mLayoutInflater.inflate(R.layout.ease_layout_item_popup, parent, false)
        return MenuViewHolder(mItemView)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        //数据内容设置
        holder.mTextView.text =  mMenuDate[position]
        holder.mTextView.textSize = 16F
        holder.mItemView.setOnClickListener {
            mOnItemClickListener?.onItemClick(mMenuDate[position],position)
        }
    }

    override fun getItemCount(): Int {
        return mMenuDate.size
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = onItemClickListener
    }

    class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mTextView = view.findViewById<TextView>(R.id.tv_item_popup)
        val mItemView = view
    }

    interface OnItemClickListener {
        fun onItemClick(text: String,menu: Int)
    }
}