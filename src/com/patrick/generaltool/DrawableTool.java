package com.patrick.generaltool;

import java.io.InputStream;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;

public class DrawableTool {
	public static BitmapDrawable getBitMapByResID(Context context, int id){
		InputStream is = context.getResources().openRawResource(id);
		return getBitMapByInputStream(context, is);
	}
	
	public static BitmapDrawable getBitMapByInputStream(Context context, InputStream is){
		BitmapDrawable  bmpDraw = new BitmapDrawable(context.getResources(), is);
		return bmpDraw;
	}
}
