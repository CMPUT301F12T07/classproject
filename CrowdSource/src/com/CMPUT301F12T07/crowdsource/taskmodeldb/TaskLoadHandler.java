package com.CMPUT301F12T07.crowdsource.taskmodeldb;

import com.CMPUT301F12T07.crowdsource.R;
import com.CMPUT301F12T07.crowdsource.R.layout;
import com.CMPUT301F12T07.crowdsource.R.menu;

import android.os.Bundle;
import android.provider.Settings.Secure;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class TaskLoadHandler extends Activity {

//	private Task currentTask;
//	private TextView taskTitle;
//	private TextView startDate;
//	private TextView endDate;
//	private TextView taskVisibility;
//	private TextView taskDesc;
//	private TextView taskQuantity;
//	private LocalDB db;
//
//	private Button updateTask;
//	private Button deleteTask;
//	private Button fulfillTask;
//	
//	
//    
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_task_load_handler);
//
//        db = new LocalDB(this);
//        int taskID = getIntent().getExtras().getInt("taskObject", -1);
//        if (taskID != -1)
//        {
//        	// success at retrieving web ID
//        	// check if task is in local DB
//        	// TODO: make a get by web ID function
//        	taskID = db.getWebTask(taskID);
//        }
//        else
//        	taskID = getIntent().getExtras().getInt("localObject", -1);
//
//        this.currentTask = db.getTask(taskID);
//        db.close();
//        
//        // TODO: make get owner
//        if( currentTask.getOwner() == Secure.getString(this.getContentResolver(), Secure.ANDROID_ID))
//        {
//        	// launch activity in ViewTaskActivity
//        }
//        else
//        	// launch activity in ViewOtherTaskActivity
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_task_load_handler, menu);
//        return true;
//    }
//
//    
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

}
