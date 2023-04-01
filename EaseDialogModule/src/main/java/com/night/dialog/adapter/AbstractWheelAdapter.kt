package com.night.dialog.adapter

import com.night.dialog.callback.IWheelViewAdapter
import android.database.DataSetObserver
import android.view.View
import android.view.ViewGroup

internal abstract class AbstractWheelAdapter : IWheelViewAdapter {
    private val mDataSetObservers = mutableListOf<DataSetObserver>()

    override fun getEmptyItem(convertView: View?, parent: ViewGroup?): View? {
        return null
    }

    override fun registerDataSetObserver(observer: DataSetObserver?) {
        if (observer == null) {
            return
        }
        mDataSetObservers.add(observer)
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {
        if (observer == null) {
            return
        }
        mDataSetObservers.remove(observer)
    }

    protected fun notifyDataChangedEvent() {
        for (event in mDataSetObservers) {
            event.onChanged()
        }
    }

    protected fun notifyDataInvalidatedEvent() {
        for (event in mDataSetObservers) {
            event.onInvalidated();
        }
    }
}