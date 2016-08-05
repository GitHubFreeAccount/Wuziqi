package com.wzx.backgammon.utils;

import android.content.Context;

public class AppUtil {
	
	 public static int dip2px(Context context,float dpValue) {
	        final float scale =context.getResources().getDisplayMetrics().density;
	        return (int) (dpValue * scale);
	}

}
