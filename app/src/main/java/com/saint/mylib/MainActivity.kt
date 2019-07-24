package com.saint.mylib

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.xuwuji.tuding.utillib.base.BaseAct
import com.xuwuji.tuding.utillib.util.AppLog
import com.xuwuji.tuding.utillib.util.AppToast
import com.xuwuji.tuding.utillib.util.PermissionUtil
import com.xuwuji.tuding.utillib.util.PermissionUtil.showSettingDialog
import com.yanzhenjie.permission.Action
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.Permission

class MainActivity : BaseAct() {

    private val permissions = arrayOf(
//        Permission.ACCESS_COARSE_LOCATION,
//        Permission.ACCESS_FINE_LOCATION,
//        Permission.READ_EXTERNAL_STORAGE,
//        Permission.WRITE_EXTERNAL_STORAGE,
//        Permission.READ_PHONE_STATE,
//        Permission.CAMERA,
//        Permission.CALL_PHONE,
        Permission.RECORD_AUDIO
    )

    override fun setLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

    }

    override fun initData() {
        requestPermission()
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


    private fun requestPermission() {
        PermissionUtil.request(act
            , Action<List<String>> { data ->
                if (AndPermission.hasAlwaysDeniedPermission(act, data)) {
                    AppToast.tShort("全部权限已开启！")
                }
            }
            , Action<List<String>> { data ->
                if (AndPermission.hasAlwaysDeniedPermission(act, data)) {
                    showSettingDialog(act, data, 0x1234)
                }
            }
            , *permissions)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 0x1234) {
            requestPermission()
        }
    }


}
