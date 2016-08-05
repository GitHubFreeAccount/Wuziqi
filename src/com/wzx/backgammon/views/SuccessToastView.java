package com.wzx.backgammon.views;

import com.wzx.backgammon.utils.AppUtil;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class SuccessToastView extends View {
	   private static final String TAG="SuccessToastView";
	    RectF rectF=new RectF();
	    ValueAnimator valueAnimator;
	    float mAnimatedValue = 0f;
	    private Paint mPaint;
	    private float mWidth = 0f;
	    private float mEyeWidth = 0f;
	    private float mPadding = 0f;
	    private float endAngle = -180f;
	    private boolean isSmileLeft = true;
	    private boolean isSmileRight = true;

	    public SuccessToastView(Context context) {
			// TODO Auto-generated constructor stub
	    	super(context);
		}
	    
	public SuccessToastView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public SuccessToastView(Context context, AttributeSet attrs,int defstyle) {
		// TODO Auto-generated constructor stub
		super(context,attrs,defstyle);
		initPaint();
	}

	@Override
	protected void onDraw(Canvas canvas) {
	// TODO Auto-generated method stub
		super.onDraw(canvas);
		initPaint();
		rectF=new RectF(mPadding, mPadding,mWidth-mPadding, mWidth-mPadding);
		mPaint.setStyle(Style.STROKE);  //»­Ô²»¡ÐÎ
		canvas.drawArc(rectF, 180, endAngle, true, mPaint);
		mPaint.setStyle(Paint.Style.FILL);
		if(isSmileLeft){
			canvas.drawCircle(mPadding+mEyeWidth+mEyeWidth/2,mWidth/3, mEyeWidth, mPaint);
		}
		if(isSmileRight){
			canvas.drawCircle(mWidth-mPadding-mEyeWidth-mEyeWidth/2,mWidth/3,mEyeWidth, mPaint);
		}
		
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mWidth=getMeasuredWidth();
		mPadding=AppUtil.dip2px(getContext(), 10);
		mEyeWidth=AppUtil.dip2px(getContext(), 4);
		Log.e(TAG, " mPadding ="+mPadding);
	}
	
	private void initPaint(){
		mPaint=new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Style.STROKE);
		mPaint.setColor(Color.parseColor("#5cb85c"));
		mPaint.setStrokeWidth(AppUtil.dip2px(getContext(),4));		
	}
	
	
}
