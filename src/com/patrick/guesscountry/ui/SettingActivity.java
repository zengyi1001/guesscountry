package com.patrick.guesscountry.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

import com.patrick.generaltool.BaseActivity;
import com.patrick.guesscountry.R;
import com.patrick.guesscountry.data.PrefenceData;

public class SettingActivity extends BaseActivity{
	private ToggleButton mSilencButton;
	private ToggleButton mCommButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		initUI();
	}
	
	private void initUI(){
		findViewById(R.id.btn_back).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
		mSilencButton = (ToggleButton)findViewById(R.id.silence);
		mCommButton = (ToggleButton)findViewById(R.id.common);
		
		if (PrefenceData.getInstance().isOnlyCommon()){
			mCommButton.setChecked(true);
		}else{
			mCommButton.setChecked(false);
		}
		
		if (PrefenceData.getInstance().isSilence()){
			mSilencButton.setChecked(true);
		}else{
			mSilencButton.setChecked(false);
		}
		
		mSilencButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				PrefenceData.getInstance().setIsSilence(arg1);
			}
		});
		
		mCommButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				PrefenceData.getInstance().setOnlyCommon(arg1);
			}
		});
	}
}
