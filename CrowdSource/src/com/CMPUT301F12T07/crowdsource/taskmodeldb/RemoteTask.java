package com.CMPUT301F12T07.crowdsource.taskmodeldb;

/**
 * Task Value Object
 * @author  Victor Guana - guana[at]ualberta.ca University of Alberta, Department  of Computing Science
 */
public class RemoteTask {

	/**
	 * @uml.property  name="summary"
	 * @uml.associationEnd  
	 */
	private TaskSummary summary;// desciption of Task object
	/**
	 * @uml.property  name="content"
	 * @uml.associationEnd  
	 */
	private Task content;
	/**
	 * @uml.property  name="id"
	 */
	private String id;
	/**
	 * @uml.property  name="description"
	 */
	private String description;// title of Task object

	/** Empty constructor */
	public RemoteTask() {
	}

	/** constructor without id */
	public RemoteTask(Task task) {
		TaskSummary taskSummary = new TaskSummary(task.get_wid(), task.get_title(), task.get_dateDue(), task.get_quantity(), task.get_qty_filled(), task.get_type(), task.get_uid());
		this.setSummary(taskSummary);
		this.setContent(task);
		this.setDescription(task.get_description());
	}

	/** constructor with id */
	public RemoteTask(Task task, String id) {
		TaskSummary taskSummary = new TaskSummary(task.get_wid(), task.get_title(), task.get_dateDue(), task.get_quantity(), task.get_qty_filled(), task.get_type(), task.get_uid());
		this.setSummary(taskSummary);
		this.setContent(task);
		this.setId(id);
		this.setDescription(task.get_description());
	}

	/**
	 * @return
	 * @uml.property  name="summary"
	 */
	public TaskSummary getSummary() {
		return summary;
	}

	/**
	 * @param summary
	 * @uml.property  name="summary"
	 */
	public void setSummary(TaskSummary summary) {
		this.summary = summary;
	}

	/**
	 * @return
	 * @uml.property  name="content"
	 */
	public Task getContent() {
		return content;
	}

	/**
	 * @param task
	 * @uml.property  name="content"
	 */
	public void setContent(Task task) {
		this.content = task;
	}

	/**
	 * @return
	 * @uml.property  name="id"
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 * @uml.property  name="id"
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return
	 * @uml.property  name="description"
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 * @uml.property  name="description"
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
