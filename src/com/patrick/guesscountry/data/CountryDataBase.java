package com.patrick.guesscountry.data;

import java.util.HashMap;
import java.util.Random;

import com.patrick.generaltool.AssertFileTool;

public class CountryDataBase {
	public interface IDataInitListener{
		public void onInitFinished();
	}
	
	static private CountryDataBase mInstance;
	private boolean mIsInited = false;
	private String[] mAllDataStrings; 
	private HashMap<String, String> mAllDataPicPath;
	
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
		
		mAllDataPicPath = new HashMap<String, String>();
		mAllDataStrings = AssertFileTool.ListAssetsFile("pics");
		if (mAllDataStrings != null){
			for (int i = 0; i < mAllDataStrings.length; i++){
				mAllDataPicPath.put(mAllDataStrings[i], "pics/" + mAllDataStrings[i]);
			}
		}
		
		mIsInited = true;
		
		if (listener != null){
			listener.onInitFinished();
		}
	}
	
	public int getDataSize(){
		if (mAllDataStrings == null){
			return 0;
		}
		return mAllDataStrings.length;
	}
	
	public String getDataPath(String data){
		if (mAllDataPicPath == null || !mAllDataPicPath.containsKey(data)){
			return null;
		}
		
		return mAllDataPicPath.get(data);
	}
	
	public String generateDataRandom(){
		if (getDataSize() == 0){
			return null;
		}
		int randomIndex = new Random().nextInt(getDataSize());
		randomIndex %= getDataSize();
		
		return mAllDataStrings[randomIndex];
	}
}
