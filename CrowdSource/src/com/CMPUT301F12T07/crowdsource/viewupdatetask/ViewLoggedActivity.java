package com.CMPUT301F12T07.crowdsource.viewupdatetask;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.CMPUT301F12T07.crowdsource.R;
import com.CMPUT301F12T07.crowdsource.R.id;
import com.CMPUT301F12T07.crowdsource.R.layout;
import com.CMPUT301F12T07.crowdsource.R.menu;
import com.CMPUT301F12T07.crowdsource.taskmodeldb.LocalDB;
import com.CMPUT301F12T07.crowdsource.taskmodeldb.Task;

public class ViewLoggedActivity extends Activity {

	private Task currentTask;
	private TextView taskTitle;
	private TextView startDate;
	private TextView endDate;
	private TextView taskVisibility;
	private TextView taskDesc;
	private TextView taskQuantity;
	private LocalDB db;

	private Button deleteTask;
	private Button viewFulfill;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_logged);
        
        db = new LocalDB(this);
        this.currentTask = db.getTask(getIntent().getExtras().getLong("taskObject"));
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
        
        this.deleteTask = (Button) findViewById(R.id.buttonDelete);
        deleteTask.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(v.getContext(), 
						"This feature has not been implemented yet.", 
						Toast.LENGTH_SHORT).show();
			}
        });

        this.viewFulfill = (Button) findViewById(R.id.buttonFulfill);
        viewFulfill.setOnClickListener(new OnClickListener() {
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
        getMenuInflater().inflate(R.menu.activity_view_logged, menu);
        return true;
    }
}
