package com.tdmuproj.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {
    /// <global-variable>
    Button buttonCancelAdd, buttonConfirmAddNote;
    EditText editTextTextNoteTitle, editTextTextNoteContent;
    DBHelper dbHelper;
    NoteSingleton noteSingleton;
    /// </global-variable>
    private void initViews(){
        buttonCancelAdd = findViewById(R.id.buttonCancelAdd);
        buttonConfirmAddNote = findViewById(R.id.buttonConfirmUpdateNote);
        editTextTextNoteTitle = findViewById(R.id.editTextTextNoteTitle);
        editTextTextNoteContent = findViewById(R.id.editTextTextNoteContent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initViews();
        onClickListeners(savedInstanceState);
    }
    private void onClickListeners(Bundle savedInstanceState){
        buttonCancelAdd.setOnClickListener(v -> {
            finish();
        });
        buttonConfirmAddNote.setOnClickListener(v -> {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();

            Note note = new Note();
            note.setTitle(editTextTextNoteTitle.getText().toString());
            note.setContent(editTextTextNoteContent.getText().toString());
            note.setDate(formatter.format(date));
            noteSingleton = NoteSingleton.getInstance();
            noteSingleton.setAddNoteOrNot(true);
            noteSingleton.setNote(note);


            dbHelper = new DBHelper(this);
            dbHelper.addNoteToDB(note.getTitle(), note.getContent(), note.getDate());
            finishActivityWithResult(note);
        });
    }
    // Inside AddNoteActivity
    private void finishActivityWithResult(Note note) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("title", note.getTitle());
        resultIntent.putExtra("content", note.getContent());
        resultIntent.putExtra("date", note.getDate());
        setResult(RESULT_OK, resultIntent);
        finish();
    }

}
