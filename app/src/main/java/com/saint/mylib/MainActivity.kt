package com.saint.mylib

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.xuwuji.tuding.utillib.base.BaseAct
import com.xuwuji.tuding.utillib.util.AppLog

class MainActivity : BaseAct() {

    override fun setLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

    }

    override fun initData() {
    }


    val url = "tudingteacher://splash"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppLog.e("sdfasdf adf   sdfadf  LIb 测试")
    }

    fun click(v: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }


}
