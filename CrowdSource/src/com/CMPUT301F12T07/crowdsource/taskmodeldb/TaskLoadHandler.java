package com.CMPUT301F12T07.crowdsource.taskmodeldb;

import com.CMPUT301F12T07.crowdsource.R;
import com.CMPUT301F12T07.crowdsource.viewupdatetask.ViewOtherTaskActivity;
import com.CMPUT301F12T07.crowdsource.viewupdatetask.ViewTaskActivity;

import android.os.Bundle;
import android.provider.Settings.Secure;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class TaskLoadHandler extends Activity {

	/**
	 * Member variables
	 */
	private TextView taskTitle;
	private DBHandler db;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_load_handler);
        db = new DBHandler(this);
        taskTitle = (TextView) findViewById(R.id.taskTitleLoading);  
        Long taskID = getIntent().getExtras().getLong("taskLocalObject", -1);
        if (taskID != -1){
        	Task localTask = db.getTask(taskID.toString(), DBHandler.LOCAL_FLAG);
        	taskTitle.setText(localTask.get_title());
        	if (localTask.get_uid().equals(Secure.getString(this.getContentResolver(), Secure.ANDROID_ID))) {
        		Intent intent = new Intent(this, ViewTaskActivity.class);
        		intent.putExtra("taskID", localTask.get_tid());
        		startActivity(intent);
        		finish();
        	} else {
        		Intent intent = new Intent(this, ViewOtherTaskActivity.class);
        		intent.putExtra("taskID", localTask.get_tid());
        		startActivity(intent);
        		finish();
        	}
        } else {
        	String thisTask = getIntent().getExtras().getString("taskWebObject");
        	Task remoteTask = db.getTask(thisTask, DBHandler.REMOTE_FLAG);
        	taskTitle.setText(remoteTask.get_title());
        	if (remoteTask.get_uid().equals(Secure.getString(this.getContentResolver(), Secure.ANDROID_ID))) {
        		Intent intent = new Intent(this, ViewTaskActivity.class);
        		intent.putExtra("taskID", remoteTask.get_tid());
        		startActivity(intent);
        		finish();
        	} else {
        		long taskIdentifier = db.cacheTask(remoteTask);
        		Intent intent = new Intent(this, ViewOtherTaskActivity.class);
        		intent.putExtra("taskID", taskIdentifier);
        		startActivity(intent);
        		finish();
        	}
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
