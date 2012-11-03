package com.CMPUT301F12T07.crowdsource;

import android.R;
import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class ViewTask extends Activity {
	
	
	private Task currentTask;
	//private DatabaseHandler db;

	private TextView taskTitle;
	private TextView startDate;
	private TextView endDate;
	private TextView taskContent;
	private TextView taskDesc;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        
        Task curentTask = getIntent().getExtras().getParcelable(@string/TaskObject);
        
        // Getting the task title field
        this.taskTitle = (TextView) findViewById(R.id.textViewTitle);
        // unknown implimentation of task getters
        string titleStr = this.currentTask.getTitle();
        taskDesc.setText(titleStr));
        
        // Getting the start Date field
        this.startDate = (TextView) findViewById(R.id.textViewCreatedDate);
        // unknown implimentation of task getters
        int startDateValue = this.currentTask.getStart();
        startDate.setText(Integer.toString(startDateValue));
        
        // Getting the end Date field
        this.endDate = (TextView) findViewById(R.id.textViewDueDate);
        // unknown implimentation of task getters
        int endDateValue = this.currentTask.getEnd();
        endDate.setText(Integer.toString(endDateValue));
        
        // Getting the task content field
        this.taskContent = (TextView) findViewById(R.id.textViewContent);
        // unknown implimentation of task getters
        string contentStr = this.currentTask.getContent();
        taskDesc.setText(contentStr));
        
        // Getting the task description field
        this.taskDesc = (TextView) findViewById(R.id.textViewDescription);
        // unknown implimentation of task getters
        string descStr = this.currentTask.getDesc();
        taskDesc.setText(descStr));
        
        /*
        timeEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
        	public void onFocusChange(View v, boolean hasFocus){
        		if (!hasFocus){
        			currentTask.setTime(Integer.parseInt(timeEdit.getText().toString()));
        			db.updateTask(currentTask);
        		}
        	}
        }); */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_task, menu);
        return true;
    }
}