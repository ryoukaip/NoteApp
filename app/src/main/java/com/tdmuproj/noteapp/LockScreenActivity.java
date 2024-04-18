package com.tdmuproj.noteapp;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LockScreenActivity extends AppCompatActivity {
    /// <global-variable>
    Button buttonExit;
    EditText editTextNumberPassword;
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
        editTextNumberPassword = findViewById(R.id.editTextNumberPassword);
        editTextNumberPassword.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        editTextNumberPassword.setFilters(new InputFilter[]{new DotPasswordInputFilter()});
    }
    private void onClickListeners(){
        buttonExit.setOnClickListener(v -> {
            this.finish();
        });
    }
}
