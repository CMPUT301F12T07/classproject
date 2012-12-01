package com.CMPUT301F12T07.crowdsource.taskmodeldb;

public class Task { 
	
	/** private variables */
    int _tid; 
    String _uid;
    String _title;
    String _description; 
    String _dateCreate; 
    String _dateDue;
    String _type;
    String _email;
    int _visibility;
    int _quantity;
    int _follows;
    int _submitted;
    Boolean _followed;
    
    /** Empty constructor */
    public Task(){ } 
   
// TODO: Add Flag to Model and Database for Following
// TODO: User Email Address
// TODO: Add these for Model/Database Handler
//    public Task(String webid, String title){ 
//    	this._webid = webid;
//    	this._title = title;
//    }
//    
// TODO: These should be in Database Handler
//    public Task getAllWebTasks(){    	
//    	return new Task(parsedWebID, parsedTitle);
//    }
//    
//    public Task getTaskByWid(wid){
//    	return new Task(etc., etc., etc.)
//    }

    /** constructor without tid */ 
    public Task(String uid, String title, String description, String dateCreate, 
    		String dateDue, String type, int visibility, int quantity){ 
        this._uid = uid;
        this._title = title;
        this._description = description; 
        this._dateCreate = dateCreate; 
        this._dateDue = dateDue;
        this._type = type;
        this._visibility = visibility;
        this._quantity = quantity;
    }
    
    /** constructor with tid */ 
    public Task( int tid, String uid, String title, String description, String dateCreate, 
    		String dateDue, String type, int visibility, int quantity){ 
    	this._tid = tid;
        this._uid = uid;
        this._title = title;
        this._description = description; 
        this._dateCreate = dateCreate; 
        this._dateDue = dateDue;
        this._type = type;
        this._visibility = visibility;
        this._quantity = quantity;
    }

    /** getter and setter */
	public int get_tid() {
		return _tid;
	}

	public void set_tid(int _tid) {
		this._tid = _tid;
	}

	public String get_uid() {
		return _uid;
	}

	public void set_uid(String _uid) {
		this._uid = _uid;
	}

	public String get_title() {
		return _title;
	}

	public void set_title(String _title) {
		this._title = _title;
	}

	public String get_description() {
		return _description;
	}

	public void set_description(String _description) {
		this._description = _description;
	}

	public String get_dateCreate() {
		return _dateCreate;
	}

	public void set_dateCreate(String _dateCreate) {
		this._dateCreate = _dateCreate;
	}

	public String get_dateDue() {
		return _dateDue;
	}

	public void set_dateDue(String _dateDue) {
		this._dateDue = _dateDue;
	}

	public String get_type() {
		return _type;
	}

	public void set_type(String _type) {
		this._type = _type;
	}
	
	public String get_email(){
		return _email;
	}
	
	public void set_email(String _email){
		this._email = _email;
	}
	
	public int get_visibility() {
		return _visibility;
	}

	public void set_visibility(int _visibility) {
		this._visibility = _visibility;
	}
	
	public int get_quantity() {
		return _quantity;
	}

	public void set_quantity(int _quantity) {
		this._quantity = _quantity;
	}
	
	public int get_follows(){
		return _follows;
	}
	
	public void set_follows(int _follows){
		this._follows = _follows;
	}
	
	public int get_submitted(){
		return _submitted;
	}
	
	public void set_submitted(int _submitted){
		this._submitted = _submitted;
	}
	
	public Boolean get_followed(){
		return _followed;
	}
	
	public void set_followed(Boolean _followed){
		this._followed = _followed;
	}

}