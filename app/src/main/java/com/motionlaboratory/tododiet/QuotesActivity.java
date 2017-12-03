package com.motionlaboratory.tododiet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.motionlaboratory.tododiet.Adapter.AdapterQoute;
import com.motionlaboratory.tododiet.Config.SessionManager;
import com.motionlaboratory.tododiet.Config.SessionPatient;
import com.motionlaboratory.tododiet.DatabaseConfig.ConnectionDb;
import com.motionlaboratory.tododiet.DatabaseConfig.RequestHandler;
import com.motionlaboratory.tododiet.Model.Quote;
import com.motionlaboratory.tododiet.Model.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuotesActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Quote> quoteList;
    private ImageButton btn_add;
    private SessionManager sessionManager;
    private ProgressDialog progressDialog;
    private EditText et_sentence;
    private SessionPatient sessionPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        progressDialog = new ProgressDialog(this);

        sessionManager = new SessionManager(this);
        sessionPatient = new SessionPatient(this);

        HashMap<String,String> hashMap1 = sessionPatient.getPatientId();


        btn_add = (ImageButton) findViewById(R.id.btn_add_quote);
        btn_add.setOnClickListener(this);
        et_sentence = (EditText) findViewById(R.id.et_sentence_quote);

        recyclerView = (RecyclerView) findViewById(R.id.rec_quote);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(QuotesActivity.this));

        quoteList = new ArrayList<>();
        loadDataQuotes(hashMap1.get(sessionPatient.KEY_ID));
    }

    private void loadDataQuotes(final String id) {
        progressDialog.setMessage("loading quotes");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                ConnectionDb.SHOW_QUOTE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("quote");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        quoteList.add(new Quote(jsonObject1.getString("sentence"),"Yourself"));
                    }
                        adapter = new AdapterQoute(QuotesActivity.this,quoteList);
                        recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(QuotesActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> stringMap = new HashMap<>();
                stringMap.put("patient_id",id);
                return stringMap;
            }
        };
        RequestHandler.getInstance(QuotesActivity.this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        HashMap<String,String> hashMap = sessionManager.getDoctorDetails();
        HashMap<String,String> hashMap1 = sessionPatient.getPatientId();
        insertQuotes(hashMap1.get(sessionPatient.KEY_ID),hashMap.get(sessionManager.KEY_ID));

//        startActivity(new Intent(QuotesActivity.this,QuotesActivity.class));
//        finish();
    }

    private void insertQuotes(final String patient_id, final String doctor_id) {
        progressDialog.setMessage("adding quotes");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConnectionDb.INSERT_QUOTE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            Toast.makeText(QuotesActivity.this,new JSONObject(response).getString("message"),Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(QuotesActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> stringMap = new HashMap<>();
                stringMap.put("doctor_id",doctor_id);
                stringMap.put("patient_id",patient_id);
                stringMap.put("sentence",et_sentence.getText().toString());
                return stringMap;
            }
        };

        RequestHandler.getInstance(QuotesActivity.this).addToRequestQueue(stringRequest);
    }
}
