package com.patrick.guesscountry.ui;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.patrick.generaltool.AppContext;
import com.patrick.generaltool.AssetFileTool;
import com.patrick.generaltool.BaseActivity;
import com.patrick.guesscountry.R;
import com.patrick.guesscountry.data.CountryDataBase;
import com.patrick.guesscountry.data.CountryItem;
import com.patrick.guesscountry.data.PrefenceData;
import com.patrick.guesscountry.data.SqliteDataBaseHelper;

public class SettingActivity extends BaseActivity{
	class FlagAdapter extends BaseAdapter{
		private ArrayList<String> mStarNameStrings;
		private LayoutInflater inflater;
		public FlagAdapter(){
			inflater = LayoutInflater.from(AppContext.getInstance());
		}
		@Override
		public int getCount() {
			if (mStarNameStrings == null){
				return 0;
			}
			return mStarNameStrings.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		public void setStarList(ArrayList<String> list){
			mStarNameStrings = list;
			((BaseAdapter)this).notifyDataSetChanged();
		}

		@SuppressLint("NewApi")
		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			if (arg1 == null){
				arg1 = inflater.inflate(R.layout.flag_star_item, null);
			}

			String name = mStarNameStrings.get(arg0);
			Log.v("dog", "arg :" + arg0 + "get name : " + name);
			CountryItem item = CountryDataBase.getInstance().getCountryItem(name);
			Log.v("dog", "get cnName:" + item.getCnName());
			((ImageView) arg1.findViewById(R.id.flag)).setBackground(
					AssetFileTool.getBitmapDrawable(item.getSmallPath()));
			
			((TextView)arg1.findViewById(R.id.name)).setText(item.getCnName());
			return arg1;
		}
		
	}
	
	private ToggleButton mSilencButton;
	private ToggleButton mCommButton;
	private ListView mStarsListView;
	private FlagAdapter mAdapter;
	
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
		mStarsListView = (ListView)findViewById(R.id.starlist);
		mAdapter = new FlagAdapter();
		mStarsListView.setAdapter(mAdapter);
		mAdapter.setStarList(SqliteDataBaseHelper.getInstance().getAllStarNames());
		
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
		
		((TextView)findViewById(R.id.flagnumtip)).setText("您已捕获国旗("+SqliteDataBaseHelper.getInstance().getAllStarNames().size() + ")个");
		((TextView)findViewById(R.id.flagstarinfo)).setText("连续答对该国" + CountryDataBase.BE_STAR_COUNT + "次即可捕获该国国旗");
	}
}
