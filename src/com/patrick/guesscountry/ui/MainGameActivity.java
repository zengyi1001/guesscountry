package com.patrick.guesscountry.ui;

import android.os.Bundle;
import android.view.Menu;

import com.patrick.generaltool.BaseActivity;
import com.patrick.guesscountry.R;

public class MainGameActivity extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maingame);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
