package com.wb.kotlin

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.wb.kotlin.base.BaseActivity

/**
 * Created by 文博 on 2018/9/20
 */
class LoginActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initToolbar()
    }

    private fun initToolbar() {
        findViewById<Toolbar>(R.id.toolbar).title = "登录"
    }
}