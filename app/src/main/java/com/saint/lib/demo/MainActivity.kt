package com.saint.lib.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saint.lib.util.AppUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        my_action_bar.setTitle("测试")
        progress_view.autoChange("999", 0f, 1f, 1000)
        AppUtil.copyString("ssss")
    }
}