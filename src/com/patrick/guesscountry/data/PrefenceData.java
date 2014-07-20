package com.patrick.guesscountry.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.patrick.generaltool.AppContext;

public class PrefenceData {
	private static PrefenceData mInstance;
	
	private final String PREFENCE_FILE_NAME = "pf_file";
	private final String PARAM_ONLY_USUAL = "onlyUsual";
	private final String PARAM_SILENCE = "silence";
	
	private boolean mIsOnlyCommon = false;
	private boolean mIsSilence = false;
	
	private static SharedPreferences mSp;
	public static PrefenceData getInstance(){
		if (mInstance == null){
			mInstance = new PrefenceData();
		}
		return mInstance;
	}
	
	private PrefenceData(){
		mSp = AppContext.getInstance().getSharedPreferences(PREFENCE_FILE_NAME, Context.MODE_PRIVATE);
		mIsOnlyCommon = mSp.getBoolean(PARAM_ONLY_USUAL, true);
		mIsSilence = mSp.getBoolean(PARAM_SILENCE, true);
	}
	
	/**
	 * 是否只显示常用的国家
	 * @return
	 */
	public boolean isOnlyCommon(){
		return mIsOnlyCommon;
	}
	
	public void setOnlyCommon(boolean value){
		Editor editor = mSp.edit();
		editor.putBoolean(PARAM_ONLY_USUAL, value);
		editor.commit();
		mIsOnlyCommon = value;
	}
	
	/**
	 * 是否播报声音
	 */
	/**
	 * 是否只显示常用的国家
	 * @return
	 */
	public boolean isSilence(){
		return mIsSilence;
	}
	
	public void setIsSilence(boolean value){
		Editor editor = mSp.edit();
		editor.putBoolean(PARAM_SILENCE, value);
		editor.commit();
		mIsOnlyCommon = value;
	}
}
