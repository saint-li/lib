package com.saint.lib.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatEditText;


/**
 * @author Saint  2020/9/16
 */
public class ClearRightEdit extends AppCompatEditText {
    private final int DRAWABLE_LEFT = 0;
    private final int DRAWABLE_TOP = 1;
    private final int DRAWABLE_RIGHT = 2;
    private Drawable mClearDrawable;
    private OnClickListener onClickListener;

    public ClearRightEdit(Context context) {
        super(context);
        init();
    }

    public ClearRightEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClearRightEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mClearDrawable = getResources().getDrawable(R.drawable.view_clear_24);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                Drawable drawable = getCompoundDrawables()[DRAWABLE_RIGHT];
                if (drawable != null && event.getX() <= (getWidth() - getPaddingRight())
                        && event.getX() >= (getWidth() - getPaddingRight() - drawable.getBounds().width())) {
                    setText("");
                    if (onClickListener != null) onClickListener.onClick(this);
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public void setOnClearListener(OnClickListener onClearListener) {
        onClickListener = onClearListener;
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        setClearIconVisible(text.length() > 0);
    }

    private void setClearIconVisible(boolean visible) {
        int DRAWABLE_BOTTOM = 3;
        setCompoundDrawablesWithIntrinsicBounds(getCompoundDrawables()[DRAWABLE_LEFT], getCompoundDrawables()[DRAWABLE_TOP],
                visible ? mClearDrawable : null, getCompoundDrawables()[DRAWABLE_BOTTOM]);
    }
}
