package com.patrick.guesscountry.gamelogic;

import com.patrick.guesscountry.gamelogic.GameLogic.AnswerInfomation;

public interface IGameControlListener {
	public void onExaminationStart(ExaminationItem exam);
	public void onAnswerResult(AnswerInfomation answer);
}
