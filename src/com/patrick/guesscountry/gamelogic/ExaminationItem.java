package com.patrick.guesscountry.gamelogic;

import java.util.ArrayList;
import java.util.Random;

import com.patrick.guesscountry.data.CountryDataBase;
import com.patrick.guesscountry.data.CountryItem;
import com.patrick.guesscountry.data.PrefenceData;

public class ExaminationItem{

	private final static int Max_Options = 4;
	private ArrayList<CountryItem> mOptions;
	private CountryItem mAnswer;
	private boolean mIsOneTimeShot = true;

	public ExaminationItem(){
		mOptions = new ArrayList<CountryItem>();
		generateOptions();
	}
	
	private boolean isOptionsFull(){
		if (mOptions.size() >= Max_Options){
			return true;
		}
		return false;
	}
	
	private boolean addOption(CountryItem option){
		if (option == null || mOptions.contains(option)){
			return false;
		}
		mOptions.add(option);
		return true;
	}
	
	public CountryItem getAnswer(){
		return mAnswer;
	}
	
	private void generateAnswer(){
		mAnswer = mOptions.get(new Random().nextInt(mOptions.size()));
	}
	
	public ArrayList<CountryItem> getOptions(){
		return mOptions;
	}
	
	private void generateOptions(){
		mOptions.clear();
		while (!isOptionsFull()){
			CountryItem country = CountryDataBase.getInstance().generateDataRandom();
			if (PrefenceData.getInstance().isOnlyCommon() && !country.isCommon()){
				continue;
			}
			addOption(country);
		}
		
		generateAnswer();
	}
	
	public boolean answerExam(CountryItem country){
		if (country != null && country.equals(mAnswer)){		
			return true;
		}
		mIsOneTimeShot = false;
		return false;
	}
	
	public boolean isOneTimeShot(){
		return mIsOneTimeShot;
	}
}
