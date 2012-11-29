package com.CMPUT301F12T07.crowdsource;

import java.io.File;
import java.io.IOException;
import java.util.Timer;

import com.CMPUT301F12T07.crowdsource.R;
import android.media.MediaRecorder;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RecordAudioActivity extends Activity {

	// Timer
	private TextView hour;
	private TextView sec;
	private Button start;
	private Button stop;
	
	private Handler handler;
	private int cHour;
	private int cMin;
	
	private Timer myTimer;
	final static int delay = 1000;
	private ActionListener timerHandler;
	
	
	private static final String LOG_TAG = "AudioRecordTest";
    private String folder;
    private File audioFile;

    private MediaRecorder mRecorder = null;

    Runnable runnable = new Runnable() {
    	public void run() {
    		if (cMin == 60) cHour++;
    		cMin++;
    		
    		hour.setText(cHour);
    		sec.setText(cMin);
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
				handler.postDelayed(runnable, 1000);
				
				
//			    try {
//			        setUpRecorder();
//			        setUpPath();
//			        recordAudio();
//		        } catch (IOException e) {
//		        	Log.e(LOG_TAG, "start recording error");
//		        }
			}
    	});
    }
    
    private void setUpStop() {
    	stop = (Button) findViewById(R.id.stop);
    	stop.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				handler.removeCallbacks(runnable);
				
			}
    	});
    }
    
    private void setUpTimer() {
    	hour = (TextView) findViewById(R.id.hour);
    	sec = (TextView) findViewById(R.id.sec);
    	
    	cHour = 0;
    	cMin = 0;
    	
    	handler = new Handler();
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
        
		audioFile = File.createTempFile("ibm", ".3gp", audioFile);

    }
    
    private void recordAudio() throws IOException {
        mRecorder.setOutputFile(audioFile.getAbsolutePath());
        
        mRecorder.prepare();
        mRecorder.start();
    }
    
    private void stopRecording() {
    	mRecorder.stop();
    	mRecorder.release();
    	//processaudiofile(audioFile.getAbsoluteFile());
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_record_audio, menu);
        return true;
    }
}
