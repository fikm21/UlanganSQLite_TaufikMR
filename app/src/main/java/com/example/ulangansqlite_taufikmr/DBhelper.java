package com.example.ulangansqlite_taufikmr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class DBhelper extends SQLiteOpenHelper {
    private static final int DB_Version=2;
    private static final String DB_Name="db_Note";
    private static final String TABLE_Name="tbl_note";
    private static final String KEY_id="id";
    private static final String KEY_title="title";
    private static final String KEY_isi="isi";
    private static final String KEY_tgl="tgl";

    public DBhelper (Context context){

        super(context, DB_Name, null, DB_Version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate="Create Table "+ TABLE_Name + "(" + KEY_id + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_title + " TEXT ," + KEY_isi + " TEXT ,"+
        KEY_tgl + " TEXT "+")";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
       String sql =  ("drop table if exists "+ TABLE_Name);
       db.execSQL(sql);
       onCreate(db);
    }

    public void insert(Note note){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(KEY_id, note.getId());
        values.put(KEY_title, note.getTitle());
        values.put(KEY_isi, note.getIsi());
        values.put(KEY_tgl, note.getTgl());
        db.insert(TABLE_Name, null, values);
    }

    public void update (Note note){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues value = new ContentValues();
        value.put(KEY_title, note.getTitle());
        value.put(KEY_isi, note.getIsi());
        value.put(KEY_tgl, note.getTgl());
        String whereClause = KEY_id + "='" + note.getId() + "'";
        db.update(TABLE_Name, value, whereClause, null);
    }

    public List<Note> getAllNotes(){
        ArrayList<Note> noteList = new ArrayList<Note>();
        SQLiteDatabase db  = getReadableDatabase();

        String[] colomns = {KEY_title,KEY_isi,KEY_tgl,KEY_id};
        Cursor cursor = db.query(TABLE_Name,colomns, null,null,null,null,null);

        while (cursor.moveToNext()){
            Note note = new Note();
            note.setTitle(cursor.getString(0));
            note.setIsi(cursor.getString(1));
            note.setTgl(cursor.getString(2));
            note.setId(cursor.getString(3));
            noteList.add(note);
        }

        return  noteList;

    }

    public void delete(Integer id){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = KEY_id+ "='" + id + "'";
        db.delete(TABLE_Name,whereClause,null);
    }
}
