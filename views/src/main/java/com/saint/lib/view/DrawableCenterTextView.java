package com.saint.lib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class DrawableCenterTextView extends androidx.appcompat.widget.AppCompatTextView {
    public DrawableCenterTextView(Context context) {
        super(context);
    }

    public DrawableCenterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableCenterTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //获取左边的图片
        Drawable drawableLeft = getCompoundDrawables()[0];
        if (drawableLeft != null) {
            //取得字符串的宽度值
            float textWidth = getPaint().measureText(getText().toString());
            //获取控件的内边距
            int drawablePadding = getCompoundDrawablePadding();
            int drawableWidth;
            //返回图片呢的固有宽度,单位是DP
            drawableWidth = drawableLeft.getIntrinsicWidth();
            float bodyWidth = textWidth + drawableWidth + drawablePadding;
            canvas.translate((getWidth() - bodyWidth) / 2, 0);
        }
        Drawable drawableRight = getCompoundDrawables()[2];
        if (drawableRight != null) {
            float textWidth = getPaint().measureText(getText().toString());
            int drawablePadding = getCompoundDrawablePadding();
            int drawableWidth = 0;
            drawableWidth = drawableRight.getIntrinsicWidth();
            float bodyWidth = textWidth + drawableWidth + drawablePadding;
            setPadding(0, getPaddingTop(), (int) (getWidth() - bodyWidth), getPaddingBottom());
            canvas.translate((getWidth() - bodyWidth) / 2, 0);
        }
        super.onDraw(canvas);
    }
}
