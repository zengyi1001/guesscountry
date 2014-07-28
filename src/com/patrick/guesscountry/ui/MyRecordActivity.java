package com.patrick.guesscountry.ui;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.patrick.generaltool.AppContext;
import com.patrick.generaltool.AssetFileTool;
import com.patrick.generaltool.BaseActivity;
import com.patrick.guesscountry.R;
import com.patrick.guesscountry.data.CountryData;
import com.patrick.guesscountry.data.CountryItem;
import com.patrick.guesscountry.data.SqliteDataBaseHelper;

public class MyRecordActivity extends BaseActivity{
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
			
			CountryItem item = CountryData.getInstance().getCountryItem(name);
			((ImageView) arg1.findViewById(R.id.flag)).setBackgroundDrawable(
					AssetFileTool.getBitmapDrawable(item.getSmallPath()));
			
			((TextView)arg1.findViewById(R.id.name)).setText(item.getShowName());
			return arg1;
		}
		
	}
	
	private ListView mStarsListView;
	private FlagAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myrecord);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		initUI();
	}
	
	private void initUI(){
		mStarsListView = (ListView)findViewById(R.id.starlist);
		mAdapter = new FlagAdapter();
		
		mStarsListView.setAdapter(mAdapter);
		mAdapter.setStarList(SqliteDataBaseHelper.getInstance().getAllStarNames());
		
		((TextView)findViewById(R.id.random)).setText("随机模式连对记录: " + SqliteDataBaseHelper.getInstance().getRecordCount("random"));
		((TextView)findViewById(R.id.expert)).setText("专家模式连对记录: " + SqliteDataBaseHelper.getInstance().getRecordCount("expert"));
		
		findViewById(R.id.btn_back).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				MyRecordActivity.this.finish();
			}
		});
		
		((TextView)findViewById(R.id.flagnumtip)).setText("您捕获的国旗 " + SqliteDataBaseHelper.getInstance().getAllStarNames().size() + " 个");
		((TextView)findViewById(R.id.flagstarinfo)).setText("（连续答对该国国旗 " + CountryData.BE_STAR_COUNT + " 次即可捕获）");
	}
}
