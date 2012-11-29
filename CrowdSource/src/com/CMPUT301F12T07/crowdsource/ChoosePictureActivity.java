package com.CMPUT301F12T07.crowdsource;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;

public class ChoosePictureActivity extends Activity {

	private ImageView pic;
	private static final int RETURN_IMAGE_CODE = 1;
	private Bitmap bmp;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_picture);
        
        pic = (ImageView) findViewById(R.id.image);
        
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        
        startActivityForResult(intent,1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RETURN_IMAGE_CODE && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            
            Intent result = new Intent();
            result.putExtra("image", selectedImage.toString());
            setResult(RESULT_OK, result);
            finish();
            
//            String[] filePathColumn = {MediaStore.Images.Media.DATA};
//            Cursor cursor = getContentResolver().query(selectedImage, null, null, null, null);
//            
//            cursor.moveToFirst();
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            String filePath = cursor.getString(columnIndex);
//            cursor.close();
//
//            Log.w("------", "------------------");
//            Log.w("filepath", filePath);
//            if (bmp != null && !bmp.isRecycled()) {
//                bmp = null;
//            }
//            
//            bmp = BitmapFactory.decodeFile(filePath);
//            
//            pic.setImageBitmap(bmp);
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_choose_picture, menu);
        return true;
    }
}
