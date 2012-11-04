package com.CMPUT301F12T07.crowdsource;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

public class MyTasksActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tasks);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_my_tasks, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
        	case R.id.addTask:
        		Intent intent = new Intent(this, AddTaskActivity.class);
        		startActivity(intent);
        		return true;
        	default:
        		NavUtils.navigateUpFromSameTask(this);
        		return true;
    	}

    }

}
