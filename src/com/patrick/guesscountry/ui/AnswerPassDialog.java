package com.patrick.guesscountry.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.patrick.generaltool.AppContext;
import com.patrick.generaltool.AssetFileTool;
import com.patrick.guesscountry.R;
import com.patrick.guesscountry.gamelogic.GameLogic.AnswerInfomation;

public class AnswerPassDialog {
	public interface IDialogDismissListener{
		public void onAnswerPassDialogDismiss();
	}
	private Dialog mDialog;
	private TextView mNameTextView;
	private IDialogDismissListener mListener;
	private View mRootView;
	private ImageView mFlagView;
	private Activity mActivity;
	
	public AnswerPassDialog(Activity activity, IDialogDismissListener listener){
		mActivity = activity;
		mListener = listener;
		initUI();
	}
	
	public void setListener(IDialogDismissListener listener){
		mListener = listener;
	}
	
	private void initUI(){
		mDialog = new Dialog(mActivity, R.style.dialog);
		
		mDialog.setCanceledOnTouchOutside(true);
		mDialog.setCancelable(true);
		mDialog.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss(DialogInterface arg0) {
				if (mListener != null){
					mListener.onAnswerPassDialogDismiss();
				}
			}
		});
		
		mRootView = LayoutInflater.from(AppContext.getInstance().getApplicationContext()).inflate(R.layout.dialog_show_passed, null);
		mDialog.setContentView(mRootView);
		
		mNameTextView = (TextView)mRootView.findViewById(R.id.name);
		mFlagView = (ImageView)mRootView.findViewById(R.id.img);
		
		mRootView.findViewById(R.id.body).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				mDialog.dismiss();
			}
		});
	}
	
	public void showAnswerName(AnswerInfomation ai){
		mNameTextView.setText(ai.countrySelected.getShowName());
		mFlagView.setBackgroundDrawable(
				AssetFileTool.getBitmapDrawable(
						ai.countrySelected.getPicPath()));
		mDialog.show();
	}
	
}
