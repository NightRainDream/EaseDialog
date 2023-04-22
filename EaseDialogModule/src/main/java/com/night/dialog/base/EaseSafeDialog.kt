package com.night.dialog.base

import android.app.Dialog
import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.night.dialog.tools.LogcatToos
import java.lang.ref.WeakReference

internal class EaseSafeDialog(context: Context, themeResId: Int) : Dialog(context,themeResId) {
    private val mWeakContext = WeakReference(context)

    override fun show() {
        LogcatToos.w("准备弹出SafeDialog")
        val mContext = mWeakContext.get() ?: return
        if(mContext !is FragmentActivity){
            LogcatToos.w("SafeDialog 引用错误上下文")
            return
        }
        if(mContext.isFinishing || mContext.isDestroyed){
            LogcatToos.w("SafeDialog 引用已销毁Activity")
            return
        }
        LogcatToos.w("安全弹出SafeDialog")
        super.show()
    }

    override fun dismiss() {
        LogcatToos.w("准备关闭SafeDialog")
        val mContext = mWeakContext.get() ?: return
        if(mContext !is FragmentActivity){
            LogcatToos.w("SafeDialog 引用错误上下文")
            return
        }
        if(mContext.isFinishing || mContext.isDestroyed){
            LogcatToos.w("SafeDialog 引用已销毁Activity")
            return
        }
        if(!isShowing){
            LogcatToos.w("SafeDialog 重复调用dismiss")
            return
        }
        LogcatToos.w("安全关闭SafeDialog")
        super.dismiss()
    }
}