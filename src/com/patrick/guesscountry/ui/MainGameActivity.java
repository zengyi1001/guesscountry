package com.patrick.guesscountry.ui;

import java.util.HashMap;

import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.patrick.generaltool.AppContext;
import com.patrick.generaltool.BaseActivity;
import com.patrick.generaltool.MediaTonePlayer;
import com.patrick.guesscountry.R;
import com.patrick.guesscountry.data.CountryData;
import com.patrick.guesscountry.data.SqliteDataBaseHelper;
import com.patrick.guesscountry.data.SqliteDataBaseHelper.IGetNewStarListener;
import com.patrick.guesscountry.gamelogic.ExaminationItem;
import com.patrick.guesscountry.gamelogic.GameLogic;
import com.patrick.guesscountry.gamelogic.GameLogic.AnswerInfomation;
import com.patrick.guesscountry.gamelogic.GamePlayType;
import com.patrick.guesscountry.gamelogic.GameRecord;
import com.patrick.guesscountry.gamelogic.IGameControlListener;
import com.patrick.guesscountry.ui.AnswerPassDialog.IDialogDismissListener;

public class MainGameActivity extends BaseActivity implements IDialogDismissListener
		, IGameControlListener, IGetNewStarListener{
	class ShowLanguageChangedReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			changeShowLanguage();
		}
		
	}
	
	
	private ExaminationItem mCurExamination;
	private FlagSelectView mImg1;
	private FlagSelectView mImg2;
	private FlagSelectView mImg3;
	private FlagSelectView mImg4;
	private HashMap<String, FlagSelectView> mCountry2ViewMap;
	private MediaTonePlayer mMediaTonePlayer;
	private WaitProgressDialog mProgressDialog;
	private int mGameType;
	private AnswerPassDialog mShowAnswerDialog;
	private ShowLanguageChangedReceiver mShowLanguageChangedReceiver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		mMediaTonePlayer = new MediaTonePlayer(null);
		mGameType = getIntent().getIntExtra("type", GamePlayType.GAME_TYPE_RAMDON);
		GameLogic.getInstance().setType(mGameType);
		mShowLanguageChangedReceiver = new ShowLanguageChangedReceiver();
		registerReceiver(mShowLanguageChangedReceiver, new IntentFilter(SettingActivity.ACTION_SHOW_LANGUAGE_CHANGED));
		initUI();	
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		GameLogic.getInstance().addListener(this);
		if (mCurExamination == null){
			GameLogic.getInstance().requestExam();
		}
		SqliteDataBaseHelper.getInstance().setListener(this);
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		GameLogic.getInstance().removeListener(this);
		SqliteDataBaseHelper.getInstance().removeListener(this);
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		unregisterReceiver(mShowLanguageChangedReceiver);
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
				finish();
			}
		});
		findViewById(R.id.btn_menu).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(MainGameActivity.this, SettingActivity.class));
			}
		});
		mShowAnswerDialog = new AnswerPassDialog(this, this);
		mProgressDialog = new WaitProgressDialog(this);
		
		if (mGameType == GamePlayType.GAME_TYPE_EXPERT){
			((TextView)findViewById(R.id.modetip)).setText("专家模式");
		}else{
			((TextView)findViewById(R.id.modetip)).setText("随机模式");
		}
	}
	
	private void showExam(){
		((TextView)findViewById(R.id.word)).setText(mCurExamination.getAnswer().getShowName());
		mImg1.setCountry(mCurExamination.getOptions().get(0));
		mCountry2ViewMap.put(mCurExamination.getOptions().get(0).getEnName(), mImg1);
		mImg2.setCountry(mCurExamination.getOptions().get(1));
		mCountry2ViewMap.put(mCurExamination.getOptions().get(1).getEnName(), mImg2);
		mImg3.setCountry(mCurExamination.getOptions().get(2));
		mCountry2ViewMap.put(mCurExamination.getOptions().get(2).getEnName(), mImg3);
		mImg4.setCountry(mCurExamination.getOptions().get(3));
		mCountry2ViewMap.put(mCurExamination.getOptions().get(3).getEnName(), mImg4);
		String recordString = String.format("当前连对 %d 个\n最高连对 %d 个", GameRecord.getInstance().getMaxShot(),
				GameRecord.getInstance().getRecord());
		((TextView)findViewById(R.id.record)).setText(recordString);
		mMediaTonePlayer.playBeepSound(mCurExamination.getAnswer().getSoundString());
	}

	@Override
	public void onAnswerPassDialogDismiss() {
		GameLogic.getInstance().requestExam();
	}

	@Override
	public void onExaminationStart(ExaminationItem exam) {
		mProgressDialog.showProgressDialog("读题中...");
		mCurExamination = exam;
		showExam();
		mProgressDialog.hideProgressDialog();
	}

	@Override
	public void onAnswerResult(AnswerInfomation ai) {
		FlagSelectView view = mCountry2ViewMap.get(ai.countrySelected.getEnName());
		if (!ai.isRight){
			view.setCry();
			mMediaTonePlayer.playBeepSound(R.raw.oo);
		}else{
			mShowAnswerDialog.showAnswerName(ai);
			if (ai.shoudCheer){
				mMediaTonePlayer.playBeepSound(R.raw.junyue);
				Builder builder = new Builder(this);
				builder.setMessage("您诞生了新的连胜纪录!!!");
				builder.setTitle("恭喜！");
				builder.setPositiveButton("确定", null);
				builder.create().show();
			}else{
				mMediaTonePlayer.playBeepSound(R.raw.tieqin);
			}
		}
	}

	@Override
	public void onGetNewStar(String starName) {
		String cnName = CountryData.getInstance().getCnName(starName);
		Builder builder = new Builder(this);
		builder.setMessage("您收藏了国家“"+cnName+"”");
		builder.setTitle("恭喜！");
		builder.setPositiveButton("确定", null);
		builder.create().show();
	}		
	
	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    MainGameActivity.this.finish();
	    return super.onKeyDown(keyCode, event);
	}
	
	private void changeShowLanguage(){
		mImg1.changeShowLanguage();
		mImg2.changeShowLanguage();
		mImg3.changeShowLanguage();
		mImg4.changeShowLanguage();
		((TextView)findViewById(R.id.word)).setText(mCurExamination.getAnswer().getShowName());
	}
	
}
