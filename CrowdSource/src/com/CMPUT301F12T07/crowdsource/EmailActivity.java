package com.CMPUT301F12T07.crowdsource;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class EmailActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        
        startEmailIntent("falserecipiant@gmail.com");
    }

    private void startEmailIntent(String email) {
    	String[] recipients = new String[]{};
    	
    	Intent intent = new Intent(android.content.Intent.ACTION_SEND);
    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_email, menu);
        return true;
    }
}
