package com.example.android.noteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
  public class CategrayAdapter extends RecyclerView.Adapter<CategrayAdapter.CategoryVh>{
    Context context ;
    List<Categraty> categoryList;

    public CategrayAdapter(Context context, List<Categraty> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view =LayoutInflater.from(context).inflate(R.layout., parent , false);

//        return new CategoryVh(view);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryVh holder, int position) {
        holder.setData(categoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }



class CategoryVh extends RecyclerView.ViewHolder{
    TextView  categor_name , categor_desc , categor_date;
    ImageView movie_image;
    public CategoryVh(@NonNull View itemView) {
        super(itemView);
//        movie_name = itemView.findViewById(R.id.movie_name);
//        movie_image = itemView.findViewById(R.id.movie_image);
//        movie_desc = itemView.findViewById(R.id.movie_desc);
//        movie_date = itemView.findViewById(R.id.movie_date);
    }

    public void setData(Categraty categraty) {

    }
    }
}
