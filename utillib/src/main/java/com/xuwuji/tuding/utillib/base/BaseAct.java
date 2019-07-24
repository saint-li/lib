package com.xuwuji.tuding.utillib.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.xuwuji.tuding.utillib.util.ActivityUtil;
import com.xuwuji.tuding.utillib.util.AppUtil;
import com.xuwuji.tuding.utillib.widget.MyActionBar;

/**
 * `Author: Administrator
 * Time:
 * ReadMe:
 */
public abstract class BaseAct extends AppCompatActivity {
    protected BaseAct act;

    protected MyActionBar mActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (setTransparent())
            AppUtil.setAlphaTitle(this);
        ActivityUtil.getInstance().addActivity(this);
        AppUtil.setAndroidNativeLightStatusBar(this, true);
        act = this;
        setContentView(setLayout());
//        setFitsSystemWindows();
        getIntentData();
        initTitleView();
        initView();
        initListener();
        initData();
    }

    protected boolean setTransparent() {
        return true;
    }

    /**
     * 设置内容布局
     */
    protected abstract int setLayout();

    /**
     * 初始化标题栏
     */
    protected void initTitleView() {
    }

    /**
     * 初始化布局
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化监听
     */
    protected void initListener() {

    }


    /**
     * 获取intent携带数据
     */
    protected void getIntentData() {

    }

    /**
     * 数据处理
     */
    protected void handleMessage(Message msg) {
    }

    @Override
    protected void onDestroy() {
        ActivityUtil.getInstance().removeActivity(this);
        super.onDestroy();
    }

    protected boolean isDispatch() {
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!isDispatch()) return super.dispatchTouchEvent(ev);
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    assert v != null;
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    getWindow().getDecorView().setClickable(true);
                    getWindow().getDecorView().setFocusable(true);
                    getWindow().getDecorView().setFocusableInTouchMode(true);
                    getWindow().getDecorView().requestFocusFromTouch();
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return super.dispatchTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    private void setFitsSystemWindows() {
        // 华为,OPPO机型在StatusBarUtil.setLightStatusBar后布局被顶到状态栏上去了
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View content = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
            if (content != null) {
                content.setFitsSystemWindows(true);
            }
        }
    }

    public void showAct(Class clazz) {
        act.startActivity(new Intent(act, clazz));
    }

    public void showAct(Class clazz, String key, String value) {
        Intent intent = new Intent(act, clazz);
        intent.putExtra(key, value);
        act.startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }
}