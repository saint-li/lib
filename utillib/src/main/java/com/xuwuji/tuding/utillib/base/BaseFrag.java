package com.xuwuji.tuding.utillib.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xuwuji.tuding.utillib.widget.MyActionBar;

public abstract class BaseFrag extends Fragment {
    protected Fragment frag;
    protected MyActionBar mActionBar;
    protected View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        frag = this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(setLayout(), container, false);
        initTitleView();
        initView();
        initData();
        initListener();
        return rootView;
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
     * 数据处理
     */
    protected void handleMessage(Message msg) {
    }

    public <T extends View> T findView(int resId) {
        return (T) rootView.findViewById(resId);
    }


    public void showAct(Class clazz) {
        Activity act = getActivity();
        if (act != null) {
            act.startActivity(new Intent(act, clazz));
        }
    }

    public void showAct(Class clazz, String key, String value) {
        Activity act = getActivity();
        if (act != null) {
            Intent intent = new Intent(act, clazz);
            intent.putExtra(key, value);
            act.startActivity(intent);
        }
    }

    public void showAct(Class clazz, String key, long value) {
        Activity act = getActivity();
        if (act != null) {
            Intent intent = new Intent(act, clazz);
            intent.putExtra(key, value);
            act.startActivity(intent);
        }
    }

    public void showAct(Class clazz, String key, int value) {
        Activity act = getActivity();
        if (act != null) {
            Intent intent = new Intent(act, clazz);
            intent.putExtra(key, value);
            act.startActivity(intent);
        }
    }
}
