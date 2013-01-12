package com.CMPUT301F12T07.crowdsource.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;

import com.CMPUT301F12T07.crowdsource.taskmodeldb.LocalDB;
import com.CMPUT301F12T07.crowdsource.taskmodeldb.Task;

import android.content.Context;
import android.test.AndroidTestCase;


/**
 * This tests operation of the local SQLite database
 * Note: Must be run in separate Android JUnit Test Project
 * 
 * @author thomasfung
 *
 */

public class TestLocalDB extends AndroidTestCase {

	LocalDBExtend mLocalDBExtend;
	TaskExtend mTaskExtend;
	
	// False TID
	private long TID = 1000;
	
	// False Task values
	private String UID = "1200";
	private String TITLE = "title";
	private String DESCRIPTION = "description";
	private String DATE_CREATE = "2012-01-01";
	private String DATE_DUE = "2014-01-01";
	private String TYPE = "Photo";
	private int VISIBILITY = 1;
	private int QUANTITY = 11;
	private int QTY_FILLED = 0;
	private int FOLLOWED = 1000;
	private int NUM_FOLLOWED = 1;
	private String EMAIL = "test@email.ca"; 
	
	@Before
	public void setUp() throws Exception {
		mLocalDBExtend = new LocalDBExtend(getContext());
		mTaskExtend = new TaskExtend();
		
		mLocalDBExtend.emptyDatabase();
	}

	@After
	public void tearDown() throws Exception {
	}

	public void testDBNotNull() {
		Assert.assertNotNull(mLocalDBExtend);
	}
	
	/**
	 * Insert task into database test
	 */
	public void testCreateTask() {
		Task mTask = mTaskExtend.createNewTask(UID, TITLE, DESCRIPTION, DATE_CREATE, DATE_DUE,
							TYPE, VISIBILITY, QUANTITY, QTY_FILLED, FOLLOWED, NUM_FOLLOWED, EMAIL);
		Task retrievedTask = null;

		// recreates task in LocalDB, then inserts it into the DB
		// returns tid of the inserted item
		long tid = mLocalDBExtend.createTask(mTask);
		
		// retrieves newly inserted task from database
		retrievedTask = mLocalDBExtend.getTask(tid);
		
		// ensures the retrieved Task is the same
		Assert.assertEquals(retrievedTask.get_title(), TITLE);
		
		mLocalDBExtend.emptyDatabase();
	}
	
	
	/**
	 * Delete task from database test
	 */
	public void testDBDelete() {
		Task mTask = mTaskExtend.createNewTask(UID, TITLE, DESCRIPTION, DATE_CREATE, DATE_DUE,
							TYPE, VISIBILITY, QUANTITY, QTY_FILLED, FOLLOWED, NUM_FOLLOWED, EMAIL);
		boolean pass = false;
		
		// recreates task in LocalDB, then inserts it into the DB
		mLocalDBExtend.createTask(mTask);
		mLocalDBExtend.deleteTask(TID);
		
		try {
			mLocalDBExtend.getTask(TID);
		} catch (Exception e) {
			pass = true;
		}
		
		Assert.assertTrue(pass);
	}
	
	
	/**
	 * private classes that are related to the testing subject
	 * @author thomasfung
	 *
	 */
	
	private class LocalDBExtend extends LocalDB {
		public LocalDBExtend(Context context) {
			super(context);
		}
	}
	
	private class TaskExtend extends Task {
		public TaskExtend () {}
		
		private Task createNewTask(String uid, String title, String description, String dateCreate, 
									String dateDue, String type, int visibility, int quantity, 
									int qty_filled, int followed, int num_followed, String user_email) {
			
			return new Task(uid, title, description, dateCreate, dateDue, type, visibility, quantity, 
					qty_filled, followed, num_followed, user_email);
		}
	}

}
