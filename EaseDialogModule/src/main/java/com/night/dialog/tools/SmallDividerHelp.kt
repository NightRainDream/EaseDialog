package com.night.dialog.tools

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

internal object SmallDividerHelp {
    /**
     * 初始化横向LinearLayout空间
     *
     * @param itemPosition Item位置
     * @param outRect Item装饰空间
     * @param dividerWidth 分割线宽度
     */
    fun initLinearLayoutHorizontalOffsets(itemPosition: Int, outRect: Rect, dividerWidth: Int) {
        if (itemPosition == 0) {
            return
        }
        outRect.set(dividerWidth, 0, 0, 0)
    }

    /**
     * 绘制横向LinearLayout空间
     *
     * @param c 画布
     * @param paint 绘制画笔
     * @param parent RecyclerView
     * @param dividerWidth 分割线宽度
     */
    fun drawLinearLayoutHorizontal(c: Canvas, paint: Paint?, parent: RecyclerView, dividerWidth: Int) {
        if (paint == null) {
            return
        }
        paint.strokeWidth = dividerWidth.toFloat()
        val mChildCount = parent.childCount
        for (i in 0 until mChildCount) {
            val mChild = parent.getChildAt(i)
            val mItemPosition = parent.getChildAdapterPosition(mChild)
            if (mItemPosition == 0) {
                continue
            }
            val mLeft = mChild.left
            val mStartX = mLeft - dividerWidth / 2F
            val mStartY = mChild.top.toFloat()
            val mEndY = mChild.bottom.toFloat()
            c.drawLine(mStartX, mStartY, mStartX, mEndY, paint)
        }
    }

    /**
     * 初始化纵向LinearLayout空间
     *
     * @param itemPosition Item位置
     * @param outRect Item装饰空间
     * @param dividerWidth 分割线宽度
     */
    fun initLinearLayoutVerticalOffsets(itemPosition: Int, outRect: Rect, dividerWidth: Int) {
        if (itemPosition == 0) {
            return
        }
        outRect.set(0, dividerWidth, 0, 0)
    }

    /**
     * 绘制纵向LinearLayout空间
     *
     * @param c 画布
     * @param paint 绘制画笔
     * @param parent RecyclerView
     * @param dividerWidth 分割线宽度
     */
    fun drawLinearLayoutVertical(c: Canvas, paint: Paint?, parent: RecyclerView, dividerWidth: Int) {
        if (paint == null) {
            return
        }
        val mChildCount = parent.childCount
        paint.strokeWidth = dividerWidth.toFloat()
        for (i in 0 until mChildCount) {
            val mChild = parent.getChildAt(i)
            val mItemPosition = parent.getChildAdapterPosition(mChild)
            if (mItemPosition == 0) {
                continue
            }
            val mTop = mChild.top
            val mStartX = mChild.left.toFloat()+mChild.paddingStart
            val mStartY = mTop - dividerWidth / 2F
            val mEndX = mChild.right.toFloat() - mChild.paddingEnd
            c.drawLine(mStartX, mStartY, mEndX, mStartY, paint)
        }
    }
}