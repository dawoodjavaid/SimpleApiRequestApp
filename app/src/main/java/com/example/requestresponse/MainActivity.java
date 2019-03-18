package com.example.requestresponse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    Button GetResponseBtn ;
    TextView ResponseTextView;
    RequestQueue requestQueue;
    String Server_url ;
    MyJsonResponse myJsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Server_url ="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";
        GetResponseBtn = findViewById(R.id.get_response_btn);
        ResponseTextView = findViewById(R.id.my_text_TV);
        myJsonResponse = new MyJsonResponse();


        requestQueue = Volley.newRequestQueue(this);

        GetResponseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest stringRequest = new StringRequest(Request.Method.GET,Server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ResponseTextView.setText(response);
                        requestQueue.stop();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        ResponseTextView.setText("Something Went Wrong...");
                        error.printStackTrace();
                        requestQueue.stop();

                    }
                });

                  requestQueue.add(stringRequest);
            }
        });


    }
}
