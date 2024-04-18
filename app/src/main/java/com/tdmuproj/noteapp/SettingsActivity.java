package com.tdmuproj.noteapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    /// <global-variable>
    Button buttonGoBack;
    /// </global-variable>
    private void initViews(){
        buttonGoBack = findViewById(R.id.buttonGoBack);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initViews();
        onClickListeners(savedInstanceState);
    }
    private void onClickListeners(Bundle savedInstanceState){
        buttonGoBack.setOnClickListener(v -> {
            finish();
        });
    }
}
