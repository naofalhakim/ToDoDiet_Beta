package com.motionlaboratory.tododiet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.motionlaboratory.tododiet.Config.SessionManager;
import com.motionlaboratory.tododiet.DatabaseConfig.ConnectionDb;
import com.motionlaboratory.tododiet.DatabaseConfig.RequestHandler;
import com.motionlaboratory.tododiet.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_email, et_pass;
    private Button btn_login, btn_goRegis;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_pass = (EditText) findViewById(R.id.et_pass_login);
        et_email = (EditText) findViewById(R.id.et_email_login);
        btn_goRegis = (Button) findViewById(R.id.btn_goregis_doctor);
        btn_login= (Button) findViewById(R.id.btn_login);
        btn_goRegis.setOnClickListener(this);
        btn_login.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);

    }

    @Override
    public void onClick(View v) {
        if (v==btn_login){
            if(!et_email.getText().toString().isEmpty() && !et_pass.getText().toString().isEmpty()){
                cekLoginServer();
            }else{
                Toast.makeText(LoginActivity.this,"Ada yang masih kosong",Toast.LENGTH_SHORT).show();
            }

        }else if(v==btn_goRegis){
            startActivity(new Intent(LoginActivity.this,DoctorRegisterActivity.class));
        }
    }

    private boolean cekLoginServer() {
        final String email = et_email.getText().toString().trim();
        final String pass = et_pass.getText().toString().trim();

        final SessionManager sessionManager = new SessionManager(LoginActivity.this);

        progressDialog.setMessage("Cecking for login ...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                ConnectionDb.LOGIN_DOCTOR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.getInt("success") != 0){
                                JSONArray jsonArray = jsonObject.getJSONArray("user");

                                sessionManager.createLoginSession(
                                        jsonArray.getJSONObject(0).getString("username"),
                                        jsonArray.getJSONObject(0).getString("email"),
                                        jsonArray.getJSONObject(0).getInt("id")
                                );

                                //Toast.makeText(LoginActivity.this, "Login berhasil", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(LoginActivity.this, PatientActivity.class));


                            }else {
                                Toast.makeText(LoginActivity.this,jsonObject.getString("message") , Toast.LENGTH_SHORT).show();
                            }

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
                params.put("password",pass);
                params.put("email",email);

                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

        return true;
    }
}
