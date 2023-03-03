package com.night.dialog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.night.dialog.DialogHelp
import com.night.dialog.R
import com.night.dialog.entity.MenuInfoEntity

class MenuDialogAdapter(menu: MutableList<MenuInfoEntity>) : RecyclerView.Adapter<MenuDialogAdapter.MenuViewHolder>() {
    private lateinit var mAttachRecyclerView: RecyclerView
    private lateinit var mLayoutInflater: LayoutInflater
    private val mTitleTextInfo = DialogHelp.getTitleTextInfo()
    private val mMenuDate = menu
    private var mOnItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val mItemView = mLayoutInflater.inflate(R.layout.item_menu, parent, false)
        mItemView.setOnClickListener {
            val index = mAttachRecyclerView.getChildAdapterPosition(mItemView)
            mOnItemClickListener?.onItemClick(mMenuDate[index].title,index)
        }
        return MenuViewHolder(mItemView)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.mTextView.setTextColor(mTitleTextInfo.textColor)
        holder.mImageVIew.setColorFilter(mTitleTextInfo.textColor)
        holder.itemView.background = DialogHelp.getItemPressBackground()

        holder.mTextView.text = mMenuDate[position].title
        holder.mImageVIew.setImageResource(mMenuDate[position].icon)
    }

    override fun getItemCount(): Int {
        return mMenuDate.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.mAttachRecyclerView = recyclerView
        mLayoutInflater = LayoutInflater.from(recyclerView.context)
    }

    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTextView = itemView.findViewById<AppCompatTextView>(R.id.tv_item_menu)
        val mImageVIew = itemView.findViewById<AppCompatImageView>(R.id.iv_item_menu)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.mOnItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(title: String,index: Int)
    }
}