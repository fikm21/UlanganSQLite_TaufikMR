package com.example.ulangansqlite_taufikmr;

public class Note {
    private String id;
    private String title;
    private String isi;
    private String tgl;

    public Note(String title, String note, String tgl){
        this.title = title;
        this.isi = note;
        this.id = tgl+title;
    }

    public Note(String title,String note, String tgl, String id){
        this.title = title;
        this.isi = note;
        this.tgl = tgl;
        this.id = id;
    }

    public Note(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }
}
