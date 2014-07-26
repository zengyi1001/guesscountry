package com.patrick.guesscountry.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

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
				Intent intent = new Intent(NaviActivity.this, MainGameActivity.class);
				NaviActivity.this.startActivity(intent);
			}
		});
		
		findViewById(R.id.exit).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				AppContext.getInstance().exitApp();
			}
		});
	}
}
