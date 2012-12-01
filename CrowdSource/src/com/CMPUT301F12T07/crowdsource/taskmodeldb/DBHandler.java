package com.CMPUT301F12T07.crowdsource.taskmodeldb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import android.content.Context;
import android.util.Log;

public class DBHandler {

	private LocalDB localDB;
	private WebDB webDB;
	private HashMap<String, Queue<Command>> syncDict = new HashMap<String, Queue<Command>>();

	public DBHandler(Context context){
		this.localDB = new LocalDB(context);
		this.webDB = new WebDB();
	}

	// Add task to localDB and to webDB if public.
	public long createTask(Task task) throws Exception{
		long tid = localDB.createTask(task);
		if (task.get_visibility() == 0){
			// add createCommand to hashMap

			// copy tid from sqlite to web
			task.set_tid(tid);
			WebTask webTask = new WebTask(task);
			webTask= webDB.createTask(webTask);
			// copy wid from web to sqlite (task fail here, sqlite wid are all null)
			task.set_wid(webTask.getId());
			localDB.updateTask(task, LocalDB.FOR_PUBLIC);
		}
		return tid;
	}

	// Delete task from localDB and from webDB if public.
	public void deleteTask(long tid) throws Exception{
		Task task = localDB.getTask(tid);
		if ( (task.get_visibility() == 0) ){
			if (!task.get_wid().equals("")) {
				// add deleteCommand to hashMap
				webDB.deleteTask(task.get_wid());
			}
		}
		localDB.deleteTask(tid);

	}

	// Update task from localDb and from webDB if public.
	public void updateTask(Task task) throws Exception {
		long tid = localDB.updateTask(task, LocalDB.FOR_PRIVATE);
		task = localDB.getTask(tid);
		/*
		if ( (task.get_visibility() == 0) ){
			// if task has wid, update web as well
			if (task.get_wid() != null && !task.get_wid().isEmpty()) {
				// add updateCommand to hashMap
				WebTask webTask = new WebTask(task, task.get_wid());
				webDB.updateTask(webTask);
			}
			// if task doesn't have wid,  
			else{

				WebTask webTask = new WebTask(task);
				webTask= webDB.createTask(webTask);
				// copy wid from web to sqlite (task fail here, sqlite wid are all null)
				task.set_wid(webTask.getId());
				localDB.updateTask(task, LocalDB.FOR_PUBLIC);
			}
		}*/
		/* four possible logic here:
		 * visibility == 1 && wid != null => task is from public to private
		 * visibility == 1 && wid == null => task is always private
		 * visibility == 0 && wid != null => task is always public
		 * visibility == 0 && wid == null => task is from private to public
		 */
		if ( (task.get_visibility() == 1 ) ){
			if (task.get_wid() != null && !task.get_wid().isEmpty()){
				// task is from public to private
				// delete task from web
				// delete wid of task in local
				webDB.deleteTask(task.get_wid());
				task.set_wid(null);
				localDB.updateTask(task, LocalDB.FOR_PUBLIC);
			}
			else{
				// task is always private
				// do nothing to the web
			}

		}
		else /* task.get_visibility == 0*/ {
			if (task.get_wid() != null && !task.get_wid().isEmpty())
			{
				// task is always public
				// update task to web
				WebTask webTask = new WebTask(task, task.get_wid());
				webDB.updateTask(webTask);
			}
			else{
				// task is from private to public
				// create task to web
				// set back wid to local
				WebTask webTask = new WebTask(task);
				webTask = webDB.createTask(webTask);
				task.set_wid(webTask.getId());
				localDB.updateTask(task, LocalDB.FOR_PUBLIC);
			}

		}
	}



	public void followTask(String wid) throws Exception{
		WebTask webTask = webDB.getTask(wid);
		localDB.createTask(webTask.getContent());
	}

	// get all my tasks from localDB (Heavy Task)
	// separate private and public (take the one create by Jeremy)
	public List<Task> getAllMyTasks(String uid){
		List<Task> taskList = localDB.getAllTasksByUid("user1");
		return taskList;
	}

	// get all my follow tasks from localDB (Heavy Task)
	public List<Task> getFollowedTasks(String uid){
		List<Task> taskList = localDB.getFollowTasks(uid);
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

	// get all tasks from web DB. (Light  weight Task, only title and wid)
	public List<Task> getAllWebTasks() throws Exception{
		//    	String jsonStringVersion = webDB.listTasks();
		//    	List<Task> taskList = webDB.parseJson(jsonStringVersion);

		List<Task> taskList = webDB.parseJson("jsonStringVersion");
		return taskList;
	}

	// Queries database for all tasks which you have followed or 
	// created that have passed the due date or that have filled
	// their quota for quantity.
	public List<Task> getLoggedTasks(String uid){
		// on github
		List<Task> taskList = new ArrayList<Task>();
		// TODO: Add query with somthing like: SELECT * FROM TABLE_TASKS WHERE (KEY_FLOOWED='1' AND KEY_DATA...
		// Note: this willl need the fllowed field added on, as well as an update to own task createion so that
		// we implicity imply that tasks you own you fllow.
		// IE. I make task IMPLIES I follow task, and am unable to unfollow except when deleting task.
		return taskList;
	}

	public Task getTask(long l) { 
		return localDB.getTask(l);
	}

	public Task getRandomTask(){
		return new Task();
	}

	public void printLocalDB() {
		localDB.printAllTasks();
	}

	//   public void printWebDB() {
	//  	webDB.printAllTasks();
	// }
}
