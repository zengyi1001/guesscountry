package com.patrick.guesscountry.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.patrick.generaltool.AssetFileTool;
import com.patrick.guesscountry.R;
import com.patrick.guesscountry.data.CountryItem;
import com.patrick.guesscountry.gamelogic.GameLogic;

public class FlagSelectView {
	private View mRootView;
	private CountryItem mCountryItem;
	private ImageView mFlagImageView;
	private ImageView mCryImageView;
	private TextView mNameTextView;
	private ImageView mQuestionView;
	
	public FlagSelectView(){
		
	}

	public void setRootView(View rootView){
		mRootView = rootView;
		mFlagImageView = (ImageView)mRootView.findViewById(R.id.img);
		mCryImageView = (ImageView)mRootView.findViewById(R.id.cry);
		mNameTextView = (TextView)mRootView.findViewById(R.id.name);
		mFlagImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				GameLogic.getInstance().answer(mCountryItem);
				mNameTextView.setText(mCountryItem.getCnName());
				mNameTextView.setVisibility(View.VISIBLE);
				mQuestionView.setVisibility(View.INVISIBLE);
			}
		});
		
		mQuestionView = (ImageView)mRootView.findViewById(R.id.questionshow);
	}
	
	public void setCountry(CountryItem country){
		mCountryItem = country;
		mFlagImageView.setBackgroundDrawable(
				AssetFileTool.getBitmapDrawable(mCountryItem.getPicPath()));
		
		mCryImageView.setVisibility(View.INVISIBLE);
		mNameTextView.setVisibility(View.INVISIBLE);
		mQuestionView.setVisibility(View.VISIBLE);
	}
	
	public void setCry(){
		mCryImageView.setVisibility(View.VISIBLE);
	}

}
