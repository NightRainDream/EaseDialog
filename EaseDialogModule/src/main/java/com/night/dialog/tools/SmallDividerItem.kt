package com.night.dialog.tools

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SmallDividerItem(width: Int, color: Int = Color.TRANSPARENT) : RecyclerView.ItemDecoration() {
    //分割线宽度
    private val mDividerWidth = width

    //分割线颜色
    private val mDividerColor = color

    //绘制分割线画笔
    private var mPaint: Paint? = null

    init {
        if (mDividerWidth > 0 && mDividerColor != Color.TRANSPARENT) {
            mPaint = Paint()
            mPaint!!.isAntiAlias = true
            mPaint!!.color = color
            mPaint!!.style = Paint.Style.FILL_AND_STROKE
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (mDividerWidth <= 0) {
            return
        }
        val mLayoutManager = parent.layoutManager
        val itemPosition = parent.getChildAdapterPosition(view)
        //线性布局
        if (mLayoutManager is LinearLayoutManager) {
            if (mLayoutManager.orientation == LinearLayoutManager.HORIZONTAL) {
                SmallDividerHelp.initLinearLayoutHorizontalOffsets(itemPosition, outRect, mDividerWidth)
            }
            if (mLayoutManager.orientation == LinearLayoutManager.VERTICAL) {
                SmallDividerHelp.initLinearLayoutVerticalOffsets(itemPosition, outRect, mDividerWidth)
            }
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        if (mDividerColor == Color.TRANSPARENT || mDividerWidth <= 0) {
            return
        }
        val mLayoutManager = parent.layoutManager
        //线性布局
        if (mLayoutManager is LinearLayoutManager) {
            if (mLayoutManager.orientation == LinearLayoutManager.HORIZONTAL) {
                SmallDividerHelp.drawLinearLayoutHorizontal(c, mPaint, parent, mDividerWidth)
            }
            if (mLayoutManager.orientation == LinearLayoutManager.VERTICAL) {
                SmallDividerHelp.drawLinearLayoutVertical(c, mPaint, parent, mDividerWidth)
            }
        }
    }
}