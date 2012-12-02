package com.CMPUT301F12T07.crowdsource;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class ChoosePictureActivity extends Activity {

	private static final int RETURN_IMAGE_CODE = 1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_picture);
       
        startPicture();
    }
    
    /**
     * starts the built in Android camera application, and captures photos.
     * After capturing, it will call on ActivityResult.
     */
    private void startPicture() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        
        startActivityForResult(intent,RETURN_IMAGE_CODE);
    }

    /**
     * When onActivityResult is called, it will setup the return intent back
     * to the original caller
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        Intent result = new Intent();
        
        if (requestCode == RETURN_IMAGE_CODE && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            
            result.putExtra("Photo", selectedImage.toString());
            setResult(RESULT_OK, result);
            
        } else {
	        setResult(RESULT_CANCELED, result);
        }
        
        finish();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_choose_picture, menu);
        return true;
    }
}
