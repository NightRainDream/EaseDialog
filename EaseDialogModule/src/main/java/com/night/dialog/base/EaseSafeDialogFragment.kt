package com.night.dialog.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.night.dialog.R
import com.night.dialog.entity.TextInfoEntity
import com.night.dialog.tools.DialogHelp
import com.night.dialog.tools.LogcatToos

abstract class EaseSafeDialogFragment<VM : EaseBaseViewModel> : DialogFragment() {
    //标题属性
    private var mTitleTextInfo: TextInfoEntity? = null

    //提示文字属性
    private var mMainTextInfo: TextInfoEntity? = null

    //取消按钮属性
    private var mCancelTextInfo: TextInfoEntity? = null

    //确定按钮属性
    private var mPositiveTextInfo: TextInfoEntity? = null

    //ViewModel
    protected lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogcatToos.w("EaseSafeDialog==> onCreate()")
        mViewModel = ViewModelProvider(this)[initViewModel()]
        mTitleTextInfo?.let {
            LogcatToos.w("EaseSafeDialog==> 初始化标题属性")
            mViewModel.setTitleTextInfo(it)
        }
        mMainTextInfo?.let {
            LogcatToos.w("EaseSafeDialog==> 初始化提示文字属性")
            mViewModel.setMainTextInfo(it)
        }
        mCancelTextInfo?.let {
            LogcatToos.w("EaseSafeDialog==> 初始化取消按钮属性")
            mViewModel.setCancelTextInfo(it)
        }
        mPositiveTextInfo?.let {
            LogcatToos.w("EaseSafeDialog==> 初始化确定按钮属性")
            mViewModel.setPositiveTextInfo(it)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        LogcatToos.w("EaseSafeDialog==> onCreateView()")
        return inflater.inflate(initLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogcatToos.w("EaseSafeDialog==> onViewCreated()")
        initView(view, savedInstanceState)
        initAdapter(savedInstanceState)
        initListener(savedInstanceState)
        initData(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        LogcatToos.w("EaseSafeDialog==> onCreateDialog()")
        return EaseSafeDialog(requireContext(), R.style.BaseDialog)
    }

    override fun onStart() {
        LogcatToos.w("onStart")
        val mWindow = dialog?.window
        mWindow ?: return
        val attributes = mWindow.attributes
        //设置Dialog窗口的高度
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT
        //设置Dialog窗口的宽度
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT
        //设置Dialog的居中方向
        attributes.gravity = initGravity()
        //设置Dialog弹出时背景的透明度
        attributes.dimAmount = 0.5f
        //设置Dialog水平方向的间距
        attributes.horizontalMargin = 0f
        //设置Dialog垂直方向的间距
        attributes.verticalMargin = 0f
        //设置Dialog显示时X轴的坐标,具体屏幕X轴的偏移量
        attributes.x = 0
        //设置Dialog显示时Y轴的坐标,距离屏幕Y轴的偏移量
        attributes.y = 0
        //覆盖默认背景
        mWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //Dialog动画
        attributes.windowAnimations = initAnimations()
        mWindow.attributes = attributes
        //是否可取消
        isCancelable = isCancel()
        super.onStart()
    }

    override fun dismiss() {
        LogcatToos.w("EaseSafeDialog==> dismiss()")
        this.dismissAllowingStateLoss()
    }


    @LayoutRes
    abstract fun initLayout(): Int

    abstract fun initViewModel(): Class<VM>

    abstract fun initView(view: View, savedInstanceState: Bundle?)

    abstract fun initAdapter(savedInstanceState: Bundle?)

    abstract fun initListener(savedInstanceState: Bundle?)

    abstract fun initData(savedInstanceState: Bundle?)

    abstract fun initGravity(): Int

    abstract fun initAnimations(): Int

    abstract fun isCancel(): Boolean

    protected fun setViewParameter(view: TextView?, textInfo: TextInfoEntity?) {
        if (view == null || textInfo == null) {
            return
        }
        DialogHelp.setTextViewInfo(view, textInfo)
    }

    /**
     * 设置标题TextView属性
     *
     * @param title TextView属性[TextInfoEntity]
     * @return DialogBuilder
     */
    fun setTitleTextInfo(title: TextInfoEntity) {
        this.mTitleTextInfo = title
    }


    /**
     * 设置内容TextView属性
     *
     * @param main TextView属性[TextInfoEntity]
     * @return DialogBuilder
     */
    fun setMainTextInfo(main: TextInfoEntity) {
        this.mMainTextInfo = main
    }


    /**
     * 设置取消按钮TextView属性
     *
     * @param cancel TextView属性[TextInfoEntity]
     * @return DialogBuilder
     */
    fun setCancelTextInfo(cancel: TextInfoEntity) {
        this.mCancelTextInfo = cancel
    }


    /**
     * 设置确定按钮TextView属性
     *
     * @param positive TextView属性[TextInfoEntity]
     * @return DialogBuilder
     */
    fun setPositiveTextInfo(positive: TextInfoEntity) {
        this.mPositiveTextInfo = positive
    }
}