package com.saint.lib.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Saint  2020/12/16.
 * DESC：
 */
public class MultistageProgressView extends View {
    private Paint backgroundPaint, progressPaint, dividePaint, textPaint;//背景和进度条画笔
    private Paint textProgressPaint;
    private float progress = 0;//进度
    private float cusProgress;//实际进度
    //指示图标背景
    private Drawable mIcon;
    //指示图标背景宽度高度
    private float iconWidth, iconHeight;
    //分割线均分宽度  宽度；
    private float divideX, divideWidth;
    //进度条长度 高度
    private float progressWidth, progressHeight;
    private List<String> title;
    private int level = -1;

    private String progressText = "0";


    public MultistageProgressView(Context context) {
        super(context);
        init();
    }

    public MultistageProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MultistageProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        title = new ArrayList<>();
        title.add("薄弱");
        title.add("中等");
        title.add("较高");

        mIcon = ContextCompat.getDrawable(getContext(), R.mipmap.progress_purple_1);
        iconWidth = dpToPx(33f);
        iconHeight = dpToPx(31);
        divideWidth = dpToPx(3);
        progressHeight = dpToPx(10);

        initConstant();

        backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setStrokeCap(Paint.Cap.ROUND);
        backgroundPaint.setStrokeWidth(progressHeight);
        backgroundPaint.setColor(Color.parseColor("#FFF5F5F5"));

        progressPaint = new Paint();
        progressPaint.setStyle(Paint.Style.FILL);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setStrokeWidth(progressHeight);
        progressPaint.setColor(Color.parseColor("#FF84A0FF"));

        dividePaint = new Paint();
        dividePaint.setStyle(Paint.Style.FILL);
        dividePaint.setColor(Color.WHITE);
        dividePaint.setStrokeWidth(divideWidth);

        textPaint = new Paint();
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.parseColor("#FFAAAAAA"));
        textPaint.setTextSize(spToPx(12));

        textProgressPaint = new Paint();
        textProgressPaint.setStyle(Paint.Style.FILL);
        textProgressPaint.setColor(Color.WHITE);
        textProgressPaint.setTextSize(spToPx(14));


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initConstant();
    }

    private void initConstant() {
        progressWidth = getWidth() - iconWidth;
        divideX = progressWidth / title.size();
    }

    private void compute() {
        cusProgress = progress * progressWidth;
        Log.e("View", "progeress: " + progress + "cus: " + cusProgress + " divideX: " + divideX + " level: " + level);
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        compute();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //文字背景Icon
        drawIcon(canvas);
        //进度
        drawProgress(canvas);
        //进度分割线
        drawCutLine(canvas);
        //文字
        drawLevelText(canvas);
    }

    /**
     * 进度背景色
     *
     * @param canvas
     */
    private void drawIcon(Canvas canvas) {
        if (cusProgress < 0) return;
        if (cusProgress >= progressWidth) cusProgress = progressWidth;
        if (level == 2) {
            mIcon = ContextCompat.getDrawable(getContext(), R.mipmap.progress_purple_3);
        } else if (level == 1) {
            mIcon = ContextCompat.getDrawable(getContext(), R.mipmap.progress_purple_2);
        } else if (level == 0) {
            mIcon = ContextCompat.getDrawable(getContext(), R.mipmap.progress_purple_1);
        } else {
            mIcon = ContextCompat.getDrawable(getContext(), R.mipmap.progress_purple_0);
        }

        float left = cusProgress;
        mIcon.setBounds(new Rect((int) left, 0, (int) (left + iconWidth), (int) iconHeight));
        mIcon.draw(canvas);

        float textWidth = textProgressPaint.measureText(progressText);
        canvas.drawText(progressText, left + iconWidth / 2 - textWidth / 2, iconHeight / 2, textProgressPaint);

    }

    private void drawProgress(Canvas canvas) {
        //进度条背景
        canvas.drawLine(iconWidth / 2, iconHeight, getWidth() - iconWidth / 2, iconHeight, backgroundPaint);

        //进度
        float temp;
        if (level == 2 || level < 0) {
            temp = 0;
        } else {
            temp = progressHeight / 2;
        }
        if (level == 2) {
            progressPaint.setColor(Color.parseColor("#FFFF7E7E"));
        } else if (level == 1) {
            progressPaint.setColor(Color.parseColor("#FFFFB97E"));
        } else if (level == 0) {
            progressPaint.setColor(Color.parseColor("#FF84A0FF"));
        } else {
            progressPaint.setColor(Color.parseColor("#FFF5F5F5"));
        }
        canvas.drawLine(iconWidth / 2, iconHeight, cusProgress + iconWidth / 2 - temp, iconHeight, progressPaint);

    }

    /**
     * 分割线
     *
     * @param canvas
     */
    private void drawCutLine(Canvas canvas) {
        for (int i = 1; i < title.size(); i++) {
            float left = divideX * i + iconWidth / 2;
            canvas.drawLine(left, iconHeight - progressHeight / 2, left, iconHeight + progressHeight / 2, dividePaint);
        }
    }

    private void drawLevelText(Canvas canvas) {
        float tempWidth = progressWidth / title.size() / 2;
        float textWidth = textPaint.measureText(title.get(0)) / 2;
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;//标题高度
        for (int i = 0; i < title.size(); i++) {
            float left = iconWidth / 2 + tempWidth + divideX * i;
            if (level == 0 && i == level) {
                textPaint.setColor(Color.parseColor("#FF84A0FF"));
            } else if (level == 1 && i == level) {
                textPaint.setColor(Color.parseColor("#FFFFB97E"));
            } else if (level == 2 && i == level) {
                textPaint.setColor(Color.parseColor("#FFFF7E7E"));
            } else {
                textPaint.setColor(Color.parseColor("#FFAAAAAA"));
            }
            canvas.drawText(title.get(i), left - textWidth / 2, iconHeight + progressHeight + fontHeight, textPaint);
        }
    }

    ObjectAnimator valueAnimator;

    public void autoChange(String progressText, float startProgress, float endProgress, long changeTime) {
        if (valueAnimator != null && valueAnimator.isRunning()) return;
        this.progressText = progressText;
        level = (int) (endProgress * progressWidth / divideX);
        if (endProgress > 0 && endProgress <= 0.333333) {
            level = 0;
        } else if (endProgress > 0 && endProgress <= 0.666666) {
            level = 1;
        } else if (endProgress > 0 && endProgress <= 1f) {
            level = 2;
        }
//        setProgress((int) startProgress);
        valueAnimator = ObjectAnimator.ofFloat(this, "progress", startProgress, endProgress);
        valueAnimator.setDuration(changeTime);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                setProgress((int) value);
            }
        });
        valueAnimator.start();
    }

    public void stopChange() {
        if (valueAnimator != null && valueAnimator.isRunning()) valueAnimator.cancel();
    }

    @Override
    protected void onDetachedFromWindow() {
        if (valueAnimator != null && valueAnimator.isRunning()) {
            valueAnimator.cancel();
        }
        super.onDetachedFromWindow();
    }

    public interface OnProgressChangeListener {
        /**
         * 进度改变时触发
         *
         * @param progress 进度
         * @param position 所在区间段
         */
        void onProgressChange(float progress, int position);
    }

    public int dpToPx(float dp) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getContext().getResources().getDisplayMetrics()));
    }

    public int spToPx(float dp) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp, getContext().getResources().getDisplayMetrics()));
    }


}
