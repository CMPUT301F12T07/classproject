package com.CMPUT301F12T07.crowdsource;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class ViewLoggedActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_logged);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_logged, menu);
        return true;
    }
}
