package com.patrick.guesscountry.data;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.patrick.generaltool.AppContext;


public class SqliteDataBaseHelper {
	public interface IGetNewStarListener{
		public void onGetNewStar(String starName);
	}
	private static SqliteDataBaseHelper mInstance;
	
	public static SqliteDataBaseHelper getInstance(){
		if (mInstance == null){
			mInstance = new SqliteDataBaseHelper();
		}
		
		return mInstance;
	}
	
	private boolean mIsDBInited = false;
	private IGetNewStarListener mListener;
	private SqliteDataBaseHelper(){
		mStarNames = new ArrayList<String>();
		try {
			initDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setListener(IGetNewStarListener listener){
		mListener = listener;
	}
	
	private ArrayList<String> mStarNames;
	
	public void init(){
		try {
			initDBData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private final String DBNAME = "data.db";
	private SQLiteDatabase mDB;
	
	// 如果数据库损坏，则应该抛出异常并删除损坏的数据库文件	
	private void initDB() throws Exception{
		String dbPathString = AppContext.getInstance().getFilesDir() + "/" + DBNAME;
		mDB = SQLiteDatabase.openOrCreateDatabase(dbPathString, null);
		mDB.beginTransaction();
		try{
			mDB.execSQL("create table if not exists star(name VARCHAR(128) PRIMARY KEY, count INTEGER32);");
			mDB.execSQL("create table if not exists record(name VARCHAR(128) PRIMARY KEY , num INTEGER32);");
			mDB.setTransactionSuccessful();
		}catch(Exception e){
			e.printStackTrace();
			
			throw e;
		}
		finally{
			mDB.endTransaction();
		}
		mIsDBInited = true;
	}
	
	private void initDBData(){
		if (!mIsDBInited){
			return;
		}
		String columns[] = {"name", "count"};
		Cursor cursor = mDB.query("star", columns, null, null, null, null, null);
		if (cursor == null || cursor.getCount() == 0){
			return;
		}
		for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			String starName = cursor.getString(cursor.getColumnIndex("name"));
			int count = cursor.getInt(cursor.getColumnIndex("count"));
			Log.v("dog", "init name : " + starName + ", count :" + count);
			if (count >= CountryData.BE_STAR_COUNT){
				mStarNames.add(starName);
			}
		}
		
		cursor.close();
	}
	
	public ArrayList<String> getAllStarNames(){
		return mStarNames;
	}
	
	public void resetStarCount(String starName){
		mDB.beginTransaction();
		try{
			String sqlString = String.format("DELETE FROM star WHERE name=\'%s\'", starName);
			Log.v("dog", "Sql : " + sqlString);
			mDB.execSQL(sqlString);
			mDB.setTransactionSuccessful();
			if (mStarNames.contains(starName)){
				mStarNames.remove(starName);
			}
		}finally{
			mDB.endTransaction();
		}
	}
	
	public void addStarCount(String starName){
		int count = getStarCount(starName);
		if (0 == count){
			count++;
			insertStar(starName);
			if (count == CountryData.BE_STAR_COUNT){
				if (mListener != null){
					mListener.onGetNewStar(starName);
				}
				mStarNames.add(starName);
			}
			return;
		}else{
			count++;
			updateStarRecord(starName, count);
			if (count == CountryData.BE_STAR_COUNT){
				if (mListener != null){
					mListener.onGetNewStar(starName);
				}
				mStarNames.add(starName);
			}
		}
	}
	
	public void removeListener(IGetNewStarListener listener){
		if (mListener == listener){
			mListener = null;
		}
	}
	
	public int getStarCount(String name){
		mDB.beginTransaction();
		int count = 0;
		try{
			String selection = "name=?";
			String selectionArgs[] = {name};
			Cursor cursor = mDB.query("star", new String[]{"name","count"}, selection, selectionArgs, null, null, null);
			if (!cursor.moveToFirst()){
				return 0;
			}
			count = cursor.getInt(cursor.getColumnIndex("count"));
			mDB.setTransactionSuccessful();
		}finally{
			mDB.endTransaction();
		}
		return count;
		
	}
	
	public void insertStar(String starName){
		if (mDB == null){
			return;
		}
		
		mDB.beginTransaction();
		try{
			
			String sqlString = String.format("INSERT INTO star VALUES (\'%s\', \'%d\')", starName, 1);
			Log.v("dog", "Sql : " + sqlString);
			mDB.execSQL(sqlString);
			mDB.setTransactionSuccessful();
		}catch(Exception e){
			Log.v("dog", e.getMessage());
		}
		finally{
			mDB.endTransaction();
		}
	}
	
	public void insertRecord(String type, int num){
		if (mDB == null){
			return;
		}
		
		mDB.beginTransaction();
		try{
			
			String sqlString = String.format("INSERT INTO record VALUES (\'%s\', \'%d\')", type, num);
			Log.v("dog", "Sql : " + sqlString);
			mDB.execSQL(sqlString);
			mDB.setTransactionSuccessful();
		}catch(Exception e){
			Log.v("dog", e.getMessage());
		}
		finally{
			mDB.endTransaction();
		}
	}
	
	public void updateStarRecord(String starName,int count){
		
		mDB.beginTransaction();
		try{
			String sqlString = String.format("UPDATE star SET count=\'%d\' WHERE name=\'%s\'", count, starName);
			Log.v("dog", "Sql : " + sqlString);
			mDB.execSQL(sqlString);
			mDB.setTransactionSuccessful();
		}finally{
			mDB.endTransaction();
		}
	}
	
	public void updateRecord(String type,int count){
		
		mDB.beginTransaction();
		try{
			String sqlString = String.format("UPDATE record SET num=\'%d\' WHERE name=\'%s\'", count, type);
			Log.v("dog", "Sql : " + sqlString);
			mDB.execSQL(sqlString);
			mDB.setTransactionSuccessful();
		}finally{
			mDB.endTransaction();
		}
	}
	
	public void setRecord(String type, int num){
		int record = getRecordCount(type);
		if (record == 0){
			insertRecord(type, num);
		}else{
			updateRecord(type, num);
		}
	}
	
	public int getRecordCount(String type){
		mDB.beginTransaction();
		int count = 0;
		try{
			String selection = "name=?";
			String selectionArgs[] = {type};
			Cursor cursor = mDB.query("record", new String[]{"name","num"}, selection, selectionArgs, null, null, null);
			if (!cursor.moveToFirst()){
				return 0;
			}
			count = cursor.getInt(cursor.getColumnIndex("num"));
			mDB.setTransactionSuccessful();
		}finally{
			mDB.endTransaction();
		}
		return count;
		
	}
}
