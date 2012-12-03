package com.CMPUT301F12T07.crowdsource.test;

import android.test.ActivityInstrumentationTestCase2;
import com.CMPUT301F12T07.crowdsource.tabviews.*;
import com.CMPUT301F12T07.crowdsource.taskmodeldb.*;

public class LocalDBTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private MainActivity mActivity;
	private LocalDB db;
	
	// Fields for Test Task
	private static final int TASKID = 1;
	private static final String TASK_NAME = "TEST";
	private static final String TASK_UID = "1111";
	private static final String TASK_DESC = "Testing Description";
	private static final String TASK_DATE = "2012-12-12";
	private static final String TASK_DUE = "2012-12-13";
	private static final String TASK_TYPE = "Photo";
	private static final int TASK_VISI = 1;
	private static final int TASK_QUANTITY = 11;
	
	public LocalDBTest() {
		super(MainActivity.class);
	}
	
	@Override
	  protected void setUp() throws Exception {
	    super.setUp();
	    setActivityInitialTouchMode(false);
	    
	    mActivity = getActivity();
	    db = new LocalDB(mActivity);
	    
	  }
	
	public void test_create() {
		// Make sure database is empty by emptying database
		db.emptyDatabase();
		Task task = new Task(TASK_UID, TASK_NAME, TASK_DESC, TASK_DATE, TASK_DUE, TASK_TYPE, TASK_VISI, TASK_QUANTITY);
		long id = db.createTask(task);
		assertEquals(id, TASKID);
	}
	
	public void test_getFromDB() {
		// Tests if the Data that was persisted to the database
		// is correct.
		Task task = db.getTask(TASKID);
		assertEquals(task.get_tid(), TASKID);
		assertEquals(task.get_uid(), TASK_UID);
		assertEquals(task.get_title(), TASK_NAME);
		assertEquals(task.get_description(), TASK_DESC);
		assertEquals(task.get_dateCreate(), TASK_DATE);
		assertEquals(task.get_dateDue(), TASK_DUE);
		assertEquals(task.get_type(), TASK_TYPE);
		assertEquals(task.get_visibility(), TASK_VISI);
		assertEquals(task.get_quantity(), TASK_QUANTITY);
	}
	
	public void test_update(){
		// Push a task into the DB, then push an update to that task to the database.
		db.emptyDatabase();
		Task task = new Task(TASK_UID, TASK_NAME, TASK_DESC, TASK_DATE, TASK_DUE, TASK_TYPE, TASK_VISI, TASK_QUANTITY);
		long id = db.createTask(task);
		
		task.set_title("New Title");
		db.updateTask(task, db.PUBLIC_FLAG);
		Task ret_task = db.getTask(id);
		assertEquals(ret_task.get_title(), task.get_title());
			
	}
	
	public void test_delete(){
		db.emptyDatabase();
		Task task = new Task(TASK_UID, TASK_NAME, TASK_DESC, TASK_DATE, TASK_DUE, TASK_TYPE, TASK_VISI, TASK_QUANTITY);
		long id = db.createTask(task);
		
		db.deleteTask(id);
		assertEquals(db.checkExists(id), 0);
		
	}
	
}
