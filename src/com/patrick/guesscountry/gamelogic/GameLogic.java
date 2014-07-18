package com.patrick.guesscountry.gamelogic;

import com.patrick.guesscountry.gamelogic.Examination.IExamAnsweredListener;


public class GameLogic implements IExamAnsweredListener{
	
	
	private static GameLogic mInstance;
	
	public static GameLogic getInstance(){
		if (mInstance == null){
			mInstance = new GameLogic();
		}
		
		return mInstance;
	}
	
	private Examination mCurExamination;
	
	public Examination generateExamination(){
		if (mCurExamination == null){
			mCurExamination = new Examination(this);
		}
		mCurExamination.generateOptions();
		return mCurExamination;
	}

	@Override
	public void onAnswered(boolean isRight) {
		// TODO Auto-generated method stub
		
	}
}
