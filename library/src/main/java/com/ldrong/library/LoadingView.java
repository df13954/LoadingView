package com.ldrong.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 进度条（圆饼）
 * Created by lrd on 2016/8/15.
 */
public class LoadingView extends View {

    private static final String TAG = "LoadingView";

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private Paint mPaintCircleOuter;
    private Paint mPaintCircleInner;

    private void init(Context context, AttributeSet attrs) {
        //外圆圈
        mPaintCircleOuter = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCircleOuter.setStyle(Paint.Style.STROKE);
        //宽度
        mPaintCircleOuter.setStrokeWidth(3F);
        mPaintCircleOuter.setColor(Color.parseColor("#bfffffff"));

        //内饼
        mPaintCircleInner = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCircleInner.setColor(Color.parseColor("#bfffffff"));
    }

    private float mWidth;
    private float mHeight;
    private float mRadius;
    private RectF mOval;

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
        mRadius = Math.min(mWidth / 2, mHeight / 2) - 6;

        

        // 9 : 外圆，和饼之间的距离
        mOval = new RectF(-mRadius + 5, -mRadius + 5, mRadius - 5, mRadius - 5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //作用就是移动原点，默认的原点（0，0）是在屏幕左上角的，你可以通过translate（x，y）把点（x，y）作为原点.
        canvas.translate(mWidth / 2, mHeight / 2);
        //画外圆
        canvas.drawCircle(0, 0, mRadius, mPaintCircleOuter);
        //饼
        /**
         * 参数说明
         oval：圆弧所在的椭圆对象。
         startAngle：圆弧的起始角度。
         sweepAngle：圆弧的角度。 如果是0,从有半圆，水平，往下开始,-90f:从上半圆，垂直开始
         useCenter：是否显示半径连线，true表示显示圆弧与圆心的半径连线，false表示不显示。
         paint：绘制时所使用的画笔。
         */
        canvas.drawArc(mOval, -90F, mSweepAngel, true, mPaintCircleInner);
    }

    /**
     * 圆圈扫过的角度
     */
    private float mSweepAngel;

    /**
     * 设置加载进度
     */
    public void setProgress(int progress) {
        mSweepAngel = (float) (progress / 100.0 * 360);
        invalidate();
    }
}
