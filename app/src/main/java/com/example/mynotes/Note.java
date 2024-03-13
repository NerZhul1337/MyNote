package com.example.mynotes;

public class Note {
    private long id;
    private String note_text;

    Note(long id, String note_text){
        this.id = id;
        this.note_text = note_text;
    }
    public long getId() {

        return id;
    }
    public String getNoteText() {

        return note_text;
    }

    public void setNoteText(String note_text) {

        this.note_text = note_text;
    }

    @Override
    public String toString() {

        return this.note_text ;
    }

}
