package com.example.android.noteapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devs.vectorchildfinder.VectorChildFinder;
import com.devs.vectorchildfinder.VectorDrawableCompat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteVh> {
    public static final String NOTE_TRANSFER = "NOTE_TRANSFER";
    public static final String NOTE_POSITION = "NOTE_POSITION";
    Context context;
    List<Note> noteList;


    public NotesAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NotesAdapter.NoteVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_note, parent, false);
        return new NoteVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteVh holder, int position) {
        holder.setData(noteList.get(position), position, holder);


    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class NoteVh extends RecyclerView.ViewHolder {
        TextView title, date, describtion;
        ImageView title_img;
        View itemCard;

        public NoteVh(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_name);
            date = itemView.findViewById(R.id.note_date);
            describtion = itemView.findViewById(R.id.note_desc);
            title_img = itemView.findViewById(R.id.title_img);
            itemCard = itemView;

        }

        public void setData(Note note, final int position, NoteVh holder) {

            VectorChildFinder vector = new VectorChildFinder(context, R.drawable.ic_oval_6, holder.title_img);
            VectorDrawableCompat.VFullPath path1 = vector.findPathByName("path1");
            path1.setFillColor(note.getColor());
            title.setText(note.getTitle());
            DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(note.getCreatedAt());
            date.setText(formatter.format(calendar.getTime()));
            describtion.setText(note.getBody());
            holder.itemCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), EditNote.class);
                    intent.putParcelableArrayListExtra(NOTE_TRANSFER, (ArrayList) noteList);
                    intent.putExtra(NOTE_POSITION, position);
                    intent.putExtra("old", true);
                    context.startActivity(intent);
                }
            });


        }
    }


}