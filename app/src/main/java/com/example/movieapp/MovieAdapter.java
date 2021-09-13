package com.example.movieapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private Context context;
    private List<Movie> movieList;

    public MovieAdapter(Context context,List<Movie> movies){
        this.context=context;
        movieList=movies;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieHolder holder, int position) {
        Movie movie=movieList.get(position);

        System.out.println("Json_response "+movie.getTitle() +" "+movie.getRating()+" "+movie.getPoster());
        holder.rating.setText(movie.getRating().toString());
        holder.title.setText(movie.getTitle());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+movie.getPoster()).into(holder.imageView);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("title",movie.getTitle());
                bundle.putDouble("vote_average",movie.getRating());
                bundle.putString("poster_path",movie.getPoster());
                bundle.putString("overview", movie.getOverview());
                bundle.putString("release_date", movie.getReleasedate());
                bundle.putInt("id",movie.getId());
                bundle.putString("backdrop_path",movie.getPhoto());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title,rating;
        ConstraintLayout constraintLayout;
        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.photo);
            title=itemView.findViewById(R.id.name);
            rating=itemView.findViewById(R.id.rating);
            constraintLayout=itemView.findViewById(R.id.main_layout);
            System.out.println(title+""+rating+""+R.id.photo);
        }
    }
}
