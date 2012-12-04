package com.CMPUT301F12T07.crowdsource.taskmodeldb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TaskSummary {

	private String _wid;
	private String _title;
	private long _dateDue;
	private int _quantity;
	private int _qty_filled;
	private String _type;
	private String _uid;
	
	/** constructor with wid */
    public TaskSummary(String webid, String title, String dateDue, int quantity, int qty_filled, String type, String uid) { 
    	this._wid = webid;
    	this._title = title;
    	set_dateDue(dateDue);
    	this._quantity = quantity;
    	this._qty_filled = qty_filled;
    	this._type = type;
    	this._uid = uid;
    }
    
    public String get_title() {
		return _title;
	}

	public void set_title(String _title) {
		this._title = _title;
	}
	
	public String get_dateDue() {
		Date date = new Date(_dateDue);
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    return format.format(date).toString();
	}

	public void set_dateDue(String _dateDue) {
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
	
	public String get_type() {
		return _type;
	}

	public void set_type(String _type) {
		this._type = _type;
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
	
	public String get_wid() {
		return _wid;
	}
	
	public void set_wid(String webid) {
		this._wid = webid;
	}
	
	public String get_uid() {
		return _uid;
	}
	
	public void set_uid(String uid) {
		this._uid = uid;
	}
	
}
