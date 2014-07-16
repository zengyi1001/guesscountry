package com.partrick.widget;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class Animatin3DFlip extends Animation{
	private final float mFromDegrees;		// ��ת�Ƕ�
	private final float mToDegrees;
	private final float mCenterX;			//���ģ�������Ϊ��λ
	private final float mCenterY;			
	private Camera mCamera;

	public Animatin3DFlip(float fromDegrees, float toDegrees,
	   float centerX, float centerY) {
		mFromDegrees = fromDegrees;
		mToDegrees = toDegrees;
		mCenterX = centerX;
		mCenterY = centerY;
	}

	@Override
	public void initialize(int width, int height, int parentWidth, int parentHeight) {
		super.initialize(width, height, parentWidth, parentHeight);
		mCamera = new Camera();
	}

	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		final float fromDegrees = mFromDegrees;
		float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);
	
		final float centerX = mCenterX;
		final float centerY = mCenterY;
		final Camera camera = mCamera;
	
		final Matrix matrix = t.getMatrix();
	
		camera.save();
	
		camera.rotateY(degrees);
	
		camera.getMatrix(matrix);
		camera.restore();
	
		matrix.preTranslate(-centerX, -centerY);
		matrix.postTranslate(centerX, centerY);

	}
}


