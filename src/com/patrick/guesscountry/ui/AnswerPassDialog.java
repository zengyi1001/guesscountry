package com.patrick.guesscountry.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.patrick.generaltool.AppContext;
import com.patrick.generaltool.AssetFileTool;
import com.patrick.guesscountry.R;
import com.patrick.guesscountry.data.CountryDataBase;

public class AnswerPassDialog {
	public interface IDialogDismissListener{
		public void onDismiss();
	}
	private static AnswerPassDialog mInstance = null;
	private Dialog mDialog;
	private TextView mNameTextView;
	private TextView mRecordTextView;
	private IDialogDismissListener mListener;
	private View mRootView;
	private ImageView mFlagView;
	
	public static AnswerPassDialog getInstance(){
		if (mInstance == null){
			mInstance = new AnswerPassDialog();
		}
		
		return mInstance;
	}
	
	public AnswerPassDialog(){
		initUI();
	}
	
	public void setListener(IDialogDismissListener listener){
		mListener = listener;
	}
	
	private void initUI(){
		mDialog = new Dialog(AppContext.getInstance()
				.getApplicationContext(), R.style.dialog);
		
		mDialog.setCanceledOnTouchOutside(true);
		mDialog.setCancelable(true);
		mDialog.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss(DialogInterface arg0) {
				if (mListener != null){
					mListener.onDismiss();
				}
			}
		});
		mDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG);
		mDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		
		mRootView = LayoutInflater.from(AppContext.getInstance().getApplicationContext()).inflate(R.layout.dialog_show_passed, null);
		mDialog.setContentView(mRootView);
		
		mNameTextView = (TextView)mRootView.findViewById(R.id.name);
		mFlagView = (ImageView)mRootView.findViewById(R.id.img);
		mRecordTextView = (TextView)mRootView.findViewById(R.id.record);
	}
	
	public void showAnswerName(String name){
		mNameTextView.setText(name);
		mFlagView.setBackgroundDrawable(
				AssetFileTool.getBitmapDrawable(
						CountryDataBase.getInstance().getDataPath(name)));
		mDialog.show();
	}
	
}
