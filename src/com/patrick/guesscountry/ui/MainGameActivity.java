package com.patrick.guesscountry.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.patrick.generaltool.BaseActivity;
import com.patrick.generaltool.MediaTonePlayer;
import com.patrick.guesscountry.R;
import com.patrick.guesscountry.gamelogic.Examination;
import com.patrick.guesscountry.gamelogic.GameLogic;
import com.patrick.guesscountry.ui.FlagSelectView.IPicClickedListener;

public class MainGameActivity extends BaseActivity implements IPicClickedListener{
	private Examination mCurExamination;
	private FlagSelectView mImg1;
	private FlagSelectView mImg2;
	private FlagSelectView mImg3;
	private FlagSelectView mImg4;
	
	private MediaTonePlayer mMediaTonePlayer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maingame);
		mMediaTonePlayer = new MediaTonePlayer(null);
		initUI();
		getExam();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	private void handleExame(boolean isRight){
		if (isRight){
			mMediaTonePlayer.playBeepSound(R.raw.tieqin);
			getExam();
		}else {
			mMediaTonePlayer.playBeepSound(R.raw.oo);
		}
	}
	
	private void initUI(){
		mImg1 = new FlagSelectView(this);
		mImg1.setRootView(findViewById(R.id.option1));
		
		mImg2 = new FlagSelectView(this);
		mImg2.setRootView(findViewById(R.id.option2));
		
		mImg3 = new FlagSelectView(this);
		mImg3.setRootView(findViewById(R.id.option3));
		
		mImg4 = new FlagSelectView(this);
		mImg4.setRootView(findViewById(R.id.option4));
		
		
		findViewById(R.id.btn_back).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	
	}
	
	private void showExample(){
		((TextView)findViewById(R.id.word)).setText(mCurExamination.getAnswer());
		mImg1.setName(mCurExamination.getOptions().get(0));
		mImg2.setName(mCurExamination.getOptions().get(1));
		mImg3.setName(mCurExamination.getOptions().get(2));
		mImg4.setName(mCurExamination.getOptions().get(3));
	}
	
	private void getExam(){
		mCurExamination = GameLogic.getInstance().generateExamination();
		showExample();
	}

	@Override
	public void onPicClick(FlagSelectView view, String name) {
		boolean right = mCurExamination.answerExam(name);
		if (!right){
			view.setCry();
		}
		handleExame(right);
	}
}
