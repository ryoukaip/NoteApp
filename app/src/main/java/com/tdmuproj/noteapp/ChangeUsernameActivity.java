package com.tdmuproj.noteapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChangeUsernameActivity extends AppCompatActivity{
    /// <global-variable>
    Button buttonOK;
    EditText editTextUsername;
    /// </global-variable>
    private void initViews(){
        buttonOK = findViewById(R.id.buttonOK);
        editTextUsername = findViewById(R.id.editTextUsername);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_username);
        initViews();
        onClickListeners(savedInstanceState);
    }
    private void onClickListeners(Bundle savedInstanceState){
        buttonOK.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString();
            if (username.isEmpty()){
                Toast.makeText(ChangeUsernameActivity.this, "Vui lòng nhập tên người dùng", Toast.LENGTH_SHORT).show();
            } else {
                ControlFlowSingleton.username = username;
                Toast.makeText(ChangeUsernameActivity.this, ControlFlowSingleton.username, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
