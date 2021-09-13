package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        RatingBar ratingBar=findViewById(R.id.ratingBar);
        ImageView imageView = findViewById(R.id.CoverPhoto);
        ImageView imageView2 = findViewById(R.id.PosterPhoto);
        TextView rating_tv = findViewById(R.id.mRating);
        TextView title_tv = findViewById(R.id.MovieTitle);
        TextView TextSummary= findViewById(R.id.OverView);
        TextView TextDate=findViewById(R.id.dateText);

        Bundle bundle = getIntent().getExtras();

        String mTitle = bundle.getString("title");
        String mPoster = bundle.getString("poster_path");
        String mOverView = bundle.getString("overview");
        String mDate=bundle.getString("release_date");
        String photo=bundle.getString("backdrop_path");
        double mRating = bundle.getDouble("vote_average");

        System.out.println(mTitle+"  "+mPoster+"  "+mRating);
        Glide.with(this.getApplicationContext()).load("https://image.tmdb.org/t/p/w500"+photo)
                .centerCrop()
                .into(imageView);
        Glide.with(this.getApplicationContext()).load("https://image.tmdb.org/t/p/w500"+mPoster)
                .centerCrop()
                .into(imageView2);
        System.out.println("https://image.tmdb.org/t/p/w500"+mPoster);
        rating_tv.setText(Double.toString(mRating));
        title_tv.setText(mTitle);
        TextSummary.setText(mOverView);
        TextDate.setText(mDate);
        ratingBar.setRating((float) mRating/2);

    }
}