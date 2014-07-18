package com.patrick.guesscountry.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.patrick.generaltool.AppContext;
import com.patrick.guesscountry.R;
import com.patrick.guesscountry.data.CountryDataBase;
import com.patrick.guesscountry.data.CountryDataBase.IDataInitListener;

public class StartUpActivity extends Activity implements IDataInitListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup);
		CountryDataBase.getInstance().init(this);
	}
	

	@Override
	public void onInitFinished() {
		AppContext.getInstance().runOnUIThread(new Runnable() {
			
			@Override
			public void run() {
				startActivity(new Intent(StartUpActivity.this, MainGameActivity.class));
				finish();
			}
		}, 1 * 1000);
	}
}
