package com.night.easedialog

import android.app.Application
import com.night.dialog.EaseDialog

class MeApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        EaseDialog.initialize(this)
    }
}