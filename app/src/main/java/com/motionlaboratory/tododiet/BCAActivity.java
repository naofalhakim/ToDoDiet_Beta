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
import com.motionlaboratory.tododiet.Adapter.AdapterBCA;
import com.motionlaboratory.tododiet.Config.SessionPatient;
import com.motionlaboratory.tododiet.DatabaseConfig.ConnectionDb;
import com.motionlaboratory.tododiet.DatabaseConfig.RequestHandler;
import com.motionlaboratory.tododiet.Model.BCA;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BCAActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<BCA> bcaList;
    private Button btn_add;
    private SessionPatient sessionPatient;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bca);

        progressDialog = new ProgressDialog(this);

        recyclerView = (RecyclerView) findViewById(R.id.rec_bca);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(BCAActivity.this));
        btn_add = (Button) findViewById(R.id.add_bca);

        sessionPatient = new SessionPatient(this);
        final String id = sessionPatient.getPatientId().get(sessionPatient.KEY_ID);
        bcaList = new ArrayList<>();
        btn_add.setOnClickListener(this);

        loadDataBCA(id);
    }

    private void loadDataBCA(final String id) {
        progressDialog.setMessage("loading BCA data . .");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConnectionDb.SHOW_BCA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("bca");
                            for (int i = 0; i <jsonArray.length() ; i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                                bcaList.add(new BCA(
//                                        String.valueOf(jsonObject1.getInt("serial_number")),
//                                        jsonObject1.getString("date")
//                                ));
                                bcaList.add(new BCA(
                                jsonObject1.getString("date"),
                                        String.valueOf(jsonObject1.getInt("serial_number")),
                                        String.valueOf(jsonObject1.getDouble("bca_weight")),
                                        String.valueOf(jsonObject1.getDouble("fat_percent")),
                                        String.valueOf(jsonObject1.getDouble("fat_mass")),
                                        String.valueOf(jsonObject1.getDouble("ffm")),
                                        String.valueOf(jsonObject1.getDouble("muscle_mass")),
                                        String.valueOf(jsonObject1.getDouble("tbw")),
                                        String.valueOf(jsonObject1.getDouble("tbw_percent")),
                                        String.valueOf(jsonObject1.getDouble("bone_mass")),
                                        String.valueOf(jsonObject1.getInt("bmr")),
                                        String.valueOf(jsonObject1.getInt("metabolic_age")),
                                        String.valueOf(jsonObject1.getDouble("visceral_fat_rating")),
                                        String.valueOf(jsonObject1.getDouble("bca_bmi")),
                                        String.valueOf(jsonObject1.getDouble("ideal_body_weight")),
                                        String.valueOf(jsonObject1.getDouble("degree_of_obesity_percent")),
                                        String.valueOf(jsonObject1.getDouble("fat_bot_range_percent")),
                                        String.valueOf(jsonObject1.getDouble("fat_top_range_percent")),
                                        String.valueOf(jsonObject1.getDouble("fat_mass_bot_range_percent")),
                                        String.valueOf(jsonObject1.getDouble("fat_mass_top_range_percent"))
                                        ));
                            }
                            adapter = new AdapterBCA(bcaList,BCAActivity.this);
                            recyclerView.setAdapter(adapter);
                            Toast.makeText(BCAActivity.this,response,Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(BCAActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> stringMap = new HashMap<>();
                stringMap.put("patient_id",id);
                return stringMap;
            }
        };

        RequestHandler.getInstance(BCAActivity.this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(BCAActivity.this,AddBCAActivity.class));
    }
}
