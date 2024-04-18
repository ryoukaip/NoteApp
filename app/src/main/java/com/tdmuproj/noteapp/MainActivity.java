package com.tdmuproj.noteapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /// <global-variable>
    ImageButton imageButtonSettings;
    ScrollView scrollViewMain;
    LinearLayout notesContainer;
    TextView textViewInstruction, textViewAppTitle;
    Button buttonAddNote;
    DBHelper dbHelper;
    NoteSingleton noteSingleton = NoteSingleton.getInstance();
    List<Note> noteList = new ArrayList<>();
    private static final int ADD_NOTE_REQUEST = 1;
    private static final int PASSWORD_REQUEST = 2;
    /// </global-variable>
    private void initViews(){
        imageButtonSettings = findViewById(R.id.imageButtonSettings);
        scrollViewMain = findViewById(R.id.scrollViewMain);
        notesContainer = findViewById(R.id.notesContainer);
        textViewInstruction = findViewById(R.id.textViewInstruction);
        buttonAddNote = findViewById(R.id.buttonAddNote);
        textViewAppTitle = findViewById(R.id.textViewAppTitle);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ControlFlowSingleton.isPasswordLocked){
            Intent intent = new Intent(MainActivity.this, LockScreenActivity.class);
            startActivityForResult(intent, PASSWORD_REQUEST);
        }

        setContentView(R.layout.activity_main);
        initViews();
        onClickListeners(savedInstanceState);
        loadNoteFromDB();
        ControlFlowSingleton.isAppInstanceRunning = true;
    }
    private void onClickListeners(Bundle savedInstanceState){
        buttonAddNote.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivityForResult(intent, ADD_NOTE_REQUEST);
        });
        imageButtonSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        /// <testing>
        textViewAppTitle.setOnClickListener(v -> {
            createNoteView(new Note("testing", "testingContent", "testDate"));
        });
        // on Long Click
        textViewAppTitle.setOnLongClickListener(v -> {
            notesContainer.removeAllViews();
            setTextViewInstructionState();
            return true;
        });
        /// </testing>
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            if (noteSingleton.isAddNoteOrNot()){
                createNoteView(noteSingleton.getNote());
            }
        } else if (requestCode == PASSWORD_REQUEST && resultCode == RESULT_OK){

        }
    }
    private void createNoteView(Note note){
            View noteView = getLayoutInflater().inflate(R.layout.note_item, null, false);
            TextView titleTextView = noteView.findViewById(R.id.titleTextView);
            TextView contentTextView = noteView.findViewById(R.id.contentTextView);
            TextView dateTextView = noteView.findViewById(R.id.dateTextView);

            titleTextView.setText(note.getTitle());
            contentTextView.setText(note.getContent());
            dateTextView.setText(note.getDate());

            noteView.setOnLongClickListener(v -> {
                // show toast of the dateTime of the note
                // Toast.makeText(this, note.getDate(), Toast.LENGTH_SHORT).show();
                PopupMenu popupMenu = new PopupMenu(this, noteView);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_note_item, popupMenu.getMenu());
                popupMenu.show();
                // android:id="@+id/menu_update_note"
                //android:id="@+id/menu_delete_note"
                popupMenu.setOnMenuItemClickListener(item -> {
                    int itemId = item.getItemId();
                    if (itemId == R.id.menu_update_note) {
                        Intent intent = new Intent(MainActivity.this, UpdateNoteActivity.class);
                        intent.putExtra("title", note.getTitle());
                        intent.putExtra("content", note.getContent());
                        intent.putExtra("date", note.getDate());
                        startActivityForResult(intent, ADD_NOTE_REQUEST);
                        return true;
                    } else if (itemId == R.id.menu_delete_note) {
                        AlertDialog alertDialog = new AlertDialog.Builder(this)
                                .setTitle("Delete this note")
                                .setMessage("Are you sure you want to delete this note?")
                                .setPositiveButton("Delete", (dialog, which) -> {
                                    deleteNote(note.getDate());
                                    notesContainer.removeView(noteView);
                                    setTextViewInstructionState();
                                })
                                .setNegativeButton("Cancel", null)
                                .create();
                        alertDialog.show();
                        return true;
                    }
                    return false;
                });
                return true;
            });
            noteView.setOnClickListener(v -> {
                // toast "Nhấn giữ để sửa hoặc xóa"
                Toast.makeText(this, "Nhấn giữ để sửa hoặc xóa", Toast.LENGTH_SHORT).show();
            });

            notesContainer.addView(noteView, 0);
            setTextViewInstructionState();
    }
    private void setTextViewInstructionState(){
        // if notesContainer is empty
        if (notesContainer.getChildCount() == 0){
            textViewInstruction.setVisibility(View.VISIBLE);
        } else {
            textViewInstruction.setVisibility(View.INVISIBLE);
        }
    }
    private void loadNoteFromDB(){
        dbHelper = new DBHelper(this);
        dbHelper.createNoteListFromDB();
        noteList = noteSingleton.getNoteList();
        for (Note note : noteList){
            createNoteView(note);
        }
    }
    private void deleteNote(String dateTime){
        dbHelper = new DBHelper(this);
        dbHelper.deleteNoteFromDB(dateTime);
        // delete note from noteList
        noteList.removeIf(note -> note.getDate().equals(dateTime));
    }
}