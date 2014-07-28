package com.patrick.guesscountry.data;

public class CountryItem{
	private String mEnName;
	private String mCnName;
	private String mPicPathString;
	private String mSmallPicPathString;
	private String mCnStateNameString = "";
	private String mEnStateNameString = "";
	private String mSoundString = "";
	private boolean mIsUsual = false;
	private int mType = 0;
	
	public String getEnName(){
		return mEnName;
	}
	
	public void setEnName(String name){
		mEnName = name;
	}
	
	public String getCnName(){
		return mCnName;
	}
	
	public void setCnName(String name){
		mCnName = name;
	}
	
	public String getPicPath(){
		return mPicPathString;
	}
	
	public void setPicPath(String path){
		mPicPathString = path;
	}
	
	public boolean isCommon(){
		return mIsUsual;
	}
	
	public void setCommon(boolean isCommon){
		mIsUsual = isCommon;
	}
	
	public void setSmallPicPath(String path){
		mSmallPicPathString = path;
	}
	
	public String getSmallPath(){
		return mSmallPicPathString;
	}
	
	public String getShowName(){
		if (PrefenceData.getInstance().isUseEN()){
			return mEnName+"(" + mEnStateNameString+")";
		}else{
			return mCnName+"(" + mCnStateNameString+")";
		}
	}
	
	public int getType(){
		return mType;
	}
	
	public void setType(int type){
		mType = type;
	}
	
	public void setCNStateName(String name){
		mCnStateNameString = name;
	}
	
	public void setEnStateName(String name){
		mEnStateNameString = name;
	}
	
	public String getCnStateName(){
		return mCnStateNameString;
	}
	
	public String getSoundString(){
		return mSoundString;
	}
	
	public void setSoundString(String soundString){
		mSoundString = soundString;
	}
}
