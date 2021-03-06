package com.patrick.guesscountry.gamelogic;

import com.patrick.guesscountry.data.CountryData;
import com.patrick.guesscountry.data.SqliteDataBaseHelper;

public class GameIniter {
	private static GameIniter mInstance;
	public static GameIniter getInstance(){
		if (mInstance == null){
			mInstance = new GameIniter();
		}
		
		return mInstance;
	}
	private boolean mIsInited = false;
	public interface IGameInitListener{
		public void onInitFinished();
	}
	
	public void init(IGameInitListener listener){
		if (mIsInited){
			if (listener != null){
				listener.onInitFinished();
			}
		}
		SqliteDataBaseHelper.getInstance().init();
		CountryData.getInstance().init();
		GameLogic.getInstance().init();
		GameRecord.getInstance().init();
		if (listener != null){
			listener.onInitFinished();
		}
	}
}
