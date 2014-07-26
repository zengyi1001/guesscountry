package com.patrick.guesscountry.gamelogic;

import java.util.ArrayList;
import java.util.Iterator;

import com.patrick.guesscountry.data.CountryItem;
import com.patrick.guesscountry.data.SqliteDataBaseHelper;


public class GameLogic {
	public class AnswerInfomation{
		public CountryItem countrySelected;
		public boolean isRight;
		public boolean shoudCheer;
	}
	
	private static GameLogic mInstance;
	private ArrayList<IGameControlListener> mGameListeners;
	private int mGamePlayType = GamePlayType.GAME_TYPE_RAMDON;
	
	public static GameLogic getInstance(){
		if (mInstance == null){
			mInstance = new GameLogic();
		}
		
		return mInstance;
	}
	
	private GameLogic(){
		
	}
	
	private ExaminationItem mCurExamination;
	
	private ExaminationItem generateExamination(){
		mCurExamination = GamePlayTypeFactory.getInstance().getGameType(mGamePlayType).getExamItem();
		return mCurExamination;
	}
	
	public void addListener(IGameControlListener listener){
		if (listener != null && mGameListeners != null && !mGameListeners.contains(listener)){
			mGameListeners.add(listener);
		}
	}
	
	public void removeListener(IGameControlListener listener){
		if (listener != null && mGameListeners != null && mGameListeners.contains(listener)){
			mGameListeners.remove(listener);
		}
	}
	
	public void answer(CountryItem country){
		boolean isRight = mCurExamination.answerExam(country);
		boolean isOneTimeShot = mCurExamination.isOneTimeShot();
		
		if (isOneTimeShot){
			SqliteDataBaseHelper.getInstance().addStarCount(country.getEnName());
		}else{
			SqliteDataBaseHelper.getInstance().resetStarCount(country.getEnName());
		}
		
		boolean isCheer = GameRecord.getInstance().answer(isOneTimeShot);
		AnswerInfomation ai = new AnswerInfomation();
		ai.isRight = isRight;
		ai.countrySelected = country;
		ai.shoudCheer = isCheer;
		Iterator<IGameControlListener> iterator = mGameListeners.iterator();
		while (iterator.hasNext()){
			iterator.next().onAnswerResult(ai);
		}
	}
	
	public void requestExam(){
		generateExamination();
		Iterator<IGameControlListener> iterator = mGameListeners.iterator();
		while (iterator.hasNext()){
			iterator.next().onExaminationStart(mCurExamination);
		}
	}
	
	public void init(){
		mGameListeners = new ArrayList<IGameControlListener>();
	}
	
	public void setType(int type){
		mGamePlayType = type;
		GameRecord.getInstance().setGamePlayType(mGamePlayType);
	}
}
