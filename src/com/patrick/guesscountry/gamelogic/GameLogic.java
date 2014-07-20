package com.patrick.guesscountry.gamelogic;

import java.util.ArrayList;
import java.util.Iterator;

import com.patrick.guesscountry.data.CountryItem;


public class GameLogic {
	public class AnswerInfomation{
		public CountryItem countrySelected;
		public boolean isRight;
		public boolean shoudCheer;
	}
	private static GameLogic mInstance;
	private ArrayList<IGameControlListener> mGameListeners;
	
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
		mCurExamination = new ExaminationItem();
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
		boolean isCheer = GameRecord.getInstance().answer(mCurExamination.isOneTimeShot());
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
}
