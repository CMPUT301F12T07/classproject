package com.CMPUT301F12T07.crowdsource;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class EmailActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        
        String type = getIntent().getStringExtra("type");
        Uri received = Uri.parse(getIntent().getStringExtra("data"));

        startEmailIntent("tfung@ualberta.ca", type, received);
        finish();
    }

    private void startEmailIntent(String email, String type, Uri input) {
    	Intent intent = new Intent(android.content.Intent.ACTION_SEND);
    	
		Log.v("onactivity", "asdf");
    	
    	intent.setType("message/rfc822");
    	intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});
    	intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
    	
    	// if type is not equal to Text, then do not attach
    	if (type.compareTo("Text") != 0) {
    		intent.putExtra(android.content.Intent.EXTRA_TEXT, "This attachment contains your requested task");
    		intent.putExtra(Intent.EXTRA_STREAM, input);
    	} else {
    		intent.putExtra(android.content.Intent.EXTRA_TEXT, "Text:\n\n\n");
    	}
    	
    	intent.setType("text/plain");
    	startActivity(intent);
    }
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_email, menu);
        return true;
    }
}
