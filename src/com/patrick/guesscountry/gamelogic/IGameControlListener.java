package com.patrick.guesscountry.gamelogic;

import com.patrick.guesscountry.data.CountryItem;

public interface IGameControlListener {
	public void onExaminationStart(ExaminationItem exam);
	public void onAnswerResult(CountryItem country, boolean isRight);
}
