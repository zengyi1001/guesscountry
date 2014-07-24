package com.patrick.generaltool;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

public class AssetFileTool {
	public static String[] ListAssetsFile(String AssetsPath)
    {
		AssetManager am = AppContext.getInstance().getAssets();
		try {
			String[] FileOrDirName = am.list(AssetsPath);
			return FileOrDirName;
//			for (int i = 0; i < FileOrDirName.length; i++) {
//				Log.v("dog", FileOrDirName[i]);
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
    }
	
	public static BitmapDrawable getBitmapDrawable(String mAssertFilePath){
		AssetManager am = AppContext.getInstance().getAssets();
		InputStream is;
		try {
			is = am.open(mAssertFilePath);
			return DrawableTool.getBitMapByInputStream(AppContext.getInstance(), is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static boolean isFileExist(String filePath){
		AssetManager am = AppContext.getInstance().getAssets();
		try{
			InputStream is = am.open(filePath);
			is.close();
			return true;
		}catch(Exception exception){
			return false;
			
		}
	}
}
