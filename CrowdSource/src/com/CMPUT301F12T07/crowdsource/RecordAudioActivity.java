package com.CMPUT301F12T07.crowdsource;

import java.io.File;
import java.io.IOException;
import java.util.Timer;

import com.CMPUT301F12T07.crowdsource.R;
import android.media.MediaRecorder;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RecordAudioActivity extends Activity {

	// Timer
	private TextView min;
	private TextView sec;
	private Button start;
	private Button stop;
	
	private int cMin;
	private int cSec;
	
	private Timer myTimer;
	final static int delay = 1000;
	private ActionListener timerHandler;
	
	
	private static final String LOG_TAG = "AudioRecordTest";
    private String folder;
    private File audioFile;

    private MediaRecorder mRecorder = null;

    final Handler handler = new Handler();
    final Runnable runnable = new Runnable() {
    	public void run() {
    		if (cSec == 60) {
    			cMin++;
    			cSec = 0;
    			
    			if (cMin < 10) min.setText("0"+cMin);
    			else min.setText(""+cMin);
    		}
    		cSec++;
    		
    		handler.postDelayed(this, 1000);
    		Log.w("r", "" + cSec);
    		if (cSec < 10) sec.setText("0"+cSec);
    		else sec.setText(""+cSec);
    	}
    };
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_audio);
        
        setUpTimer();
        setUpStart();
        setUpStop();

        
    }
    
    private void setUpStart() {
    	start = (Button) findViewById(R.id.start);
    	start.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				runnable.run();
				
			    try {
			        setUpRecorder();
			        setUpPath();
			        recordAudio();
		        } catch (IOException e) {
		        	Log.e(LOG_TAG, "start recording error");
		        }
			}
    	});
    }
    
    private void setUpStop() {
    	stop = (Button) findViewById(R.id.stop);
    	stop.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				handler.removeCallbacks(runnable);
				stopRecording();
			}
    	});
    }
    
    private void setUpTimer() {
    	min = (TextView) findViewById(R.id.min);
    	sec = (TextView) findViewById(R.id.sec);
    	
    	cMin = 0;
    	cSec = 0;
    }
    
    private void setUpRecorder() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
    }
    
    private void setUpPath() throws IOException {
        folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/crowdsource/audio";
        
        File folderF = new File(folder);
        if (!folderF.exists()) folderF.mkdir();
        
		audioFile = File.createTempFile("ibm", ".3gp", folderF);

    }
    
    private void recordAudio() throws IOException {
        mRecorder.setOutputFile(audioFile.getAbsolutePath());
        
        mRecorder.prepare();
        mRecorder.start();
    }
    
    private void stopRecording() {
    	mRecorder.stop();
    	mRecorder.release();
    	
//    	String type = "Audio";
    	String data = Uri.fromFile(audioFile).toString();
    	
    	Intent result = new Intent();
    	result.putExtra("Audio", data);
    	result.putExtra("result", "pass");
    	setResult(RESULT_OK,result);
    	finish();
    	
    	
//    	Intent email = new Intent(RecordAudioActivity.this, EmailActivity.class);
//    	email.putExtra("type", type);
//    	email.putExtra("data", data);
//    	finish();
//    	startActivity(email);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_record_audio, menu);
        return true;
    }
}
