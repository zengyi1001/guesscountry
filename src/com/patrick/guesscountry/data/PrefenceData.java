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
	private final String PARAM_MAX_RIGHT_RECORD = "record";	// 连续答对的最大记录
	
	private boolean mIsOnlyCommon = false;
	private boolean mIsSilence = false;
	private int mRecord = 0;
	
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
		mIsSilence = mSp.getBoolean(PARAM_SILENCE, false);
		mRecord = mSp.getInt(PARAM_MAX_RIGHT_RECORD, 0);
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
		mIsSilence = value;
	}
	
	/**
	 * 连对纪录
	 */
	/**
	 * 是否只显示常用的国家
	 * @return
	 */
	public int getRecord(){
		return mRecord;
	}
	
	public void setRecord(int value){
		Editor editor = mSp.edit();
		editor.putInt(PARAM_MAX_RIGHT_RECORD, value);
		editor.commit();
		mRecord = value;
	}
}
