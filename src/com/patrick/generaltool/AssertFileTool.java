package com.patrick.generaltool;

import java.io.IOException;

import android.content.res.AssetManager;

public class AssertFileTool {
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
}
