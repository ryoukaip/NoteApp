package com.tdmuproj.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    /// <global-variable>
    Button buttonGoBack, changePasswordButton, enableLockButton, nameButton;
    DBHelperPassword dbHelperPassword;
    TextView textViewWelcome,textViewUsername;

    /// </global-variable>
    private void initViews(){
        buttonGoBack = findViewById(R.id.buttonGoBack);
        changePasswordButton = findViewById(R.id.changePasswordButton);
        enableLockButton = findViewById(R.id.enableLockButton);
        nameButton = findViewById(R.id.nameButton);
        dbHelperPassword = new DBHelperPassword(SettingsActivity.this);
        dbHelperPassword.getBooleanFromDB();
        if (ControlFlowSingleton.isPasswordLocked){
            enableLockButton.setText("Hủy khóa ứng dụng");
        } else {
            enableLockButton.setText("Kích hoạt khóa ứng dụng");
        }
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
        changePasswordButton.setOnClickListener(v -> {
            // intent to ChangePasswordActivity
            Intent intent = new Intent(SettingsActivity.this, ChangePasswordActivity.class);
            startActivity(intent);
        });
        enableLockButton.setOnClickListener(v -> {
            dbHelperPassword = new DBHelperPassword(SettingsActivity.this);
            // change to true if it is false, and vice versa
            dbHelperPassword.getBooleanFromDB();
            if (ControlFlowSingleton.isPasswordLocked){
                dbHelperPassword.updateBooleanToDB("false");
                enableLockButton.setText("Kích hoạt khóa ứng dụng");
            } else {
                dbHelperPassword.updateBooleanToDB("true");
                enableLockButton.setText("Hủy khóa ứng dụng");
            }
        });
        nameButton.setOnClickListener(v -> {
            // intent to ChangeUsernameActivity
            Intent intent = new Intent(SettingsActivity.this, ChangeUsernameActivity.class);
            startActivity(intent);
        });
    }
    private void updateUsername(){
        textViewUsername.setText(ControlFlowSingleton.username);
    }
}
