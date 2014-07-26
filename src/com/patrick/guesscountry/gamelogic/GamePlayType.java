package com.patrick.guesscountry.gamelogic;

public abstract class GamePlayType {
	final public static int FLAG_TYPE_NONE = 0;
	final public static int FLAG_TYPE_BARS = 1;	// 横条竖条
	final public static int FLAG_TYPE_CROSS = 2;	// 十字
	final public static int FLAG_TYPE_INBARS = 3;	// 条条中有一个图案
	final public static int FLAG_TYPE_MOON_STARS = 4;	// 有一个星星月亮图案
	
	
	final public static int GAME_TYPE_RAMDON = 0;
	final public static int GAME_TYPE_EXPERT = 1;
	
	protected int mType = 0;
	
	public int getType(){
		return mType;
	}
	
	abstract public ExaminationItem getExamItem();
}
