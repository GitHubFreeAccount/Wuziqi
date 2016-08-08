package com.wzx.backgammon.views;

import java.util.ArrayList;
import java.util.List;

import com.wzx.backgammon.R;
import com.wzx.backgammon.utils.AppUtil;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author sdt13151
 *  双人对战
 */
public class ChessBoard extends View {
	private String TAG="ChessBoard";
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
    private ArrayList<Point> Picece=new ArrayList<Point>();
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
		inital();
	}
	public ChessBoard(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		inital();
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
		Log.e(TAG, "onMeasure");
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
		Log.e(TAG, "onSizeChanged");
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
		canvas.drawColor(Color.parseColor("#DEB887"));
		canvas.save();		
		DrawBoard(canvas);
		DrawPiece(canvas);
		canvas.restore();
	}
	
	/**
	 * wuzhouxing 2016年8月8日下午4:21:45
	 * 画棋盘
	 */
	private void DrawBoard(Canvas canvas){
		for (int i = 0; i <MAX_LINE; i++) {
			int startX=(int)SingelHeight/2;
			int endX=(int)(PanelWidth-SingelHeight/2);
			int y=(int)((0.5+i)*SingelHeight);	
			canvas.drawLine(startX, y, endX, y, paint);  //画横线
			canvas.drawLine(y, startX, y, endX, paint);  //画竖线
		}
	}
	
	/**
	 * wuzhouxing 2016年8月8日下午4:21:21
	 * 画棋子
	 */
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
	
	public boolean GetGameResult() {
        return IsGameOver;
    }

    public int GetPieceSize() {
        if (WhitePoint.size() == 0 && BlackPoint.size() == 0) {
            return 0;
        }
        return 1;
    }

    /**
     * wuzhouxing 2016年8月8日下午4:18:39
     * 都没赢 是格子填满？
     */
    private boolean checkNoWin(boolean whiteWin, boolean blackWin) {
        if (whiteWin || blackWin) {
            return false;
        }
        int max = MAX_LINE * MAX_LINE;

        if (WhitePoint.size() + BlackPoint.size() == max) {
            return true;
        }
        return false;
    }

    //重新开始
    public void restart() {
        WhitePoint.clear();
        BlackPoint.clear();
        IsGameOver = false;
        IsWhite=true;
        invalidate();
    }

    //悔棋
    public void regret() {
        if (BlackPoint.size() > 0 || WhitePoint.size() > 0) {
            if (IsWhite) {
                BlackPoint.remove(BlackPoint.size() - 1);
                IsWhite = !IsWhite;
            } else {
                WhitePoint.remove(WhitePoint.size() - 1);
                IsWhite = !IsWhite;
            }
            invalidate();
        }
    }
    
    
    //游戏是否结束
    private void IsGameOver() {
        boolean WhiteWin = checkFiveInLine(WhitePoint);
        boolean BlackWin = checkFiveInLine(BlackPoint);
        boolean NoWin = checkNoWin(WhiteWin, BlackWin);
        if (WhiteWin) {
            IsGameOver = true;
        } else if (BlackWin) {
            IsGameOver = true;
        } else if (NoWin) {
            IsGameOver = true;
        }
    }
    
    /**
     * wuzhouxing 2016年8月8日下午4:13:10
     * 判断五子是否相连
     */
    private boolean checkFiveInLine(List<Point> point) {
        for (Point p : point) {
            int x = p.x;
            int y = p.y;

            boolean checkHorizontal = checkHorizontalFiveInLine(x, y, point);
            boolean checkVertical = checkVerticalFiveInLine(x, y, point);
            boolean checkLeftDiagonal = checkLeftFiveInLine(x, y, point);
            boolean checkRightDiagonal = checkRightFiveInLine(x, y, point);
            if (checkHorizontal || checkVertical || checkLeftDiagonal || checkRightDiagonal) {
                return true;
            }
        }

        return false;
    }
    
    
    
    /**
     * wuzhouxing 2016年8月8日下午4:13:54
     * 向右斜
     */
    private boolean checkRightFiveInLine(int x,int y,List<Point> point){
    	int count=1;
    	  if (count == FIVE_POINT) {
              return true;
          }
          return false;
    }
    
    /**
     * wuzhouxing 2016年8月8日下午4:14:03
     * 向左斜
     */
    private boolean checkLeftFiveInLine(int x,int y,List<Point> point){
    	int count=1;
    	
    	  if (count == FIVE_POINT) {
              return true;
          }
          return false;
    }
	
    /**
     * wuzhouxing 2016年8月8日下午4:14:12
     * 水平方向
     */
    private boolean checkVerticalFiveInLine(int x,int y,List<Point> point){
    	int count=1;
    	for (int i = 0; i < FIVE_POINT; i++) {
			if(point.contains(new Point(x, y+i))){
				
			}
		}
    	  if (count == FIVE_POINT) {
              return true;
          }
          return false;
    }
    
    

    /**
     * horizontal five in line
     * wuzhouxing 2016年8月8日下午2:02:56
     */
    private boolean checkHorizontalFiveInLine(int x, int y, List<Point> point) {
        int count = 1;
        for (int i = 1; i < FIVE_POINT; i++) {
            if (point.contains(new Point(x - i, y))) {
                count++;
            } else {
                break;
            }
        }
        
        if (count == FIVE_POINT) {
            return true;
        }
        return false;
    }
    
  
}
