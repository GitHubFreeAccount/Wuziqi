package com.wzx.backgammon.views;

import com.wzx.backgammon.utils.AppUtil;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class DefaultToastView extends View {
	public static final String TAG="DefaultToastView";
	ValueAnimator valueAnimator;
	float mAnimatedValue=0f;
	private Paint mPaint,mSpikePaint;
	private float mWidth=0f;
	private float mPadding=0f;
    private float mSpikeLength;
	
	public DefaultToastView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public DefaultToastView(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
	}
	
	public DefaultToastView(Context context, AttributeSet attrs,int defstyle) {
		// TODO Auto-generated constructor stub
		super(context,attrs,defstyle);
		initPaint();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		initPaint();
		canvas.save();
		canvas.drawCircle(mWidth/2, mWidth/2, mWidth/4, mPaint);
		for (int i = 0; i < 360; i++) {
			int angle=(int)(mAnimatedValue*40+i);
			float initialX = (float) ((mWidth / 4) * Math.cos(angle * Math.PI / 180));
	        float initialY = (float) ((mWidth / 4) * Math.sin(angle * Math.PI / 180));
	        float finalX = (float) ((mWidth / 4 + mSpikeLength) * Math.cos(angle * Math.PI / 180));
	        float finalY = (float) ((mWidth / 4 + mSpikeLength) * Math.sin(angle * Math.PI / 180));
	            canvas.drawLine(mWidth / 2 - initialX, mWidth / 2 - initialY, mWidth / 2 - finalX,
	                    mWidth / 2 - finalY, mSpikePaint);
		}
		canvas.restore();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mWidth=getMeasuredWidth();
		mPadding=AppUtil.dip2px(getContext(),5);
	}
	
	private void initPaint(){
		mPaint=new Paint();
		mPaint.setAntiAlias(true);  //抗锯齿效果 使边缘平滑
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setColor(Color.parseColor("#222222"));
		mPaint.setStrokeWidth(AppUtil.dip2px(getContext(),4));
		
		mSpikePaint=new Paint();
		mSpikePaint.setAntiAlias(true);
		mSpikePaint.setStyle(Paint.Style.STROKE);
		mSpikePaint.setColor(Color.parseColor("#222222"));
		mSpikePaint.setStrokeWidth(AppUtil.dip2px(getContext(),4));
		
		mSpikeLength=AppUtil.dip2px(getContext(),4);
	}
	
    public void startAnim() {
        stopAnim();
        startViewAnim(0f, 1f, 2000);
    }

    public void stopAnim() {
        if (valueAnimator != null) {
            clearAnimation();

            valueAnimator.end();
            postInvalidate();
        }
    }

    private ValueAnimator startViewAnim(float startF, final float endF, long time) {
        valueAnimator = ValueAnimator.ofFloat(startF, endF);
        valueAnimator.setDuration(time);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                mAnimatedValue = (float) valueAnimator.getAnimatedValue();
                Log.e(TAG, "mAnimatedValue="+mAnimatedValue);
                postInvalidate();
            }
        });

        if (!valueAnimator.isRunning()) {
            valueAnimator.start();

        }
        return valueAnimator;
    }
	
	
	 

}
