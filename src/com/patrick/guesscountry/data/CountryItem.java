package com.patrick.guesscountry.data;

public class CountryItem{
	public String mEnName;
	public String mCnName;
	public String mPicPathString;
	public boolean mIsUsual = false;
	
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
	
}
