package com.patrick.guesscountry.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.patrick.generaltool.AppContext;
import com.patrick.generaltool.AssetFileTool;

public class CountryDataBase {
	static private CountryDataBase mInstance;
	
	private ArrayList<CountryItem> mAllCountrys;
	private ArrayList<String> mAllUsualCountryNames;

	private HashMap<String, String> mCountryEN2CNMap;
	
	public static CountryDataBase getInstance(){
		if (mInstance == null){
			mInstance = new CountryDataBase();
		}
		return mInstance;
	}
		
	public void init(){	
		initEN2CNMap();
		getAllUsualCountryNames();
		getAllCountryDatas();
	}
	
	public int getDataSize(){
		if (mAllCountrys == null){
			return 0;
		}
		return mAllCountrys.size();
	}
	
		
	public CountryItem generateDataRandom(){
		if (getDataSize() == 0){
			return null;
		}
		int randomIndex = new Random().nextInt(getDataSize());
		randomIndex %= getDataSize();
		
		return mAllCountrys.get(randomIndex);
	}
	
	private void getAllCountryDatas(){
		mAllCountrys = new ArrayList<CountryItem>();
		String[] allPicFiles = AssetFileTool.ListAssetsFile("countrys");
		for (int i = 0; i < allPicFiles.length; i++){
			String nameString = allPicFiles[i].replaceAll(".jpg", "");
			nameString = nameString.replaceAll(".png", "");
			String cnName = mCountryEN2CNMap.get(nameString);
			CountryItem item = new CountryItem();
			item.setCnName(cnName);
			item.setEnName(nameString);
			item.setPicPath("countrys/" + allPicFiles[i]);
			if (mAllUsualCountryNames.contains(item.getEnName())){
				item.setCommon(true);
			}
			
			mAllCountrys.add(item);
		}
	}
	
	private void getAllUsualCountryNames(){
		mAllUsualCountryNames = new ArrayList<String>();
		InputStream is;
		try {
			is = AppContext.getInstance().getAssets().open("usualcountrys.txt");
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String lineString = null;
			while ((lineString = br.readLine()) != null){
				String[] enCnStrings = lineString.split(":");
				mAllUsualCountryNames.add(enCnStrings[1]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	private void initEN2CNMap(){
		try {
			mCountryEN2CNMap = new HashMap<String, String>();
			InputStream is = AppContext.getInstance().getAssets().open("flagscn2en.txt");
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			
			while((line = br.readLine()) != null){
				String[] cnEn = line.split(":");
				if (mCountryEN2CNMap.containsKey(cnEn[1])){
					System.out.println("ºÏ≤‚µΩ÷ÿ∏¥œÓ: " + cnEn[1]);
					return;
				}
				mCountryEN2CNMap.put(cnEn[1], cnEn[0]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
