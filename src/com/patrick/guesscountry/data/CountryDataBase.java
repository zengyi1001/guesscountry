package com.patrick.guesscountry.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.patrick.generaltool.AppContext;
import com.patrick.generaltool.AssetFileTool;

public class CountryDataBase {
	static private CountryDataBase mInstance;
	private ArrayList<CountryItem> mAllCountrys;
	
	public static int BE_STAR_COUNT = 3;	// 收集国家需要的次数
	
	public static CountryDataBase getInstance(){
		if (mInstance == null){
			mInstance = new CountryDataBase();
		}
		return mInstance;
	}
		
	public void init(){	
		getAllUsualCountryNames();
		getAllCountryDatas();
	}
	
	public int getDataSize(){
		if (mAllCountrys == null){
			return 0;
		}
		return mAllCountrys.size();
	}
	
		
	
	
	private void getAllCountryDatas(){
		mAllCountrys = new ArrayList<CountryItem>();
		String[] allPicFiles = AssetFileTool.ListAssetsFile("countrys");
		HashMap<String, String> countryEn2CnMap = getEN2CNMap();
		ArrayList<String> allUsualCountryNames = getAllUsualCountryNames();
		HashMap<String, Integer> en2TypeMap = getCountryType();
		for (int i = 0; i < allPicFiles.length; i++){
			String nameString = allPicFiles[i].replaceAll(".jpg", "");
			
			nameString = nameString.replaceAll(".png", "");
			String cnName = countryEn2CnMap.get(nameString);
			CountryItem item = new CountryItem();
			item.setCnName(cnName);
			item.setEnName(nameString);
			item.setPicPath("countrys/" + allPicFiles[i]);
			String bianpingPathString= "countrys_small/" + "small_" + allPicFiles[i];
			item.setSmallPicPath(bianpingPathString);
			if (allUsualCountryNames.contains(item.getEnName())){
				item.setCommon(true);
			}
			item.setType(en2TypeMap.get(item.getEnName()));
			mAllCountrys.add(item);
		}
	}
	
	private  ArrayList<String> getAllUsualCountryNames(){
		 ArrayList<String> allUsualCountryNames = new ArrayList<String>();
		InputStream is;
		try {
			is = AppContext.getInstance().getAssets().open("usualcountrys.txt");
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String lineString = null;
			while ((lineString = br.readLine()) != null){
				String[] enCnStrings = lineString.split(":");
				allUsualCountryNames.add(enCnStrings[1]);
			}
			
			br.close();
			isr.close();
			
			return allUsualCountryNames;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	public String getCnName(String enName){
		Iterator<CountryItem> iterator = mAllCountrys.iterator();
		while (iterator.hasNext()){
			CountryItem item = iterator.next();
			if (item.getEnName().equals(enName)){
				return item.getCnName();
			}
		}
		return null;
	}
	
	public CountryItem getCountryItem(String enName){
		for (int i = 0; i < mAllCountrys.size(); i++){
			if (mAllCountrys.get(i).getEnName().equals(enName)){
				return mAllCountrys.get(i);
			}
		}
		
		return null;
	}
	
	
	private HashMap<String, String> getEN2CNMap(){
		try {
			HashMap<String, String> mCountryEN2CNMap = new HashMap<String, String>();
			InputStream is = AppContext.getInstance().getAssets().open("flagscn2en.txt");
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			
			while((line = br.readLine()) != null){
				String[] cnEn = line.split(":");
				if (mCountryEN2CNMap.containsKey(cnEn[1])){
					System.out.println("检测到重复项: " + cnEn[1]);
					return mCountryEN2CNMap;
				}
				mCountryEN2CNMap.put(cnEn[1], cnEn[0]);
			}
			br.close();
			isr.close();
			
			return mCountryEN2CNMap;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<CountryItem> getAllCountrys(){
		return mAllCountrys;
	}
	
	private HashMap<String, Integer> getCountryType(){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		try{
			InputStream is = AppContext.getInstance().getAssets().open("flagscn2en.txt");
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			
			while((line = br.readLine()) != null){
				String[] cnEn = line.split(":");
				if (cnEn.length < 3){
					map.put(cnEn[1], 0);
				}else{
					map.put(cnEn[1], Integer.parseInt(cnEn[2]));
				}
			}
		}catch (Exception e){
			
		}
		
		
		return map;
	}
}
