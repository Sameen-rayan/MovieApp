package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        movieList=new ArrayList<>();
        fetchMovies();
    }

    private void fetchMovies() {

        String url="https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&page=1&api_key=3fa9058382669f72dcb18fb405b7a831&gt;";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray= response.getJSONArray("results");

                            for(int i=0;i<jsonArray.length();i++){
                                try {
                                    JSONObject jsonObject= jsonArray.getJSONObject(i);
                                    String title=jsonObject.getString("title");
                                    String poster=jsonObject.getString("poster_path");
                                    String photo=jsonObject.getString("backdrop_path");
                                    Double rating=jsonObject.getDouble("vote_average");
                                    String date=jsonObject.getString("release_date");
                                    String overview=jsonObject.getString("overview");
                                    Integer id=jsonObject.getInt("id");

                                    Movie movie= new Movie(title,poster,rating,date,overview,id,photo);
                                    movieList.add(movie);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                            MovieAdapter adapter=new MovieAdapter(MainActivity.this,movieList);
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