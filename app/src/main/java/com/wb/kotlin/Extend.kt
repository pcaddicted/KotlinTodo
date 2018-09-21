package com.wb.kotlin

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.wb.kotlin.config.Config
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by 文博 on 2018/9/21
 */

fun log(content: String) {
    Log.i("Wanzi", content)
}

fun Context.toast(content: String) {
    Config.showToast?.apply {
        setText(content)
        show()
    } ?: apply {
        Toast.makeText(this.applicationContext, content, Toast.LENGTH_SHORT).apply {
            Config.showToast = this
        }.show()
    }
}

fun getDate(): String {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
    return simpleDateFormat.format(Date().time)
}
