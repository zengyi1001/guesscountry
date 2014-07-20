package com.patrick.guesscountry.ui;

import java.util.HashMap;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.patrick.generaltool.AppContext;
import com.patrick.generaltool.BaseActivity;
import com.patrick.generaltool.MediaTonePlayer;
import com.patrick.guesscountry.R;
import com.patrick.guesscountry.data.CountryItem;
import com.patrick.guesscountry.gamelogic.ExaminationItem;
import com.patrick.guesscountry.gamelogic.GameLogic;
import com.patrick.guesscountry.gamelogic.IGameControlListener;
import com.patrick.guesscountry.ui.AnswerPassDialog.IDialogDismissListener;

public class MainGameActivity extends BaseActivity implements IDialogDismissListener, IGameControlListener{
	private ExaminationItem mCurExamination;
	private FlagSelectView mImg1;
	private FlagSelectView mImg2;
	private FlagSelectView mImg3;
	private FlagSelectView mImg4;
	private HashMap<String, FlagSelectView> mCountry2ViewMap;
	private MediaTonePlayer mMediaTonePlayer;
	private WaitProgressDialog mProgressDialog;
	
	private AnswerPassDialog mShowAnswerDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maingame);
		mMediaTonePlayer = new MediaTonePlayer(null);
		initUI();	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		GameLogic.getInstance().addListener(this);
		if (mCurExamination == null){
			GameLogic.getInstance().requestExam();
		}
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		GameLogic.getInstance().removeListener(this);
	}
	
	private void initUI(){
		mImg1 = new FlagSelectView();
		mImg1.setRootView(findViewById(R.id.option1));
		
		mImg2 = new FlagSelectView();
		mImg2.setRootView(findViewById(R.id.option2));
		
		mImg3 = new FlagSelectView();
		mImg3.setRootView(findViewById(R.id.option3));
		
		mImg4 = new FlagSelectView();
		mImg4.setRootView(findViewById(R.id.option4));
		
		mCountry2ViewMap = new HashMap<String, FlagSelectView>();
		findViewById(R.id.btn_back).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AppContext.getInstance().exitApp();
			}
		});
		mShowAnswerDialog = new AnswerPassDialog(this, this);
		mProgressDialog = new WaitProgressDialog(this);
	}
	
	private void showExample(){
		((TextView)findViewById(R.id.word)).setText(mCurExamination.getAnswer().getCnName());
		mImg1.setCountry(mCurExamination.getOptions().get(0));
		mCountry2ViewMap.put(mCurExamination.getOptions().get(0).getEnName(), mImg1);
		mImg2.setCountry(mCurExamination.getOptions().get(1));
		mCountry2ViewMap.put(mCurExamination.getOptions().get(1).getEnName(), mImg2);
		mImg3.setCountry(mCurExamination.getOptions().get(2));
		mCountry2ViewMap.put(mCurExamination.getOptions().get(2).getEnName(), mImg3);
		mImg4.setCountry(mCurExamination.getOptions().get(3));
		mCountry2ViewMap.put(mCurExamination.getOptions().get(3).getEnName(), mImg4);
	}

	@Override
	public void onAnswerPassDialogDismiss() {
		GameLogic.getInstance().requestExam();
	}

	@Override
	public void onExaminationStart(ExaminationItem exam) {
		mProgressDialog.showProgressDialog("∂¡Ã‚÷–...");
		mCurExamination = exam;
		showExample();
		mProgressDialog.hideProgressDialog();
	}

	@Override
	public void onAnswerResult(CountryItem country, boolean isRight) {
		FlagSelectView view = mCountry2ViewMap.get(country.getEnName());
		if (!isRight){
			view.setCry();
			mMediaTonePlayer.playBeepSound(R.raw.oo);
		}else{
			mMediaTonePlayer.playBeepSound(R.raw.tieqin);
			mShowAnswerDialog.showAnswerName(country);
		}
	}

		
	
}
