package com.patrick.guesscountry.gamelogic;

import java.util.ArrayList;
import java.util.Random;

import com.patrick.guesscountry.data.CountryDataBase;

public class Examination{
	public interface IExamAnsweredListener{
		public void onAnswered(boolean isRight);
	}
	
	private ArrayList<String> mOptions;
	private String mAnswer;
	private IExamAnsweredListener mListener;
	
	public Examination(IExamAnsweredListener listener){
		mOptions = new ArrayList<String>();
		mListener = listener;
	}
	
	private boolean isOptionsFull(){
		if (mOptions.size() >= 4){
			return true;
		}
		return false;
	}
	
	private boolean addOption(String option){
		if (option == null || mOptions.contains(option)){
			return false;
		}
		mOptions.add(option);
		return true;
	}
	
	public String getAnswer(){
		return mAnswer;
	}
	
	private void generateAnswer(){
		mAnswer = mOptions.get(new Random().nextInt(mOptions.size()));
	}
	
	public ArrayList<String> getOptions(){
		return mOptions;
	}
	
	public void generateOptions(){
		mOptions.clear();
		while (!isOptionsFull()){
			String optionString = CountryDataBase.getInstance().generateDataRandom();
			addOption(optionString);
		}
		
		generateAnswer();
	}
	
	public boolean answerExam(String answer){
		if (answer != null && answer.equals(mAnswer)){
			if (mListener != null){
				mListener.onAnswered(true);
			}
			return true;
		}
		if (mListener != null){
			mListener.onAnswered(false);
		}
		return false;
	}
}
