package com.saint.lib.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * @author Saint  2020/10/22
 * @desc TODO
 */
public class ScrollLinearLayoutManager extends LinearLayoutManager {

    private boolean isScrollEnable = true;

    public ScrollLinearLayoutManager(Context context) {
        super(context);
    }

    public ScrollLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public ScrollLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnable && super.canScrollVertically();
    }

    /**
     * 设置 RecyclerView 是否可以垂直滑动
     *
     * @param isEnable
     */
    public void setScrollEnable(boolean isEnable) {
        this.isScrollEnable = isEnable;
    }
}