package com.example.kanbankasi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class acilis extends Activity {

	MediaPlayer song;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acilis);
	song= new MediaPlayer().create(acilis.this,R.raw.fsdfsd);
 SharedPreferences getPref=PreferenceManager.getDefaultSharedPreferences(getBaseContext());
	song.start();

		Thread timer =new Thread(){
			public void run(){
				try{
					sleep(5000);
					
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
				finally{
					startActivity(new Intent(getApplicationContext(),Mainactivity.class));
				}
				}
			};
			timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		song.release();
		finish();
	}

	}


