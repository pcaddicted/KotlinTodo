package com.wb.kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider


import com.wb.kotlin.base.BaseActivity
import com.wb.kotlin.bean.TodoDetail
import com.wb.kotlin.config.Config
import com.wb.kotlin.network.HttpResultFunction
import com.wb.kotlin.network.HttpSender
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.include_toolbar.*

/**
 * Created by 文博 on 2018/10/11
 */
class AddActivity :BaseActivity() {
    private var currentType = Config.TYPE_0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        initView()
    }

    private fun initView() {
        toolbar.run {
            setSupportActionBar(this)
        }

        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            title="Todo添加"
        }

        tv_date.run {
            text = getDate()
        }

        fab_add.run {
            setOnClickListener {
                if (checkContent()) {
                    addList()
                }
            }
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                currentType = p2
            }

        }
    }

    private fun addList() {
        HttpSender.instances.add(et_title.text.toString(), et_content.text.toString(), tv_date.text.toString(), currentType)
                .map(HttpResultFunction())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .`as`(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(object : Observer<TodoDetail> {
                    override fun onComplete() {
                        enableInteraction()
                        progressBar.visibility = View.GONE

                        setResult(Config.MAIN_ADD_REQUEST_CODE, Intent().putExtra(Config.INTENT_NAME_TYPE, currentType))
                        finish()
                    }

                    override fun onSubscribe(d: Disposable) {
                        disableInteraction()
                        progressBar.visibility = View.VISIBLE
                    }

                    override fun onNext(t: TodoDetail) {
                        toast("添加Todo成功！")
                    }

                    override fun onError(e: Throwable) {
                        enableInteraction()
                        progressBar.visibility = View.GONE
                        toast("添加Todo失败，:${e.message}")
                    }

                })
    }

    private fun checkContent(): Boolean {
        til_title.error = null
        til_content.error = null

        var cancel = false

        var focusView: View? = null

        val title = et_title.text.toString()

        if (title.isEmpty()) {
            til_title.error = "标题不能为空"
            focusView = til_title
            cancel = true
        }

        return if (cancel) {
            focusView?.requestFocus()
            false
        } else {
            true
        }
    }

}