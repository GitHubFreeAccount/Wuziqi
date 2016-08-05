package com.wzx.backgammon;

import com.wzx.backgammon.views.TastyToast;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
	
	}
	
	public void showDefaultToast(View view){
		TastyToast.makeText(this, "This is default Toast", TastyToast.LENGTH_LONG,TastyToast.SUCCESS);
	}
}
