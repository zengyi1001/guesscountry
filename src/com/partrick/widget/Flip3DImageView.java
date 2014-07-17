package com.partrick.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 	实现图片翻滚切换效果
 * @author PatrickStar
 *
 */
public class Flip3DImageView extends ImageView{
	public Flip3DImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	public Flip3DImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public Flip3DImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
		
	private Drawable mCurDrawable;
	private Drawable mNextDrawable;
	
	public void setCurDrawable(Drawable cur){
		
	}
}
