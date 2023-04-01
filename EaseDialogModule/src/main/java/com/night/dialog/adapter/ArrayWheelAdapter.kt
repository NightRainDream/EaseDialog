package com.night.dialog.adapter


import android.content.Context

internal class ArrayWheelAdapter<T> : AbstractWheelTextAdapter {
    private val items: MutableList<T>

    constructor(context: Context, items: MutableList<T>) : super(context) {
        this.items = items
    }

    override fun getItemText(index: Int): CharSequence? {
        if (index >= 0 && index < items.size) {
            val item = items[index]
            if (item is CharSequence) {
                return item
            }
            return item.toString()
        }
        return null
    }

    override fun getItemsCount(): Int {
        return items.size
    }
}