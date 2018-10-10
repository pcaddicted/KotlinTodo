package com.wb.kotlin

import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.ViewTarget
import com.wb.kotlin.base.Preferences
import com.wb.kotlin.config.Config
import com.wb.kotlin.fragment.MainFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var glide: ViewTarget<ImageView, Drawable>
    private val username: String by Preferences(Config.USERNAME_KEY, "")
    var currentIndex = 0   // 当前Fragment
    var doneFragment: MainFragment? = null
    var notDoFragment: MainFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        toolbar.run {
            setSupportActionBar(this)
//            supportActionBar!!.setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
            supportActionBar!!.setDisplayShowTitleEnabled(false)
        }

        drawer_layout.run {
            val toggle = ActionBarDrawerToggle(
                    this@MainActivity,
                    this,
                    toolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close
            )

            this.addDrawerListener(toggle)
            toggle.syncState()
        }

        glide = Glide
                .with(this)
                .load(Config.NAV_HEADER_IMG)
                .apply(RequestOptions.bitmapTransform(CircleCrop())) // 圆形处理
                .into(nav_view.getHeaderView(0).findViewById(R.id.iv_nav_header))


        nav_view.run {
            getHeaderView(0).findViewById<TextView>(R.id.tv_nav_header).text = username

            setNavigationItemSelectedListener{
                when(it.itemId){
                    R.id.nav_exit -> {
                        Preferences.clear();
                        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                        finish()
                    }
                }
                return@setNavigationItemSelectedListener true
            }
        }

    }

}
