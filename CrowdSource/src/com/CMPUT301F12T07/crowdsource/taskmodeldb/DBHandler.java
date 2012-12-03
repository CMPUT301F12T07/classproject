package com.CMPUT301F12T07.crowdsource.taskmodeldb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;

public class DBHandler {

	private LocalDB localDB;
	private RemoteDB remoteDB;


	public DBHandler(Context context) {
		this.localDB = new LocalDB(context);
		this.remoteDB = new RemoteDB();
	}

	public long createTask(Task task) throws Exception {
		long tid = localDB.createTask(task);
		if (task.get_visibility() == 0) {
			// add createCommand to hashMap

			// copy tid from sqlite to web
			task.set_tid(tid);
			RemoteTask remoteTask = new RemoteTask(task);
			remoteTask = remoteDB.createTask(remoteTask);
			// copy wid from web to sqlite (task fail here, sqlite wid are all
			// null)
			task.set_wid(remoteTask.getId());
			localDB.updateTask(task, LocalDB.PUBLIC_FLAG);
		}
		return tid;
	}

	public void deleteTask(long tid) throws Exception {
		Task task = localDB.getTask(tid);
		if (task.get_visibility() == 0) {
			if (!task.get_wid().equals("")) {
				// add deleteCommand to hashMap
				remoteDB.deleteTask(task.get_wid());
			}
		}
		localDB.deleteTask(tid);
	}

	public void updateTask(Task task) throws Exception {
		long tid = localDB.updateTask(task, LocalDB.PRIVATE_FLAG);
		task = localDB.getTask(tid);
		/*
		 * four possible logic for the web service: 
		 * visibility == 1 && wid != null => task is from public to private 
		 * visibility == 1 && wid == null => task is always private 
		 * visibility == 0 && wid != null => task is always public 
		 * visibility == 0 && wid == null => task is from private to public
		 */
		if ((task.get_visibility() == 1)) {
			if (task.get_wid() != null && !task.get_wid().isEmpty()) {
				// task is from public to private
				// delete task from web
				// delete wid of task in local
				remoteDB.deleteTask(task.get_wid());
				task.set_wid(null);
				localDB.updateTask(task, LocalDB.PUBLIC_FLAG);
			} else {
				// task is always private
				// do nothing to the web
			}

		} else /* task.get_visibility == 0 */{
			if (task.get_wid() != null && !task.get_wid().isEmpty()) {
				// task is always public
				// update task to web
				RemoteTask remoteTask = new RemoteTask(task, task.get_wid());
				remoteDB.updateTask(remoteTask);
			} else {
				// task is from private to public
				// create task to web
				// set back wid to local
				RemoteTask remoteTask = new RemoteTask(task);
				remoteTask = remoteDB.createTask(remoteTask);
				task.set_wid(remoteTask.getId());
				localDB.updateTask(task, LocalDB.PUBLIC_FLAG);
			}

		}
	}

	public void followTask(String wid) throws Exception {
		RemoteTask remoteTask = remoteDB.getTask(wid);
		localDB.createTask(remoteTask.getContent());
	}

	public List<Task> getAllMyTasks(String uid) {
		List<Task> taskList = localDB.getAllTasksByUid(uid);
		return taskList;
	}

	public List<Task> getFollowedTasks(String uid) {
		List<Task> taskList = localDB.getFollowedTasks(uid);
		return taskList;
	}

	public List<Task> getPrivateTasksByUid(String uid) {
		List<Task> taskList = localDB.getPrivateTasksByUid(uid);
		return taskList;

	}

	public List<Task> getPublicTasksByUid(String uid) {
		List<Task> taskList = localDB.getPublicTasksByUid(uid);
		return taskList;
	}

	public List<Task> getAllWebTasks() throws Exception {
		// String jsonStringVersion = webDB.listTasks();
		// List<Task> taskList = webDB.parseJson(jsonStringVersion);
		List<Task> taskList = remoteDB.parseJson("jsonStringVersion");
		return taskList;
	}

	// TODO: 
	public List<Task> getLoggedTasks(String uid) {
		// on github
		List<Task> taskList = new ArrayList<Task>();
		// TODO: Add query with somthing like: SELECT * FROM TABLE_TASKS WHERE
		// (KEY_FLOOWED='1' AND KEY_DATA...
		// Note: this willl need the fllowed field added on, as well as an
		// update to own task createion so that
		// we implicity imply that tasks you own you fllow.
		// IE. I make task IMPLIES I follow task, and am unable to unfollow
		// except when deleting task.
		return taskList;
	}

	// For HomeScreen 
	// remoteList object only has:
	// wid, title, dateDue, quantity, qty_filled and type
	// store them to localDB
	// check if wid exist then do nothing,  if not then add to localDB
	public List<Task> getAllTasks() {

		// get remoteList
		List<Task> remoteList = new ArrayList<Task>();
		String jsonToParse = null;
		try {
			jsonToParse = remoteDB.listTasks();
		} catch (Exception e) {
			e.printStackTrace();
		}
		remoteList = remoteDB.parseJson(jsonToParse);

		// Check each task exist in localDB or not
		for (int i = 0; i < remoteList.size(); i++) {
			Task task = remoteList.get(i);
			String wid = task.get_wid();
			if (localDB.checkExists(wid) == 0){
				// add to localDB
				localDB.createTask(task);
			}
		}

		return localDB.getAllTasks();
	}

	public void emptyDatabase() {
		localDB.emptyDatabase();
	}

	public void createRandomTask() {
		localDB.createRandomTask();
	}

	public Task getTask(long l) {
		return localDB.getTask(l);
	}

	public Task getRandomTask(String uid) {

		// Get all Task summaries from localDB
		List<Task> taskList = new ArrayList<Task>();
		taskList = localDB.getAllTaskSummaries(uid);
		
		// Pick random task
		int max = taskList.size();
		Random generator = new Random(); 
		int randomIndex = generator.nextInt(max);
		Task task = taskList.get(randomIndex);

		return task;
	}

}
