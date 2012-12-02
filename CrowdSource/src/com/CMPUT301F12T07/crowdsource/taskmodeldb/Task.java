package com.CMPUT301F12T07.crowdsource.taskmodeldb;

public class Task { 
	
	/** private variables */
    long _tid; 
    String _uid;
    String _title;
    String _description; 
    String _dateCreate; 
    String _dateDue;
    String _type;
    String _email;
    int _visibility; // 1 or 0
    int _quantity;
    int _qty_filled;
    int _followed; // 1 or 0
    int _num_followed;
    String _user_email;
    String _wid;
    
    /** Empty constructor */
    public Task(){ } 
   
    /** constructor with wid */
    public Task(String webid, String title, String dateDue, int quantity, int qty_filled, String type) { 
    	this._wid = webid;
    	this._title = title;
    	this._dateDue = dateDue;
    	this._quantity = quantity;
    	this._qty_filled = qty_filled;
    	this._type = type;
    }
    
    /** constructor without tid */ 
    public Task(String uid, String title, String description, String dateCreate, 
    		String dateDue, String type, int visibility, int quantity, int qty_filled, int followed, int num_followed, String user_email){ 
        this._uid = uid;
        this._title = title;
        this._description = description; 
        this._dateCreate = dateCreate; 
        this._dateDue = dateDue;
        this._type = type;
        this._visibility = visibility;
        this._quantity = quantity;
        this._qty_filled = qty_filled;
        this._followed = followed;
        this._num_followed = num_followed;
        this._user_email = user_email;
    }
    
    /** constructor with tid */ 
    public Task( long tid, String uid, String title, String description, String dateCreate, 
    		String dateDue, String type, int visibility, int quantity, int qty_filled, int followed, int num_followed, String user_email){ 
    	this._tid = tid;
        this._uid = uid;
        this._title = title;
        this._description = description; 
        this._dateCreate = dateCreate; 
        this._dateDue = dateDue;
        this._type = type;
        this._visibility = visibility;
        this._quantity = quantity;
        this._qty_filled = qty_filled;
        this._followed = followed;
        this._num_followed = num_followed;
        this._user_email = user_email;
    }
    
    /** constructor with tid & wid */ 
    public Task( long tid, String uid, String title, String description, String dateCreate, 
    		String dateDue, String type, int visibility, int quantity, int qty_filled, int followed, int num_followed, String user_email, String webid){ 
    	this._tid = tid;
        this._uid = uid;
        this._title = title;
        this._description = description; 
        this._dateCreate = dateCreate; 
        this._dateDue = dateDue;
        this._type = type;
        this._visibility = visibility;
        this._quantity = quantity;
        this._qty_filled = qty_filled;
        this._followed = followed;
        this._num_followed = num_followed;
        this._user_email = user_email;
        this._wid = webid;
    }

    /** getter and setter */
	public long get_tid() {
		return _tid;
	}

	public void set_tid(long _tid) {
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
	
	public int get_qty_filled() {
		return _qty_filled;
	}
	
	public void set_qty_filled(int _qty_filled) {
		this._qty_filled = _qty_filled;
	}
	
	public int get_followed() {
		return _followed;
	}
	
	public void set_followed(int _followed) {
		this._followed = _followed;
	}
	
	public int get_num_followed() {
		return _num_followed;
	}
	
	public void set_num_followed(int _num_followed) {
		this._num_followed = _num_followed;
	}
	
	public String get_user_email() {
		return _user_email;
	}
	
	public void set_user_email(String user_email) {
		this._user_email = user_email;
	}
	
	public String get_wid() {
		return _wid;
	}
	
	public void set_wid(String webid) {
		this._wid = webid;
	}
}
