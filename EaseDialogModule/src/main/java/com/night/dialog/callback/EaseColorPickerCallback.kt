package com.night.dialog.callback


/**
 * 颜色选择组件回调
 */
interface EaseColorSelectListener {
    /**
     * 颜色选中改变，当未释放触点时调用此方法，此方法在选择颜色过程中可能颜色改变时就会被调用，整个过程中会被多次调用，
     * 但所传值为选择的不同颜色值
     *
     * @param red   RED
     * @param green GREEN
     * @param blue  BLUE
     */
    fun onColorSelecting(red: Int, green: Int, blue: Int)

    /**
     * 颜色选中改变，选中最终颜色，此方法在触点被释放时调用，在选择颜色过程中只会调用一次，所传值为最终选择的颜色值
     *
     * @param red   RED
     * @param green GREEN
     * @param blue  BLUE
     */
    fun onColorSelected(red: Int, green: Int, blue: Int)
}

/**
 * 颜色选择回调
 */
interface IColorSelectCallback {

    fun onSelected(red: Int, green: Int, blue: Int)

    fun onCancel() {}

    fun onDismiss() {}
}