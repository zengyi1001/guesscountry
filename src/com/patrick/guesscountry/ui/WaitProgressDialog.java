package com.patrick.guesscountry.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

public class WaitProgressDialog {
	ProgressDialog mProgressDialog;
	private Activity mActivity;
	public WaitProgressDialog(Activity activity){
		mActivity = activity;
	}
		
	public void showProgressDialog(String message){
		if (mProgressDialog == null){
			mProgressDialog = new ProgressDialog(mActivity);
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
