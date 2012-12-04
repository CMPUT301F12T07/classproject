package com.CMPUT301F12T07.crowdsource.taskmodeldb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author  jsmereka
 */
public class Task { 
	
	/**
	 * private variables
	 * @uml.property  name="_tid"
	 */
    Long _tid; 
    /**
	 * @uml.property  name="_uid"
	 */
    String _uid;
    /**
	 * @uml.property  name="_title"
	 */
    String _title;
    /**
	 * @uml.property  name="_description"
	 */
    String _description; 
    /**
	 * @uml.property  name="_dateCreate"
	 */
    long _dateCreate; 
    /**
	 * @uml.property  name="_dateDue"
	 */
    long _dateDue;
    /**
	 * @uml.property  name="_type"
	 */
    String _type;
    /**
	 * @uml.property  name="_visibility"
	 */
    int _visibility; // 1 or 0
    /**
	 * @uml.property  name="_quantity"
	 */
    int _quantity;
    /**
	 * @uml.property  name="_qty_filled"
	 */
    int _qty_filled;
    /**
	 * @uml.property  name="_followed"
	 */
    int _followed; // 1 or 0
    /**
	 * @uml.property  name="_num_followed"
	 */
    int _num_followed;
    /**
	 * @uml.property  name="_user_email"
	 */
    String _user_email;
    /**
	 * @uml.property  name="_wid"
	 */
    String _wid;
    
    public static final int TASK_REMOTE = 1;
    public static final int TASK_LOCAL = 0;
    
    /** Empty constructor */
    public Task(){ } 
   
    /** constructor with wid */
    public Task(String webid, String title, long dateDue, int quantity, int qty_filled, String type, String uid) { 
    	this._wid = webid;
    	this._title = title;
    	this._dateDue = dateDue;
    	this._quantity = quantity;
    	this._qty_filled = qty_filled;
    	this._type = type;
    	this._uid = uid;
    }
    
    /** constructor without tid */ 
    public Task(String uid, String title, String description, String dateCreate, 
    		String dateDue, String type, int visibility, int quantity, int qty_filled, int followed, int num_followed, String user_email){ 
        this._uid = uid;
        this._title = title;
        this._description = description; 
        set_dateCreate(dateCreate, TASK_LOCAL); 
        set_dateDue(dateDue, TASK_LOCAL);
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
        set_dateCreate(dateCreate, TASK_LOCAL); 
        set_dateDue(dateDue, TASK_LOCAL);
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
        set_dateCreate(dateCreate, TASK_LOCAL); 
        set_dateDue(dateDue, TASK_LOCAL);
        this._type = type;
        this._visibility = visibility;
        this._quantity = quantity;
        this._qty_filled = qty_filled;
        this._followed = followed;
        this._num_followed = num_followed;
        this._user_email = user_email;
        this._wid = webid;
    }

    /**
	 * getters and setters
	 * @uml.property  name="_tid"
	 */
    
	public Long get_tid() {
		return _tid;
	}

	/**
	 * @param _tid
	 * @uml.property  name="_tid"
	 */
	public void set_tid(Long _tid) {
		this._tid = _tid;
	}

	/**
	 * @return
	 * @uml.property  name="_uid"
	 */
	public String get_uid() {
		return _uid;
	}

	/**
	 * @param _uid
	 * @uml.property  name="_uid"
	 */
	public void set_uid(String _uid) {
		this._uid = _uid;
	}

	/**
	 * @return
	 * @uml.property  name="_title"
	 */
	public String get_title() {
		return _title;
	}

	/**
	 * @param _title
	 * @uml.property  name="_title"
	 */
	public void set_title(String _title) {
		this._title = _title;
	}

	/**
	 * @return
	 * @uml.property  name="_description"
	 */
	public String get_description() {
		return _description;
	}

	/**
	 * @param _description
	 * @uml.property  name="_description"
	 */
	public void set_description(String _description) {
		this._description = _description;
	}

	/**
	 * @return
	 * @uml.property  name="_dateCreate"
	 */
	public String get_dateCreate() {
		Long dateLong = _dateCreate;
		if (dateLong == 0){
			return dateLong.toString();
		} else {
			Date date = new Date(_dateCreate);
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.format(date).toString();
		}
	}

	public void set_dateCreate(String _dateCreate, int taskFlag) {
		if (taskFlag == TASK_REMOTE){
			this._dateCreate = Long.parseLong(_dateCreate);
		} else {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = format.parse(_dateCreate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			cal.setTime(date);
			this._dateCreate = cal.getTime().getTime();
		}
	}

	/**
	 * @return
	 * @uml.property  name="_dateDue"
	 */
	public String get_dateDue() {
		Date date = new Date(_dateDue);
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    return format.format(date).toString();
	}

	public void set_dateDue(String _dateDue, int taskFlag) {
		if (taskFlag == TASK_REMOTE){
			this._dateDue = Long.parseLong(_dateDue);
		} else {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = format.parse(_dateDue);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			cal.setTime(date);
			this._dateDue = cal.getTime().getTime();
		}
	}

	/**
	 * @return
	 * @uml.property  name="_type"
	 */
	public String get_type() {
		return _type;
	}

	/**
	 * @param _type
	 * @uml.property  name="_type"
	 */
	public void set_type(String _type) {
		this._type = _type;
	}
	
	/**
	 * @return
	 * @uml.property  name="_visibility"
	 */
	public int get_visibility() {
		return _visibility;
	}

	/**
	 * @param _visibility
	 * @uml.property  name="_visibility"
	 */
	public void set_visibility(int _visibility) {
		this._visibility = _visibility;
	}
	
	/**
	 * @return
	 * @uml.property  name="_quantity"
	 */
	public int get_quantity() {
		return _quantity;
	}

	/**
	 * @param _quantity
	 * @uml.property  name="_quantity"
	 */
	public void set_quantity(int _quantity) {
		this._quantity = _quantity;
	}
	
	/**
	 * @return
	 * @uml.property  name="_qty_filled"
	 */
	public int get_qty_filled() {
		return _qty_filled;
	}
	
	/**
	 * @param _qty_filled
	 * @uml.property  name="_qty_filled"
	 */
	public void set_qty_filled(int _qty_filled) {
		this._qty_filled = _qty_filled;
	}
	
	/**
	 * @return
	 * @uml.property  name="_followed"
	 */
	public int get_followed() {
		return _followed;
	}
	
	/**
	 * @param _followed
	 * @uml.property  name="_followed"
	 */
	public void set_followed(int _followed) {
		this._followed = _followed;
	}
	
	/**
	 * @return
	 * @uml.property  name="_num_followed"
	 */
	public int get_num_followed() {
		return _num_followed;
	}
	
	/**
	 * @param _num_followed
	 * @uml.property  name="_num_followed"
	 */
	public void set_num_followed(int _num_followed) {
		this._num_followed = _num_followed;
	}
	
	/**
	 * @return
	 * @uml.property  name="_user_email"
	 */
	public String get_user_email() {
		return _user_email;
	}
	
	/**
	 * @param user_email
	 * @uml.property  name="_user_email"
	 */
	public void set_user_email(String user_email) {
		this._user_email = user_email;
	}
	
	/**
	 * @return
	 * @uml.property  name="_wid"
	 */
	public String get_wid() {
		return _wid;
	}
	
	/**
	 * @param webid
	 * @uml.property  name="_wid"
	 */
	public void set_wid(String webid) {
		this._wid = webid;
	}
}
