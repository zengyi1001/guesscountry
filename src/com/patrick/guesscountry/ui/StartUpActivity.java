package com.patrick.guesscountry.ui;

import android.content.Intent;
import android.os.Bundle;

import com.patrick.generaltool.AppContext;
import com.patrick.generaltool.BaseActivity;
import com.patrick.guesscountry.R;
import com.patrick.guesscountry.gamelogic.GameIniter;
import com.patrick.guesscountry.gamelogic.GameIniter.IGameInitListener;

public class StartUpActivity extends BaseActivity implements IGameInitListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup);
		GameIniter.getInstance().init(this);
	}
	
	@Override
	public void onInitFinished() {
		AppContext.getInstance().runOnUIThread(new Runnable() {
			
			@Override
			public void run() {
				Intent intent = new Intent(AppContext.getInstance(), MainGameActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				AppContext.getInstance().startActivity(intent);
				finish();
			}
		}, 1 * 1000);
	}
}
