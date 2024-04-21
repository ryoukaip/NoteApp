package com.tdmuproj.noteapp;

import java.util.ArrayList;
import java.util.List;

public class NoteSingleton {
    private static NoteSingleton instance;
    private Note note;
    private boolean AddNoteOrNot;
    private String date;
    private List<Note> noteList = new ArrayList<>();
    private NoteSingleton(){

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static synchronized NoteSingleton getInstance(){
        if (instance == null){
            instance = new NoteSingleton();
        }
        return instance;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public boolean isAddNoteOrNot() {
        return AddNoteOrNot;
    }

    public void setAddNoteOrNot(boolean addNoteOrNot) {
        AddNoteOrNot = addNoteOrNot;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

}
