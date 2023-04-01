package com.night.dialog.entity

class ItemsRange(first: Int = 0, count: Int = 0) {
    private val mFirst = first
    private val mCount = count
    fun getFirst(): Int {
        return mFirst
    }

    fun getCount(): Int {
        return mCount
    }

    fun getLast(): Int {
        return getFirst() + getCount() - 1;
    }


    fun contains(index: Int): Boolean {
        return index >= getFirst() && index <= getLast();
    }
}