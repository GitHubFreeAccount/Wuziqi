package com.wzx.backgammon.utils;

import android.content.Context;
import android.util.Log;

public class AppUtil {
	public static final String TAG="AppUtil";
	
	 public static int dip2px(Context context,float dpValue) {		
	        final float scale =context.getResources().getDisplayMetrics().density;
	        int px=(int)(dpValue * scale);
	        Log.e(TAG, "dip2px dp="+dpValue+"  px="+px);	        
	        return px;
	}

}
