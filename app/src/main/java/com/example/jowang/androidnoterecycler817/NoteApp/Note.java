package com.example.jowang.androidnoterecycler817.NoteApp;

/**
 * Created by jowang on 16/8/10.
 */
public class Note {
    private String title,content;
    private int id;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.id=0;
    }

    public Note(String title, String content, int id) {
        this.title = title;
        this.content = content;
        this.id=id;
    }

    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public int getId() {
        return id;
    }
}
