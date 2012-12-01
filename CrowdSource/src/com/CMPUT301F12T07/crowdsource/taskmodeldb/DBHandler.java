package com.CMPUT301F12T07.crowdsource.taskmodeldb;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class DBHandler {
	
	private LocalDB localDB;
	private RemoteDB remoteDB;
	
	public DBHandler(Context context){
		this.localDB = new LocalDB(context);
		this.remoteDB = new RemoteDB();
	}
	
	public long createTask(Task task) throws Exception {
		long tid = localDB.createTask(task);
		if (task.get_visibility() == 0) {
			// TODO: Remote Task Stuff
		}
		return tid;
	}
	
	public void deleteTask(long tid) throws Exception {
		Task task = localDB.getTask(tid);
		if (task.get_visibility() == 0) {
			// TODO: Remote Task Stuff
		}
		localDB.deleteTask(tid);
	}
	
	public void updateTask(Task task) throws Exception {
		localDB.updateTask(task, LocalDB.PUBLIC_FLAG);
		if (task.get_visibility() == 0) {
			// TODO: Remote Task Stuff
		}
	}
	
	public void cacheTask(Task task) throws Exception {
		// TODO: All this
	}
	
	public void followTask(Task task) throws Exception {
		// TODO: All this
	}
	
	public List<Task> getAllMyTasks(String uid) {
		List<Task> taskList = localDB.getAllTasksByUid(uid);
	    return taskList;
	}
	
	public List<Task> getFollowedTasks(String uid){
	     List<Task> taskList = localDB.getFollowedTasks(uid);
	     return taskList;
	    }
	    
	public List<Task> getPrivateTasksByUid(String uid){
	     List<Task> taskList = localDB.getPrivateTasksByUid(uid);
	     return taskList;

	}
	public List<Task> getPublicTasksByUid(String uid){
	     List<Task> taskList = localDB.getPublicTasksByUid(uid);
	     return taskList;
	}
	
	public List<Task> getAllWebTasks() throws Exception{
		// String jsonStringVersion = webDB.listTasks();
		// List<Task> taskList = webDB.parseJson(jsonStringVersion);
		List<Task> taskList = remoteDB.parseJson("jsonStringVersion");
		return taskList;
	}

	public List<Task> getLoggedTasks(String uid){
		// on github
		List<Task> taskList = new ArrayList<Task>();
		// TODO: Add query with somthing like: SELECT * FROM TABLE_TASKS WHERE (KEY_FLOOWED='1' AND KEY_DATA...
		// Note: this willl need the fllowed field added on, as well as an update to own task createion so that
		// we implicity imply that tasks you own you fllow.
		// IE. I make task IMPLIES I follow task, and am unable to unfollow except when deleting task.
		return taskList;
	}
	
	public List<Task> getAllTasks() {
		List<Task> taskList = new ArrayList<Task>();
		taskList = localDB.getAllTasks();
		
		// TODO: Add in Web DB + Cache Summary from WebDB
		return taskList;
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
		    
	public Task getRandomTask(){
		return new Task();
	}
	
	
	
}
