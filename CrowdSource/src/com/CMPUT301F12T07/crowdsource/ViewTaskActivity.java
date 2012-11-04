package com.CMPUT301F12T07.crowdsource;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewTaskActivity extends Activity {


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
        
        LocalDB db = new LocalDB(this);
        this.currentTask = db.getTask(getIntent().getExtras().getInt("taskObject"));
        
        // Getting the task title field
        this.taskTitle = (TextView) findViewById(R.id.textViewTitle);
        //String titleStr = currentTask.get_title();
        System.out.println(currentTask.get_title());
        taskTitle.setText(currentTask.get_title());
        
        // Getting the start Date field
        this.startDate = (TextView) findViewById(R.id.textViewCreatedDate);
        startDate.setText(currentTask.get_dateCreate());
        
        // Getting the end Date field
        this.endDate = (TextView) findViewById(R.id.textViewDueDate);
        endDate.setText(currentTask.get_dateDue());
        
        // Getting the task content field
        // type? content?
        this.taskContent = (TextView) findViewById(R.id.textViewContent);
        //String contentStr = currentTask.get_type();
        taskContent.setText(currentTask.get_type());
        
        // Getting the task description field
        this.taskDesc = (TextView) findViewById(R.id.textViewDescription);
        //String descStr = currentTask.get_description();
        taskDesc.setText(currentTask.get_description());
        
        /*
        timeEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
        	public void onFocusChange(View v, boolean hasFocus){
        		if (!hasFocus){
        			currentTask.setTime(Integer.parseInt(timeEdit.getText().toString()));
        			db.updateTask(currentTask);
        		}
        	}
        }); */
        
        final Button Update = (Button) findViewById(R.id.buttonUpdate);
        Update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(ViewTaskActivity.this, UpdateTaskActivity.class);
        		intent.putExtra("taskObject", currentTask);
        		startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_task, menu);
        return true;
    }
}
