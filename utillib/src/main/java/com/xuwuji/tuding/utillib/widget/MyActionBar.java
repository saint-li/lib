package com.xuwuji.tuding.utillib.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xuwuji.tuding.utillib.AppConfig;
import com.xuwuji.tuding.utillib.R;
import com.xuwuji.tuding.utillib.util.AppUtil;

/**
 * `Author: Administrator
 * Time: 2018/6/6 16:26
 * ReadMe:
 */
public class MyActionBar extends LinearLayout {
    private View contentView;
    private RelativeLayout layRoot;
    private View vStatusBar;
    private ImageView ivLeft, ivRight;
    private TextView tvTitle, tvRight;
    private int colorTitleBG;


    public MyActionBar(Context context) {
        super(context);
    }

    public MyActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyActionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        setOrientation(VERTICAL);
        contentView = inflate(getContext(), R.layout.layout_base_title, this);
        vStatusBar = contentView.findViewById(R.id.view_status_bar);
        layRoot = contentView.findViewById(R.id.rl_title_parent);
        ivLeft = contentView.findViewById(R.id.iv_left);
        tvTitle = contentView.findViewById(R.id.tv_title);
        ivRight = contentView.findViewById(R.id.iv_right);
        tvRight = contentView.findViewById(R.id.tv_right);
        setStatusBarHeight();
        setTitleBarBG(R.color.bgActionBar);
    }

    /**
     * 设置状态栏高度
     */
    public void setStatusBarHeight() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;
        int height = AppUtil.getStatusBarHeight();
        ViewGroup.LayoutParams params = vStatusBar.getLayoutParams();
        params.height = height;
        vStatusBar.setLayoutParams(params);
    }

    /**
     * 设置状态栏高度
     */
    public void setStatusBarHeight(int height) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;
//        int height = AppUtil.getStatusBarHeight();
        ViewGroup.LayoutParams params = vStatusBar.getLayoutParams();
        params.height = height;
        vStatusBar.setLayoutParams(params);
    }


    public void setTitleBarBG(int colorRes) {
        if (colorRes == colorTitleBG) return;
        contentView.setBackground(new ColorDrawable(ContextCompat.getColor(getContext(), colorRes)));
        colorTitleBG = colorRes;
    }

    /**
     * 设置是否需要渐变
     *
     * @param translucent
     */
    public void setNeedTranslucent(boolean translucent) {
        if (translucent) {
            layRoot.setBackgroundDrawable(null);
        }
    }

    /**
     * 设置标题
     *
     * @param strTitle 标题
     */
    public void setTitle(String strTitle) {
        if (!TextUtils.isEmpty(strTitle)) {
            tvTitle.setText(strTitle);
            tvTitle.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
    }

    /**
     * 设置标题
     *
     * @param resId 标题资源ID
     */
    public void setTitle(int resId) {
        String strTitle = AppConfig.getApp().getResources().getString(resId);
        setTitle(strTitle);
    }

    public void setLeftListener(int iconRes, OnClickListener onClickListener) {
        if (ivLeft != null) {
            ivLeft.setImageResource(iconRes);
            ivLeft.setOnClickListener(onClickListener);
            ivLeft.setVisibility(VISIBLE);
        }
    }

    public void setLeftListener(OnClickListener onClickListener) {
        if (ivLeft != null) {
            ivLeft.setOnClickListener(onClickListener);
            ivLeft.setVisibility(VISIBLE);
        }
    }

    public void setRightListener(int iconRes, OnClickListener onClickListener) {
        if (ivRight != null) {
            ivRight.setImageResource(iconRes);
            ivRight.setOnClickListener(onClickListener);
            ivRight.setVisibility(VISIBLE);
            tvRight.setVisibility(GONE);
        }
    }

    public void setRightIcon(int iconRes) {
        if (ivRight != null) {
            ivRight.setImageResource(iconRes);
            ivRight.setVisibility(VISIBLE);
            tvRight.setVisibility(GONE);
        }
    }

    public void setRightTextListener(int textRes, OnClickListener listener) {
        if (tvRight != null) {
            tvRight.setText(textRes);
            tvRight.setOnClickListener(listener);
            tvRight.setVisibility(VISIBLE);
            ivRight.setVisibility(GONE);
        }
    }

    public void addView(View view) {
        if (layRoot != null) {
            layRoot.addView(view);
        }
    }


    public void setRightText(String text) {
        if (tvRight != null) {
            tvRight.setText(text);
            tvRight.setVisibility(VISIBLE);
            ivRight.setVisibility(GONE);
        }
    }
}