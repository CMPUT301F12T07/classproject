package com.CMPUT301F12T07.crowdsource.taskmodeldb;

import com.CMPUT301F12T07.crowdsource.R;

import android.os.Bundle;
import android.provider.Settings.Secure;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class TaskLoadHandler extends Activity {

	private Task currentTask;
	private TextView taskTitle;
	private DBHandler db;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_load_handler);

        db = new DBHandler(this);
        long taskID = getIntent().getExtras().getLong("taskObject", -1);
        if (taskID != -1)
        {
        	// success at retrieving web ID
        	// check if task is in local DB
        	// TODO: make a get by web ID function
        } else {
        	taskID = getIntent().getExtras().getLong("localObject", -1);
        	this.currentTask = db.getTask(taskID);
        }
        // TODO: make get owner
        if(currentTask.get_uid() == Secure.getString(this.getContentResolver(), Secure.ANDROID_ID)) {
        	// launch activity in ViewTaskActivity
        } else {
        	// launch activity in ViewOtherTaskActivity
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_task_load_handler, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
