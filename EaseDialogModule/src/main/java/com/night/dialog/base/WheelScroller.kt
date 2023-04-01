package com.night.dialog.base

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.animation.Interpolator
import android.widget.Scroller
import kotlin.math.abs

const val SCROLLING_DURATION: Int = 400
const val MIN_DELTA_FOR_SCROLLING = 1
const val MESSAGE_SCROLL = 0
const val MESSAGE_JUSTIFY = 1

class WheelScroller(context: Context, listener: ScrollingListener) {
    private val mContext = context
    private val mListener = listener
    private var scroller = Scroller(mContext)
    private var lastScrollY: Int = 0
    private var lastTouchedY: Float = 0F
    private var isScrollingPerformed: Boolean = false
    private val gestureListener = object : GestureDetector.SimpleOnGestureListener() {
        override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
            return true
        }

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            lastScrollY = 0
            val maxY = 0x7FFFFFFF
            val minY = -maxY
            scroller.fling(0, lastScrollY, 0, -velocityY.toInt(), 0, 0, minY, maxY)
            setNextMessage(MESSAGE_SCROLL)
            return true
        }
    }
    private val animationHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            scroller.computeScrollOffset()
            val currY = scroller.currY
            val delta = lastScrollY - currY
            lastScrollY = currY
            if (delta != 0) {
                listener.onScroll(delta)
            }

            // scrolling is not finished when it comes to final Y
            // so, finish it manually
            if (abs(currY - scroller.finalY) < MIN_DELTA_FOR_SCROLLING) {
//                currY = scroller.finalY
                scroller.forceFinished(true)
            }
            if (!scroller.isFinished) {
                this.sendEmptyMessage(msg.what)
            } else if (msg.what == MESSAGE_SCROLL) {
                justify()
            } else {
                finishScrolling()
            }
        }
    }

    // Scrolling
    private val gestureDetector = GestureDetector(context, gestureListener)

    init {
        gestureDetector.setIsLongpressEnabled(false)
    }

    private fun setNextMessage(message: Int) {
        clearMessages()
        animationHandler.sendEmptyMessage(message)
    }

    private fun clearMessages() {
        animationHandler.removeMessages(MESSAGE_SCROLL)
        animationHandler.removeMessages(MESSAGE_JUSTIFY)
    }

    /**
     * Handles Touch event
     * @param event the motion event
     * @return
     */
    fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.getAction()) {
            MotionEvent.ACTION_DOWN -> {
                lastTouchedY = event.getY()
                scroller.forceFinished(true)
                clearMessages()
            }


            MotionEvent.ACTION_MOVE -> {
                // perform scrolling
                val distanceY = (event.getY() - lastTouchedY).toInt()
                if (distanceY != 0) {
                    startScrolling()
                    mListener.onScroll(distanceY)
                    lastTouchedY = event.getY()
                }
            }
        }

        if (!gestureDetector.onTouchEvent(event) && event.getAction() == MotionEvent.ACTION_UP) {
            justify()
        }

        return true
    }

    /**
     * Set the the specified scrolling interpolator
     * @param interpolator the interpolator
     */
    fun setInterpolator(interpolator: Interpolator) {
        scroller.forceFinished(true)
        scroller = Scroller(mContext, interpolator)
    }

    /**
     * Scroll the wheel
     * @param distance the scrolling distance
     * @param time the scrolling duration
     */
    fun scroll(distance: Int, time: Int) {
        scroller.forceFinished(true)
        lastScrollY = 0
        scroller.startScroll(0, 0, 0, distance, if (time != 0) time else SCROLLING_DURATION)
        setNextMessage(MESSAGE_SCROLL)
        startScrolling()
    }

    /**
     * Stops scrolling
     */
    fun stopScrolling() {
        scroller.forceFinished(true)
    }

    /**
     * Justifies wheel
     */
    private fun justify() {
        mListener.onJustify()
        setNextMessage(MESSAGE_JUSTIFY)
    }

    /**
     * Starts scrolling
     */
    private fun startScrolling() {
        if (!isScrollingPerformed) {
            isScrollingPerformed = true
            mListener.onStarted()
        }
    }

    /**
     * Finishes scrolling
     */
    private fun finishScrolling() {
        if (isScrollingPerformed) {
            mListener.onFinished()
            isScrollingPerformed = false
        }
    }


    interface ScrollingListener {
        /**
         * Scrolling callback called when scrolling is performed.
         * @param distance the distance to scroll
         */
        fun onScroll(distance: Int)

        /**
         * Starting callback called when scrolling is started
         */
        fun onStarted()

        /**
         * Finishing callback called after justifying
         */
        fun onFinished()

        /**
         * Justifying callback called to justify a view when scrolling is ended
         */
        fun onJustify()
    }
}