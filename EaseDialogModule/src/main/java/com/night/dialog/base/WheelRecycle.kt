package com.night.dialog.base

import android.view.View
import android.widget.LinearLayout
import com.night.dialog.entity.ItemsRange

class WheelRecycle(wheelView: WheelView) {
    private var items: MutableList<View>? = null
    private var emptyItems: MutableList<View>? = null
    private var wheel = wheelView

    fun recycleItems(layout: LinearLayout, firstItem: Int, range: ItemsRange): Int {
        var mFirstItem = firstItem
        var index = firstItem
        var i = 0
        while (i < layout.childCount) {
            if (!range.contains(index)) {
                recycleView(layout.getChildAt(i), index)
                layout.removeViewAt(i)
                if (i == 0) { // first item
                    mFirstItem++
                }
            } else {
                i++ // go to next item
            }
            index++
        }
        return mFirstItem
    }

    /**
     * Gets item view
     * @return the cached view
     */
    fun getItem(): View? {
        return getCachedView(items)
    }

    /**
     * Gets empty item view
     * @return the cached empty view
     */
    fun getEmptyItem(): View? {
        return getCachedView(emptyItems)
    }

    /**
     * Clears all views
     */
    fun clearAll() {
        items?.clear()
        emptyItems?.clear()
    }

    /**
     * Adds view to specified cache. Creates a cache list if it is null.
     * @param view the view to be cached
     * @param cache the cache list
     * @return the cache list
     */
    private fun addView(view: View, cache: MutableList<View>?): MutableList<View> {
        val mCache = cache ?: mutableListOf()
        mCache.add(view)
        return mCache
    }

    /**
     * Adds view to cache. Determines view type (item view or empty one) by index.
     * @param view the view to be cached
     * @param index the index of view
     */
    private fun recycleView(view: View?, index: Int) {
        if (view == null) {
            return
        }
        val count = wheel.viewAdapter.getItemsCount()
        if ((index < 0 || index >= count) && !wheel.isCyclic()) {
            emptyItems = addView(view, emptyItems)
        }else{
            items = addView(view, items)
        }
    }

    /**
     * Gets view from specified cache.
     * @param cache the cache
     * @return the first view from cache.
     */
    private fun getCachedView(cache: MutableList<View>?): View? {
        if (cache == null) {
            return null
        }
        if (cache.isEmpty()) {
            return null
        }
        return cache.removeAt(0)
    }
}