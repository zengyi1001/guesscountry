package com.patrick.guesscountry.gamelogic;

import com.patrick.guesscountry.data.PrefenceData;

/**
 * 游戏纪录
 * @author Patrick
 *
 */
public class GameRecord{
	private static GameRecord mInstance;
	public static GameRecord getInstance(){
		if (mInstance == null){
			mInstance = new GameRecord();
		}
		
		return mInstance;
	}
	
	private int mMaxShot = 0;
	private boolean mHasCheered = false;
		
	public void init(){
		mMaxShot = 0;
		mHasCheered = false;
	}
	
		
	/**
	 * 给出答案给record,返回是否需要庆祝一下
	 * @param isRight
	 * @return
	 */
	public boolean answer(boolean isOneTimeShot){
		if (isOneTimeShot){
			mMaxShot++;
			if (mMaxShot > PrefenceData.getInstance().getRecord()){
				PrefenceData.getInstance().setRecord(mMaxShot);
				if (!mHasCheered){
					mHasCheered = true;
					return true;
				}
			}
			return false;
		}else{
			mMaxShot = 0;
			mHasCheered = false;
			return false;
		}
	}
	
	public int getMaxShot(){
		return mMaxShot;
	}
	
	public int getRecord(){
		return PrefenceData.getInstance().getRecord();
	}
}
