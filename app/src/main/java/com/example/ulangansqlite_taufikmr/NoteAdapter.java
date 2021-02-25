package com.example.ulangansqlite_taufikmr;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    Context context;
    OnUserClickListener listener;
    List<Note> noteList = new ArrayList<>();

    public NoteAdapter(Context context, List<Note> noteList){
        this.context = context;
        this.noteList = noteList;
    }

    public NoteAdapter(Context context, List<Note> noteList,  OnUserClickListener listener){
        this.context = context;
        this.noteList = noteList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notes_list,parent,false);
        NoteViewHolder nvh =new NoteViewHolder(view);
        return nvh;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder,final int position) {
        holder.tvTgl.setText(noteList.get(position).getTgl());
        holder.tvTitle.setText(noteList.get(position).getTitle());
        holder.tvContent.setText(noteList.get(position).getIsi());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(context, AddNote.class);
                in.putExtra(AddNote.EXTRA_tgl,noteList.get(position).getTgl());
                in.putExtra(AddNote.EXTRA_id, noteList.get(position).getId());
                in.putExtra(AddNote.EXTRA_title,noteList.get(position).getTitle());
                in.putExtra(AddNote.EXTRA_isi, noteList.get(position).getIsi());
                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public interface OnUserClickListener{
        void onUserClick(Note currentPerson, String action);
    }




    public class NoteViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout item;
        TextView tvTitle, tvContent, tvTgl;
        public NoteViewHolder(@NonNull View interview){
            super(interview);
            tvTgl = interview.findViewById(R.id.tvNoteTgl);
            tvTitle = interview.findViewById(R.id.tvNotetitle);
            tvContent = interview.findViewById(R.id.tvNoteIsi);
            item = interview.findViewById(R.id.itemNote);

        }
    }
}
