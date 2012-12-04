/**
 *  Structure design of LocalDB referencing the following source:  
 *  http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
 *  Author: Ravi Tamada <mailto:ravi8x@gmail.com>
 */

package com.CMPUT301F12T07.crowdsource.taskmodeldb;

import java.util.ArrayList; 
import java.util.Date;
import java.util.List; 


import android.content.ContentValues; 
import android.content.Context; 
import android.database.Cursor; 
import android.database.sqlite.SQLiteDatabase; 
import android.database.sqlite.SQLiteOpenHelper; 
import android.util.Log;

public class LocalDB extends SQLiteOpenHelper { 

	/////////////////////////////////
	// STATIC VARIABLES
	/////////////////////////////////
	
	/* Database Version */ 
	private static final int DATABASE_VERSION = 1; 

	/* Database Name */
	private static final String DATABASE_NAME = "LocalDBManager"; 

	/* Tasks table name */
	private static final String TABLE_TASKS = "tasks"; 

	/* Tasks Table Columns names */
	private static final String KEY_TID = "_tid"; 
	private static final String KEY_UID = "uid"; 
	private static final String KEY_TITLE = "title"; 
	private static final String KEY_DESCRIPTION = "description";
	private static final String KEY_DATECREATE = "dateCreate";
	private static final String KEY_DATEDUE = "dateDue";
	private static final String KEY_TYPE = "type";
	private static final String KEY_VISIBILITY = "visibility";
	private static final String KEY_QUANTITY = "quantity";
	private static final String KEY_QTY_FILLED = "qtyfilled";
	private static final String KEY_FOLLOWED = "followed";
	private static final String KEY_NUM_FOLLOWED = "num_followed";
	private static final String KEY_USER_EMAIL = "user_email";
	private static final String KEY_WID = "webid";
	
	
	/* Data Flags */
	public static final String PRIVATE_FLAG = "private";
	public static final String PUBLIC_FLAG = "public";

	/**
	 * Database Constructor
	 * 
	 * @param context - Context which is calling upon Database
	 */
	public LocalDB(Context context) { 
		super(context, DATABASE_NAME, null, DATABASE_VERSION); 
	} 

	/* Creating Table */ 
	@Override
	public void onCreate(SQLiteDatabase db) { 
		String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_TASKS + "("
				+ KEY_TID + " INTEGER PRIMARY KEY," 
				+ KEY_UID + " INTEGER,"
				+ KEY_TITLE + " TEXT," 
				+ KEY_DESCRIPTION + " TEXT," 
				+ KEY_DATECREATE + " INTEGER,"
				+ KEY_DATEDUE + " INTEGER,"
				+ KEY_TYPE + " TEXT,"
				+ KEY_VISIBILITY + " NUMERIC,"
				+ KEY_QUANTITY + " INTEGER," 
				+ KEY_QTY_FILLED + " INTEGER,"  
				+ KEY_FOLLOWED + " INTEGER,"
				+ KEY_NUM_FOLLOWED + " INTEGER,"
				+ KEY_USER_EMAIL + " TEXT,"
				+ KEY_WID + " TEXT)"; 
		
		db.execSQL(CREATE_TASKS_TABLE); 
	} 

	/* Upgrading database */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { 
		// Drop older table if existed 
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS); 

		// Create tables again 
		onCreate(db); 
	} 
	
	/* Force Database Upgrade */
	public void forceUpgrade() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
		onCreate(db);
		db.close();
	}

	/////////////////////////////////////////////////// 
	// All CRUD
	// (Create, Read, Update, Delete)
	// Operations 
	//////////////////////////////////////////////////
	
	/**
	 * 
	 * @param uid
	 * @return
	 */
	public List<Task> getAllTaskSummaries(String uid) {
		List<Task> taskList = new ArrayList<Task>();
		
		Date date = new Date();
		long compareDate = date.getTime();
		String selectQuery = "SELECT "+ KEY_WID +","+ KEY_TITLE +","+ KEY_TYPE +" FROM "+ TABLE_TASKS +" WHERE "+ KEY_UID +"!='"+ uid +"' AND "+ KEY_QUANTITY +">"+ KEY_QTY_FILLED +" AND "+ KEY_DATEDUE +">"+ compareDate +" ORDER BY "+ KEY_DATEDUE +" DESC";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Task task = new Task();
				task.set_wid(cursor.getString(0));
				task.set_title(cursor.getString(1));
				task.set_type(cursor.getString(2));
				// Adding contact to list
				taskList.add(task);
			} while (cursor.moveToNext());
		}
		db.close();
		return taskList;
	}
	
	/**
	 * 
	 * @param wid
	 * @return
	 */
	public Integer checkExists(String wid){
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT COUNT(*) FROM "+ TABLE_TASKS +" WHERE "+ KEY_WID +"='"+ wid +"'";
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null)
			cursor.moveToFirst();

		Integer result = cursor.getInt(0);
		if (result == null){
			result = 0;
		}

		return result; // Returns 1 if in DB, otherwise return 0
	}
	
	/**
	 * Adding of Task to database
	 * 
	 * Takes a Task Object and explodes it 
	 * into its individual components to save
	 * to the database.
	 * 
	 * @param task - Task Object
	 * @return ID of Inserted Task
	 */
	public long createTask(Task task) { 
		SQLiteDatabase db = this.getWritableDatabase(); 

		ContentValues values = new ContentValues(); 
		values.put(KEY_UID, task.get_uid());
		values.put(KEY_TITLE, task.get_title());
		values.put(KEY_DESCRIPTION, task.get_description());
		values.put(KEY_DATECREATE, task.get_dateCreate());
		values.put(KEY_DATEDUE, task.get_dateDue());
		values.put(KEY_TYPE, task.get_type());
		values.put(KEY_VISIBILITY, task.get_visibility());
		values.put(KEY_QUANTITY, task.get_quantity());
		values.put(KEY_QTY_FILLED, task.get_qty_filled());
		values.put(KEY_FOLLOWED, task.get_followed());
		values.put(KEY_NUM_FOLLOWED, task.get_num_followed());
		values.put(KEY_USER_EMAIL, task.get_user_email());
		values.put(KEY_WID, task.get_wid());

		// Inserting Row 
		long id = db.insert(TABLE_TASKS, null, values); 
		db.close();
		
		return id;
	} 

	/**
	 * Get an individual task
	 * 
	 * Queries database for an individual task
	 * based off its tid then returns this as a 
	 * task object.
	 * 
	 * @param tid - Task Identifier
	 * @return Task Object
	 */
	public Task getTask(long tid) { 
		SQLiteDatabase db = this.getReadableDatabase(); 

		String selectQuery = "SELECT  * FROM " + TABLE_TASKS + " WHERE " + KEY_TID + "='" + tid + "'"; 
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor != null) 
			cursor.moveToFirst(); 

		Task task = new Task(
				Long.parseLong(cursor.getString(0)), 
				cursor.getString(1), cursor.getString(2), 
				cursor.getString(3), cursor.getString(4),
				cursor.getString(5), cursor.getString(6), 
				Integer.parseInt(cursor.getString(7)),
				Integer.parseInt(cursor.getString(8)),
				Integer.parseInt(cursor.getString(9)),
				Integer.parseInt(cursor.getString(10)),
				Integer.parseInt(cursor.getString(11)),
				cursor.getString(12),
				cursor.getString(13)); 
		db.close();
		return task; 
	} 

	/**
	 * Gets all Tasks
	 * 
	 * Queries database for all tasks and
	 * returns them in a list.
	 * 
	 * @return List of Tasks
	 */
	public List<Task> getAllTasks() { 
		List<Task> taskList = new ArrayList<Task>(); 

		Date date = new Date();
		long compareDate = date.getTime();
		String selectQuery = "SELECT  * FROM " + TABLE_TASKS +" WHERE "+ KEY_QUANTITY +">"+ KEY_QTY_FILLED +" AND "+ KEY_DATEDUE +">"+ compareDate +" ORDER BY "+ KEY_DATEDUE +" ASC"; 

		SQLiteDatabase db = this.getWritableDatabase(); 
		Cursor cursor = db.rawQuery(selectQuery, null); 

		// looping through all rows and adding to list 
		if (cursor.moveToFirst()) { 
			do { 
					Task task = new Task(); 
					task.set_tid(Long.parseLong(cursor.getString(0))); 
					task.set_uid(cursor.getString(1)); 
					task.set_title(cursor.getString(2)); 
					task.set_description(cursor.getString(3)); 
					if (cursor.getLong(4) == 0)
						task.set_dateCreate(cursor.getString(4), Task.TASK_REMOTE); 
					else 
						task.set_dateCreate(cursor.getString(4), Task.TASK_LOCAL);
					task.set_dateDue(cursor.getString(5), Task.TASK_LOCAL); 
					task.set_type(cursor.getString(6)); 
					task.set_visibility(cursor.getInt(7));
					task.set_quantity(cursor.getInt(8));
					task.set_qty_filled(cursor.getInt(9));
					task.set_followed(cursor.getInt(10));
					task.set_num_followed(cursor.getInt(11));
					task.set_user_email(cursor.getString(12));
					task.set_wid(cursor.getString(13));
					// Adding contact to list 
					taskList.add(task); 
			} while (cursor.moveToNext()); 
		} 
		
		db.close();

		return taskList; 
	} 
	
	/**
	 * Gets all Tasks by UserID
	 * 
	 * Queries database for all tasks where UserID=uid
	 * and returns them in a list 
	 * 
	 * @param uid - User Identifier
	 * @return List of Tasks
	 */
	public List<Task> getAllTasksByUid(String uid) { 
		List<Task> taskList = new ArrayList<Task>(); 

		Date date = new Date();
		long compareDate = date.getTime();
		String selectQuery = "SELECT * FROM " + TABLE_TASKS + " WHERE " + KEY_UID + "='" + uid + "' AND "+ KEY_QUANTITY +">"+ KEY_QTY_FILLED +" AND "+ KEY_DATEDUE +">"+ compareDate +" ORDER BY "+ KEY_DATEDUE +" DESC";

		SQLiteDatabase db = this.getWritableDatabase(); 
		Cursor cursor = db.rawQuery(selectQuery, null); 

		// looping through all rows and adding to list 
		if (cursor.moveToFirst()) { 
			do { 
				Task task = new Task(); 
				task.set_tid(Long.parseLong(cursor.getString(0))); 
				task.set_uid(cursor.getString(1)); 
				task.set_title(cursor.getString(2)); 
				task.set_description(cursor.getString(3)); 
				task.set_dateCreate(cursor.getString(4), Task.TASK_LOCAL); 
				task.set_dateDue(cursor.getString(5), Task.TASK_LOCAL); 
				task.set_type(cursor.getString(6)); 
				task.set_visibility(cursor.getInt(7));
				task.set_quantity(cursor.getInt(8));
				task.set_qty_filled(cursor.getInt(9));
				task.set_followed(cursor.getInt(10));
				task.set_num_followed(cursor.getInt(11));
				task.set_user_email(cursor.getString(12));
				// Adding contact to list 
				taskList.add(task); 
			} while (cursor.moveToNext()); 
		} 
		db.close();
		return taskList; 
	} 
	
	/**
	 * Gets all Public Tasks by UserID	
	 * 
	 * Queries database for all tasks where UserID=uid
	 * and where Visibility=1 (public) then returns them 
	 * in a list of Task objects.
	 * 
	 * @param uid - User Identifier
	 * @return List of Tasks
	 */
	public List<Task> getPublicTasksByUid(String uid){
		List<Task> taskList = new ArrayList<Task>();
		
		Date date = new Date();
		long compareDate = date.getTime();
		String selectQuery = "SELECT * FROM "+ TABLE_TASKS +" WHERE "+ KEY_UID +"='"+ uid +"' AND "+ KEY_VISIBILITY +"='0' AND "+ KEY_QUANTITY +">"+ KEY_QTY_FILLED +" AND "+ KEY_DATEDUE +">"+ compareDate +" ORDER BY "+ KEY_DATEDUE +" DESC";
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		// looping through all rows and adding to list 
		if (cursor.moveToFirst()) { 
			do { 
				Task task = new Task(); 
				task.set_tid(Long.parseLong(cursor.getString(0))); 
				task.set_uid(cursor.getString(1)); 
				task.set_title(cursor.getString(2)); 
				task.set_description(cursor.getString(3)); 
				task.set_dateCreate(cursor.getString(4), Task.TASK_LOCAL); 
				task.set_dateDue(cursor.getString(5), Task.TASK_LOCAL); 
				task.set_type(cursor.getString(6)); 
				task.set_visibility(cursor.getInt(7));
				task.set_quantity(cursor.getInt(8));
				task.set_qty_filled(cursor.getInt(9));
				task.set_followed(cursor.getInt(10));
				task.set_num_followed(cursor.getInt(11));
				task.set_user_email(cursor.getString(12));
				// Adding contact to list 
				taskList.add(task); 
			} while (cursor.moveToNext()); 
		} 
		db.close();
		return taskList;
	}

	/**
	 * Gets all Private Tasks by UserID
	 * 
	 * Queries database for all tasks where UserID=uid
	 * and where Visibility=1 (private) then returns them 
	 * in a list of Task objects.
	 * 
	 * @param uid - User Identifier
	 * @return List of Tasks
	 */
	public List<Task> getPrivateTasksByUid(String uid){
		List<Task> taskList = new ArrayList<Task>();
		
		Date date = new Date();
		long compareDate = date.getTime();
		String selectQuery = "SELECT * FROM "+ TABLE_TASKS +" WHERE "+ KEY_UID +"='"+ uid +"' AND "+ KEY_VISIBILITY +"='1' AND "+ KEY_QUANTITY +">"+ KEY_QTY_FILLED +" AND "+ KEY_DATEDUE +">"+ compareDate +" ORDER BY "+ KEY_DATEDUE +" DESC";
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		// looping through all rows and adding to list 
		if (cursor.moveToFirst()) { 
			do { 
				Task task = new Task(); 
				task.set_tid(Long.parseLong(cursor.getString(0))); 
				task.set_uid(cursor.getString(1)); 
				task.set_title(cursor.getString(2)); 
				task.set_description(cursor.getString(3)); 
				task.set_dateCreate(cursor.getString(4), Task.TASK_LOCAL); 
				task.set_dateDue(cursor.getString(5), Task.TASK_LOCAL); 
				task.set_type(cursor.getString(6)); 
				task.set_visibility(cursor.getInt(7));
				task.set_quantity(cursor.getInt(8));
				task.set_qty_filled(cursor.getInt(9));
				task.set_followed(cursor.getInt(10));
				task.set_num_followed(cursor.getInt(11));
				task.set_user_email(cursor.getString(12));
				// Adding contact to list 
				taskList.add(task); 
			} while (cursor.moveToNext()); 
		} 
		db.close();
		return taskList;
	}
	
	/**
	 * Gets Tasks which you have followed/made that are complete.
	 * 
	 * Queries database for all tasks which you have followed OR
	 * created that have passed the due date OR that have filled
	 * their quota for quantity.
	 * 
	 * @param uid - User Identifier
	 * @return List of Tasks
	 */
	public List<Task> getLoggedTasks(String uid){
List<Task> taskList = new ArrayList<Task>();
		
		Date date = new Date();
		long compareDate = date.getTime();
		String selectQuery = "SELECT * FROM "+ TABLE_TASKS +" WHERE "+ KEY_FOLLOWED +"='1' AND ("+ KEY_DATEDUE +"<"+ compareDate +" OR "+ KEY_QUANTITY +"<="+ KEY_QTY_FILLED +") ORDER BY "+ KEY_DATEDUE +" DESC";
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		// looping through all rows and adding to list 
		if (cursor.moveToFirst()) { 
			do { 
				Task task = new Task(); 
				task.set_tid(Long.parseLong(cursor.getString(0))); 
				task.set_uid(cursor.getString(1)); 
				task.set_title(cursor.getString(2)); 
				task.set_description(cursor.getString(3)); 
				task.set_dateCreate(cursor.getString(4), Task.TASK_LOCAL); 
				task.set_dateDue(cursor.getString(5), Task.TASK_LOCAL); 
				task.set_type(cursor.getString(6)); 
				task.set_visibility(cursor.getInt(7));
				task.set_quantity(cursor.getInt(8));
				task.set_qty_filled(cursor.getInt(9));
				task.set_followed(cursor.getInt(10));
				task.set_num_followed(cursor.getInt(11));
				task.set_user_email(cursor.getString(12));
				task.set_wid(cursor.getString(13));
				// Adding contact to list 
				taskList.add(task); 
			} while (cursor.moveToNext()); 
		} 
		db.close();
		return taskList;
	}
	
	/**
	 * Get Followed Tasks
	 * 
	 * Queries database for all tasks which you have followed
	 * and do not own and are not complete, and returns them in
	 * a list.
	 * 
	 * @param uid - User Identifier
	 * @return List of Tasks
	 */
	public List<Task> getFollowedTasks(String uid){
		List<Task> taskList = new ArrayList<Task>();
		
		Date date = new Date();
		long compareDate = date.getTime();
		String selectQuery = "SELECT * FROM "+ TABLE_TASKS +" WHERE "+ KEY_FOLLOWED +"='1' AND "+ KEY_DATEDUE +">"+ compareDate +" AND "+ KEY_QUANTITY +">"+ KEY_QTY_FILLED +" AND "+ KEY_UID +"!='"+ uid +"' ORDER BY "+ KEY_DATEDUE +" DESC";
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		// looping through all rows and adding to list 
		if (cursor.moveToFirst()) { 
			do { 
				Task task = new Task(); 
				task.set_tid(Long.parseLong(cursor.getString(0))); 
				task.set_uid(cursor.getString(1)); 
				task.set_title(cursor.getString(2)); 
				task.set_description(cursor.getString(3)); 
				task.set_dateCreate(cursor.getString(4), Task.TASK_LOCAL); 
				task.set_dateDue(cursor.getString(5), Task.TASK_LOCAL); 
				task.set_type(cursor.getString(6)); 
				task.set_visibility(cursor.getInt(7));
				task.set_quantity(cursor.getInt(8));
				task.set_qty_filled(cursor.getInt(9));
				task.set_followed(cursor.getInt(10));
				task.set_num_followed(cursor.getInt(11));
				task.set_user_email(cursor.getString(12));
				task.set_wid(cursor.getString(13));
				// Adding contact to list 
				taskList.add(task); 
			} while (cursor.moveToNext()); 
		} 
		db.close();
		return taskList;
	}
	
	/**
	 * Updates a Single Tasks Information
	 * 
	 * Queries Database to update a Single
	 * Task by utilizing a Task Object to
	 * overwrite information in the database.
	 * 
	 * @param task - Task Object
	 * @return Affected Row
	 */
	public int updateTask(Task task, String flag) { 
		SQLiteDatabase db = this.getWritableDatabase(); 	
		ContentValues values = new ContentValues(); 
		
		if (flag.equals(LocalDB.PUBLIC_FLAG)){
			values.put(KEY_WID, task.get_wid());
			values.put(KEY_TID, task.get_tid());
		}
		
		values.put(KEY_UID, task.get_uid());
		values.put(KEY_TITLE, task.get_title());
		values.put(KEY_DESCRIPTION, task.get_description());
		values.put(KEY_DATECREATE, task.get_dateCreate());
		values.put(KEY_DATEDUE, task.get_dateDue());
		values.put(KEY_TYPE, task.get_type());
		values.put(KEY_VISIBILITY, task.get_visibility());
		values.put(KEY_QUANTITY, task.get_quantity());
		values.put(KEY_QTY_FILLED, task.get_qty_filled());
		values.put(KEY_FOLLOWED, task.get_followed());
		values.put(KEY_NUM_FOLLOWED, task.get_num_followed());
		values.put(KEY_USER_EMAIL, task.get_user_email());
		values.put(KEY_WID, task.get_wid());
		
		int affectedRows = db.update(TABLE_TASKS, values, KEY_TID + " = ?", 
				new String[] { String.valueOf(task.get_tid()) });
		db.close();
		// updating row 
		return affectedRows;
	} 

	/**
	 * Delete task from database
	 * 
	 * Deletes a task from database
	 * based on its Task Identifier
	 * 
	 * @param tid - Task Identifier
	 */
	public void deleteTask(long tid) {
		SQLiteDatabase db = this.getWritableDatabase(); 
		db.delete(TABLE_TASKS, KEY_TID + " = ?", 
				new String[] { String.valueOf(tid) }); 
		db.close(); 		
	}
	
	public long cacheWebTask(Task task) {
		SQLiteDatabase db = this.getWritableDatabase(); 	
		ContentValues values = new ContentValues(); 
		
		values.put(KEY_UID, task.get_uid());
		values.put(KEY_TITLE, task.get_title());
		values.put(KEY_DESCRIPTION, task.get_description());
		values.put(KEY_DATECREATE, task.get_dateCreate());
		values.put(KEY_DATEDUE, task.get_dateDue());
		values.put(KEY_TYPE, task.get_type());
		values.put(KEY_VISIBILITY, 1);
		values.put(KEY_QUANTITY, task.get_quantity());
		values.put(KEY_QTY_FILLED, task.get_qty_filled());
		values.put(KEY_FOLLOWED, 0);
		values.put(KEY_NUM_FOLLOWED, task.get_num_followed());
		values.put(KEY_USER_EMAIL, task.get_user_email());
		
		db.update(TABLE_TASKS, values, KEY_WID + " = ?", new String[] { task.get_wid() });
		
		String selectQuery = "SELECT "+ KEY_TID +" FROM "+ TABLE_TASKS +" WHERE "+ KEY_WID +"='"+ task.get_wid() +"'";
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor != null) 
			cursor.moveToFirst();
		long tidOfTask = cursor.getLong(0);
		
		db.close();
		// updating row 
		return tidOfTask;
	}
		
	/**
	 * DEBUGGING: Prints all Tasks
	 * 
	 * Used to print all tasks 
	 * DO NOT USE on live product.
	 */
	public void printAllTasks(){
		Log.d("Print: ", "Printing all tasks..."); 
		List<Task> tasks = this.getAllTasks();        

		for (Task n : tasks) { 
			String log = "Tid: " + n.get_tid() 
					+ ", Uid: " + n.get_uid()
					+ ", Title: " + n.get_title() 
					+ ", Description: " + n.get_description()
					+ ", DateCreate: " + n.get_dateCreate()
					+ ", DateDue: " + n.get_dateDue()
					+ ", Type: " + n.get_type()
					+ ", Visibility: " + n.get_visibility()
					+ ", Quantity: " + n.get_quantity();
			
			// Printing Tasks to log 
			Log.d("Task: ", log); 
		}
	}
	
	/**
	 * DEBUGGING: Print all Tasks by UserID
	 * 
	 * Used to print all tasks where UserID=uid
	 * DO NOT USE on live product
	 * 
	 * @param uid - User Identifier
	 */
	public void printAllTasksByUid(String uid){
		Log.d("Print: ", "Printing all tasks..."); 
		List<Task> tasks = this.getAllTasksByUid(uid);        

		for (Task n : tasks) { 
			String log = "Tid: " + n.get_tid() 
					+ ", Uid: " + n.get_uid()
					+ ", Title: " + n.get_title() 
					+ ", Description: " + n.get_description()
					+ ", DateCreate: " + n.get_dateCreate()
					+ ", DateDue: " + n.get_dateDue()
					+ ", Type: " + n.get_type()
					+ ", Visibility: " + n.get_visibility()
					+ ", Quantity: " + n.get_quantity();
			
			// Printing Tasks to log 
			Log.d("Task: ", log); 
		}
	}
	
	/**
	 * DEBUGGING: Create Random Task
	 * 
	 * Creates a random task
	 * DO NOT USE on live product
	 */
	public void createRandomTask() {
		Task task = new Task("1234567890", "TITLE", "DESCRIPTION", "2012-11-11", "2012-11-30", "TYPE", 1, 1, 0, 1, 1, "jsmereka@ualberta.ca");
		createTask(task);
	}
	
	/**
	 * DEBUGGING: Empties Database of all Tasks
	 * 
	 * Empties database of all tasks it currently
	 * has stored in it.
	 * DO NOT USE on live product
	 */
	public void emptyDatabase() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_TASKS, null, null);
		db.close();
	}

}

