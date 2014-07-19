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
	public interface IDataInitListener{
		public void onInitFinished();
	}
	
	static private CountryDataBase mInstance;
	private boolean mIsInited = false;
	private ArrayList<String> mAllNames; 
	private HashMap<String, String> mNamePathMap;
	private HashMap<String, String> mCountryEN2CNMap;
	
	public static CountryDataBase getInstance(){
		if (mInstance == null){
			mInstance = new CountryDataBase();
		}
		return mInstance;
	}
		
	public void init(IDataInitListener listener){
		if (mIsInited){
			if (listener != null){
				listener.onInitFinished();
			}
		}
		
		initEN2CNMap();
		
		mNamePathMap = new HashMap<String, String>();
		String[] allPicFiles = AssetFileTool.ListAssetsFile("countrys");
		mAllNames = new ArrayList<String>();
		if (allPicFiles != null){
			for (int i = 0; i < allPicFiles.length; i++){
				String nameWithNoPostSuffix = allPicFiles[i].replaceAll(".jpg", "");
				nameWithNoPostSuffix = nameWithNoPostSuffix.replaceAll(".png", "");
				String cnName = mCountryEN2CNMap.get(nameWithNoPostSuffix);
				if (cnName != null){
					mAllNames.add(cnName);
				}
				mNamePathMap.put(cnName, "countrys/" + allPicFiles[i]);
			}
		}
		
		mIsInited = true;
		
		if (listener != null){
			listener.onInitFinished();
		}
	}
	
	public int getDataSize(){
		if (mAllNames == null){
			return 0;
		}
		return mAllNames.size();
	}
	
	public String getDataPath(String data){
		if (mNamePathMap == null || !mNamePathMap.containsKey(data)){
			return null;
		}
		
		return mNamePathMap.get(data);
	}
	
	public String generateDataRandom(){
		if (getDataSize() == 0){
			return null;
		}
		int randomIndex = new Random().nextInt(getDataSize());
		randomIndex %= getDataSize();
		
		return mAllNames.get(randomIndex);
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
