package com.patrick.generaltool;

import android.app.Activity;

public class BaseActivity extends Activity{
	@Override
	protected void onResume(){
		super.onResume();
		AppContext.getInstance().setCurActivity(this);
	}
}
