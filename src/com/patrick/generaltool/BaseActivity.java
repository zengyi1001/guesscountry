package com.patrick.generaltool;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.baidu.mobstat.StatService;

public class BaseActivity extends Activity{
	class FinishReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			BaseActivity.this.finish();
		}
		
	}
	private FinishReceiver mFinishReceiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mFinishReceiver = new FinishReceiver();
		registerReceiver(mFinishReceiver, new IntentFilter(AppContext.ACTION_FINISH_ACTIVITY));	
		AppContext.getInstance().addActivity(this);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		AppContext.getInstance().setCurActivity(this);
		StatService.onResume(this);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//���ó�ȫ��ģʽ  
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//ǿ��Ϊ����  
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//����  
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		StatService.onPause(this);
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		unregisterReceiver(mFinishReceiver);
		AppContext.getInstance().removeActivity(this);
	}
}
