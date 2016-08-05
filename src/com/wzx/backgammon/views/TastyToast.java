package com.wzx.backgammon.views;

import com.wzx.backgammon.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TastyToast {
 
	public static final int LENGTH_LONG=30;
	static DefaultToastView defaultToastView;
	public static final int DEFAULT=0;
	public static final int SUCCESS=1;
	public static void makeText(Context context,String msg,int length,int type){
		Toast toast=new Toast(context);
		switch (type) {
		case DEFAULT:
			View layout=LayoutInflater.from(context).inflate(R.layout.default_toast_layout, null,false);
			TextView textView=(TextView) layout.findViewById(R.id.toastMessage);
			textView.setText(msg);
			defaultToastView=(DefaultToastView) layout.findViewById(R.id.defaultView);
			defaultToastView.startAnim();
			textView.setBackgroundResource(R.drawable.background_toast);
			textView.setTextColor(Color.parseColor("#000000"));
			toast.setView(layout);
			break;
		case SUCCESS:
			layout=LayoutInflater.from(context).inflate(R.layout.success_toast_layout, null,false);
			textView=(TextView) layout.findViewById(R.id.toastMessage);
			textView.setText(msg);			
			textView.setBackgroundResource(R.drawable.background_toast);
			textView.setTextColor(Color.parseColor("#000000"));
			toast.setView(layout);
			break;
		default:
			break;
		}
		
	
		toast.setDuration(length);
		toast.show();
	}
}
