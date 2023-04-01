package com.night.dialog.tools

import android.content.res.Resources
import android.view.View

class PopMenuHelp {
    private var mToViewX = -1F
    private var mToScreenX = -1F
    private var mToViewY = -1F
    private var mToScreenY = -1F

    /**
     * 设置点击位置
     *
     * @param x 手指相对锚点ViewX轴位置
     * @param y 手指相对锚点ViewY轴位置
     * @param rawX 手指相对屏幕X轴位置
     * @param rawY 手指相对屏幕Y轴位置
     *
     */
    fun setTouchCoordinate(x: Float, y: Float, rawX: Float, rawY: Float) {
        mToViewX = x
        mToViewY = y
        mToScreenX = rawX
        mToScreenY = rawY
    }

    fun getDropDownOffset(contentView: View,anchor: View): IntArray {
        val mContentViewSize = getContentViewSize(contentView)
        val contentWidth = mContentViewSize[0]
        val contentHeight = mContentViewSize[1]
        var mXoff = 0
        var mYoff = 0
        if (mToViewX != -1F && mToScreenX != -1F) {
            mXoff = if (mToScreenX < contentWidth) {
                mToViewX.toInt()
            } else {
                (mToViewX - contentWidth).toInt()
            }
        }
        if (mToViewY != -1F && mToScreenY != -1F) {
            //屏幕高度
            val mScreenHeight = Resources.getSystem().displayMetrics.heightPixels
            mYoff = if ((mScreenHeight - mToScreenY) < contentHeight) {
                (anchor.height - mToViewY).toInt()
            } else {
                -(anchor.height - mToViewY).toInt()
            }
        }
        return intArrayOf(mXoff,mYoff)
    }

    fun getContentViewSize(contentView: View): IntArray{
        contentView.measure(0, 0)
        val contentWidth = contentView.measuredWidth
        val contentHeight = contentView.measuredHeight
        return intArrayOf(contentWidth,contentHeight)
    }
}