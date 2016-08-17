package com.example.jowang.androidnoterecycler817.NoteApp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.jowang.androidnoterecycler817.R;

import java.util.ArrayList;

/**
 * Created by jowang on 16/8/10.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyHolder> {
    Context context;
    private ArrayList<Note> notes=new ArrayList<>();
    public NoteAdapter(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes=notes;
    }
    public static interface myItemClick{
        public void onClick(View view, int i);
        public void onLongClick(View view, int i);
    }
    private myItemClick click;
    public void setClick(myItemClick click){
        this.click=click;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_data,parent,false);
        MyHolder mh=new MyHolder(v);
        return mh;
    }
    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        final int x=notes.size()-1;
        final int y=x-position;
        holder.texttitle.setText(notes.get(y).getTitle());
        holder.textcontent.setText(notes.get(y).getContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.onClick(view,y);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                click.onLongClick(view,y);
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {
        return notes.size();
    }
    public static class MyHolder extends RecyclerView.ViewHolder{
        TextView texttitle,textcontent;
        CardView cardView;
        public MyHolder(View itemView) {
            super(itemView);
            texttitle=(TextView)itemView.findViewById(R.id.note_title);
            textcontent=(TextView)itemView.findViewById(R.id.note_content);
            cardView=(CardView)itemView.findViewById(R.id.cardview);
        }
    }
}
