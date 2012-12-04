package com.CMPUT301F12T07.crowdsource.viewupdatetask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.CMPUT301F12T07.crowdsource.R;
import com.CMPUT301F12T07.crowdsource.taskmodeldb.DBHandler;
import com.CMPUT301F12T07.crowdsource.taskmodeldb.Task;

public class ViewLoggedActivity extends Activity {

	private Task currentTask;
	private TextView taskTitle;
	private TextView startDate;
	private TextView endDate;
	private TextView taskVisibility;
	private TextView taskDesc;
	private TextView taskQuantity;
	private TextView taskFollowers;
	private DBHandler db;

	private Button deleteTask;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_logged);
        
        db = new DBHandler(this);
        Long taskID = getIntent().getExtras().getLong("taskID");
		this.currentTask = db.getTask(taskID.toString(), DBHandler.LOCAL_FLAG);
        
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
        // Getting the task followers field
        this.taskFollowers = (TextView) findViewById(R.id.textViewFollowers);
        
        this.deleteTask = (Button) findViewById(R.id.buttonDelete);
        deleteTask.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

        		// set title
        		builder.setTitle("Delete");

        		// set dialog message
        		builder.setMessage("Are you sure you want to delete this task?");
        		builder.setCancelable(false);
        		builder.setPositiveButton(R.string.Delete, new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog,int id) {
        				// if this button is clicked, delete the task and leave the activity
        				try {
							db.deleteTask(currentTask.get_tid());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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

        });;
    }
    
    @Override
    public void onResume() {
    	super.onResume();

    	Long taskID = getIntent().getExtras().getLong("taskID");
		this.currentTask = db.getTask(taskID.toString(), DBHandler.LOCAL_FLAG);
    	
    	taskTitle.setText(currentTask.get_title());
    	startDate.setText(currentTask.get_dateCreate());
    	endDate.setText(currentTask.get_dateDue());
    	taskQuantity.setText(Integer.toString(currentTask.get_qty_filled())+" of "+Integer.toString(currentTask.get_quantity())+" fulfilled");
    	taskFollowers.setText(Integer.toString(currentTask.get_num_followed()));
    	if(currentTask.get_visibility() == 1)
    		taskVisibility.setText("Private");
    	else
    		taskVisibility.setText("Public");
    	taskDesc.setText(currentTask.get_description());
    	
    	if (!currentTask.get_uid().equals(Secure.getString(this.getContentResolver(), Secure.ANDROID_ID))){
    		deleteTask.setEnabled(false);
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_logged, menu);
        return true;
    }
}
