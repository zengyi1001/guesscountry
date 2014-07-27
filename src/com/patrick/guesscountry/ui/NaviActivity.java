package com.patrick.guesscountry.ui;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.patrick.generaltool.AppContext;
import com.patrick.generaltool.BaseActivity;
import com.patrick.guesscountry.R;
import com.patrick.guesscountry.gamelogic.GamePlayType;

public class NaviActivity extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navi);
		initUI();
	}
	
	private void initUI(){
		findViewById(R.id.random).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(NaviActivity.this, MainGameActivity.class);
				intent.putExtra("type", GamePlayType.GAME_TYPE_RAMDON);
				NaviActivity.this.startActivity(intent);
			}
		});
		
		findViewById(R.id.expert).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(NaviActivity.this, MainGameActivity.class);
				intent.putExtra("type", GamePlayType.GAME_TYPE_EXPERT);
				NaviActivity.this.startActivity(intent);
			}
		});
		
		findViewById(R.id.showall).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(NaviActivity.this, ShowCountryActivity.class);
				NaviActivity.this.startActivity(intent);
			}
		});
		
		findViewById(R.id.myrecord).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(NaviActivity.this, MyRecordActivity.class);
				NaviActivity.this.startActivity(intent);
			}
		});
		
		findViewById(R.id.exit).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Builder builder = new Builder(NaviActivity.this);
				builder.setTitle("提示");
				builder.setMessage("您确定要退出吗?");
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						AppContext.getInstance().exitApp();
					}
				});
				builder.setNegativeButton("取消", null);
				builder.create().show();
			}
		});
	}
	
	private long exitTime = 0;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
	        if((System.currentTimeMillis()-exitTime) > 2000){  
	            Toast.makeText(getApplicationContext(), "再按一次退出应用", Toast.LENGTH_SHORT).show();                                
	            exitTime = System.currentTimeMillis();   
	        } else {
	            AppContext.getInstance().exitApp();
	        }
	        return true;   
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
