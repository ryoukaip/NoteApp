package com.tdmuproj.noteapp;
public class Note {
    int id = -1;
    String title;
    String content;
    String date;
    public Note() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String description) {
        this.content = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Note(String title, String content, String date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }
    public Note (int id, String title, String content, String date){
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }
}




