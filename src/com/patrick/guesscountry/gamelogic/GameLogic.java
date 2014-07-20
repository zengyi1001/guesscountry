package com.patrick.guesscountry.gamelogic;

import java.util.ArrayList;
import java.util.Iterator;

import com.patrick.guesscountry.data.CountryItem;


public class GameLogic {
	
	private static GameLogic mInstance;
	private ArrayList<IGameControlListener> mGameListeners;
	
	public static GameLogic getInstance(){
		if (mInstance == null){
			mInstance = new GameLogic();
		}
		
		return mInstance;
	}
	
	private GameLogic(){
		mGameListeners = new ArrayList<IGameControlListener>();
	}
	
	private ExaminationItem mCurExamination;
	
	private ExaminationItem generateExamination(){
		if (mCurExamination == null){
			mCurExamination = new ExaminationItem();
		}
		mCurExamination.generateOptions();
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
		
		Iterator<IGameControlListener> iterator = mGameListeners.iterator();
		while (iterator.hasNext()){
			iterator.next().onAnswerResult(country, isRight);
		}
	}
	
	public void requestExam(){
		generateExamination();
		Iterator<IGameControlListener> iterator = mGameListeners.iterator();
		while (iterator.hasNext()){
			iterator.next().onExaminationStart(mCurExamination);
		}
	}
}
