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
        
        startEmailIntent("thomas.fung1@gmail.com");
        finish();
    }

    private void startEmailIntent(String email) {
    	Intent intent = new Intent(android.content.Intent.ACTION_SEND);
    	
    	intent.setType("message/rfc822");
    	intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});
    	intent.putExtra(android.content.Intent.EXTRA_TEXT, "This attachment contains your requested task");
    	intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
    	
    	intent.setType("text/plain");
    	startActivity(intent);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_email, menu);
        return true;
    }
}
