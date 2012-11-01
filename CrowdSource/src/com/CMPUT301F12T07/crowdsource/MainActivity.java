package com.CMPUT301F12T07.crowdsource;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /* Function to call ViewTask
     * Intent intent = new intent(this, ViewTask.class);
     * intent.putExtra(@string/TaskObject, database.getTaskById(Id));  // database call needs to be fixed
     * startActivity(intent);
     */
}
