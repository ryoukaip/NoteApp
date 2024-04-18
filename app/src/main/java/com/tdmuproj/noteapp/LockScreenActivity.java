package com.tdmuproj.noteapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LockScreenActivity extends AppCompatActivity {
    /// <global-variable>
    Button buttonExit;
    /// </global-variable>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);
        initViews();
        onClickListeners();
    }
    private void initViews(){
        buttonExit = findViewById(R.id.buttonExit);
    }
    private void onClickListeners(){
        buttonExit.setOnClickListener(v -> {
            this.finish();
        });
    }
}
