package com.motionlaboratory.tododiet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.motionlaboratory.tododiet.Config.SessionManager;
import com.motionlaboratory.tododiet.DatabaseConfig.ConnectionDb;
import com.motionlaboratory.tododiet.DatabaseConfig.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddPatientActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_username, et_alamat, et_email, et_age, et_password, et_full_name;
    private RadioButton rd_male, rd_female;
    private Button btn_add_patient;
    private SessionManager sessionManager;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        sessionManager = new SessionManager(AddPatientActivity.this);
        progressDialog = new ProgressDialog(this);

        et_full_name = (EditText) findViewById(R.id.et_fullname_patient);
        et_age = (EditText) findViewById(R.id.et_age_patient);
        et_email = (EditText) findViewById(R.id.et_email_patient);
        et_alamat = (EditText) findViewById(R.id.et_location_patient);
        et_password = (EditText) findViewById(R.id.et_pass_patient);
        et_username = (EditText) findViewById(R.id.et_username_patient);

        rd_female = (RadioButton) findViewById(R.id.rd_female);
        rd_male = (RadioButton) findViewById(R.id.rd_male);
        rd_male.setChecked(true);

        btn_add_patient = (Button) findViewById(R.id.btn_register_patient);
        btn_add_patient.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        HashMap<String,String> doctorDetail = sessionManager.getDoctorDetails();
        final String id = doctorDetail.get(SessionManager.KEY_ID);

        final String username = et_username.getText().toString();
        final String fullname = et_full_name.getText().toString();
        final String email = et_email.getText().toString();
        final String location = et_alamat.getText().toString();
        final String pass = et_password.getText().toString();
        final String age = et_age.getText().toString();
        final String gender;

        if(rd_male.isChecked()){
            gender = "1";
        }else if(rd_female.isChecked()){
            gender = "0";
        }else{
            gender = "1";
        }


        progressDialog.setMessage("Registering...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
        Request.Method.POST,
                ConnectionDb.INSERT_PATIENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(AddPatientActivity.this,PatientActivity.class));
                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username",username);
                params.put("password",pass);
                params.put("email",email);
                params.put("full_name",fullname);
                params.put("location",location);
                params.put("gender",gender);
                params.put("age",age);
                params.put("id",id);

                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
