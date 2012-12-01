package com.CMPUT301F12T07.crowdsource;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class EmailActivity extends Activity {
	
	private static final int PHOTO_CODE = 1;
	private static final int AUDIO_CODE = 2;
	private static final int TEXT_CODE = 3;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        
        startEmail();
    }

    /**
     * startEmail will retrieve the data from the callers, and set up
     * its Uri and type. With these received 'Extras', they are passed to
     * startEmailIntent for actual emailing.
     */
    private void startEmail() {
        Intent intent = getIntent();
        
        int type = getType(intent.getStringExtra("type"));
        String email = intent.getStringExtra("email");
        
        if (type == 0) {
        	Log.v("EmailActivity", "Wrong type.");
        }
        
        try {
	        Uri received = Uri.parse(intent.getStringExtra("data"));
	        startEmailIntent(email, type, received);
        } catch (Exception e) {
        	Log.v("EmailActivity", "Error receiving Uri");
        }
        
        finish();
    }
    
    private int getType(String type) {
    	if (type.equals("Audio")) 		return AUDIO_CODE;
    	else if (type.equals("Photo")) return PHOTO_CODE;
    	else if (type.equals("Text")) 	return TEXT_CODE;
    	else return 0;
    }
    
    /**
     * startEmailIntent will setup the intent by attaching the data if need be, and
     * send it to the email client the user picks.
     * 
     * @param email This is the recipient's email address
     * @param type	This would be the fulfillment type
     * @param input	This would the data of the fulfillment
     */
    private void startEmailIntent(String email, int type, Uri input) {
    	Intent intent = new Intent(android.content.Intent.ACTION_SEND);
 
    	intent.setType("message/rfc822");
    	intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});
    	intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
    	
    	// if type is not equal to Text, then do not attach
    	if (type == TEXT_CODE) {
    		intent.putExtra(android.content.Intent.EXTRA_TEXT, "This attachment contains your requested task");
    		intent.putExtra(Intent.EXTRA_STREAM, input);
    	} else {
    		intent.putExtra(android.content.Intent.EXTRA_TEXT, "Text:\n\n\n");
    	}
    	
    	intent.setType("text/plain");
    	startActivityForResult(intent, type);
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    		
    		if (resultCode == RESULT_CANCELED) {finish(); return;}
    		
    		switch(requestCode) {
	    		case PHOTO_CODE:
	    			Log.v("PHOTO", "EmailActivity");
	    			break;
	    		
	    		case AUDIO_CODE:
	    			Log.v("AUDIO", "EmailActivity");
	    			break;
	
	    		case TEXT_CODE:
	    			Log.v("TEXT", "EmailActivity");
	    			break;
	    			
	    		default:
	    			Log.v("default", "default case in EmailActivity");
    		}
    	
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_email, menu);
        return true;
    }
}
