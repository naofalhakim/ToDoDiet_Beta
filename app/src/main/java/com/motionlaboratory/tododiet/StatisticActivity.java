package com.motionlaboratory.tododiet;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.motionlaboratory.tododiet.Config.SessionPatient;
import com.motionlaboratory.tododiet.DatabaseConfig.ConnectionDb;
import com.motionlaboratory.tododiet.DatabaseConfig.RequestHandler;
import com.motionlaboratory.tododiet.Model.Statistic;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StatisticActivity extends AppCompatActivity {

    private TextView txt_level, txt_gold,txt_weight, txt_height;
    private SessionPatient sessionPatient;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        sessionPatient = new SessionPatient(this);
        HashMap<String,String> hashMap = sessionPatient.getPatientId();

        txt_gold =(TextView) findViewById(R.id.txt_gold);
        txt_level =(TextView) findViewById(R.id.txt_level);
        txt_weight =(TextView) findViewById(R.id.txt_weight);
        txt_height =(TextView) findViewById(R.id.txt_height);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
         getDataStatistic(hashMap.get(sessionPatient.KEY_ID));
    }

    private void getDataStatistic(final String s) {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConnectionDb.SHOW_PATIENT_STATISTIC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("patient");
                        JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                        progressDialog.dismiss();
                        txt_gold.setText(String.valueOf(jsonObject1.getDouble("gold")));
                        txt_height.setText(String.valueOf(jsonObject1.getDouble("height")));
                        txt_weight.setText(String.valueOf(jsonObject1.getDouble("weight")));
                        txt_level.setText(String.valueOf(jsonObject1.getInt("level")));

                    }catch (Exception e){

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StatisticActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> hashMap = new HashMap<>();
                hashMap.put("patient_id",s);
                return hashMap;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
