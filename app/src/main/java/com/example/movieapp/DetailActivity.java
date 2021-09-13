package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {


    private List<Cast> castList;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    int id;

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

        id = bundle.getInt("id");

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


        recyclerView=findViewById(R.id.recyclerview2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        castList=new ArrayList<>();
        fetchCast(id);

    }


    private void fetchCast(int id) {

        String url="https://api.themoviedb.org/3/movie/"+id+"/credits?api_key=3fa9058382669f72dcb18fb405b7a831";

        System.out.println("Test  "+url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("Test  "+response);
                            JSONArray jsonArray= response.getJSONArray("cast");

                            for(int i=0;i<jsonArray.length();i++){
                                try {
                                    JSONObject jsonObject= jsonArray.getJSONObject(i);
                                    String name=jsonObject.getString("name");
                                    String photo=jsonObject.getString("profile_path");


                                    Cast cast= new Cast(name,photo);
                                    castList.add(cast);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                            System.out.println("Test  "+castList.size());
                            CastAdapter adapter=new CastAdapter(DetailActivity.this,castList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });



        requestQueue.add(jsonObjectRequest);
    }
}