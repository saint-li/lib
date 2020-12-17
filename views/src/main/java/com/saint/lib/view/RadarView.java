package com.saint.lib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Saint  2020/12/15.
 * DESC：雷达图、蛛网图
 */
public class RadarView extends View {
    //数据个数
    private int count = 5;
    //成绩圆点半径
    private int valueRadius = 8;
    //网格最大半径
    private float radius;
    //中心X
    private float centerX;
    //中心Y
    private float centerY;
    //雷达区画笔
    private Paint mainPaint;
    //文本画笔
    private Paint textPaint;
    //文本背景色画笔
    private Paint textBgPaint;
    //文本背景色圆半径
    private int textBgRadius;
    private int space;
    //数据区画笔
    private Paint valuePaint;
    //标题文字
    private List<String> titles;
    //各维度分值
    private List<Double> data;
    //数据最大值
    private double maxValue = 100;
    //弧度
    private float angle;


    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //雷达区画笔初始化
        mainPaint = new Paint();
        mainPaint.setColor(Color.parseColor("#FFFFFF"));
        mainPaint.setAntiAlias(true);
        //文本画笔初始化
        textPaint = new Paint();
        textPaint.setColor(Color.parseColor("#FF239796"));
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(spToPx(12));
        textPaint.setStrokeWidth(1);
        textPaint.setAntiAlias(true);
        //文本背景色画笔初始化
        textBgPaint = new Paint();
        textBgPaint.setColor(Color.parseColor("#BBFDF9"));
        textBgPaint.setAntiAlias(true);
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, textBgRadius * 2, new int[]{
                Color.rgb(238, 255, 254),
                Color.rgb(187, 253, 249)},
                null, Shader.TileMode.MIRROR);
        //new float[]{},中的数据表示相对位置，将150,50,150,300，划分10个单位，.3，.6，.9表示它的绝对位置。300到400，将直接画出rgb（0,232,210）
        textBgPaint.setShader(linearGradient);

        textBgRadius = dpToPx(18);
        space = dpToPx(6);

        //数据区（分数）画笔初始化
        valuePaint = new Paint();
        valuePaint.setColor(Color.WHITE);
        valuePaint.setAntiAlias(true);
        valuePaint.setStyle(Paint.Style.FILL);

        titles = new ArrayList<>();
        titles.add("意识");
        titles.add("保障");
        titles.add("睡眠");
        titles.add("运动");
        titles.add("心理");
        count = titles.size();

        //默认分数
        data = new ArrayList<>(count);
        data.add(100.0);
        data.add(80.0);
        data.add(90.0);
        data.add(70.0);
        data.add(60.0);


        //1度=1*PI/180   360度=2*PI   那么我们每旋转一次的角度为2*PI/内角个数
        //中心与相邻两个内角相连的夹角角度
        angle = (float) (2 * Math.PI / count);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(w, h) / 2 - textBgRadius * 2 - space;
        centerX = w / 2;
        centerY = h / 2;
        //一旦size发生改变，重新绘制
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawPolygon(canvas);//绘制蜘蛛网
        drawLines(canvas);//绘制直线
        drawTitle(canvas);//绘制标题
        drawRegion(canvas);//绘制覆盖区域
    }

    /**
     * 绘制多边形
     *
     * @param canvas
     */
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        //每个蛛丝之间的间距
        float r = radius / (count - 1);

        for (int i = 0; i < count; i++) {
            //当前半径
            float curR = r * i;
            path.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    float x = (float) (centerX + curR * Math.sin(angle));
                    float y = (float) (centerY - curR * Math.cos(angle));
                    path.moveTo(x, y);
                } else {
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x1 = (float) (centerX + curR * Math.sin(angle / 2));
                    float y1 = (float) (centerY + curR * Math.cos(angle / 2));
                    path.lineTo(x1, y1);
                    float x2 = (float) (centerX - curR * Math.sin(angle / 2));
                    float y2 = (float) (centerY + curR * Math.cos(angle / 2));
                    path.lineTo(x2, y2);
                    float x3 = (float) (centerX - curR * Math.sin(angle));
                    float y3 = (float) (centerY - curR * Math.cos(angle));
                    path.lineTo(x3, y3);
                    float x4 = centerX;
                    float y4 = centerY - curR;
                    path.lineTo(x4, y4);
                    float x = (float) (centerX + curR * Math.sin(angle));
                    float y = (float) (centerY - curR * Math.cos(angle));
                    path.lineTo(x, y);
                }
            }
            path.close();
            //填充覆盖区域
            mainPaint.setAlpha(45);
            mainPaint.setStyle(Paint.Style.FILL);
            canvas.drawPath(path, mainPaint);
            //绘制线
            mainPaint.setAlpha(82);
            mainPaint.setStrokeWidth(dpToPx(1f));
            mainPaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, mainPaint);
        }
    }


    /**
     * 绘制直线
     */
    private void drawLines(Canvas canvas) {
        Path path = new Path();
        path.reset();
        //直线1
        path.moveTo(centerX, centerY);
        float x1 = (float) (centerX + radius * Math.sin(angle));
        float y1 = (float) (centerY - radius * Math.cos(angle));
        path.lineTo(x1, y1);
        //直线2
        path.moveTo(centerX, centerY);
        float x2 = (float) (centerX + radius * Math.sin(angle / 2));
        float y2 = (float) (centerY + radius * Math.cos(angle / 2));
        path.lineTo(x2, y2);
        //直线3
        path.moveTo(centerX, centerY);
        float x3 = (float) (centerX - radius * Math.sin(angle / 2));
        float y3 = (float) (centerY + radius * Math.cos(angle / 2));
        path.lineTo(x3, y3);
        //直线4
        path.moveTo(centerX, centerY);
        float x4 = (float) (centerX - radius * Math.sin(angle));
        float y4 = (float) (centerY - radius * Math.cos(angle));
        path.lineTo(x4, y4);
        //直线5
        path.moveTo(centerX, centerY);
        float x5 = (float) (centerX);
        float y5 = (float) (centerY - radius);
        path.lineTo(x5, y5);
        path.close();
        canvas.drawPath(path, mainPaint);
    }

    /**
     * 绘制标题文字
     *
     * @param canvas
     */
    private void drawTitle(Canvas canvas) {
        if (count != titles.size()) {
            return;
        }
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;//标题高度

        //绘制文字1
        float x1 = centerX;
        float textBgCenterRadius = radius + space + textBgRadius;
        float y1 = centerY - textBgCenterRadius;
        float fontWight = textPaint.measureText(titles.get(1));//标题一半的宽度
        //文字背景
        canvas.drawCircle(x1, y1, textBgRadius, textBgPaint);
        canvas.drawText(titles.get(0), x1, y1 + fontHeight / 3, textPaint);

        //绘制文字2
        float x2 = (float) (centerX + textBgCenterRadius * Math.sin(angle));
        float y2 = (float) (centerY - textBgCenterRadius * Math.cos(angle));
        //文字背景
        canvas.drawCircle(x2, y2, textBgRadius, textBgPaint);
        canvas.drawText(titles.get(1), x2, y2 + fontHeight / 3, textPaint);

        //绘制文字3
        float x3 = (float) (centerX + textBgCenterRadius * Math.sin(angle / 2));
        float y3 = (float) (centerY + textBgCenterRadius * Math.cos(angle / 2));
        //文字背景
        canvas.drawCircle(x3, y3, textBgRadius, textBgPaint);
        canvas.drawText(titles.get(2), x3, y3 + fontHeight / 3, textPaint);

        //绘制文字4
        float x4 = (float) (centerX - textBgCenterRadius * Math.sin(angle / 2));
        //文字背景
        canvas.drawCircle(x4, y3, textBgRadius, textBgPaint);
        canvas.drawText(titles.get(3), x4, y3 + fontHeight / 3, textPaint);
        //绘制文字5
        float x5 = (float) (centerX - textBgCenterRadius * Math.sin(angle));
//        float y5 = (float) (centerY - radius * Math.cos(angle));
        //文字背景
        canvas.drawCircle(x5, y2, textBgRadius, textBgPaint);
        canvas.drawText(titles.get(4), x5, y2 + fontHeight / 3, textPaint);
    }

    /**
     * 绘制覆盖区域
     */
    private void drawRegion(Canvas canvas) {
        valuePaint.setAlpha(255);
        Path path = new Path();
        double dataValue;
        double percent;
        //绘制圆点1
        dataValue = data.get(0);
        if (dataValue != maxValue) {
            percent = dataValue / maxValue;
        } else {
            percent = 1;
        }
        float x1 = centerX;
        float y1 = (float) (centerY - radius * percent);
        path.moveTo(x1, y1);
        canvas.drawCircle(x1, y1, valueRadius, valuePaint);
        //绘制圆点2
        dataValue = data.get(1);
        if (dataValue != maxValue) {
            percent = dataValue / maxValue;
        } else {
            percent = 1;
        }
        float x2 = (float) (centerX + radius * percent * Math.sin(angle));
        float y2 = (float) (centerY - radius * percent * Math.cos(angle));
        path.lineTo(x2, y2);
        canvas.drawCircle(x2, y2, valueRadius, valuePaint);
        //绘制圆点3
        dataValue = data.get(2);
        if (dataValue != maxValue) {
            percent = dataValue / maxValue;
        } else {
            percent = 1;
        }
        float x3 = (float) (centerX + radius * percent * Math.sin(angle / 2));
        float y3 = (float) (centerY + radius * percent * Math.cos(angle / 2));
        path.lineTo(x3, y3);
        canvas.drawCircle(x3, y3, valueRadius, valuePaint);
        //绘制圆点4
        dataValue = data.get(3);
        if (dataValue != maxValue) {
            percent = dataValue / maxValue;
        } else {
            percent = 1;
        }
        float x4 = (float) (centerX - radius * percent * Math.sin(angle / 2));
        float y4 = (float) (centerY + radius * percent * Math.cos(angle / 2));
        path.lineTo(x4, y4);
        canvas.drawCircle(x4, y4, valueRadius, valuePaint);
        //绘制圆点5
        dataValue = data.get(3);
        if (dataValue != maxValue) {
            percent = dataValue / maxValue;
        } else {
            percent = 1;
        }
        float x5 = (float) (centerX - radius * percent * Math.sin(angle));
        float y5 = (float) (centerY - radius * percent * Math.cos(angle));
        path.lineTo(x5, y5);
        canvas.drawCircle(x5, y5, valueRadius, valuePaint);

        path.close();
        //绘制覆盖区域外的连线
        valuePaint.setAlpha(255);
        valuePaint.setStrokeWidth(4f);
        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, valuePaint);
        //填充覆盖区域
        valuePaint.setAlpha(80);
        valuePaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, valuePaint);
    }


    //设置蜘蛛网颜色
    public void setMainPaint(Paint mainPaint) {
        this.mainPaint = mainPaint;
        postInvalidate();
    }

    //设置标题颜色
    public void setTextPaint(Paint textPaint) {
        this.textPaint = textPaint;
    }

    //设置覆盖局域颜色
    public void setValuePaint(Paint valuePaint) {
        this.valuePaint = valuePaint;
        postInvalidate();
    }

    //设置各门得分
    public void setData(List<Double> data) {
        this.data = data;
        postInvalidate();
    }

    //设置满分分数，默认是100分满分
    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public int dpToPx(float dp) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getContext().getResources().getDisplayMetrics()));
    }

    public int spToPx(float dp) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp, getContext().getResources().getDisplayMetrics()));
    }


}
