package com.CMPUT301F12T07.crowdsource.taskmodeldb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author  jsmereka
 */
public class TaskSummary {

	/**
	 * Private variables
	 * @uml.property  name="_wid"
	 */
	private String _wid;
	/**
	 * @uml.property  name="_title"
	 */
	private String _title;
	/**
	 * @uml.property  name="_dateDue"
	 */
	private long _dateDue;
	/**
	 * @uml.property  name="_quantity"
	 */
	private int _quantity;
	/**
	 * @uml.property  name="_qty_filled"
	 */
	private int _qty_filled;
	/**
	 * @uml.property  name="_type"
	 */
	private String _type;
	/**
	 * @uml.property  name="_uid"
	 */
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
    
    /**
	 * Getter and setter for TaskSummary
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
	 * @uml.property  name="_dateDue"
	 */
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
	
	/**
	 * @return
	 * @uml.property  name="_uid"
	 */
	public String get_uid() {
		return _uid;
	}
	
	/**
	 * @param uid
	 * @uml.property  name="_uid"
	 */
	public void set_uid(String uid) {
		this._uid = uid;
	}
	
}
