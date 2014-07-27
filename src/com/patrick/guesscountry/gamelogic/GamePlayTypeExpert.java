package com.patrick.guesscountry.gamelogic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.patrick.guesscountry.data.CountryData;
import com.patrick.guesscountry.data.CountryItem;

public class GamePlayTypeExpert extends GamePlayType{
	private ExaminationItem item;
	private ArrayList<CountryItem> mAllType1CountryArrayList;
	private ArrayList<CountryItem> mAllType2CountryArrayList;
	private ArrayList<CountryItem> mAllType3CountryArrayList;
	private ArrayList<CountryItem> mAllType4CountryArrayList;
	ArrayList<CountryItem> mAllCountry;
	public GamePlayTypeExpert(){
		mType = GamePlayType.GAME_TYPE_EXPERT;
		
		mAllType1CountryArrayList = new ArrayList<CountryItem>();
		mAllType2CountryArrayList = new ArrayList<CountryItem>();
		mAllType3CountryArrayList = new ArrayList<CountryItem>();
		mAllType4CountryArrayList = new ArrayList<CountryItem>();
		mAllCountry = CountryData.getInstance().getAllCountrys();
		initAllType();
	}
	@Override
	public ExaminationItem getExamItem() {
		if (item == null){
			item = new ExaminationItem();
		}
		generateExam();
		return item;
	}
	
	private void initAllType(){
		ArrayList<CountryItem> allCountry = CountryData.getInstance().getAllCountrys();
		
		Iterator<CountryItem> iterator = allCountry.iterator();
		while (iterator.hasNext()){
			CountryItem item = iterator.next();
			switch (item.getType()){
			case 1:
				mAllType1CountryArrayList.add(item);
				break;
				
			case 2:
				mAllType2CountryArrayList.add(item);
				break;
				
			case 3:
				mAllType3CountryArrayList.add(item);
				break;
				
			case 4:
				mAllType4CountryArrayList.add(item);
				break;
			}
		}
	}

	private void generateExam(){
		item = new ExaminationItem();
		ArrayList<CountryItem> goalCountrys = null;
		int randomIndex = new Random().nextInt(mAllCountry.size());
		
		while (mAllCountry.get(randomIndex).getType() == 0){
			randomIndex = new Random().nextInt(mAllCountry.size());
		}
		
		int type = mAllCountry.get(randomIndex).getType();
		
		switch (type) {
		case 1:
			goalCountrys = mAllType1CountryArrayList;
			break;
		case 2:
			goalCountrys = mAllType2CountryArrayList;
			break;
		case 3:
			goalCountrys = mAllType3CountryArrayList;
			break;
		case 4:
			goalCountrys = mAllType4CountryArrayList;
			break;
		default:
			break;
		}
		
		while (!item.isOptionsFull()){
			CountryItem country = generateDataRandom(goalCountrys);
			
			item.addOption(country);
		}
		
		item.generateAnswer();
	}
	private CountryItem generateDataRandom(ArrayList<CountryItem> goal){
		int randomIndex = new Random().nextInt(goal.size());	
		return goal.get(randomIndex);
	}
}
