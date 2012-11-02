package com.CMPUT301F12T07.crowdsource;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;

public class ViewTask extends Activity {
	
	
	private Task currentTask;
	//private DatabaseHandler db;

	private TextView taskTitle;
	private TextView startDate;
	private TextView endDate;
	private TextView taskDesc;
	private TextView taskContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        
        Task curentTask = getIntent().getExtras().getParcelable(@string/TaskObject);
        
        // Getting the start Date field
        this.startDate = (TextView) findViewById(R.id.textViewCreatedDate);

        // unknown implimentation of task date getter
        int startDateValue = this.currentTask.getStart();
        startDate.setText(Integer.toString(startDateValue));
        
        /*
        timeEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
        	public void onFocusChange(View v, boolean hasFocus){
        		if (!hasFocus){
        			currentTask.setTime(Integer.parseInt(timeEdit.getText().toString()));
        			db.updateTask(currentTask);
        		}
        	}
        }); */

        /*
        // Getting and Setting of Start Battery field
        this.startEdit = (TextView) findViewById(R.id.taskStartBattery);
        double startValue = this.currentTask.getStart();
        startEdit.setText(Double.toString(startValue));
        startEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
        	public void onFocusChange(View v, boolean hasFocus){
        		if (!hasFocus){
        			currentTask.setStart(Double.parseDouble(startEdit.getText().toString()));
        			db.updateTask(currentTask);
        		}
        	}
        });

        // Getting and Setting of End Battery field
        this.endEdit = (TextView) findViewById(R.id.taskEndBattery);
        double endValue = this.currentTask.getEnd();
        endEdit.setText(Double.toString(endValue));
        endEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
        	public void onFocusChange(View v, boolean hasFocus){
        		if (!hasFocus){
        			currentTask.setEnd(Double.parseDouble(endEdit.getText().toString()));
        			db.updateTask(currentTask);
        		}
        	}
        });

        // Getting and Setting of Description field
        this.descEdit = (TextView) findViewById(R.id.taskDesc);
        String descValue = this.currentTask.getDesc();
        descEdit.setText(descValue);
        descEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
        	public void onFocusChange(View v, boolean hasFocus){
        		if (!hasFocus){
        			currentTask.setDesc(descEdit.getText().toString());
        			db.updateTask(currentTask);
        		}
        	}
        });
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_task, menu);
        return true;
    }
}
