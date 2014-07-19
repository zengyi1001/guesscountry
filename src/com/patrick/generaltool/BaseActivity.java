package com.patrick.generaltool;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.WindowManager;

public class BaseActivity extends Activity{
	@Override
	protected void onResume(){
		super.onResume();
		AppContext.getInstance().setCurActivity(this);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//���ó�ȫ��ģʽ  
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//ǿ��Ϊ����  
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//����  
	}
}
