package com.tdmuproj.noteapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePasswordActivity extends AppCompatActivity {
    /// <global-variable>
    Button buttonExit;
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    Button buttonDel, buttonOK;
    EditText editTextNumberPassword;
    DBHelperPassword dbHelperPassword;
    /// </global-variable>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initViews();
        onClickListeners();
    }
    private void initViews(){
        /// <init-views>
        buttonExit = findViewById(R.id.buttonExit);
        editTextNumberPassword = findViewById(R.id.editTextUsername);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonDel = findViewById(R.id.buttonDel);
        buttonOK = findViewById(R.id.buttonOK);
        /// </init-views>
    }
    private void onClickListeners(){
        /// <on-click-listeners>
        buttonExit.setOnClickListener(v -> {
            this.finish();
        });
        buttonExit.setOnLongClickListener(v -> {
            // toast to show the password
            Toast.makeText(this, editTextNumberPassword.getText().toString(), Toast.LENGTH_SHORT).show();
            return true;
        });
        /// <number buttons>
        button0.setOnClickListener(v -> {
            editTextNumberPassword.append("0-");
            String password = editTextNumberPassword.getText().toString();
            if (password.length() > 0){
                editTextNumberPassword.setText(password.substring(0, password.length() - 1));
            }
        });
        button1.setOnClickListener(v -> {
            editTextNumberPassword.append("1-");
            String password = editTextNumberPassword.getText().toString();
            if (password.length() > 0){
                editTextNumberPassword.setText(password.substring(0, password.length() - 1));
            }
        });
        button2.setOnClickListener(v -> {
            editTextNumberPassword.append("2-");
            String password = editTextNumberPassword.getText().toString();
            if (password.length() > 0){
                editTextNumberPassword.setText(password.substring(0, password.length() - 1));
            }
        });
        button3.setOnClickListener(v -> {
            editTextNumberPassword.append("3-");
            String password = editTextNumberPassword.getText().toString();
            if (password.length() > 0){
                editTextNumberPassword.setText(password.substring(0, password.length() - 1));
            }
        });
        button4.setOnClickListener(v -> {
            editTextNumberPassword.append("4-");
            String password = editTextNumberPassword.getText().toString();
            if (password.length() > 0){
                editTextNumberPassword.setText(password.substring(0, password.length() - 1));
            }
        });
        button5.setOnClickListener(v -> {
            editTextNumberPassword.append("5-");
            String password = editTextNumberPassword.getText().toString();
            if (password.length() > 0){
                editTextNumberPassword.setText(password.substring(0, password.length() - 1));
            }
        });
        button6.setOnClickListener(v -> {
            editTextNumberPassword.append("6-");
            String password = editTextNumberPassword.getText().toString();
            if (password.length() > 0){
                editTextNumberPassword.setText(password.substring(0, password.length() - 1));
            }
        });
        button7.setOnClickListener(v -> {
            editTextNumberPassword.append("7-");
            String password = editTextNumberPassword.getText().toString();
            if (password.length() > 0){
                editTextNumberPassword.setText(password.substring(0, password.length() - 1));
            }
        });
        button8.setOnClickListener(v -> {
            editTextNumberPassword.append("8-");
            String password = editTextNumberPassword.getText().toString();
            if (password.length() > 0){
                editTextNumberPassword.setText(password.substring(0, password.length() - 1));
            }
        });
        button9.setOnClickListener(v -> {
            editTextNumberPassword.append("9-");
            String password = editTextNumberPassword.getText().toString();
            if (password.length() > 0){
                editTextNumberPassword.setText(password.substring(0, password.length() - 1));
            }
        });

        /// </number buttons>
        buttonDel.setOnClickListener(v -> {
            String password = editTextNumberPassword.getText().toString();
            if (password.length() > 0){
                editTextNumberPassword.setText(password.substring(0, password.length() - 1));
            }
        });
        buttonOK.setOnClickListener(v -> {
            String password = editTextNumberPassword.getText().toString();
            if (password.length() == 0){
                Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                dbHelperPassword = new DBHelperPassword(this);
                dbHelperPassword.updatePasswordToDB(password);
                Toast.makeText(this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                this.finish();
            }
        });
        /// </on-click-listeners>
    }

}
