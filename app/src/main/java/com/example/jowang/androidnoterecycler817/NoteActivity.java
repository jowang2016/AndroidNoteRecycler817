package com.example.jowang.androidnoterecycler817;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import com.example.jowang.androidnoterecycler817.NoteApp.AddNote;
import com.example.jowang.androidnoterecycler817.NoteApp.Detail;
import com.example.jowang.androidnoterecycler817.NoteApp.Note;
import com.example.jowang.androidnoterecycler817.NoteApp.NoteAdapter;
import com.example.jowang.androidnoterecycler817.NoteApp.NoteDB;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Note> notes=new ArrayList<>();
    private NoteAdapter adapter;
    public static final String TITLE="title";
    public static final String CONTENT="content";
    public static final String ID="id";
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.getBackground().setAlpha(200);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new NoteAdapter(this,notes);
        adapter.setClick(new NoteAdapter.myItemClick() {
            @Override
            public void onClick(View view, int i) {
                Intent intent=new Intent(NoteActivity.this,Detail.class);
                intent.putExtra(NoteActivity.TITLE,notes.get(i).getTitle());
                intent.putExtra(NoteActivity.CONTENT,notes.get(i).getContent());
                intent.putExtra(NoteActivity.ID,notes.get(i).getId());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int i) {
            }
        });
        recyclerView.setAdapter(adapter);
        retrieve();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.createnote,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.create){
            Intent intent=new Intent(this,AddNote.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void retrieve() {
        NoteDB db = new NoteDB(this);

        //OPEN
        db.open();

        notes.clear();

        //SELECT
//        for (Note note:db.getAllNotes()){
//            notes.add(note);
//        }
        Cursor c = db.getAllNotes();

//        LOOP THRU THE DATA ADDING TO ARRAYLIST
        while (c.moveToNext()) {
            int id = c.getInt(0);
            String name = c.getString(1);
            String pos = c.getString(2);

            //CREATE PLAYER
            Note p = new Note(name, pos, id);

            //ADD TO PLAYERS
            notes.add(p);
        }
//        for (c.moveToLast();!c.isBeforeFirst();c.moveToPrevious()){
//            Note newNote=new Note(c.getString(1),c.getString(2));
//            notes.add(newNote);
//        }

        //SET ADAPTER TO RV
        if (!(notes.size() < 1)) {
            recyclerView.setAdapter(adapter);
        }
    }
}
