package com.patrick.generaltool;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

public class DrawableTool {
	public static Bitmap getBitMapByResID(Context context, int id){
		InputStream is = context.getResources().openRawResource(id);
		BitmapDrawable  bmpDraw = new BitmapDrawable(context.getResources(), is);
		return bmpDraw.getBitmap();
	}
}
