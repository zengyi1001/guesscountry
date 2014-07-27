package com.patrick.guesscountry.gamelogic;

import com.patrick.guesscountry.data.SqliteDataBaseHelper;

/**
 * 游戏纪录
 * @author Patrick
 *
 */
public class GameRecord{
	private static GameRecord mInstance;
	private int mGamePlayType = GamePlayType.GAME_TYPE_RAMDON;
	private int mRandomRecord;
	private int mExpertRecord;
	public static GameRecord getInstance(){
		if (mInstance == null){
			mInstance = new GameRecord();
		}
		
		return mInstance;
	}
	
	private int mMaxRandomShot = 0;
	private int mMaxExpertShot = 0;
	private boolean mHasCheered = false;
		
	public void init(){
		mMaxExpertShot = 0;
		mMaxRandomShot = 0;
		mHasCheered = false;
		
		mRandomRecord = SqliteDataBaseHelper.getInstance().getRecordCount("random");
		mExpertRecord = SqliteDataBaseHelper.getInstance().getRecordCount("expert");
	}
	
		
	/**
	 * 给出答案给record,返回是否需要庆祝一下
	 * @param isRight
	 * @return
	 */
	public boolean answer(boolean isOneTimeShot){
		if (isOneTimeShot){
			if (mGamePlayType == GamePlayType.GAME_TYPE_RAMDON){
				mMaxRandomShot++;
				if (mMaxRandomShot > mRandomRecord){
					SqliteDataBaseHelper.getInstance().setRecord("random", mMaxRandomShot);
					mRandomRecord = mMaxRandomShot;
					if (!mHasCheered){
						mHasCheered = true;
						return true;
					}
				}
			}else{
				mMaxExpertShot++;
				if (mMaxExpertShot > mExpertRecord){
					SqliteDataBaseHelper.getInstance().setRecord("expert", mMaxExpertShot);
					mExpertRecord = mMaxExpertShot;
					if (!mHasCheered){
						mHasCheered = true;
						return true;
					}
				}
			}
			
			return false;
		}else{
			if (mGamePlayType == GamePlayType.GAME_TYPE_RAMDON){
				mMaxRandomShot = 0;
			}else{
				mMaxExpertShot = 0;
			}
			mHasCheered = false;
			return false;
		}
	}
	
	public int getMaxShot(){
		if (mGamePlayType == GamePlayType.GAME_TYPE_RAMDON){
			return mMaxRandomShot;
		}
		
		return mMaxExpertShot;
	}
	
	public int getRecord(){
		if (mGamePlayType == GamePlayType.GAME_TYPE_EXPERT){
			return mExpertRecord;
		}
		
		return mRandomRecord;
	}
	
	public void setGamePlayType(int type){
		mGamePlayType = type;
		
		mHasCheered = false;
		mMaxExpertShot = 0;
		mMaxRandomShot = 0;
	}
}
