package com.patrick.guesscountry.data;

public class CountryItem{
	private String mEnName;
	private String mCnName;
	private String mPicPathString;
	private String mSmallPicPathString;
	private boolean mIsUsual = false;
	
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
}
