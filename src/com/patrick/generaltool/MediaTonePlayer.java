package com.patrick.generaltool;

import java.io.IOException;

import com.patrick.guesscountry.data.PrefenceData;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class MediaTonePlayer {
	private MediaPlayer mMediaPlayer;
	
	private OnCompletionListener mCompletionListener;
	
	public MediaTonePlayer(OnCompletionListener listener){
		mCompletionListener = listener;
		mMediaPlayer = buildMediaPlayer();
	}
	
	private MediaPlayer buildMediaPlayer() {
		MediaPlayer mediaPlayer = new MediaPlayer();
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				if (mCompletionListener != null) {
					mCompletionListener.onCompletion(null);
				}
			}
		});
		
		return mediaPlayer;
	}
			
	public void playBeepSound(int beeId) {
		if (PrefenceData.getInstance().isSilence()){
			return;
		}
		AssetFileDescriptor file = AppContext.getInstance().getResources().openRawResourceFd(beeId);
		
		if (mMediaPlayer == null) {
			throw new RuntimeException("MediaPlayer has been released.");
		}
		if (mMediaPlayer.isPlaying()) {
			mMediaPlayer.stop();
		}

		mMediaPlayer.reset();
		mMediaPlayer.setLooping(false);

		try {
			mMediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
			mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mMediaPlayer.prepare();
			mMediaPlayer.start();
		} catch (IllegalStateException ioe) {
			
		} catch (IllegalArgumentException e) {
			
		} catch (IOException e) {
			
		} finally {
			try {
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
