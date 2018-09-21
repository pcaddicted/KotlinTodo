package com.wb.kotlin.base

import android.app.Application

/**
 * Created by 文博 on 2018/9/21
 */
class BaseApplication:Application(){
    override fun onCreate() {
        super.onCreate()
        Preferences.setContext(applicationContext);
    }
}