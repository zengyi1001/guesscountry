package com.patrick.guesscountry.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.patrick.generaltool.AssetFileTool;
import com.patrick.guesscountry.data.CountryDataBase;

public class FlagPicImageView extends ImageView {
	
	private String mNameString;
	
	public FlagPicImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public FlagPicImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public FlagPicImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	public void setFlagName(String name){
		mNameString = name;
		//setBackground();
	}
	
	public String getName(){
		return mNameString;
	}
	
	@SuppressWarnings("deprecation")
	public void setName(String name){
		mNameString = name;
		setBackgroundDrawable(AssetFileTool.getBitmapDrawable(CountryDataBase.getInstance().getDataPath(mNameString)));
	}
	
	
	 /**
     * @see android.view.View#measure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }

    /**
     * Determines the width of this view
     * @param measureSpec A measureSpec packed into an int
     * @return The width of the view, honoring constraints from measureSpec
     */
    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY
        		|| specMode == MeasureSpec.AT_MOST) {
            // We were told how big to be
            result = specSize;
        } else {
            result = 100;
        }

        return result;
    }

    /**
     * Determines the height of this view
     * @param measureSpec A measureSpec packed into an int
     * @return The height of the view, honoring constraints from measureSpec
     */
    private int measureHeight(int measureSpec) {
    	 int result = 0;
         int specMode = MeasureSpec.getMode(measureSpec);
         int specSize = MeasureSpec.getSize(measureSpec);

         if (specMode == MeasureSpec.EXACTLY
         		|| specMode == MeasureSpec.AT_MOST) {
             // We were told how big to be
             result = specSize;
         } else {
             result = 100;
         }

         return result;
    }

}
