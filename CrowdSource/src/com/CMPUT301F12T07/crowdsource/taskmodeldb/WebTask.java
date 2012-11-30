package com.CMPUT301F12T07.crowdsource.taskmodeldb;

/**
 * Task Value Object
 * @author Victor Guana - guana[at]ualberta.ca
 * University of Alberta, Department of Computing Science
 */
public class WebTask {
	
	private String summary;//desciption of Task object
	private Task content;
	private String id;
	private String description;//title of Task object
	
    /** Empty constructor */
    public WebTask(){ } 

    /** constructor without id */ 
    public WebTask(Task task){ 
    	this.setSummary(task.get_title());
    	this.setContent(task);
    	this.setDescription(task.get_description());
    }
    
    /** constructor with id */ 
    public WebTask(Task task, String id){ 
    	this.setSummary(task.get_title());
    	this.setContent(task);
    	this.setId(id);
    	this.setDescription(task.get_description());
    }

	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
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

//	public String toString(){
//		return summary+", "+task+", "+id+", "+description+", "+task.toString();
//	}
}
