package com.night.dialog.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.night.dialog.callback.EaseColorSelectListener


class EaseColorPickerView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    /**
     * 自定义画笔，用于绘制控件
     */
    private val mPaint: Paint = Paint()

    /**
     * 控件高
     */
    private var mHeight = 0

    /**
     * 控件宽
     */
    private var mWidth = 0

    /**
     * 控件中心点X坐标
     */
    private var centerX = 0

    /**
     * 控件中心点Y坐标
     */
    private var centerY = 0

    /**
     * 色块大小
     */
    private var colorBlockSize = 0

    /**
     * 触摸点X坐标
     */
    private var touchX = 0f

    /**
     * 触摸点Y坐标
     */
    private var touchY = 0f

    /**
     * 默认大小，使用dp作为单位
     */
    private val DEFAULT_WIDTH_DP = 300

    /**
     * 默认大小，使用dp作为单位
     */
    private val DEFAULT_HEIGHT_DP = 300

    /**
     * 选中色相环ID
     */
    private var currentColorArrId = -1

    /**
     * 选中环中颜色ID
     */
    private var currentColorId = -1

    /**
     * 是否触点释放
     */
    private var isRelease = false

    /**
     * 是否选择的色块改变
     */
    private var isBlockChanged = false

    /**
     * 色相环颜色值
     */
    private val colorArray = arrayOf(
        arrayOf(
            "#fef5ce", "#fff3cd", "#feeeca", "#fdeac9", "#fee7c7", "#fce3c4",
            "#fbddc1", "#fad7c3", "#fad0c2", "#f2ced0", "#e6cad9",
            "#d9c7e1", "#d2c3e0", "#cfc6e3", "#cac7e4", "#c9cde8",
            "#c7d6ed", "#c7dced", "#c7e3e6", "#d2e9d9", "#deedce",
            "#e7f1cf", "#eef4d0", "#f5f7d0"
        ), arrayOf(
            "#ffeb95", "#fee591", "#fcdf8f", "#fcd68d", "#facd89", "#f9c385",
            "#f7b882", "#f5ab86", "#f29a82", "#e599a3", "#ce93b3",
            "#b48cbe", "#a588be", "#9d8cc2", "#9491c6", "#919dcf",
            "#89abd9", "#85bada", "#86c5ca", "#9fd2b1", "#bada99",
            "#cbe198", "#dde899", "#edf099"
        ), arrayOf(
            "#fee250", "#fed84f", "#fbce4d", "#f9c04c", "#f7b24a", "#f6a347",
            "#f39444", "#f07c4d", "#ec614e", "#d95f78", "#b95b90",
            "#96549e", "#7c509d", "#6e59a4", "#5c60aa", "#5572b6",
            "#3886c8", "#1c99c7", "#0daab1", "#57ba8b", "#90c761",
            "#b0d35f", "#ccdd5b", "#e5e756"
        ), arrayOf(
            "#FDD900", "#FCCC00", "#fabd00", "#f6ab00", "#f39801", "#f18101",
            "#ed6d00", "#e94520", "#e60027", "#cf0456", "#a60b73",
            "#670775", "#541b86", "#3f2b8e", "#173993", "#0c50a3",
            "#0168b7", "#0081ba", "#00959b", "#03a569", "#58b530",
            "#90c320", "#b8d201", "#dadf00"
        ), arrayOf(
            "#DBBC01", "#DAB101", "#D9A501", "#D69400", "#D28300", "#CF7100",
            "#CD5F00", "#CA3C18", "#C7001F", "#B4004A", "#900264",
            "#670775", "#4A1277", "#142E82", "#0A448E", "#005AA0",
            "#0070A2", "#018287", "#02915B", "#4A9D27", "#7DAB17",
            "#9EB801", "#BCC200", "#DBBC01"
        ), arrayOf(
            "#B49900", "#B39000", "#B18701", "#AD7901", "#AB6B01", "#AA5B00",
            "#A84A00", "#A62D10", "#A50011", "#94003C", "#770050",
            "#540060", "#3B0263", "#2B1568", "#10226C", "#053577",
            "#004A87", "#005D88", "#006C6F", "#00784A", "#38831E",
            "#648B0A", "#829601", "#999F01"
        ), arrayOf(
            "#9F8700", "#9E7F00", "#9D7601", "#9A6900", "#995E00", "#975000",
            "#954000", "#932406", "#92000B", "#840032", "#6A0048",
            "#4A0055", "#320057", "#240D5D", "#0C1860", "#032C6A",
            "#014076", "#005278", "#016064", "#006B41", "#2E7316",
            "#567C03", "#718500", "#888D00"
        )
    )

    private var mListener: EaseColorSelectListener? = null

    init {
        mPaint.isAntiAlias = true
        mPaint.isDither = true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawColors(canvas!!)
        drawTouchBlock(canvas!!)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        touchX = event!!.x
        touchY = event.y
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_UP -> isRelease = true
            MotionEvent.ACTION_MOVE -> {}
            MotionEvent.ACTION_DOWN -> isRelease = false
        }
        isBlockChanged = false
        computeTouchBlock()
        postInvalidate()
        return true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val density = getScreenDensity()
        val defaultWidth = (DEFAULT_WIDTH_DP * density).toInt()
        val defaultHeight = (DEFAULT_HEIGHT_DP * density).toInt()
        var cWidthMeasureSpec:Int = widthMeasureSpec
        var cHeightMeasureSpec:Int = heightMeasureSpec
        if (widthMode == MeasureSpec.UNSPECIFIED
            || widthMode == MeasureSpec.AT_MOST
        ) {
            cWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
                defaultWidth,
                MeasureSpec.EXACTLY
            )
            mWidth = defaultWidth
        } else {
            mWidth = widthSize
        }

        if (heightMode == MeasureSpec.UNSPECIFIED
            || heightMode == MeasureSpec.AT_MOST
        ) {
            cHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                defaultHeight,
                MeasureSpec.EXACTLY
            )
            mHeight = defaultHeight
        } else {
            mHeight = heightSize
        }

        centerX = mWidth / 2
        centerY = mHeight / 2
        val radius = getBigCircleRadius()
        /*
         * 计算色块大小
         */
        /*
         * 计算色块大小
         */colorBlockSize = radius / (colorArray.size + 1)
        super.onMeasure(cWidthMeasureSpec, cHeightMeasureSpec)
    }


    /**
     * 设置控件颜色选择监听器，用于监听控件选择颜色的改变
     *
     * @param listener 颜色选择监听器
     */
    fun setOnColorSelectedListener(listener: EaseColorSelectListener) {
        mListener = listener
    }

    /**
     * 绘制色相环
     *
     * @param canvas Canvas
     */
    private fun drawColors(canvas: Canvas) {
        mPaint.color = Color.WHITE
        mPaint.style = Paint.Style.FILL
        canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), colorBlockSize.toFloat(), mPaint)
        for (colorArrId in colorArray.indices) {
            val colors = colorArray[colorArrId]
            /* 根据当前环颜色数计算角度步长 */
            val angleStep = 360 / colors.size
            val radius = (colorArrId + 1) * colorBlockSize + colorBlockSize / 2
            for (colorId in colors.indices) {
                val startAngle = -angleStep / 2 + colorId * angleStep
                drawColorBlock(
                    canvas, radius, colors[colorId], startAngle,
                    angleStep
                )
            }
        }
    }

    /**
     * 绘制选中效果
     *
     * @param canvas Canvas
     */
    private fun drawTouchBlock(canvas: Canvas) {
        if (touchX == 0f && touchY == 0f) {
            return
        }
        if (currentColorArrId == 0) {
            mPaint.style = Paint.Style.FILL
            mPaint.color = Color.parseColor("#33ffffff")
            canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), colorBlockSize.toFloat(), mPaint)
        } else {
            val colors = colorArray[currentColorArrId - 1]
            /* 根据当前环颜色数计算角度步长 */
            val angleStep = 360 / colors.size
            val radius = ((currentColorArrId + 1) * colorBlockSize
                    + colorBlockSize / 2)
            val startAngle = -angleStep / 2 + currentColorId * angleStep
            drawColorBlock(canvas, radius, "#33ffffff", startAngle, angleStep)
        }
    }

    /**
     * 计算当前选中的色块位置
     */
    private fun computeTouchBlock() {
        if (touchX == 0f && touchY == 0f) {
            return
        }
        val tmpColorArrId = currentColorArrId
        val tmpColorId = currentColorId
        val distanceFromCenter: Double = getDistanceFromCenter()
        if (distanceFromCenter >= getBigCircleRadius()) {
            return
        }

        /*
         * 计算色环ID
         */currentColorArrId = (distanceFromCenter / colorBlockSize).toInt()
        if (currentColorArrId === colorArray.size + 1) {
            currentColorArrId -= 1
        }

        /*
         * 计算当前选中的色块在色环中的位置
         */if (currentColorArrId != 0) {
            val colors = colorArray[currentColorArrId - 1]
            /* 根据当前环颜色数计算角度步长 */
            val angleStep = 360 / colors.size
            var angle =
                (Math.atan2((touchY - centerY).toDouble(), (touchX - centerX).toDouble()) * 180 / Math.PI).toInt()
            angle %= 360
            if (angle < -angleStep / 2) {
                angle += 360
            }
            if (angle > 360 - angleStep / 2) {
                angle -= 360
            }
            currentColorId = angle / angleStep
        }

        /*
         * 判断选择的色块是否已经改变
         */if (tmpColorArrId != currentColorArrId || tmpColorId != currentColorId) {
            isBlockChanged = true
        }
        if (mListener == null) {
            return
        }

        /*
         * 处理颜色监听
         */if (currentColorArrId != 0) {
            val color = colorArray[currentColorArrId - 1][currentColorId]
            val colorInInt = Color.parseColor(color)
            if (isRelease) {
                isRelease = false
                mListener?.onColorSelected(
                    Color.red(colorInInt),
                    Color.green(colorInInt), Color.blue(colorInInt)
                )
            } else {
                mListener?.onColorSelecting(
                    Color.red(colorInInt),
                    Color.green(colorInInt), Color.blue(colorInInt)
                )
            }
        } else {
            if (isRelease) {
                isRelease = false
                mListener?.onColorSelected(0xff, 0xff, 0xff)
            } else {
                mListener?.onColorSelecting(0xff, 0xff, 0xff)
            }
        }
    }

    /**
     * 绘制色块
     *
     * @param canvas     Canvas
     * @param radius     半径
     * @param color      色块颜色
     * @param startAngle 开始角度
     * @param sweepAngle 覆盖角度
     */
    private fun drawColorBlock(
        canvas: Canvas, radius: Int, color: String,
        startAngle: Int, sweepAngle: Int
    ) {
        mPaint.strokeWidth = colorBlockSize.toFloat()
        mPaint.style = Paint.Style.STROKE
        mPaint.color = Color.parseColor(color)
        val oval = RectF(
            (centerX - radius).toFloat(), (centerY - radius).toFloat(), (centerX
                    + radius).toFloat(), (centerY + radius).toFloat()
        )
        canvas.drawArc(oval, startAngle.toFloat(), sweepAngle.toFloat(), false, mPaint)
    }

    /**
     * 获取整个色环的半径，取宽和高中最小值的二分之一减去8像素
     *
     * @return 色环半径
     */
    private fun getBigCircleRadius(): Int {
        val radius = if (mWidth > mHeight) mHeight / 2 else mWidth / 2
        return radius - 8
    }

    /**
     * 获取触点与圆心的距离
     *
     * @return 触点与圆心的距离
     */
    private fun getDistanceFromCenter(): Double {
        var factor = (touchX - centerX) * (touchX - centerX)
        factor += (touchY - centerY) * (touchY - centerY)
        return Math.sqrt(factor.toDouble())
    }

    /**
     * 获取屏幕密度，用于屏幕适配
     *
     * @return 屏幕密度
     */
    private fun getScreenDensity(): Float {
        val dm = context.resources.displayMetrics
        return dm.density
    }
}