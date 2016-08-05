package com.wzx.backgammon.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class AnnotationBoard extends View {
	private  Paint annotationPaint;
	private int maxCol=15;
	private int maxRow=15;
	private int padding=5;
	

	public AnnotationBoard(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
	}
	
	public AnnotationBoard(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	
	public AnnotationBoard(Context context,AttributeSet attrs,int paramInt) {
		// TODO Auto-generated constructor stub
		super(context,attrs,paramInt);
	}
	

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyUp(keyCode, event);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}
	
	@Override
	public boolean onTrackballEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTrackballEvent(event);
	}
	
	protected  void init(){
		this.annotationPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
	}
	
	
	
	private int measureHeight(int paramInt){
		int mode=View.MeasureSpec.getMode(paramInt);
		int size=View.MeasureSpec.getSize(paramInt);
		paramInt=200;
		if(mode==View.MeasureSpec.AT_MOST){
			paramInt=size;
			return paramInt;
		}
		if(mode!=View.MeasureSpec.EXACTLY){
			return size;
		}
		return paramInt;
	}
	
	private int measureWidth(int paramInt){
		int mode=View.MeasureSpec.getMode(paramInt);
		int size=View.MeasureSpec.getSize(paramInt);
		paramInt=200;
		if(mode==View.MeasureSpec.AT_MOST){
			paramInt=size;
			return paramInt;
		}
		if(mode!=View.MeasureSpec.EXACTLY){
			return size;
		}
		return paramInt;
		
	}
	
	
	
	
	
	
	
	
}
