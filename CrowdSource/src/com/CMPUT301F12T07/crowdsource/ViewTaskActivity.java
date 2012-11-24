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
import android.widget.TextView;
import android.widget.Toast;

import com.CMPUT301F12T07.crowdsource.taskmodeldb.LocalDB;
import com.CMPUT301F12T07.crowdsource.taskmodeldb.Task;

public class ViewTaskActivity extends Activity {


	private Task currentTask;
	private TextView taskTitle;
	private TextView startDate;
	private TextView endDate;
	private TextView taskVisibility;
	private TextView taskDesc;
	private TextView taskQuantity;
	private LocalDB db;

	private Button updateTask;
	private Button deleteTask;
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
        
        this.updateTask = (Button) findViewById(R.id.buttonUpdate);
        updateTask.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(ViewTaskActivity.this, UpdateTaskActivity.class);
        		intent.putExtra("taskID", currentTask.get_tid());
        		startActivity(intent);
            }
        });
        
        this.deleteTask = (Button) findViewById(R.id.buttonDelete);
        deleteTask.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		AlertDialog.Builder builder = new AlertDialog.Builder(context);

        		// set title
        		builder.setTitle("Delete");

        		// set dialog message
        		builder.setMessage("Are you sure you want to delete this task?");
        		builder.setCancelable(false);
        		builder.setPositiveButton(R.string.Delete, new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog,int id) {
        				// if this button is clicked, delete the task and leave the activity
        				db.deleteTask(currentTask.get_tid());
        				finish();
        			}
        		});
        		builder.setNegativeButton(R.string.Cancel,new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog,int id) {
        				// if this button is clicked, just close
        				// the dialog box and do nothing
        				dialog.cancel();
        			}
        		});

        		// create alert dialog
        		AlertDialog alertDialog = builder.create();

        		// show it
        		alertDialog.show();
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
