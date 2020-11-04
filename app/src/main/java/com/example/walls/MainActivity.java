package com.example.walls;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerview;
    private Adaptercls adaptercls;
    private ArrayList<Modelclass> model_array;
    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerview = findViewById(R.id.recyclerview);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        model_array = new ArrayList<>();//creating a new array list
        mRequestQueue = Volley.newRequestQueue(this);
        parseJson();

    }

    private void parseJson() {
        String url = "https://pixabay.com/api/?key=17652090-729b25d7cf18ddaf2ed3a7145";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("datainal", response.toString());
                        try {
                            JSONArray jsonarray = response.getJSONArray("hits");
                            for (int i = 0; i < jsonarray.length(); i++) {
                                JSONObject hit = jsonarray.getJSONObject(i);//to get each object out of the array inside the json object
                                int Likes = hit.getInt("likes");
                                String url = hit.getString("webformatURL");
                                model_array.add(new Modelclass(url, Likes));//these two values will be fetched everytime
                                adaptercls = new Adaptercls(MainActivity.this, model_array);//passing the array to the adapter
                                mRecyclerview.setAdapter(adaptercls);
                            }
                        } catch (JSONException e) {


                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(jsonObjectRequest);
    }
}