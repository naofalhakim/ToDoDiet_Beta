package com.motionlaboratory.tododiet;

import android.app.ProgressDialog;
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
import com.motionlaboratory.tododiet.Adapter.AdapterPatient;
import com.motionlaboratory.tododiet.Config.SessionManager;
import com.motionlaboratory.tododiet.DatabaseConfig.ConnectionDb;
import com.motionlaboratory.tododiet.DatabaseConfig.RequestHandler;
import com.motionlaboratory.tododiet.Model.Patient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Patient> patientList;
    private Button btn_add, btn_logout;
    SessionManager sessionManager;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        sessionManager = new SessionManager(PatientActivity.this);
        sessionManager.checkLogin();

        HashMap<String,String> doctorData = sessionManager.getDoctorDetails();

        progressDialog = new ProgressDialog(this);

        btn_add = (Button) findViewById(R.id.btn_tambah_patient);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_add.setOnClickListener(this);
        btn_logout.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.rec_patient);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(PatientActivity.this));

        patientList = new ArrayList<>();
        loadDataPatient(doctorData.get(sessionManager.KEY_ID));
    }

    private void loadDataPatient(final String doctor_id) {
        progressDialog.setMessage("loading patient "+doctor_id);
        progressDialog.show();

            StringRequest stringRequest =  new StringRequest(Request.Method.POST, ConnectionDb.SHOW_PATIENT,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("patient");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject temp = jsonArray.getJSONObject(i);
                                    Patient patientTemp = new Patient(
                                            String.valueOf(temp.getInt("patient_id")),
                                            temp.getString("full_name")
                                    );
                                    patientList.add(patientTemp);
                                }
                                adapter = new AdapterPatient(patientList,PatientActivity.this);
                                recyclerView.setAdapter(adapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            Toast.makeText(PatientActivity.this,"Patient List",Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(PatientActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> hashMap = new HashMap<>();
                    hashMap.put("doctor_id",doctor_id);
                    return hashMap;
                }
            };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    @Override
    public void onClick(View v) {
        if(v==btn_add){
            startActivity(new Intent(PatientActivity.this,AddPatientActivity.class));
        }else if(v==btn_logout){
            sessionManager.logoutUser();
            finish();
            startActivity(new Intent(PatientActivity.this,LoginActivity.class));
        }
    }
}

