package com.motionlaboratory.tododiet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.motionlaboratory.tododiet.DatabaseConfig.ConnectionDb;
import com.motionlaboratory.tododiet.DatabaseConfig.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DoctorRegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_username, et_email, et_pass, et_fullname, et_clinic;
    private Button btn_regiter_doctor;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);

        et_username = (EditText) findViewById(R.id.et_username_doctor);
        et_email = (EditText) findViewById(R.id.et_email_doctor);
        et_pass = (EditText) findViewById(R.id.et_pass_doctor);
        et_fullname = (EditText) findViewById(R.id.et_fullname_doctor);
        et_clinic = (EditText) findViewById(R.id.et_clinic_doctor);

        btn_regiter_doctor= (Button) findViewById(R.id.btn_register_doctor);
        progressDialog = new ProgressDialog(this);

        btn_regiter_doctor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        register();
    }

    private void register(){
        //StringRequest(POST/GET, Alamat Server, kodisi berhasil, kondisi eror, data berupa object HashMap)

        final String username =   et_username.getText().toString().trim();
        final String email = et_email.getText().toString().trim();
        final String pass = et_pass.getText().toString().trim();
        final String fullname = et_fullname.getText().toString().trim();
        final String clinic = et_clinic.getText().toString().trim();


        progressDialog.setMessage("Registering...");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                ConnectionDb.INSERT_DOCTOR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            startActivity(new Intent(DoctorRegisterActivity.this,LoginActivity.class));
                            finish();
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
                params.put("clinic_location",clinic);

                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
