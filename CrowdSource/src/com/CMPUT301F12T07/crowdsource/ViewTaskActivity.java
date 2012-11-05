package com.CMPUT301F12T07.crowdsource;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewTaskActivity extends Activity {


	private Task currentTask;
	private TextView taskTitle;
	private TextView startDate;
	private TextView endDate;
	private TextView taskVisibility;
	private TextView taskDesc;
	private LocalDB db;

	private Button deleteTask;
	private Button fulfillTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        
        db = new LocalDB(this);
        this.currentTask = db.getTask(getIntent().getExtras().getInt("taskObject"));
        db.close();
        
        // Getting the task title field
        this.taskTitle = (TextView) findViewById(R.id.textViewTitle);
        // Getting the start Date field
        this.startDate = (TextView) findViewById(R.id.textViewCreatedDate);
        // Getting the end Date field
        this.endDate = (TextView) findViewById(R.id.textViewDueDate);
        // Getting the task visibility level
        this.taskVisibility = (TextView) findViewById(R.id.textViewVisibility);
        // Getting the task description field
        this.taskDesc = (TextView) findViewById(R.id.textViewDescription);
        
        final Button Update = (Button) findViewById(R.id.buttonUpdate);
        Update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(ViewTaskActivity.this, UpdateTaskActivity.class);
        		intent.putExtra("taskID", currentTask.get_tid());
        		startActivity(intent);
            }
        });
        
        this.deleteTask = (Button) findViewById(R.id.buttonDelete);
        deleteTask.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				db.deleteTask(currentTask.get_tid());
				finish();
			}
        });

        this.fulfillTask = (Button) findViewById(R.id.buttonFulfill);
        fulfillTask.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// do toast
				Toast.makeText(v.getContext(), 
						"This feature has not been implemented yet.", 
						Toast.LENGTH_SHORT).show();
				
			}
        });
    }

    @Override
    public void onResume() {
    	super.onResume();

    	this.currentTask = db.getTask(getIntent().getExtras().getInt("taskObject"));
    	
    	taskTitle.setText(currentTask.get_title());
    	startDate.setText(currentTask.get_dateCreate());
    	endDate.setText(currentTask.get_dateDue());
    	if(currentTask.get_visibility() == 1)
    		taskVisibility.setText("Private");
    	else
    		taskVisibility.setText("Public");
    	taskDesc.setText(currentTask.get_description());
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.activity_view_task, menu);
    	return true;
    }
}
