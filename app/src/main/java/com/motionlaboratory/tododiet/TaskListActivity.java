package com.motionlaboratory.tododiet;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.motionlaboratory.tododiet.Adapter.AdapterTaskResult;
import com.motionlaboratory.tododiet.Config.SessionPatient;
import com.motionlaboratory.tododiet.DatabaseConfig.ConnectionDb;
import com.motionlaboratory.tododiet.DatabaseConfig.RequestHandler;
import com.motionlaboratory.tododiet.Model.TaskResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<TaskResult> taskResults;
    private SessionPatient sessionPatient;
    private RecyclerView.Adapter adapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        progressDialog = new ProgressDialog(this);
        recyclerView = (RecyclerView) findViewById(R.id.rec_task_result);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(TaskListActivity.this));

        sessionPatient = new SessionPatient(this);
        taskResults = new ArrayList<>();

        loadDataTaskResult(sessionPatient.getPatientId().get(sessionPatient.KEY_ID));
    }

    private void loadDataTaskResult(final String id) {
        progressDialog.setMessage("loading task resutl . . .");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConnectionDb.SHOW_TASK_RESULT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("task_result");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                int menit = jsonObject1.getInt("played_time") / 60 ;
                                int detik = jsonObject1.getInt("played_time") % 60 ;
                                String time;

                                if(menit < 10){
                                    time ="0"+String.valueOf(menit)+":"+String.valueOf(detik);
                                }else if(detik < 10){
                                    time =String.valueOf(menit)+":0"+String.valueOf(detik);
                                }else if( (detik < 10) && (menit<10)){
                                    time ="0"+String.valueOf(menit)+":0"+String.valueOf(detik);
                                }else if((menit < 10) && (detik < 10)){
                                    time ="0"+String.valueOf(menit)+":0"+String.valueOf(detik);
                                }
                                else{
                                    time =String.valueOf(menit)+":"+String.valueOf(detik);
                                }
                                taskResults.add(new TaskResult(
                                        String.valueOf(jsonObject1.getDouble("calories_loss")), time,
                                        String.valueOf(jsonObject1.getInt("distance")),
                                        String.valueOf(jsonObject1.getInt("jumping_count")),
                                        jsonObject1.getString("done_date")

                                ));
                                        }
                            adapter = new AdapterTaskResult(TaskListActivity.this,taskResults);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(TaskListActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> stringMap = new HashMap<>();
                stringMap.put("patient_id",id);
                return stringMap;
            }
        };
        RequestHandler.getInstance(TaskListActivity.this).addToRequestQueue(stringRequest);
    }
}
