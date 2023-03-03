package com.night.dialog

import android.app.Activity
import android.view.Gravity
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.night.dialog.adapter.MenuDialogAdapter
import com.night.dialog.adapter.MultipleMenuDialogAdapter
import com.night.dialog.adapter.SingleMenuDialogAdapter
import com.night.dialog.base.BaseBuilderDialog
import com.night.dialog.callback.IBindDialogView
import com.night.dialog.callback.IDialogActionCallback
import com.night.dialog.entity.MenuInfoEntity
import com.night.dialog.base.BaseDialog

object DialogTools {
    /**
     * 弹出自定义Dialog
     *
     * @param activity Activity
     * @param bindView IBindView[IBindDialogView]
     * @param isCancel 点击遮罩和返回按钮是否可取消
     */
    fun showCustom(activity: Activity, bindView: IBindDialogView, isCancel: Boolean = true) {
        val mBaseDialog = BaseDialog(activity)
        mBaseDialog.setContentView(bindView.mLayoutId)
        mBaseDialog.setCancelable(isCancel)
        bindView.onBind(mBaseDialog)
        mBaseDialog.show()
    }

    /**
     * 构建提示性质Dialog
     */
    class Builder : BaseBuilderDialog() {
        override fun buildTipDialog(activity: Activity, title: String, msg: String, callback: IDialogActionCallback?) {
            showCustom(activity, object : IBindDialogView(R.layout.dialog_tip) {
                override fun onBind(dialog: BaseDialog) {
                    dialog.setDialogWidth()
                    val mRootView = dialog.findViewById<ViewGroup>(R.id.ll_tip)
                    val mTitleView = dialog.findViewById<AppCompatTextView>(R.id.tv_tip_title)
                    val mContentView = dialog.findViewById<AppCompatTextView>(R.id.tv_tip_content)
                    val mCancelView = dialog.findViewById<AppCompatTextView>(R.id.tv_tip_cancel)
                    val mPositiveView = dialog.findViewById<AppCompatTextView>(R.id.tv_tip_positive)
                    //背景
                    mRootView.background = DialogHelp.getDialogBackground()
                    //标题
                    initTitleTextView(mTitleView, title)
                    //内容
                    initContentTextView(mContentView, msg)
                    //取消按钮
                    initCancelTextView(mCancelView)
                    //积极按钮
                    initPositiveTextView(mPositiveView)
                    //监听
                    mCancelView.setOnClickListener {
                        dialog.dismiss()
                        callback?.onCancel()
                    }
                    mPositiveView.setOnClickListener {
                        dialog.dismiss()
                        callback?.onPositive("",mutableListOf())
                    }
                    dialog.setOnDismissListener {
                        callback?.onDismiss()
                    }
                }
            }, isCancel)
        }

        override fun buildWarnDialog(activity: Activity, msg: String, callback: IDialogActionCallback?) {
            showCustom(activity, object : IBindDialogView(R.layout.dialog_warn) {
                override fun onBind(dialog: BaseDialog) {
                    dialog.setDialogWidth()
                    val mRootView = dialog.findViewById<ViewGroup>(R.id.ll_warn)
                    val mContentView = dialog.findViewById<AppCompatTextView>(R.id.tv_warn_content)
                    val mCancelView = dialog.findViewById<AppCompatTextView>(R.id.tv_warn_cancel)
                    val mPositiveView = dialog.findViewById<AppCompatTextView>(R.id.tv_warn_positive)
                    //背景
                    mRootView.background = DialogHelp.getDialogBackground()
                    //内容
                    initContentTextView(mContentView, msg)
                    //取消按钮
                    initCancelTextView(mCancelView)
                    //积极按钮
                    initPositiveTextView(mPositiveView)
                    //监听
                    mCancelView.setOnClickListener {
                        dialog.dismiss()
                        callback?.onCancel()
                    }
                    mPositiveView.setOnClickListener {
                        dialog.dismiss()
                        callback?.onPositive("", mutableListOf())
                    }
                    dialog.setOnDismissListener {
                        callback?.onDismiss()
                    }
                }
            }, isCancel)
        }

        override fun buildSingleDialog(activity: Activity,title: String,menu: MutableList<String>, defIndex: Int, callback: IDialogActionCallback?) {
            showCustom(activity, object : IBindDialogView(R.layout.dialog_bottom_menu) {
                override fun onBind(dialog: BaseDialog) {
                    dialog.setDialogWidth(1F)
                    dialog.setGravity(Gravity.BOTTOM)
                    val mRoomView = dialog.findViewById<ViewGroup>(R.id.ll_single)
                    val mTitleView = dialog.findViewById<AppCompatTextView>(R.id.tv_single_title)
                    val mContentView = dialog.findViewById<RecyclerView>(R.id.rv_single)
                    val mPositiveView = dialog.findViewById<AppCompatTextView>(R.id.tv_menu_ok)
                    val mCancelView = dialog.findViewById<AppCompatTextView>(R.id.tv_menu_cancel)
                    mRoomView.background = DialogHelp.getBottomDialogBackground()
                    //Title
                    initTitleTextView(mTitleView, title)
                    //Content
                    val adapter = SingleMenuDialogAdapter(activity, menu)
                    mContentView.layoutManager = LinearLayoutManager(activity)
                    mContentView.adapter = adapter
                    adapter.setSelectPosition(defIndex)
                    //Positive
                    initPositiveTextView(mPositiveView)
                    //Cancel
                    initCancelTextView(mCancelView)
                    //监听
                    mPositiveView?.setOnClickListener {
                        dialog.dismiss()
                        if (adapter.getSelectPosition() != -1) {
                            val mSelectText = adapter.getSelectDate()
                            val mSelectIndex = adapter.getSelectPosition()
                            callback?.onPositive(mSelectText, mutableListOf(mSelectIndex))
                        }
                    }
                    mCancelView?.setOnClickListener {
                        dialog.dismiss()
                        callback?.onCancel()
                    }
                    dialog.setOnDismissListener {
                        callback?.onDismiss()
                    }
                }
            },isCancel)
        }

        override fun buildMultipleDialog(activity: Activity,title: String,menu: MutableList<String>,defSelect: MutableList<Int>, callback: IDialogActionCallback?) {
            showCustom(activity, object : IBindDialogView(R.layout.dialog_bottom_menu) {
                override fun onBind(dialog: BaseDialog) {
                    dialog.setDialogWidth(1F)
                    dialog.setGravity(Gravity.BOTTOM)
                    val mRoomView = dialog.findViewById<ViewGroup>(R.id.ll_single)
                    val mTitleView = dialog.findViewById<AppCompatTextView>(R.id.tv_single_title)
                    val mContentView = dialog.findViewById<RecyclerView>(R.id.rv_single)
                    val mPositiveView = dialog.findViewById<AppCompatTextView>(R.id.tv_menu_ok)
                    val mCancelView = dialog.findViewById<AppCompatTextView>(R.id.tv_menu_cancel)
                    mRoomView.background = DialogHelp.getBottomDialogBackground()
                    //Title
                    initTitleTextView(mTitleView, title)
                    //Content
                    val adapter = MultipleMenuDialogAdapter(activity, menu,defSelect)
                    mContentView.layoutManager = LinearLayoutManager(activity)
                    mContentView.adapter = adapter
                    //Positive
                    initPositiveTextView(mPositiveView)
                    //Cancel
                    initCancelTextView(mCancelView)
                    //监听
                    mPositiveView?.setOnClickListener {
                        dialog.dismiss()
                        callback?.onPositive("",adapter.getSelectIndex())
                    }
                    mCancelView?.setOnClickListener {
                        dialog.dismiss()
                        callback?.onCancel()
                    }
                    dialog.setOnDismissListener {
                        callback?.onDismiss()
                    }
                }
            },isCancel)
        }

        override fun buildMenuDialog(activity: Activity,menu: MutableList<MenuInfoEntity>,callback: IDialogActionCallback?) {
            showCustom(activity, object : IBindDialogView(R.layout.dialog_menu) {
                override fun onBind(dialog: BaseDialog) {
                    dialog.setDialogWidth(1F)
                    dialog.setGravity(Gravity.BOTTOM)
                    val mRoomView = dialog.findViewById<ViewGroup>(R.id.ll_menu)
                    val mContentView = dialog.findViewById<RecyclerView>(R.id.rv_menu_content)
                    mRoomView.background = DialogHelp.getBottomDialogBackground()
                    //Content
                    val adapter = MenuDialogAdapter(menu)
                    mContentView.layoutManager = LinearLayoutManager(activity)
                    mContentView.adapter = adapter
                    dialog.setOnDismissListener {
                        callback?.onDismiss()
                    }
                    adapter.setOnItemClickListener(object : MenuDialogAdapter.OnItemClickListener {
                        override fun onItemClick(title: String, index: Int) {
                            dialog.dismiss()
                            callback?.onPositive(title, mutableListOf(index))
                        }
                    })
                }

            }, true)
        }
    }
}