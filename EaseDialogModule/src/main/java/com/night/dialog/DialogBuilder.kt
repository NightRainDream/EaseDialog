package com.night.dialog

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.night.dialog.adapter.PopupMenuAdapter
import com.night.dialog.base.BaseDialogBuilder
import com.night.dialog.base.BasePopupWindow
import com.night.dialog.callback.IDialogActionCallback
import com.night.dialog.entity.TextInfoEntity
import com.night.dialog.tools.DialogHelp
import com.night.dialog.tools.PopMenuHelp
import com.night.dialog.ui.loading.LoadingDialogFragment
import com.night.dialog.ui.multiple.MultipleMenuDialogFragment
import com.night.dialog.ui.sing.SingleMenuDialogFragment
import com.night.dialog.ui.tips.TipsDialogFragment
import com.night.dialog.ui.warn.WarnDialogFragment

class DialogBuilder : BaseDialogBuilder() {
    private val mPopMenuHelp by lazy {
        PopMenuHelp()
    }

    /**
     * 设置标题TextView属性
     *
     * @param title TextView属性[TextInfoEntity]
     * @return DialogBuilder
     */
    fun setTitleTextInfo(title: TextInfoEntity): DialogBuilder {
        this.mTitleTextInfo = title
        return this
    }

    /**
     * 设置标题内容
     *
     * @param title 标题内容
     */
    fun setTitleText(title: String?): DialogBuilder {
        if (title != null) {
            this.mTitleTextInfo.text = title
        }
        return this
    }

    /**
     * 设置标题内容颜色
     *
     * @param title 标题内容颜色
     */
    fun setTitleTextColor(@ColorInt title: Int): DialogBuilder {
        this.mTitleTextInfo.textColor = title
        return this
    }

    /**
     * 设置内容TextView属性
     *
     * @param main TextView属性[TextInfoEntity]
     * @return DialogBuilder
     */
    fun setMainTextInfo(main: TextInfoEntity): DialogBuilder {
        this.mMainTextInfo = main
        return this
    }

    /**
     * 设置主要内容
     *
     * @param main 主要内容
     */
    fun setMainText(main: String?): DialogBuilder {
        if (main != null) {
            this.mMainTextInfo.text = main
        }
        return this
    }

    /**
     * 设置主要内容颜色
     *
     * @param main 主要内容颜色
     */
    fun setMainTextColor(@ColorInt main: Int): DialogBuilder {
        this.mMainTextInfo.textColor = main
        return this
    }

    /**
     * 设置取消按钮TextView属性
     *
     * @param cancel TextView属性[TextInfoEntity]
     * @return DialogBuilder
     */
    fun setCancelTextInfo(cancel: TextInfoEntity): DialogBuilder {
        this.mCancelTextInfo = cancel
        return this
    }

    /**
     * 设置取消按钮文本
     *
     * @param cancel 取消按钮文本
     */
    fun setCancelText(cancel: String?): DialogBuilder {
        if (cancel != null) {
            this.mCancelTextInfo.text = cancel
        }
        return this
    }

    /**
     * 设置取消按钮文本颜色
     *
     * @param cancel 取消按钮文本颜色
     */
    fun setCancelTextColor(@ColorInt cancel: Int): DialogBuilder {
        this.mCancelTextInfo.textColor = cancel
        return this
    }

    /**
     * 设置确定按钮TextView属性
     *
     * @param positive TextView属性[TextInfoEntity]
     * @return DialogBuilder
     */
    fun setPositiveTextInfo(positive: TextInfoEntity): DialogBuilder {
        this.mPositiveTextInfo = positive
        return this
    }

    /**
     * 设置确认按钮文本
     *
     * @param positive 确认按钮文本
     */
    fun setPositiveText(positive: String?): DialogBuilder {
        if (null != positive) {
            this.mPositiveTextInfo.text = positive
        }
        return this
    }

    /**
     * 设置确认按钮文本颜色
     *
     * @param positive 确认按钮文本颜色
     */
    fun setPositiveTextColor(@ColorInt positive: Int): DialogBuilder {
        this.mPositiveTextInfo.textColor = positive
        return this
    }


    /**
     * 构建提示性质对话框
     *
     * @param activity Activity
     */
    fun toTipsDialog(activity: AppCompatActivity, callback: IDialogActionCallback?) {
        val mFragmentManage = activity.supportFragmentManager
        val mHistoryDialog = mFragmentManage.findFragmentByTag("TipsDialog")
        if (mHistoryDialog != null && mHistoryDialog is TipsDialogFragment) {
            mHistoryDialog.dismiss()
        }
        val mTipsDialog = TipsDialogFragment()
        mTipsDialog.setTitleTextInfo(mTitleTextInfo)
        mTipsDialog.setMainTextInfo(mMainTextInfo)
        mTipsDialog.setCancelTextInfo(mCancelTextInfo)
        mTipsDialog.setPositiveTextInfo(mPositiveTextInfo)
        mTipsDialog.setCallback(callback)
        mTipsDialog.show(mFragmentManage, "TipsDialog")
    }

    /**
     * 构建警告性质对话框
     *
     * @param activity Activity
     */
    fun toWarnDialog(activity: AppCompatActivity, callback: IDialogActionCallback?) {
        val mFragmentManage = activity.supportFragmentManager
        val mHistoryDialog = mFragmentManage.findFragmentByTag("WarnDialog")
        if (mHistoryDialog != null && mHistoryDialog is WarnDialogFragment) {
            mHistoryDialog.dismiss()
        }
        val mWarnDialog = WarnDialogFragment()
        mWarnDialog.setMainTextInfo(mMainTextInfo)
        mWarnDialog.setCancelTextInfo(mCancelTextInfo)
        mWarnDialog.setPositiveTextInfo(mPositiveTextInfo)
        mWarnDialog.setCallback(callback)
        mWarnDialog.showNow(mFragmentManage, "WarnDialog")
    }

    /**
     * 构建加载框
     *
     * @param activity Activity
     * @param msg 提示内容
     */
    fun toLoadingDialog(activity: AppCompatActivity, msg: String = DialogHelp.getString(R.string.loading),callback: IDialogActionCallback?=null) {
        val mFragmentManage = activity.supportFragmentManager
        val mHistoryDialog = mFragmentManage.findFragmentByTag("LoadingDialog")
        if (mHistoryDialog != null && mHistoryDialog is LoadingDialogFragment) {
            mHistoryDialog.dismiss()
        }
        val mLoadingDialog = LoadingDialogFragment()
        setMainText(msg)
        mLoadingDialog.setMainTextInfo(mMainTextInfo)
        mLoadingDialog.setCallback(callback)
        mLoadingDialog.showNow(mFragmentManage, "LoadingDialog")
    }

    /**
     * 构建单选菜单
     *
     * @param activity Activity
     * @param menu 菜单内容
     * @param defIndex 默认选中位置
     * @param callback 状态回调[IDialogActionCallback]
     */
    fun toSingleMenu(
        activity: AppCompatActivity,
        menu: MutableList<String>,
        defIndex: Int = -1,
        callback: IDialogActionCallback?
    ) {
        val mFragmentManage = activity.supportFragmentManager
        val mHistoryDialog = mFragmentManage.findFragmentByTag("SingleMenuDialog")
        if (mHistoryDialog != null && mHistoryDialog is SingleMenuDialogFragment) {
            mHistoryDialog.dismiss()
        }
        val mSingleMenuDialog = SingleMenuDialogFragment()
        mSingleMenuDialog.setTitleTextInfo(mTitleTextInfo)
        mSingleMenuDialog.setCancelTextInfo(mCancelTextInfo)
        mSingleMenuDialog.setMenuData(menu, defIndex)
        mSingleMenuDialog.setCallback(callback)
        mSingleMenuDialog.showNow(mFragmentManage, "SingleMenuDialog")
    }

    /**
     * 构建多选菜单
     *
     * @param activity Activity
     * @param menu 菜单内容
     * @param defIndex 默认选中位置
     * @param callback 状态回调[IDialogActionCallback]
     */
    fun toMultipleMenu(
        activity: AppCompatActivity,
        menu: MutableList<String>,
        defIndex: MutableList<Int>? = null,
        callback: IDialogActionCallback?
    ) {
        val mFragmentManage = activity.supportFragmentManager
        val mHistoryDialog = mFragmentManage.findFragmentByTag("MultipleMenuDialog")
        if (mHistoryDialog != null && mHistoryDialog is MultipleMenuDialogFragment) {
            mHistoryDialog.dismiss()
        }
        val mMultipleMenuDialog = MultipleMenuDialogFragment()
        mMultipleMenuDialog.setTitleTextInfo(mTitleTextInfo)
        mMultipleMenuDialog.setCancelTextInfo(mCancelTextInfo)
        mMultipleMenuDialog.setMenuData(menu, defIndex)
        mMultipleMenuDialog.setCallback(callback)
        mMultipleMenuDialog.showNow(mFragmentManage, "MultipleMenuDialog")
    }



    /**
     * 设置PopMenu弹出坐标
     *
     * @param x 相对父容器X轴坐标
     * @param y 相对父容器Y轴坐标
     * @param rawX 相对屏幕X轴坐标
     * @param rawY 相对屏幕Y轴坐标
     */
    fun setTouchCoordinate(x: Float, y: Float, rawX: Float, rawY: Float): DialogBuilder {
        mPopMenuHelp.setTouchCoordinate(x, y, rawX, rawY)
        return this
    }
//
//    /**
//     * 构建提示性质对话框
//     *
//     * @param activity Activity
//     * @param callback 状态回调[IDialogActionCallback]
//     */
//    fun toTipsDialog(activity: AppCompatActivity) {
//        val mFragmentManage = activity.supportFragmentManager
//        val mHistoryDialog = mFragmentManage.findFragmentByTag("TipsDialog")
//        if (mHistoryDialog == null) {
//            TipsDialog().showNow(mFragmentManage, "TipsDialog")
//        } else {
//            mFragmentManage.beginTransaction().show(mHistoryDialog).commitNow()
//        }
//    }
//
//
//    /**
//     * 构建警告性质对话框
//     *
//     * @param activity Activity
//     * @param callback 状态回调[IDialogActionCallback]
//     */
//    fun toWarnDialog(activity: Activity, callback: IDialogActionCallback? = null) {
//        DialogTools.showDialog(activity, object : IBindDialogView(R.layout.ease_layout_dialog_warn) {
//            override fun onBind(dialog: BaseDialog) {
//                val mContentView = dialog.findViewById<AppCompatTextView>(R.id.tv_warn_content)
//                val mCancelView = dialog.findViewById<AppCompatTextView>(R.id.tv_warn_cancel)
//                val mPositiveView = dialog.findViewById<AppCompatTextView>(R.id.tv_warn_positive)
//                //设置对话框内容参数
//                setMainParameter(mContentView)
//                //设置取消按钮参数
//                setCancelParameter(mCancelView)
//                //设置确认按钮参数
//                setPositiveParameter(mPositiveView)
//
//                mCancelView.setOnClickListener {
//                    dialog.dismiss()
//                    callback?.onCancel()
//                }
//                mPositiveView.setOnClickListener {
//                    dialog.dismiss()
//                    callback?.onPositive("", mutableListOf())
//                }
//                dialog.setOnDismissListener {
//                    callback?.onDismiss()
//                }
//            }
//
//        }, mStyle, mGravity, isCancel)
//    }
//
//    /**
//     * 构建单选菜单
//     *
//     * @param activity Activity
//     * @param menu 菜单内容
//     * @param defIndex 默认选中位置
//     * @param callback 状态回调[IDialogActionCallback]
//     */
//    fun toSingleMenu(
//        activity: Activity,
//        menu: MutableList<String>,
//        defIndex: Int = -1,
//        callback: IDialogActionCallback?
//    ) {
//        DialogTools.showDialog(activity, object : IBindDialogView(R.layout.ease_layout_dialog_single_menu) {
//            override fun onBind(dialog: BaseDialog) {
//                val mTitleView = dialog.findViewById<AppCompatTextView>(R.id.tv_menu_title)
//                val mContentView = dialog.findViewById<RecyclerView>(R.id.rv_menu_title)
//                val mCancelView = dialog.findViewById<AppCompatTextView>(R.id.rv_menu_cancel)
//                //设置标题参数
//                setTitleParameter(mTitleView)
//                //设置取消按钮参数
//                setCancelParameter(mCancelView)
//                val adapter = SingleMenuAdapter(menu)
//                mContentView.layoutManager = LinearLayoutManager(activity)
//                mContentView.adapter = adapter
//                mContentView.addItemDecoration(
//                    SmallDividerItem(
//                        DialogHelp.dpToPx(0.5F),
//                        DialogHelp.getColor(R.color.EaseColorDivider)
//                    )
//                )
//                adapter.setSelectPosition(defIndex)
//                val mMenuHeight = DialogHelp.getMaxMenuHeight()
//                val mMaxDisplayMenuSize = getMaxMenuSize(mMenuHeight)
//                if (menu.size > mMaxDisplayMenuSize) {
//                    val mLayoutParams = mContentView.layoutParams
//                    mLayoutParams.height = mMenuHeight.toInt()
//                    mContentView.layoutParams = mLayoutParams
//                }
//
//                //监听
//                adapter.setOnItemClickListener(object : SingleMenuAdapter.OnItemClickListener {
//                    override fun onItemClick(text: String, menuIndex: Int) {
//                        dialog.dismiss()
//                        callback?.onPositive(text, mutableListOf(menuIndex))
//                    }
//                })
//                mCancelView?.setOnClickListener {
//                    dialog.dismiss()
//                    callback?.onCancel()
//                }
//                dialog.setOnDismissListener {
//                    callback?.onDismiss()
//                }
//            }
//
//        }, mStyle, mGravity, isCancel)
//    }
//
//    /**
//     * 构建多选菜单
//     *
//     * @param activity Activity
//     * @param menu 菜单内容
//     * @param defIndex 默认选中位置
//     * @param callback 状态回调[IDialogActionCallback]
//     */
//    fun toMultipleMenu(
//        activity: Activity,
//        menu: MutableList<String>,
//        defIndex: MutableList<Int>? = null,
//        callback: IDialogActionCallback?
//    ) {
//        DialogTools.showDialog(activity, object : IBindDialogView(R.layout.ease_laoyut_dialog_multiple_menu) {
//            override fun onBind(dialog: BaseDialog) {
//                val mTitleView = dialog.findViewById<AppCompatTextView>(R.id.tv_menu_title)
//                val mContentView = dialog.findViewById<RecyclerView>(R.id.rv_menu_title)
//                val mCancelView = dialog.findViewById<AppCompatTextView>(R.id.tv_multiple_cancel)
//                val mPositiveView = dialog.findViewById<AppCompatTextView>(R.id.tv_multiple_positive)
//                //设置标题参数
//                setTitleParameter(mTitleView)
//                //设置取消按钮参数
//                setCancelParameter(mCancelView)
//                //设置确认按钮参数
//                setPositiveParameter(mPositiveView)
//                val adapter = MultipleMenuAdapter(menu, defIndex)
//                mContentView.layoutManager = LinearLayoutManager(activity)
//                mContentView.adapter = adapter
//                mContentView.addItemDecoration(
//                    SmallDividerItem(
//                        DialogHelp.dpToPx(0.5F),
//                        DialogHelp.getColor(R.color.EaseColorDivider)
//                    )
//                )
//                val mMenuHeight = DialogHelp.getMaxMenuHeight()
//                val mMaxDisplayMenuSize = getMaxMenuSize(mMenuHeight)
//                if (menu.size > mMaxDisplayMenuSize) {
//                    val mLayoutParams = mContentView.layoutParams
//                    mLayoutParams.height = mMenuHeight.toInt()
//                    mContentView.layoutParams = mLayoutParams
//                }
//
//                mPositiveView.setOnClickListener {
//                    dialog.dismiss()
//                    callback?.onPositive("", adapter.getSelectData())
//                }
//                mCancelView.setOnClickListener {
//                    dialog.dismiss()
//                    callback?.onCancel()
//                }
//                dialog.setOnDismissListener {
//                    callback?.onDismiss()
//                }
//            }
//        }, mStyle, mGravity, isCancel)
//    }
//
//    /**
//     * 构建加载框
//     *
//     * @param activity Activity
//     * @param msg 提示内容
//     */
//    fun toLoadingDialog(activity: Activity, msg: String = DialogHelp.getString(R.string.loading)) {
//        DialogTools.showDialog(activity, object : IBindDialogView(R.layout.ease_layout_dialog_loading) {
//            override fun onBind(dialog: BaseDialog) {
//                if (isCancel) {
//                    dialog.setCanceledOnTouchOutside(false)
//                }
//                val mLoadingTextView = dialog.findViewById<AppCompatTextView>(R.id.tv_loading)
//                mMainTextInfo.text = msg
//                //设置对话框内容参数
//                setMainParameter(mLoadingTextView)
//            }
//        }, R.style.CustomDialog, Gravity.CENTER, isCancel)
//    }
//
    fun toPopMenu(activity: Activity, anchor: View, menuList: MutableList<String>, callback: IDialogActionCallback) {
        val mPopMenu = BasePopupWindow(activity)
        mPopMenu.setBackgroundDrawable(ColorDrawable(DialogHelp.getColor(R.color.EaseColorBackground)))
        val contentView = LayoutInflater.from(activity).inflate(R.layout.ease_layout_custom_popup_menu, null)
        val mRecyclerView = contentView.findViewById<RecyclerView>(R.id.rv_popup)
        val mPopupMenuAdapter = PopupMenuAdapter(activity, menuList)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mPopupMenuAdapter
        mPopupMenuAdapter.setOnItemClickListener(object : PopupMenuAdapter.OnItemClickListener {
            override fun onItemClick(text: String, menu: Int) {
                mPopMenu.dismiss()
                callback.onPositive(text, mutableListOf(menu))
            }
        })
        val mContentSize = mPopMenuHelp.getContentViewSize(contentView)
        mPopMenu.width = mContentSize[0]
        mPopMenu.height = mContentSize[1]
        val mOffset = mPopMenuHelp.getDropDownOffset(contentView, anchor)
        mPopMenu.contentView = contentView
        mPopMenu.elevation = EaseDialog.getContext().resources.getDimension(R.dimen.dp_10)
        mPopMenu.showAsDropDown(anchor, mOffset[0], mOffset[1], Gravity.START)
    }
}