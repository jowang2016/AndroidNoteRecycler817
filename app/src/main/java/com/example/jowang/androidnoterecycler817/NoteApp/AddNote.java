package com.example.jowang.androidnoterecycler817.NoteApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.jowang.androidnoterecycler817.NoteActivity;
import com.example.jowang.androidnoterecycler817.R;


public class AddNote extends AppCompatActivity {
    private EditText title,content;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        toolbar=(Toolbar)findViewById(R.id.tool_add);
        setSupportActionBar(toolbar);
        title=(EditText)findViewById(R.id.add_title);
        content=(EditText)findViewById(R.id.add_content);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.edit,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }else if (id==R.id.save){
            NoteDB noteDB=new NoteDB(AddNote.this);
            noteDB.open();
            noteDB.CreateNote(title.getText()+"",content.getText()+"");
            noteDB.close();
            Intent intent=new Intent(AddNote.this,NoteActivity.class);
            startActivity(intent);
    }
        return super.onOptionsItemSelected(item);
    }
}
