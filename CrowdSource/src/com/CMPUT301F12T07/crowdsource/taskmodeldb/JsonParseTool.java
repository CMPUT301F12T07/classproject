package com.CMPUT301F12T07.crowdsource.taskmodeldb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonParseTool {

	/**
	 * Parse Json string to get a list of task 
	 * of the whole datebase 
	 * including localDB and remoteDB
	 * @param jsonStringVersion
	 * @return List<Task>
	 */
	public static List<Task> parseTaskList(String jsonStringVersion) {
		List<Task> taskList = new ArrayList<Task>();
		// TODO: Convert to a Regular Expression to remove "{ }" and \" \":
		jsonStringVersion = jsonStringVersion.replace("\"{", "{");
		jsonStringVersion = jsonStringVersion.replace("}\"", "}");
		jsonStringVersion = jsonStringVersion.replace("\\", "");
		JsonElement jsonElement = new JsonParser().parse(jsonStringVersion);
		JsonArray array = jsonElement.getAsJsonArray();

		String wid;
		String title;
		long dateDue;
		int quantity;
		int qty_filled;
		String type;
		String uid;

		Iterator<?> iterator = array.iterator();
		while (iterator.hasNext()) {
			JsonObject jsonObject = (JsonObject) iterator.next();

			// get wid
			JsonElement widElement = jsonObject.get("id");
			wid = widElement.getAsString();

			// get summary
			JsonObject summaryObject = jsonObject.getAsJsonObject("summary");
			title = summaryObject.get("_title").getAsString();
			dateDue = summaryObject.get("_dateDue").getAsLong();
			quantity = summaryObject.get("_quantity").getAsInt();
			qty_filled = summaryObject.get("_qty_filled").getAsInt();
			type = summaryObject.get("_type").getAsString();
			uid = summaryObject.get("_uid").getAsString();

			Task task = new Task(wid, title, dateDue, quantity, qty_filled, type, uid);
			taskList.add(task);

		}

		return taskList;
	}
	
	/**
	 * Parse Json string to get a single task
	 * @param jsonStringVersion
	 * @return Task
	 */
	public static Task parseTask(String jsonStringVersion) {
		Task remoteTask = new Task();
		// TODO: Convert to a Regular Expression to remove "{ }" and \" \":
		jsonStringVersion = jsonStringVersion.replace("\"{", "{");
		jsonStringVersion = jsonStringVersion.replace("}\"", "}");
		jsonStringVersion = jsonStringVersion.replace("\\", "");
		JsonElement jsonElement = new JsonParser().parse(jsonStringVersion);
		JsonObject jsonObject = jsonElement.getAsJsonObject();

			// get wid
		JsonElement widElement = jsonObject.get("id");
		remoteTask.set_wid(widElement.getAsString());

		// get summary
		JsonObject contentObject = jsonObject.getAsJsonObject("content");
		JsonElement titleElement = contentObject.get("_title");
		remoteTask.set_title(titleElement.getAsString());
		JsonElement descElement = contentObject.get("_description");
		remoteTask.set_description(descElement.getAsString());
		JsonElement dateCreElement = contentObject.get("_dateCreate");
		remoteTask.set_dateCreate(dateCreElement.getAsString(), Task.TASK_REMOTE);
		JsonElement dateDueElement = contentObject.get("_dateDue");
		remoteTask.set_dateDue(dateDueElement.getAsString(), Task.TASK_REMOTE);
		remoteTask.set_followed(0);
		JsonElement num_follElement = contentObject.get("_num_followed");
		remoteTask.set_num_followed(num_follElement.getAsInt());
		JsonElement qty_fillElement = contentObject.get("_qty_filled");
		remoteTask.set_qty_filled(qty_fillElement.getAsInt());
		JsonElement quantElement = contentObject.get("_quantity");
		remoteTask.set_quantity(quantElement.getAsInt());
		JsonElement typeElement = contentObject.get("_type");
		remoteTask.set_type(typeElement.getAsString());
		JsonElement uidElement = contentObject.get("_uid");
		remoteTask.set_uid(uidElement.getAsString());
		JsonElement emailElement = contentObject.get("_user_email");
		remoteTask.set_user_email(emailElement.getAsString());
		JsonElement visiElement = contentObject.get("_visibility");
		remoteTask.set_visibility(visiElement.getAsInt());
		
		return remoteTask;
	}
	
}
