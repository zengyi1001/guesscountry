package com.patrick.guesscountry.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

import com.patrick.generaltool.AppContext;

public class WaitProgressDialog {
	ProgressDialog mProgressDialog;
	
	static private WaitProgressDialog mInstance;
	
	static public WaitProgressDialog getInstance(){
		if (mInstance == null){
			mInstance = new WaitProgressDialog();
		}
		return mInstance;
	}
	
	public void showProgressDialog(String message){
		if (mProgressDialog == null){
			mProgressDialog = new ProgressDialog(AppContext.getInstance().getCurActivity());
			mProgressDialog.setOnDismissListener(new OnDismissListener() {

				@Override
				public void onDismiss(DialogInterface arg0) {
					
				}
			});
			mProgressDialog.setCanceledOnTouchOutside(false);
		}
		mProgressDialog.setMessage(message);
		mProgressDialog.show();
	}
	
	public void hideProgressDialog(){
		if (mProgressDialog != null && mProgressDialog.isShowing()){
			mProgressDialog.dismiss();
		}
	}

}
