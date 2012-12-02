package com.CMPUT301F12T07.crowdsource.taskmodeldb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonParseTool {
	
	// parse task list for HomeScreen
	public static List<Task> parseTaskList (String jsonStringVersion) {
		List<Task> taskList = new ArrayList<Task>(); 
		JsonElement jsonElement = new JsonParser().parse(jsonStringVersion);
		JsonArray array = jsonElement.getAsJsonArray();

		Iterator iterator = array.iterator();
		while(iterator.hasNext()){
			JsonObject jsonObject = (JsonObject) iterator.next();

			// get summary
			JsonElement titleElement = jsonObject.get("summary");
			String title = titleElement.getAsString();
			// get wid
			JsonElement widElement = jsonObject.get("id");
			String wid = widElement.getAsString();

			// Assume there is a constructor has only title and wid.
			Task task = new Task(wid, title, "2012-12-12", 1, 1);
			taskList.add(task);
		}
		return taskList;
	}

	// Parse a single RemoteTask
	public static RemoteTask parseRemoteTask (String jsonStringVersion) {

		long tid; 
		String uid;
		String title;
		String description; 
		String dateCreate; 
		String dateDue;
		String type;
		int visibility; // 1 or 0
		int quantity;
		int qty_filled;
		int followed; // 1 or 0
		int num_followed;
		String user_email;
		String wid;

		JsonObject jsonObject = (JsonObject) new JsonParser().parse(jsonStringVersion);

		JsonElement tidElement = jsonObject.get("tid");
		tid = tidElement.getAsLong();

		JsonElement uidElement = jsonObject.get("uid");
		uid = uidElement.getAsString();

		JsonElement titleElement = jsonObject.get("title");
		title = titleElement.getAsString();

		JsonElement descriptionElement = jsonObject.get("description");
		description = descriptionElement.getAsString();

		JsonElement dateCreateElement = jsonObject.get("dateCreate");
		dateCreate = dateCreateElement.getAsString();

		JsonElement dateDueElement = jsonObject.get("dateDue");
		dateDue = dateDueElement.getAsString();

		JsonElement typeElement = jsonObject.get("type");
		type = typeElement.getAsString();

		JsonElement visibilityElement = jsonObject.get("visibility");
		visibility = visibilityElement.getAsInt();

		JsonElement quantityElement = jsonObject.get("quantity");
		quantity = quantityElement.getAsInt();

		JsonElement qtyfilledElement = jsonObject.get("qty_filled");
		qty_filled = qtyfilledElement.getAsInt();

		JsonElement followedElement = jsonObject.get("followed");
		followed = followedElement.getAsInt();

		JsonElement numfollowedElement = jsonObject.get("num_followed");
		num_followed = numfollowedElement.getAsInt();

		JsonElement useremailElement = jsonObject.get("user_email");
		user_email = useremailElement.getAsString();

		JsonElement widElement = jsonObject.get("wid");
		wid = widElement.getAsString();

		// Create task object
		Task task = new Task(tid, uid, title, description, dateCreate, dateDue,
				type, visibility, quantity, qty_filled, followed, num_followed, user_email);

		// Create RemoteTask object
		RemoteTask remoteTask = new RemoteTask(task, wid);
		return remoteTask;
	}
}


