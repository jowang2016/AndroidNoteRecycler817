package com.example.jowang.androidnoterecycler817.NoteApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.jowang.androidnoterecycler817.NoteActivity;
import com.example.jowang.androidnoterecycler817.R;


public class Detail extends AppCompatActivity {
    TextView textView1,textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar=(Toolbar)findViewById(R.id.detail_tool);
        setSupportActionBar(toolbar);
        textView1=(TextView)findViewById(R.id.viewd);
        textView2=(TextView)findViewById(R.id.viewdd);
        Intent i=getIntent();
        final String name=i.getExtras().getString(NoteActivity.TITLE);
        final String pos=i.getExtras().getString(NoteActivity.CONTENT);

        textView1.setText(name);
        textView2.setText(pos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.delete,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        Intent i=getIntent();
        final int position=i.getExtras().getInt(NoteActivity.ID);
        if (id==R.id.delete){

            NoteDB db=new NoteDB(Detail.this);
            db.open();
            db.Delete(position);
            db.close();
            Intent intent=new Intent(Detail.this,NoteActivity.class);
            startActivity(intent);
        }else if (id==R.id.edit){
            Intent intent=new Intent(Detail.this,Edit.class);
            intent.putExtra("title",textView1.getText().toString());
            intent.putExtra("content",textView2.getText().toString());
            intent.putExtra("id",position);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
