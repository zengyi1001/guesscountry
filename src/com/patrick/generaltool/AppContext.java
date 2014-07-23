package com.patrick.generaltool;

import java.util.ArrayList;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;

public class AppContext extends Application{
	private static AppContext instance;
	Handler mHandler;
	String mImei;
	String mIccid;
	private BaseActivity mCurActivity;
	
	public static String ACTION_FINISH_ACTIVITY = "com.patrick.finish_action";
	private ArrayList<BaseActivity> mAllActivities;
	
	 @Override
	  public void onCreate() {
	      super.onCreate();
	      instance = this;
	      mHandler = new Handler();
	      mAllActivities = new ArrayList<BaseActivity>();
	  }
	 
	 public static AppContext getInstance(){
		 return instance;
	 }
	 
	 public void runOnUIThread(Runnable runnable){
		  mHandler.post(runnable);
	  }
	  
	  public void runOnUIThread(Runnable r,long delay){
		  mHandler.postDelayed(r, delay);
	  }
	  
	  public void removeCall(Runnable r){
		  mHandler.removeCallbacks(r);
	  }
	  
	  public BaseActivity getCurActivity(){
		  return mCurActivity;
	  }
	  
	  public void setCurActivity(BaseActivity activity){
		  mCurActivity = activity;
	  }
	  
	  public String getImei(){
		  if(mImei!=null){
			  return mImei;
		  }
	      TelephonyManager telephonyManager = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
	      mImei = telephonyManager.getDeviceId();
	      if(null == mImei){
	    	  return "";
	      }
		  return mImei;
	  }
	  
	  public String getIccid(){
		  if(null!=mIccid && !mIccid.equals("")){
			  return mIccid;
		  }
	      TelephonyManager telephonyManager = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
	      mIccid = telephonyManager.getSimSerialNumber();
		  return mIccid;
	  }
	  
	  public String getVerName() {
          String verName = "δ֪";
          try {
                  verName = getPackageManager().getPackageInfo(
                                  getPackageName(), 0).versionName;
          } catch (NameNotFoundException e) {
                  Log.e("Config", e.getMessage());
          }
          return verName;
	  }
	  
	public int getVercode() {
		try {
			return getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			return -1;
		}
	}
	
	public String getProcessName(){
		 int pid = android.os.Process.myPid();  
		  ActivityManager mActivityManager = (ActivityManager) this  
		    .getSystemService(Context.ACTIVITY_SERVICE);  
		  for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager  
		    .getRunningAppProcesses()) {  
		   if (appProcess.pid == pid) {  
		  
		    return appProcess.processName;  
		   }  
		  }  
		  return null; 
	 }
	
	public void addActivity(BaseActivity activity){
		if (mAllActivities != null && activity != null && !mAllActivities.contains(activity)){
			mAllActivities.add(activity);
		}
	}
	
	public void removeActivity(BaseActivity activity){
		if (mAllActivities != null && activity != null && mAllActivities.contains(activity)){
			mAllActivities.remove(activity);
		}
	}
	
	public void exitApp(){
		for (int i = 0; i < mAllActivities.size();i++){
			mAllActivities.get(i).finish();
		}
		System.exit(0);
	}
}
