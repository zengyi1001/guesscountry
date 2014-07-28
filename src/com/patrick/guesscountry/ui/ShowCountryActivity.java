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
import com.patrick.generaltool.MediaTonePlayer;
import com.patrick.guesscountry.R;
import com.patrick.guesscountry.data.CountryData;
import com.patrick.guesscountry.data.CountryItem;

public class ShowCountryActivity extends BaseActivity {
	class FlagAdapter extends BaseAdapter{
		class FlagClick implements OnClickListener{
			CountryItem item;
			public FlagClick(CountryItem item){
				this.item = item;
			}
			@Override
			public void onClick(View arg0) {
				mPlayer.playBeepSound(item.getSoundString());
			}
		}
		private ArrayList<CountryItem> mStars;
		private LayoutInflater inflater;
		public FlagAdapter(){
			inflater = LayoutInflater.from(AppContext.getInstance());
		}
		@Override
		public int getCount() {
			if (mStars == null){
				return 0;
			}
			return mStars.size();
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
		
		public void setStarList(ArrayList<CountryItem> list){
			mStars = list;
			((BaseAdapter)this).notifyDataSetChanged();
		}

		@SuppressLint("NewApi")
		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			if (arg1 == null){
				arg1 = inflater.inflate(R.layout.flag_star_item, null);
			}

			CountryItem item = mStars.get(arg0);
		
			((ImageView) arg1.findViewById(R.id.flag)).setBackgroundDrawable(
					AssetFileTool.getBitmapDrawable(item.getSmallPath()));
			
			((TextView)arg1.findViewById(R.id.name)).setText(item.getShowName());
			arg1.findViewById(R.id.flag).setOnClickListener(new FlagClick(item));
			return arg1;
		}
		
	}
	
	private ListView mListView;
	private FlagAdapter mAdapter;
	private MediaTonePlayer mPlayer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showcountrys);
		initUI();
		mPlayer = new MediaTonePlayer(null);
	}
	
	private void initUI(){
		mListView = (ListView)findViewById(R.id.starlist);
		mAdapter = new FlagAdapter();
		mAdapter.setStarList(CountryData.getInstance().getAllCountrys());
		
		mListView.setAdapter(mAdapter);
		
		findViewById(R.id.btn_back).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
}
