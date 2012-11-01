package com.CMPUT301F12T07.crowdsource;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class ViewTask extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        
        Task curentObject = getIntent().getExtras().getParcelable(@string/TaskObject);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_task, menu);
        return true;
    }
}
