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


public class Edit extends AppCompatActivity {
    private EditText title,content;
    private int position;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        title=(EditText)findViewById(R.id.edit_title);
        content=(EditText)findViewById(R.id.edit_content);
        Intent intent=getIntent();
        title.setText(intent.getStringExtra("title"));
        content.setText(intent.getStringExtra("content"));

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
            Intent intent2=getIntent();
            position=intent2.getIntExtra("id",0);
            Intent intent1=new Intent(Edit.this,NoteActivity.class);
            NoteDB db=new NoteDB(Edit.this);
            db.open();
            db.updateNotes((long)position,title.getText().toString(),content.getText().toString());
            db.close();
            startActivity(intent1);
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
////        Edit.this.finish();
//
//    }
}
