package com.CMPUT301F12T07.crowdsource;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable { 
	  
    /** private variables */
    int _tid; 
    int _uid;
    String _title;
    String _description; 
    String _dateCreate; 
    String _dateDue;
    String _type;
    int _visibility;
    int _quantity;
    
    /** Empty constructor */
    public Task(){ } 

    /** constructor without tid */ 
    public Task(int uid, String title, String description, String dateCreate, 
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
    public Task( int tid, int uid, String title, String description, String dateCreate, 
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

	public int get_uid() {
		return _uid;
	}

	public void set_uid(int _uid) {
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

	/*
	 * Parcelable Interface Requirements
	 */
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	} 

}