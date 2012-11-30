package com.CMPUT301F12T07.crowdsource.taskmodeldb;

public abstract class Command {
	
	// wid of the task
	private String target = "";
	public String action = "";
	
	public Command(String target, String action){
		this.target = target;
		this.action = action;
	}
	
	public abstract void  execute();	
	
}


