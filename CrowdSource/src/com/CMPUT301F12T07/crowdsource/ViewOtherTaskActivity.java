package com.CMPUT301F12T07.crowdsource;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.CMPUT301F12T07.crowdsource.taskmodeldb.LocalDB;
import com.CMPUT301F12T07.crowdsource.taskmodeldb.Task;

public class ViewOtherTaskActivity extends Activity {


	private Task currentTask;
	private TextView taskTitle;
	private TextView startDate;
	private TextView endDate;
	private TextView taskVisibility;
	private TextView taskDesc;
	private TextView taskQuantity;
	private LocalDB db;

	private Button followTask;
	private Button fulfillTask;
	
	final Context context = this;

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
        // Getting the task quantity field
        this.taskQuantity = (TextView) findViewById(R.id.textViewQuantity);
        
        this.followTask = (Button) findViewById(R.id.buttonFollow);
        followTask.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	// TODO: pending implementation in task
            	// currentTask.set_followed();
            	// TODO: Put check if currently following and then use this below to set icon
            	followTask.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_button_followed, 0, 0, 0);
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
        
        ImageView imageName = (ImageView) findViewById(R.id.imageView1);
        
        if (currentTask.get_type().equals("Audio")){
        	imageName.setImageResource(R.drawable.ic_tasktype_audio);
        } else if (currentTask.get_type().equals("Photo")){
        	imageName.setImageResource(R.drawable.ic_tasktype_photo);
        } else {
        	imageName.setImageResource(R.drawable.ic_tasktype_text);
        }
        
    }

    @Override
    public void onResume() {
    	super.onResume();

    	this.currentTask = db.getTask(getIntent().getExtras().getInt("taskObject"));
    	
    	taskTitle.setText(currentTask.get_title());
    	startDate.setText(currentTask.get_dateCreate());
    	endDate.setText(currentTask.get_dateDue());
    	taskQuantity.setText(Integer.toString(currentTask.get_quantity()));
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
