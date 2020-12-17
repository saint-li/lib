package com.saint.lib.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

/**
 * @author Saint  2020/11/3
 * DESC： 半圆弧温度进度条
 */
public class TempProgressView extends View {

    private int radius;//弧度半径
    private Paint mPaint;//画笔

    private int mHeight;//控件高度
    private int bottomPadding = dp2px(30);//绘制距底边距离

    private int tempColor = Color.GRAY;//温度标识颜色
    private int tempSize = sp2px(14);//温度刻度大小
    private String[] tempText = {"35°", "36°", "37°", "38°", "39°", "40°", "41°", "42°"};
    private float angle;//温度标识旋转角度

    private int tempTextHeight;//温度标识高度，用于弧形进度半径计算
    private int progressWidth = dp2px(10);//弧形进度宽度
    private int progressBgColor = Color.GRAY;//弧形进度底色
    private int progressColor = Color.BLUE;//弧形进度颜色
    private int mCurProgress;//弧形进度
    private float mProgress;//弧形进度
    private int space = dp2px(10);

    private int scaleColor = Color.GRAY;//中间刻度颜色
    private int scaleProgressColor = Color.GREEN;//中间刻度颜色
    private int scaleWidth = dp2px(1);//中间刻度宽度

    private float minScaleLength = dp2px(5);
    private float maxScaleLength = dp2px(10);

    private int scaleSpace;

    public void setScaleProgressColor(@ColorInt int scaleProgressColor) {
        this.scaleProgressColor = scaleProgressColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    public void setTempText(String[] tempText) {
        this.tempText = tempText;
        angle = 180f / ((float) tempText.length - 1);
        postInvalidate();
    }

    public TempProgressView(Context context) {
        super(context);
//        initAttrs(context);
    }

    public TempProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    public TempProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //圆的半径计算
        radius = getMeasuredWidth()/ 2;
        //设定布局高度为宽度的一半
        mHeight = MeasureSpec.makeMeasureSpec(radius + bottomPadding, MeasureSpec.EXACTLY);
        setMeasuredDimension(widthMeasureSpec,mHeight);
    }

    /**
     * 初始化参数
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        angle = 180f / (tempText.length - 1);
        scaleSpace = tempSize + space + progressWidth + space;
        mPaint = new Paint();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TempProgressView);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.TempProgressView_tempColor) {
                tempColor = a.getColor(attr, tempColor);
            } else if (attr == R.styleable.TempProgressView_tempSize) {
                tempSize = a.getDimensionPixelSize(attr, tempSize);
            } else if (attr == R.styleable.TempProgressView_progressBgColor) {
                progressBgColor = a.getColor(attr, progressBgColor);
            } else if (attr == R.styleable.TempProgressView_progressColor) {
                progressColor = a.getColor(attr, progressColor);
            } else if (attr == R.styleable.TempProgressView_tempProgressWidth) {
                progressWidth = a.getColor(attr, progressWidth);
            } else if (attr == R.styleable.TempProgressView_scaleColor) {
                scaleColor = a.getColor(attr, scaleColor);
            } else if (attr == R.styleable.TempProgressView_scaleProgressColor) {
                scaleProgressColor = a.getColor(attr, scaleProgressColor);
            } else if (attr == R.styleable.TempProgressView_scaleWidth) {
                scaleWidth = a.getColor(attr, scaleWidth);
            }
        }
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();

        //温度文字
        drawTempText(canvas, mPaint);
        //绘制进度
        drawProgressBg(canvas, mPaint);
        //绘制刻度
        drawScale(canvas, mPaint);
    }

    //绘制刻度
    private void drawScale(Canvas canvas, Paint paint) {
        canvas.restore();
//        canvas.translate(0, 0);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(scaleWidth);
        paint.setMaskFilter(null);

        canvas.translate(radius, radius);
        canvas.rotate(-270);
        for (int i = 0; i <= 90; i++) {
            if (i <= mProgress / 2) {
                paint.setColor(scaleProgressColor);
            } else {
                paint.setColor(scaleColor);
            }
            if (i % 10 == 0) {//长刻度
                canvas.drawLine(0, radius - scaleSpace, 0,
                        radius - scaleSpace - maxScaleLength, paint);
            } else {
                canvas.drawLine(0, radius - scaleSpace, 0,
                        radius - scaleSpace - minScaleLength, paint);
            }
            if (i > 0) canvas.rotate(2);

        }
//        canvas.restore();
    }


    //绘制进度
    private void drawProgressBg(Canvas canvas, Paint paint) {
        canvas.restore();
//        canvas.translate(0, 0);
//        canvas.rotate(-90);
        int left = tempSize + space + progressWidth / 2;
        int right = radius * 2 - left;
        int top = progressWidth / 2 + tempSize + space;
        int bottom = (radius - space) * 2;
        RectF rect = new RectF(left, top, right, bottom);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(progressBgColor);
        paint.setStrokeWidth(progressWidth);
        paint.setShader(null);
        canvas.drawArc(rect, 180, 180, false, paint);

        mPaint.setColor(progressColor);
        BlurMaskFilter emboss = new BlurMaskFilter(5, BlurMaskFilter.Blur.SOLID);
        mPaint.setMaskFilter(emboss);
        canvas.drawArc(rect, 180, mProgress, false, paint);
        canvas.save();
//        canvas.restore();
    }

    public void setProgress(int progress) {
        mCurProgress = progress;
        startAnimation();
    }

    public int getProgress() {
        return mCurProgress;
    }

    /**
     * 动画效果的取值
     */
    public void startAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, mCurProgress / 100f * 180);
        animator.setDuration(800).start();
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mProgress = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    //绘制温度文字
    private void drawTempText(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(radius, radius);
        mPaint.setMaskFilter(null);
        paint.setAntiAlias(true);
        paint.setColor(tempColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(tempSize);
        canvas.rotate(-90);
        for (int i = 0; i < tempText.length; i++) {
            if (i == 0) {
                drawTempText(canvas, 0, tempText[i], paint);
            } else {
                drawTempText(canvas, angle, tempText[i], paint);
            }
        }
//        canvas.restore();
    }

    /**
     * 温度文字
     *
     * @param canvas
     * @param degree
     * @param text
     * @param paint
     */
    private void drawTempText(Canvas canvas, float degree, String text, Paint paint) {
        Rect textBound = new Rect();
        paint.getTextBounds(text, 0, text.length(), textBound);
        canvas.rotate(degree);
        canvas.translate(0, -(radius - textBound.height()));
        canvas.drawText(text, -textBound.width() / 2, textBound.height() / 2, paint);
        canvas.translate(0, radius - textBound.height());
    }

    private int dp2px(float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }

    private int sp2px(float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, getResources().getDisplayMetrics());
    }


}
