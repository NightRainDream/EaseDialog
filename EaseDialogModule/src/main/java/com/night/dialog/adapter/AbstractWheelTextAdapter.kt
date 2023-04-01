package com.night.dialog.adapter
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.night.dialog.tools.DialogHelp
import com.night.dialog.R

internal abstract class AbstractWheelTextAdapter : AbstractWheelAdapter {
    companion object {
        const val TEXT_VIEW_ITEM_RESOURCE = -1
        const val NO_RESOURCE = 0
    }

    private var padding = 5

    // Current context
    protected var context: Context? = null

    // Layout inflater
    protected var inflater: LayoutInflater? = null

    // Items resources
    protected var itemResourceId = 0

    protected var itemTextResourceId = 0

    // Empty items resources
    protected var emptyItemResourceId = 0

    constructor(context: Context) : this(context, TEXT_VIEW_ITEM_RESOURCE)

    constructor(context: Context, itemResource: Int) : this(context, itemResource, NO_RESOURCE)

    constructor(context: Context, itemResource: Int, itemTextResource: Int) {
        this.context = context;
        itemResourceId = itemResource;
        itemTextResourceId = itemTextResource
        inflater = LayoutInflater.from(context)
    }

    fun setItemResource(itemResourceId: Int) {
        this.itemResourceId = itemResourceId
    }

    fun setItemTextResource(itemTextResourceId: Int) {
        this.itemTextResourceId = itemTextResourceId
    }

    override fun getItem(index: Int, convertView: View?, parent: ViewGroup?): View? {
        var mConvertView = convertView
        if (index >= 0 && index < getItemsCount()) {
            if (mConvertView == null) {
                mConvertView = getView(itemResourceId, parent)
            }
            val textView = getTextView(mConvertView, itemTextResourceId)
            if (textView != null) {
                var text = getItemText(index)
                if (text == null) {
                    text = ""
                }
                //textView.setTextColor(DialogHelp.getColor(R.color.colorMainText))
                textView.text = text
                if (itemResourceId == TEXT_VIEW_ITEM_RESOURCE) {
                    configureTextView(textView)
                }
            }
            return mConvertView
        }
        return null
    }

    override fun getEmptyItem(convertView: View?, parent: ViewGroup?): View? {
        var mConvertView = convertView
        if (mConvertView == null) {
            mConvertView = getView(emptyItemResourceId, parent)
        }
        if (emptyItemResourceId == TEXT_VIEW_ITEM_RESOURCE && mConvertView is TextView) {
            configureTextView(mConvertView)
        }
        return mConvertView
    }


    /**
     * Configures text view. Is called for the TEXT_VIEW_ITEM_RESOURCE views.
     * @param view the text view to be configured
     */
    protected fun configureTextView(view: TextView) {
        view.setTextColor(DialogHelp.getColor(R.color.colorMainText))
        view.gravity = Gravity.CENTER
        view.setPadding(0, padding, 0, padding)
    }

    /**
     * Loads a text view from view
     * @param view the text view or layout containing it
     * @param textResource the text resource Id in layout
     * @return the loaded text view
     */
    private fun getTextView(view: View?, textResource: Int): TextView? {
        return if (textResource == NO_RESOURCE && view is TextView) {
            view
        } else if (textResource != NO_RESOURCE) {
            view?.findViewById<TextView>(textResource)
        } else {
            null
        }
    }

    /**
     * Loads view from resources
     * @param resource the resource Id
     * @return the loaded view or null if resource is not set
     */
    private fun getView(resource: Int, parent: ViewGroup?): View? {
        if (parent == null) {
            return null
        }
        if (NO_RESOURCE == resource) {
            return null
        } else if (TEXT_VIEW_ITEM_RESOURCE == resource) {
            return TextView(context)
        } else {
            return inflater?.inflate(resource, parent, false)
        }
    }

    protected abstract fun getItemText(index: Int): CharSequence?
}