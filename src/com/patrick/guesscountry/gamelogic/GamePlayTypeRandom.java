package com.patrick.guesscountry.gamelogic;

import java.util.Random;

import com.patrick.guesscountry.data.CountryData;
import com.patrick.guesscountry.data.CountryItem;
import com.patrick.guesscountry.data.PrefenceData;

public class GamePlayTypeRandom extends GamePlayType {
	ExaminationItem item;
	public GamePlayTypeRandom(){
		mType = GamePlayType.GAME_TYPE_RAMDON;
	}
	@Override
	public ExaminationItem getExamItem() {
		if (item == null){
			item = new ExaminationItem();
		}
		generateOptions();
		return item;
	}

	private void generateOptions(){
		item = new ExaminationItem();
		while (!item.isOptionsFull()){
			CountryItem country = generateDataRandom();
			if (PrefenceData.getInstance().isOnlyCommon() && !country.isCommon()){
				continue;
			}
			item.addOption(country);
		}
		
		item.generateAnswer();
	}
	
	private CountryItem generateDataRandom(){
		int randomIndex = new Random().nextInt(CountryData.getInstance().getDataSize());
		return CountryData.getInstance().getAllCountrys().get(randomIndex);
	}
}
