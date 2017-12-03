package com.motionlaboratory.tododiet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.motionlaboratory.tododiet.Adapter.AdapterExercise;
import com.motionlaboratory.tododiet.Config.SessionPatient;
import com.motionlaboratory.tododiet.DatabaseConfig.ConnectionDb;
import com.motionlaboratory.tododiet.DatabaseConfig.RequestHandler;
import com.motionlaboratory.tododiet.Model.Patient;
import com.motionlaboratory.tododiet.Model.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodControlActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Task> taskList;
    private Button btn_add;
    private SessionPatient sessionPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_control);

        sessionPatient = new SessionPatient(this);
        HashMap<String,String> hashMap = sessionPatient.getPatientId();

        btn_add = (Button) findViewById(R.id.btn_tambah_food);
        btn_add.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.rec_food);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(FoodControlActivity.this));

        taskList = new ArrayList<>();
        loadDataFood(hashMap.get(sessionPatient.KEY_ID));
    }

    private void loadDataFood(final String id) {
        StringRequest stringRequest =  new StringRequest(Request.Method.POST, ConnectionDb.SHOW_PATIENT_FOOD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("patient");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject temp = jsonArray.getJSONObject(i);
                                Task task = new Task(
                                        temp.getString("type"),
                                        temp.getString("due_date"),
                                        temp.getInt("done")
                                );
                                taskList.add(task);
                            }
                            adapter = new AdapterExercise(taskList,FoodControlActivity.this);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(FoodControlActivity.this,response,Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FoodControlActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("patient_id",id);
                return hashMap;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(FoodControlActivity.this,AddFoodActivity.class));
    }
}
