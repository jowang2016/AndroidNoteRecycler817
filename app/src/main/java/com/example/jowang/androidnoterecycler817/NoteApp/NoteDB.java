package com.example.jowang.androidnoterecycler817.NoteApp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jowang on 16/8/1.
 */
public class NoteDB {
    private static final String DATABASE_NAME="note.db";
    private static final int DATABASE_VERSION=1;
    public static final String NOTE_TABLE="note";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_TITLE="_title";
    public static final String COLUMN_MESSAGE="_message";
    private String[] allcolumns={COLUMN_ID,COLUMN_TITLE,COLUMN_MESSAGE};
    public static final String CREATE_DB="create table "+NOTE_TABLE+" ( "
            +COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_TITLE+" TEXT NOT NULL, "+COLUMN_MESSAGE+" TEXT NOT NULL"
            +");";
    private SQLiteDatabase database;
    private Context context;
    private static class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_DB);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("drop table if exists"+NOTE_TABLE);
            onCreate(sqLiteDatabase);
        }
    }
    private DBHelper dbHelper;
    public NoteDB (Context ctx){
        context=ctx;
    }
    public NoteDB open() throws SQLException{
        dbHelper=new DBHelper(context);
        database=dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }
    public Note CreateNote(String title,String content){
        ContentValues values=new ContentValues();
        values.put(COLUMN_TITLE,title);
        values.put(COLUMN_MESSAGE,content);
        long insertID=database.insert(NOTE_TABLE,null,values);
        Cursor cursor=database.query(NOTE_TABLE,allcolumns,COLUMN_ID+"="+insertID,null,null,null,null);
        cursor.moveToFirst();
        Note newNote=cursorToNote(cursor);
        cursor.close();
        return newNote;
    }

    private Note cursorToNote(Cursor cursor) {
        Note newNote=new Note(cursor.getString(1),cursor.getString(2));
        return newNote;
    }
    //    public ArrayList<Note> getAllNotes(){
//        ArrayList<Note> notes=new ArrayList<>();
//        Cursor cursor=database.query(NOTE_TABLE,allcolumns,null,null,null,null,null);
//        for (cursor.moveToLast();!cursor.isBeforeFirst();cursor.moveToPrevious()){
//            Note note=cursorToNote(cursor);
//            notes.add(note);
//        }
//        cursor.close();
//        return notes;
//    }
    public Cursor getAllNotes()
    {
        String[] columns={COLUMN_ID,COLUMN_TITLE,COLUMN_MESSAGE};

        return database.query(NOTE_TABLE,columns,null,null,null,null,null);
    }
    //    public long Delete(int id)
//    {
//        try
//        {
//
//            return database.delete(NOTE_TABLE,COLUMN_ID+" ="+id,null);
//
//        }catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//
//
//        return 0;
//    }
    public long Delete(int idtodelete){
        return database.delete(NOTE_TABLE,COLUMN_ID+" =?",new String[]{String.valueOf(idtodelete)});
    }
    public long updateNotes(long id,String title,String messae){
        ContentValues values=new ContentValues();
        values.put(COLUMN_TITLE,title);
        values.put(COLUMN_MESSAGE,messae);
        return database.update(NOTE_TABLE,values,COLUMN_ID+"="+id,null);
    }

}
