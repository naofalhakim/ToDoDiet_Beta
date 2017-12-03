package com.motionlaboratory.tododiet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
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
import com.motionlaboratory.tododiet.Model.Patient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailPatientActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txt_fullname, txt_username, txt_gender, txt_email, txt_location, txt_phone;
    private LinearLayout l_sta, l_badges, l_quotes, l_bca, l_task, l_task_result;
    private Patient patient;
    private SessionPatient sessionPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_patient);

        sessionPatient = new SessionPatient(this);

        txt_fullname = (TextView) findViewById(R.id.txt_fullname);
        txt_username = (TextView) findViewById(R.id.txt_username);
        txt_email = (TextView) findViewById(R.id.txt_email);
        txt_gender = (TextView) findViewById(R.id.txt_gender);
        txt_location = (TextView) findViewById(R.id.txt_location);
        txt_phone = (TextView) findViewById(R.id.txt_phone);

        l_badges = (LinearLayout) findViewById(R.id.l_badge);
        l_bca = (LinearLayout) findViewById(R.id.l_bca);
        l_quotes = (LinearLayout) findViewById(R.id.l_quote);
        l_task = (LinearLayout) findViewById(R.id.l_task);
        l_sta = (LinearLayout) findViewById(R.id.l_statistic);
        l_task_result = (LinearLayout) findViewById(R.id.l_task_result);

        l_badges.setOnClickListener(this);
        l_task.setOnClickListener(this);
        l_quotes.setOnClickListener(this);
        l_bca.setOnClickListener(this);
        l_sta.setOnClickListener(this);
        l_task_result.setOnClickListener(this);

        HashMap<String,String> integerHashMap = sessionPatient.getPatientId();
        getDetailPatient(integerHashMap.get(sessionPatient.KEY_ID));

    }

    private String chooseGender(int gender) {
        if (gender == 1){
            return "Pria";
        }else{
            return "Wanita";
        }
    }

    private void getDetailPatient(final String id) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConnectionDb.SHOW_PATIENT_DETAIL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("patient_profile");
                            JSONArray jsonArray2 = jsonObject.getJSONArray("patient_account");
                            JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                            JSONObject jsonObject2 = jsonArray2.getJSONObject(0);


                            txt_fullname.setText(jsonObject1.getString("full_name"));
                            txt_username.setText(jsonObject2.getString("username"));
                            txt_email.setText(jsonObject2.getString("email"));
                            txt_phone.setText(String.valueOf(jsonObject1.getInt("age")));
                            txt_gender.setText(chooseGender(jsonObject1.getInt("gender")));
                            txt_location.setText(jsonObject1.getString("location"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(DetailPatientActivity.this,"Detail Patient Loaded",Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(DetailPatientActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> hashMap = new HashMap<>();
                hashMap.put("patient_id",id);
                return hashMap;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    @Override
    public void onClick(View v) {

        Intent i = null;

        if (v == l_badges){
            i = new Intent(DetailPatientActivity.this,BadgeActivity.class);
        }else if (v == l_bca){
            i = new Intent(DetailPatientActivity.this,BCAActivity.class);
        }else if(v == l_quotes){
            i = new Intent(DetailPatientActivity.this,QuotesActivity.class);
        }else if(v == l_task){
            i = new Intent(DetailPatientActivity.this,Challange.class);
        }else if(v== l_sta){
            i = new Intent(DetailPatientActivity.this,StatisticActivity.class);
        }else{
            i = new Intent(DetailPatientActivity.this,TaskListActivity.class);
        }

        startActivity(i);
    }


}