package com.patrick.guesscountry.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.patrick.generaltool.AppContext;
import com.patrick.generaltool.AssetFileTool;
import com.patrick.guesscountry.R;
import com.patrick.guesscountry.data.CountryDataBase;

public class FlagSelectView {
	public interface IPicClickedListener{
		public void onPicClick(FlagSelectView view, String name);
	}
	private View mRootView;
	private String mName;
	private ImageView mFlagImageView;
	private ImageView mCryImageView;
	private IPicClickedListener mPicClickedListener;
	
	public FlagSelectView(IPicClickedListener listener){
		mPicClickedListener = listener;
	}

	public void setRootView(View rootView){
		mRootView = rootView;
		mFlagImageView = (ImageView)mRootView.findViewById(R.id.img);
		mCryImageView = (ImageView)mRootView.findViewById(R.id.cry);
		mFlagImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (mPicClickedListener != null){
					mPicClickedListener.onPicClick(FlagSelectView.this, mName);
				}
			}
		});
	}
	
	public void setName(String name){
		mName = name;
		mFlagImageView.setBackgroundDrawable(
				AssetFileTool.getBitmapDrawable(
						CountryDataBase.getInstance().getDataPath(mName)));
		
		mCryImageView.setVisibility(View.INVISIBLE);
	}
	
	public void setCry(){
		mCryImageView.setVisibility(View.VISIBLE);
	}
}
