package com.CMPUT301F12T07.crowdsource;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button addTaskActivity;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initializeListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    private void initializeListeners() {
    	addTaskActivity = (Button) findViewById(R.id.addtask);
    	
    	addTaskActivity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), AddTask.class);
				startActivity(intent);
			}
    		
    	});
    	
    	
    }
}
