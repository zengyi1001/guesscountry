package com.patrick.generaltool;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.WindowManager;

public class BaseActivity extends Activity{
	@Override
	protected void onResume(){
		super.onResume();
		AppContext.getInstance().setCurActivity(this);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置成全屏模式  
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//强制为横屏  
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏  
	}
}
