package com.motionlaboratory.tododiet;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.motionlaboratory.tododiet.Adapter.AdapterBadge;
import com.motionlaboratory.tododiet.Adapter.AdapterPatient;
import com.motionlaboratory.tododiet.Config.SessionPatient;
import com.motionlaboratory.tododiet.DatabaseConfig.ConnectionDb;
import com.motionlaboratory.tododiet.DatabaseConfig.RequestHandler;
import com.motionlaboratory.tododiet.Model.BCA;
import com.motionlaboratory.tododiet.Model.Badge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BadgeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Badge> badgeList;
    private SessionPatient sessionPatient;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge);

        progressDialog = new ProgressDialog(this);

        recyclerView = (RecyclerView) findViewById(R.id.rec_badge);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(BadgeActivity.this));

        sessionPatient = new SessionPatient(this);
        HashMap<String,String> hashMap = sessionPatient.getPatientId();

        badgeList = new ArrayList<>();

        loadDataBadge(hashMap.get(sessionPatient.KEY_ID));
    }

    private void loadDataBadge(final String id) {
        progressDialog.setMessage("loading badge . . .");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConnectionDb.SHOW_PATIENT_BADGE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsnObject = new JSONObject(response);
                            JSONArray jsonArray = jsnObject.getJSONArray("patient");
                            for (int i = 0; i <jsonArray.length() ; i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                badgeList.add(new Badge(
                                   jsonObject1.getString("name"),
                                   jsonObject1.getString("description"),
                                        jsonObject1.getInt("objective"),
                                        jsonObject1.getInt("progress")
                                ));
                                adapter = new AdapterBadge(badgeList,BadgeActivity.this);
                                recyclerView.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(BadgeActivity.this,"Need Internet Connection ",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> stringMap = new HashMap<>();
                stringMap.put("patient_id",id);
                return stringMap;
            }
        };
        RequestHandler.getInstance(BadgeActivity.this).addToRequestQueue(stringRequest);
    }
}
