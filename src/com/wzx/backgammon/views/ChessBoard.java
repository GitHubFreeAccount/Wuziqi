package com.wzx.backgammon.views;

import java.util.ArrayList;

import com.wzx.backgammon.R;
import com.wzx.backgammon.utils.AppUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

public class ChessBoard extends View {
	private Paint paint;
	private int PanelWidth;
    private static final int MAX_LINE = 15;
    private static final float Size = 3 * 1.0f / 4;
    private float SingelHeight;
    private Bitmap WhiteBitmap;
    private Bitmap BlackBitmap;
    private boolean IsWhite = true;
    private ArrayList<Point> WhitePoint = new ArrayList<>();
    private ArrayList<Point> BlackPoint = new ArrayList<>();
    private static final int FIVE_POINT = 5;
    private boolean IsGameOver = false;

	public ChessBoard(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		inital();
	}
	
	public ChessBoard(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public ChessBoard(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	private void inital(){
		paint=new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.parseColor("#000000"));
		paint.setDither(true);
		paint.setStyle(Style.FILL);
		paint.setStrokeWidth(AppUtil.dip2px(getContext(), 2));
		WhiteBitmap=BitmapFactory.decodeResource(getResources(),R.drawable.white);
		BlackBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.black);
		
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
           int widthSize=MeasureSpec.getSize(widthMeasureSpec);
		   int widthMode=MeasureSpec.getMode(widthMeasureSpec);
		   
		   int heightSize=MeasureSpec.getSize(heightMeasureSpec);
		   int heigitMode=MeasureSpec.getMode(heightMeasureSpec);
		   int width=Math.min(widthSize, heightSize);
		    //AT_MOST:specSize 代表的是最大可获得的空间；
	        //EXACTLY:specSize 代表的是精确的尺寸；
	        //UNSPECIFIED:对于控件尺寸来说，没有任何参考意义。
	        //解决嵌套在ScrollView中时等情况出现的问题
		   if(widthMode==MeasureSpec.UNSPECIFIED){
			   width=heightSize;
		   }else if(heigitMode==MeasureSpec.UNSPECIFIED){
			   width=widthSize;
		   }
		   setMeasuredDimension(width, width);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		PanelWidth=w;
		SingelHeight=PanelWidth*1.0f/MAX_LINE;
		int onlyWidth=(int)(SingelHeight*Size);
		WhiteBitmap=Bitmap.createScaledBitmap(WhiteBitmap, onlyWidth, onlyWidth, false);
		BlackBitmap=Bitmap.createScaledBitmap(BlackBitmap, onlyWidth, onlyWidth, false);
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		DrawBoard(canvas);
		DrawPiece(canvas);
	}
	
	private void DrawBoard(Canvas canvas){
		for (int i = 0; i <MAX_LINE; i++) {
			int startX=(int)SingelHeight/2;
			int endX=(int)(PanelWidth-SingelHeight/2);
			int y=(int)((0.5+i)*SingelHeight);
			canvas.drawLine(startX, y, endX, y, paint);
			canvas.drawLine(y, startX, y, endX, paint);
		}
	}
	
	private void DrawPiece(Canvas canvas){
		for (int i = 0; i < WhitePoint.size(); i++) {
			Point whitePoint=WhitePoint.get(i);
			canvas.drawBitmap(WhiteBitmap,
					(whitePoint.x+(1-Size)/2)*SingelHeight,
					(whitePoint.y+(1-Size)/2)*SingelHeight,null);
			
		}
	
		for (int i = 0; i < BlackPoint.size(); i++) {
			Point blackPoint=BlackPoint.get(i);
			canvas.drawBitmap(BlackBitmap,
					(blackPoint.x + (1 - Size) / 2) * SingelHeight,
                    (blackPoint.y + (1 - Size) / 2) * SingelHeight, null);
		}
	}
	
	
	

}
