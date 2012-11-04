package com.CMPUT301F12T07.crowdsource;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

public class UpdateTaskActivity extends Activity {

	private Task currentTask;
	private EditText taskTitle;
	private EditText startDate;
	private EditText endDate;
	private EditText taskContent;
	private EditText taskDesc;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);
//        getActionBar().setDisplayHomeAsUpEnabled(true);

        LocalDB db = new LocalDB(this);
        this.currentTask = db.getTask(getIntent().getExtras().getInt("taskObject"));

        // Getting the task title field
        this.taskTitle = (EditText) findViewById(R.id.textEditTitle);
        //String titleStr = currentTask.get_title();
        taskTitle.setText(currentTask.get_title());

        // Getting the start Date field
        this.startDate = (EditText) findViewById(R.id.textEditCreatedDate);
        startDate.setText(currentTask.get_dateCreate());

        // Getting the end Date field
        this.endDate = (EditText) findViewById(R.id.textEditDueDate);
        endDate.setText(currentTask.get_dateDue());

        // Getting the task content field
        // type? content?
        this.taskContent = (EditText) findViewById(R.id.textEditContent);
        //String contentStr = currentTask.get_type();
        taskContent.setText(currentTask.get_type());

        // Getting the task description field
        this.taskDesc = (EditText) findViewById(R.id.textEditDescription);
        //String descStr = currentTask.get_description();
        taskDesc.setText(currentTask.get_description());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_update_task, menu);
        return true;
    }

    
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
