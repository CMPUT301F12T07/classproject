package com.CMPUT301F12T07.crowdsource;

import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView myList;
	private List<Task> tasks;
	private LocalDB db;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        db = new LocalDB(this);
        
        this.tasks = db.getAllTasks();
        
        myList = (ListView)findViewById(R.id.tasklist);
        myList.setAdapter(new TaskListAdapter(MainActivity.this, tasks));
        
        // Adds listener for when a Task is clicked in the ListView
        myList.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        		Intent intent = new Intent(MainActivity.this, ViewTaskActivity.class);
        		intent.putExtra("taskObject", tasks.get(position).get_tid());
        		startActivity(intent);
        	}
        });

        // ugly debugger
        final Button EmptyDB = (Button) findViewById(R.id.DBDEBUG);
        EmptyDB.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		db.emptyDatabase();
        		finish();
        		startActivity(getIntent());
        	}
        });
        
        // ugly debugger
        final Button RandomTaskGen = (Button) findViewById(R.id.RandTask);
        RandomTaskGen.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		db.createRandomTask();
        		finish();
        		startActivity(getIntent());
        	}
        });
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	this.tasks = db.getAllTasks();
    	myList.setAdapter(new TaskListAdapter(MainActivity.this, tasks));
    	
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
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
