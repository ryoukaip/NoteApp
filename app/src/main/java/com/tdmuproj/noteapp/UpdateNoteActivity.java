package com.tdmuproj.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateNoteActivity extends AppCompatActivity {
    /// <global-variable>
    Button buttonCancelAdd, buttonConfirmUpdateNote;
    EditText editTextTextNoteTitle, editTextTextNoteContent;
    DBHelper dbHelper;
    NoteSingleton noteSingleton;
    /// </global-variable>
    private void initViews(){
        buttonCancelAdd = findViewById(R.id.buttonCancelAdd);
        buttonConfirmUpdateNote = findViewById(R.id.buttonConfirmUpdateNote);
        editTextTextNoteTitle = findViewById(R.id.editTextTextNoteTitle);
        editTextTextNoteContent = findViewById(R.id.editTextTextNoteContent);
        Intent intent = getIntent();
        if (intent != null){
            String title = intent.getStringExtra("title");
            String content = intent.getStringExtra("content");
            editTextTextNoteTitle.setText(title);
            editTextTextNoteContent.setText(content);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        initViews();
        onClickListeners(savedInstanceState);
    }
    private void onClickListeners(Bundle savedInstanceState){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        buttonCancelAdd.setOnClickListener(v -> {
            finish();
        });
        buttonConfirmUpdateNote.setOnClickListener(v -> {
            Note note = new Note();
            note.setTitle(editTextTextNoteTitle.getText().toString());
            note.setContent(editTextTextNoteContent.getText().toString());
            note.setDate(formatter.format(date));
            Intent intent = getIntent();
            if (intent != null)
            {
                String dateOld = intent.getStringExtra("date");
                //Toast.makeText(this, dateOld, Toast.LENGTH_SHORT).show();
                dbHelper = new DBHelper(this);
                dbHelper.deleteNoteFromDB(dateOld);
            }
            noteSingleton = NoteSingleton.getInstance();
            noteSingleton.setAddNoteOrNot(true);
            noteSingleton.setNote(note);
            dbHelper = new DBHelper(this);
            dbHelper.addNoteToDB(note.getTitle(), note.getContent(), note.getDate());
            finishActivityWithResult(note);
        });
    }
    private void finishActivityWithResult(Note note) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("title", note.getTitle());
        resultIntent.putExtra("content", note.getContent());
        resultIntent.putExtra("date", note.getDate());
        setResult(RESULT_OK, resultIntent);
        finish();
    }
    private void deleteOldNote(){
        Intent intent = getIntent();
        if (intent != null)
        {
            String date = intent.getStringExtra("date");
            dbHelper = new DBHelper(this);
            dbHelper.deleteNoteFromDB(date);
        }
    }
}
