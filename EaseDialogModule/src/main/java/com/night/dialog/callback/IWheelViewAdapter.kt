package com.night.dialog.callback

import android.database.DataSetObserver
import android.view.View
import android.view.ViewGroup

internal interface IWheelViewAdapter {
    fun getItemsCount(): Int
    fun getItem(index: Int, convertView: View?, parent: ViewGroup?): View?
    fun getEmptyItem(convertView: View?, parent: ViewGroup?): View?
    fun registerDataSetObserver(observer: DataSetObserver?)
    fun unregisterDataSetObserver(observer: DataSetObserver?)
}