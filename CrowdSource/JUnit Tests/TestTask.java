package com.CMPUT301F12T07.crowdsource.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;

import com.CMPUT301F12T07.crowdsource.taskmodeldb.Task;

import android.test.AndroidTestCase;

/**
 * This tests the Task creation, modification, and retrieval of information.
 * Note: Must be run in separate Android JUnit Test Project
 * 
 * @author thomasfung
 *
 */


public class TestTask extends AndroidTestCase {

	TaskExtend mTaskExtend;

	// False TID & WID
	private long TID = 1000;
	private String WID = "2000";
	
	// False Task values
	private String UID = "1200";
	private String TITLE = "title";
	private String DESCRIPTION = "description";
	private String DATE_CREATE = "2012-01-01";
	private String DATE_DUE = "2014-01-01";
	private String TYPE = "Photo";
	private int VISIBILITY = 0;
	private int QUANTITY = 11;
	private int QTY_FILLED = 0;
	private int FOLLOWED = 1000;
	private int NUM_FOLLOWED = 1;
	private String EMAIL = "test@email.ca"; 
	
	@Before
	public void setUp() throws Exception {
		mTaskExtend = new TaskExtend();
	}

	@After
	public void tearDown() throws Exception {
	}

	
	/**
	 * Tests the Task constructors
	 */
	public void testTaskTID() {
		Task mTask = mTaskExtend.createTaskTID(TID, UID, TITLE, DESCRIPTION, DATE_CREATE, DATE_DUE,
				TYPE, VISIBILITY, QUANTITY, QTY_FILLED, FOLLOWED, NUM_FOLLOWED, EMAIL);
	
		Assert.assertEquals(mTask.get_description(), DESCRIPTION);
	}

	public void testTaskNoTID() {
		Task mTask = mTaskExtend.createTaskNoTID(UID, TITLE, DESCRIPTION, DATE_CREATE, DATE_DUE,
				TYPE, VISIBILITY, QUANTITY, QTY_FILLED, FOLLOWED, NUM_FOLLOWED, EMAIL);
	
		Assert.assertEquals(mTask.get_description(), DESCRIPTION);
	}
	
	public void testTaskWID() {
		Task mTask = mTaskExtend.createTaskWID(WID, TITLE, 20140101, QUANTITY, QTY_FILLED, TYPE, UID);
	
		Assert.assertEquals(mTask.get_title(), TITLE);
	}
	
	public void testTaskTIDWID() {
		Task mTask = mTaskExtend.createTaskTIDWID(TID, UID, TITLE, DESCRIPTION, DATE_CREATE, 
				DATE_DUE, TYPE, VISIBILITY, QUANTITY, QTY_FILLED, FOLLOWED, NUM_FOLLOWED, EMAIL, WID);
	
		Assert.assertEquals(mTask.get_description(), DESCRIPTION);
	}
	
	/**
	 * Tests the Task getter functions
	 */
	public void testTaskGet() {
		Task mTask = mTaskExtend.createTaskTIDWID(TID, UID, TITLE, DESCRIPTION, DATE_CREATE, 
				DATE_DUE, TYPE, VISIBILITY, QUANTITY, QTY_FILLED, FOLLOWED, NUM_FOLLOWED, EMAIL, WID);
	
		Assert.assertEquals(mTask.get_tid(), (Long) TID);
		Assert.assertEquals(mTask.get_uid(), UID);
		Assert.assertEquals(mTask.get_title(), TITLE);
		Assert.assertEquals(mTask.get_description(), DESCRIPTION);
		Assert.assertEquals(mTask.get_dateCreate(), DATE_CREATE);
		Assert.assertEquals(mTask.get_dateDue(), DATE_DUE);
		Assert.assertEquals(mTask.get_type(), TYPE);
		Assert.assertEquals(mTask.get_visibility(), VISIBILITY);
		Assert.assertEquals(mTask.get_quantity(), QUANTITY);
		Assert.assertEquals(mTask.get_qty_filled(), QTY_FILLED);
		Assert.assertEquals(mTask.get_followed(), FOLLOWED);
		Assert.assertEquals(mTask.get_num_followed(), NUM_FOLLOWED);
		Assert.assertEquals(mTask.get_user_email(), EMAIL);
		Assert.assertEquals(mTask.get_wid(), WID);
	}
	
	/**
	 * Test the Task setter functions
	 */
	public void testTaskSet() {
		// New Values
		long newTID = 900;
		String newWID = "4500";
		
		String newUID = "1500";
		String newTITLE = "more_title";
		String newDESCRIPTION = "more_description";
		String newDATE_CREATE = "2012-06-12";
		String newDATE_DUE = "2014-02-11";
		String newTYPE = "Text";
		int newVISIBILITY = 1;
		int newQUANTITY = 22;
		int newQTY_FILLED = 3;
		int newFOLLOWED = 1250;
		int newNUM_FOLLOWED = 3;
		String newEMAIL = "anotheremail@email.ca"; 
		
		Task mTask = mTaskExtend.createTaskTIDWID(TID, UID, TITLE, DESCRIPTION, DATE_CREATE, 
				DATE_DUE, TYPE, VISIBILITY, QUANTITY, QTY_FILLED, FOLLOWED, NUM_FOLLOWED, EMAIL, WID);
	
		mTask.set_tid(newTID);
		mTask.set_uid(newUID);
		mTask.set_title(newTITLE);
		mTask.set_description(newDESCRIPTION);
		mTask.set_dateCreate(newDATE_CREATE, VISIBILITY);
		mTask.set_dateDue(newDATE_DUE, VISIBILITY);
		mTask.set_type(newTYPE);
		mTask.set_visibility(newVISIBILITY);
		mTask.set_quantity(newQUANTITY);
		mTask.set_qty_filled(newQTY_FILLED);
		mTask.set_followed(newFOLLOWED);
		mTask.set_num_followed(newNUM_FOLLOWED);
		mTask.set_user_email(newEMAIL);
		mTask.set_wid(newWID);
		
		Assert.assertEquals(mTask.get_tid(), (Long) newTID);
		Assert.assertEquals(mTask.get_uid(), newUID);
		Assert.assertEquals(mTask.get_title(), newTITLE);
		Assert.assertEquals(mTask.get_description(), newDESCRIPTION);
		Assert.assertEquals(mTask.get_dateCreate(), newDATE_CREATE);
		Assert.assertEquals(mTask.get_dateDue(), newDATE_DUE);
		Assert.assertEquals(mTask.get_type(), newTYPE);
		Assert.assertEquals(mTask.get_visibility(), newVISIBILITY);
		Assert.assertEquals(mTask.get_quantity(), newQUANTITY);
		Assert.assertEquals(mTask.get_qty_filled(), newQTY_FILLED);
		Assert.assertEquals(mTask.get_followed(), newFOLLOWED);
		Assert.assertEquals(mTask.get_num_followed(), newNUM_FOLLOWED);
		Assert.assertEquals(mTask.get_user_email(), newEMAIL);
		Assert.assertEquals(mTask.get_wid(), newWID);
	}
	
	/**
	 * private classes that are related to the testing subject
	 * @author thomasfung
	 *
	 */
	
	private class TaskExtend extends Task {
		public TaskExtend () {}
		
		private Task createTaskTID(long tid, String uid, String title, String description, String dateCreate, 
									String dateDue, String type, int visibility, int quantity, 
									int qty_filled, int followed, int num_followed, String user_email) {

			return new Task(tid, uid, title, description, dateCreate, dateDue, type, visibility, quantity, 
					qty_filled, followed, num_followed, user_email);
		}
		
		private Task createTaskNoTID(String uid, String title, String description, String dateCreate, 
									String dateDue, String type, int visibility, int quantity, 
									int qty_filled, int followed, int num_followed, String user_email) {
			
			return new Task(uid, title, description, dateCreate, dateDue, type, visibility, quantity, 
					qty_filled, followed, num_followed, user_email);
		}
		
		private Task createTaskWID(String webid, String title, long dateDue, int quantity, int qty_filled, 
									String type, String uid) {

			return new Task(webid, title, dateDue, quantity, qty_filled, type, uid);
		}
		
		private Task createTaskTIDWID(long tid, String uid, String title, String description, String dateCreate, 
	    							String dateDue, String type, int visibility, int quantity, int qty_filled, 
	    							int followed, int num_followed, String user_email, String webid) {

			return new Task(tid, uid, title, description, dateCreate, dateDue, type, visibility, quantity, 
					qty_filled, followed, num_followed, user_email, webid);
		}
	}

	
}
