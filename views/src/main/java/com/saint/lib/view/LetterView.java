package com.saint.lib.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

/**
 * @author saint
 * @desc 字母指示组件
 */
public class LetterView extends View {

    private final String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};

    private OnLetterChangeListener mOnLetterChangeListener;

    private Paint mLetterPaint, mLetterSelectedPaint;
    private Paint mLetterIndicatorPaint;
    private int mWidth;
    private int mItemHeight;
    private int mTouchIndex = -1;
    private Context mContext;
    private boolean isTouch;
    private boolean enableIndicator;
    private float letterTextSize;
//    private int letterIndicatorTextColor;

    public LetterView(Context context) {
        super(context);
    }

    public LetterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //获取属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LetterView);
        int letterTextColor = array.getColor(R.styleable.LetterView_letterTextColor, Color.RED);
        int letterIndicatorTextColor = array.getColor(R.styleable.LetterView_letterIndicatorTextColor, Color.BLUE);
        int letterTextBackgroundColor = array.getColor(R.styleable.LetterView_letterTextBackgroundColor, Color.WHITE);
        int letterIndicatorColor = array.getColor(R.styleable.LetterView_letterIndicatorColor, Color.parseColor("#333333"));
        letterTextSize = array.getDimension(R.styleable.LetterView_letterTextSize, 12);
        enableIndicator = array.getBoolean(R.styleable.LetterView_letterEnableIndicator, true);

        //默认设置
        mContext = context;
        mLetterPaint = new Paint();
        mLetterPaint.setTextSize(letterTextSize);
        mLetterPaint.setColor(letterTextColor);
        mLetterPaint.setAntiAlias(true);
        mLetterSelectedPaint = new Paint();
        mLetterSelectedPaint.setTextSize(letterTextSize);
        mLetterSelectedPaint.setColor(letterIndicatorTextColor);
        mLetterSelectedPaint.setAntiAlias(true);

        mLetterIndicatorPaint = new Paint();
        mLetterIndicatorPaint.setStyle(Paint.Style.FILL);
        mLetterIndicatorPaint.setColor(letterIndicatorColor);
        mLetterIndicatorPaint.setAntiAlias(true);
        setBackgroundColor(letterTextBackgroundColor);
        array.recycle();
    }

    public LetterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置字母改变监听
     *
     * @param mOnLetterChangeListener 监听器
     */
    public void setOnLetterChangeListener(OnLetterChangeListener mOnLetterChangeListener) {
        this.mOnLetterChangeListener = mOnLetterChangeListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取宽高的尺寸大小
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        //wrap_content默认宽高
        @SuppressLint("DrawAllocation") Rect mRect = new Rect();
        mLetterPaint.getTextBounds("A", 0, 1, mRect);
        mWidth = mRect.width() + dpToPx(12);
        int mHeight = (mRect.height() + dpToPx(5)) * letters.length;

        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, mHeight);
        } else if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, heightSize);
        } else if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(widthSize, mHeight);
        }

        mWidth = getMeasuredWidth();
        int averageItemHeight = getMeasuredHeight() / 28;
        int mOffset = averageItemHeight / 30; //界面调整
        mItemHeight = averageItemHeight + mOffset;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 循环绘制数据
        @SuppressLint("DrawAllocation") Rect rect = new Rect(); // 提前定义 Rect 避免重复创建
        for (int i = 0; i < letters.length; i++) {
            String charStr = letters[i];

            // 获取字母宽高
            mLetterPaint.getTextBounds(charStr, 0, Math.min(1, charStr.length()), rect);
            int letterWidth = rect.width();
            int letterHeight = rect.height();

            final float centerX = 0.5f * mWidth;
            final float radius = 0.5f * Math.min(mItemHeight, mWidth);
            float centerY = (i + 1) * mItemHeight - 0.5f * mItemHeight;

            // 绘制选中字母背景
            if (enableIndicator && mTouchIndex == i) {
                canvas.drawCircle(centerX, centerY, radius, mLetterIndicatorPaint);
            }

            // 绘制字母
            final float textX = (float) (mWidth - letterWidth) / 2;
            float textY = centerY + (float) letterHeight / 2;

            if (mTouchIndex == i) {
                canvas.drawText(charStr, textX, textY, mLetterSelectedPaint);
            } else {
                canvas.drawText(charStr, textX, textY, mLetterPaint);
            }
        }
    }

    //If a View that overrides onTouchEvent or uses an OnTouchListener does not also implement performClick
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                isTouch = true;
                int y = (int) event.getY();
                int index = y / mItemHeight;
                if (index != mTouchIndex && index < 28 && index >= 0) {
                    mTouchIndex = index;
                }

                if (mOnLetterChangeListener != null && mTouchIndex > 0) {
                    mOnLetterChangeListener.onLetterListener(letters[mTouchIndex]);
                }

                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                isTouch = false;
                if (mOnLetterChangeListener != null && mTouchIndex > 0) {
                    mOnLetterChangeListener.onLetterDismissListener();
                }
                break;
        }
        return true;
    }

    /**
     * 设置触摸字母
     *
     * @param letter 选中字母
     */
    public void setTouchLetter(String letter) {
        if (!isTouch) {
            for (int i = 0; i < letters.length; i++) {
                if (letters[i].equals(letter)) {
                    mTouchIndex = i;
                    invalidate();
                    return;
                }
            }
        }
    }

    /**
     * dp to pix
     */
    public int dpToPx(float dp) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics()));
    }

    public interface OnLetterChangeListener {
        void onLetterListener(String touchIndex);

        void onLetterDismissListener();
    }
}