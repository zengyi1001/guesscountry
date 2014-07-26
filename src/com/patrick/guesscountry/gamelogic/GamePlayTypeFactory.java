package com.patrick.guesscountry.gamelogic;

public class GamePlayTypeFactory {
	private static GamePlayTypeFactory mInstance;
	private GamePlayTypeExpert mExpertType;
	private GamePlayTypeRandom mRandomType;
	public static GamePlayTypeFactory getInstance(){
		if (mInstance == null){
			mInstance = new GamePlayTypeFactory();
		}
		
		return mInstance;
	}
	
	public GamePlayType getGameType(int type){
		if (type == GamePlayType.GAME_TYPE_EXPERT){
			if (mExpertType == null){
				mExpertType = new GamePlayTypeExpert();
			}
			return mExpertType;
		}else{
			if (mRandomType == null){
				mRandomType = new GamePlayTypeRandom();
			}
			return mRandomType;
		}
	}
}
