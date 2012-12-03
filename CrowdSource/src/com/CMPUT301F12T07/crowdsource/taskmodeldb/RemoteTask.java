package com.CMPUT301F12T07.crowdsource.taskmodeldb;

/**
 * Task Value Object
 * 
 * @author Victor Guana - guana[at]ualberta.ca University of Alberta, Department
 *         of Computing Science
 */
public class RemoteTask {

	private TaskSummary summary;// desciption of Task object
	private Task content;
	private String id;
	private String description;// title of Task object

	/** Empty constructor */
	public RemoteTask() {
	}

	/** constructor without id */
	public RemoteTask(Task task) {
		TaskSummary taskSummary = new TaskSummary(task.get_wid(), task.get_title(), task.get_dateDue(), task.get_quantity(), task.get_qty_filled(), task.get_type());
		this.setSummary(taskSummary);
		this.setContent(task);
		this.setDescription(task.get_description());
	}

	/** constructor with id */
	public RemoteTask(Task task, String id) {
		TaskSummary taskSummary = new TaskSummary(task.get_wid(), task.get_title(), task.get_dateDue(), task.get_quantity(), task.get_qty_filled(), task.get_type());
		this.setSummary(taskSummary);
		this.setContent(task);
		this.setId(id);
		this.setDescription(task.get_description());
	}

	public TaskSummary getSummary() {
		return summary;
	}

	public void setSummary(TaskSummary summary) {
		this.summary = summary;
	}

	public Task getContent() {
		return content;
	}

	public void setContent(Task task) {
		this.content = task;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
