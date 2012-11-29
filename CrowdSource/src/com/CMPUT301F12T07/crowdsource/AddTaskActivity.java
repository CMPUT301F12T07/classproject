package com.CMPUT301F12T07.crowdsource;

import java.util.Calendar;

import com.CMPUT301F12T07.crowdsource.taskmodeldb.DBHandler;
import com.CMPUT301F12T07.crowdsource.taskmodeldb.LocalDB;
import com.CMPUT301F12T07.crowdsource.taskmodeldb.Task;

import android.os.Bundle;
import android.provider.Settings.Secure;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddTaskActivity extends Activity {

	private String deviceId;
	
	private Button selectDate;
	private TextView selectedDate;
	private String dateCreate;
	private String dateDue;
	private int year;
	private int month;
	private int day;
	
	private EditText titleText;
	private EditText descriptionText;
	private EditText quantityText;
	
	private Spinner typeSpinner;
	
	private CheckBox privacyCheckBox;
//	private int visibility;
	
	private Button save;
	
    // intializes listeners and gets android id on startup
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        
        /// test code for take photo
        Button photo = (Button) findViewById(R.id.takephoto);
        photo.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), TakePhotoActivity.class);
				startActivity(intent);
			}
        });
        ///
        
        deviceId = Secure.getString(this.getContentResolver(), Secure.ANDROID_ID);
        initializeListeners();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_task, menu);
        return true;
    }
    
    private void initializeListeners() {
    	initializeDates();
    	initializeTextFields();
    	initializeSpinners();
    	initializeCheckBox();
    	initializeSave();
    }

	private void initializeDates() {
    	selectDate = (Button) findViewById(R.id.selectDateButton);
    	selectedDate = (TextView) findViewById(R.id.dateTextView);
    	final Calendar cal = Calendar.getInstance();
    	
    	year = cal.get(Calendar.YEAR);
    	month = cal.get(Calendar.MONTH);
    	day = cal.get(Calendar.DAY_OF_MONTH);
    	
    	selectedDate.setText(year + "-" + (month+1) + "-" + day);
    	dateCreate = year + "-" + (month+1) + "-" + day;
    	
    	selectDate.setOnClickListener(new OnClickListener() {
    		
			@Override
			public void onClick(View v) {
				DatePickerDialog c = new DatePickerDialog(v.getContext(), mDateSetListener, year, month,  day);
				c.show();
			}
    		
    	});
    }
    
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
	    public void onDateSet(DatePicker v, int inyear, int inmonth, int inday) {
	    	year = inyear;
	        month = inmonth;
	        day = inday;
	        
	        dateDue = year + "-" + (month+1) + "-" + day;
	        selectedDate.setText(dateDue);
	        
	        Toast.makeText(v.getContext(), "Date set to: " + dateDue, Toast.LENGTH_SHORT).show();
	    }
    };
    
    
    private void initializeTextFields() {
    	titleText = (EditText) findViewById(R.id.titleText);
    	descriptionText = (EditText) findViewById(R.id.descriptionText);
    	quantityText = (EditText) findViewById(R.id.quantityText);
	}
    
    // initializes dropdown type boxes
    private void initializeSpinners() {
    	typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
    }
    
    private void initializeCheckBox() {
    	privacyCheckBox = (CheckBox) findViewById(R.id.privacyCheckbox);
    }
    
    private void initializeSave() {
    	save = (Button) findViewById(R.id.saveButton);
    	
    	save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String title = titleText.getText().toString();
				String description = descriptionText.getText().toString();
				String quantity = quantityText.getText().toString();
				String type = typeSpinner.getSelectedItem().toString();
				int visibility = (privacyCheckBox.isChecked() ? 1 : 0);
				
				if (title.compareTo("") == 0) 
					Toast.makeText(v.getContext(), "Title cannot be left blank.", Toast.LENGTH_SHORT).show();
				else if (description.compareTo("") == 0) 
					Toast.makeText(v.getContext(), "Description cannot be left blank.", Toast.LENGTH_SHORT).show();
				else if (quantity.compareTo("") == 0 || quantity.compareTo("0") == 0/*quantity == 0*/) 
					Toast.makeText(v.getContext(), "Quantity has to be at least one.", Toast.LENGTH_SHORT).show();
				else
				{
					DBHandler db = new DBHandler(v.getContext());
					Task newTask = new Task(deviceId, title, description, dateCreate, dateDue, type, visibility, Integer.parseInt(quantity));
					
					try {
						db.createTask(newTask);
					} catch (Exception e) {
						e.printStackTrace();
					}
					finish();
				}
				
			}
    		
    	});
    }

}
