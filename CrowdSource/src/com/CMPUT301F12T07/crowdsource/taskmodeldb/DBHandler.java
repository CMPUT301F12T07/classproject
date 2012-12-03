package com.CMPUT301F12T07.crowdsource.taskmodeldb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;

public class DBHandler {

	private LocalDB localDB;
	private RemoteDB remoteDB;
	
	public static String LOCAL_FLAG = "local";
	public static String REMOTE_FLAG = "remote";
	

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
			task.set_followed(0);
			RemoteTask remoteTask = new RemoteTask(task);
			remoteTask = remoteDB.createTask(remoteTask);
			// copy wid from web to sqlite (task fail here, sqlite wid are all
			// null)
			task.set_wid(remoteTask.getId());
			task.set_followed(1);
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
				task.set_followed(0);
				RemoteTask remoteTask = new RemoteTask(task);
				remoteTask = remoteDB.createTask(remoteTask);
				task.set_wid(remoteTask.getId());
				task.set_followed(1);
				localDB.updateTask(task, LocalDB.PUBLIC_FLAG);
			}

		}
	}

	// TODO: Rewrite This
	public void followTask(String wid) throws Exception {
		
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

	public List<Task> getLoggedTasks(String uid) {
		List<Task> taskList = localDB.getLoggedTasks(uid);
		return taskList;
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

	public Task getTask(String id, String visi_flag) {
		if (visi_flag == LOCAL_FLAG) {
			return localDB.getTask(Long.parseLong(id));
		} else {
			Task remoteTask = new Task();
			String jsonToParse = null;
			try {
				jsonToParse = remoteDB.getTask(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			remoteTask = remoteDB.parseIndividualJson(jsonToParse);
			
			return remoteTask;
		}
	}
	
	public long cacheTask(Task remoteTask) {
		return localDB.cacheWebTask(remoteTask);
	}

}
