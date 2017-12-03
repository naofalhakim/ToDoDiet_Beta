package com.motionlaboratory.tododiet;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class AddFoodActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txt_date;
    private EditText et_name, et_desc;
    private Button btn_add_food;
    int tanggal,bulan,tahun;
    private SessionPatient sessionPatient;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        sessionPatient = new SessionPatient(this);
        progressDialog = new ProgressDialog(this);

        txt_date = (TextView) findViewById(R.id.txt_date_food);
        et_name = (EditText) findViewById(R.id.et_name_fooctrl);
        et_desc = (EditText) findViewById(R.id.et_desc_food);
        btn_add_food = (Button) findViewById(R.id.btn_add_foodctrl);

        btn_add_food.setOnClickListener(this);
        txt_date.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==txt_date){
//            final Calendar c = Calendar.getInstance();
//            tanggal = c.get(Calendar.DAY_OF_MONTH);
//            bulan = c.get(Calendar.MONTH);
//            tahun = c.get(Calendar.YEAR);
//
//            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//                @Override
//                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                    txt_date.setText(dayOfMonth+"/"+(month+1)+"/"+year);
//                }
//            },tanggal,bulan,tahun);
//
//            datePickerDialog.show();
        }else if(btn_add_food == v){
            HashMap<String,String> hashMap = sessionPatient.getPatientId();
            final String id = hashMap.get(sessionPatient.KEY_ID);

            progressDialog.setMessage("Adding Food");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, ConnectionDb.INSERT_FOOD,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            Toast.makeText(AddFoodActivity.this,response,Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(AddFoodActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                   Map<String,String> stringMap = new HashMap<>();
                    stringMap.put("patient_id",id);
                    stringMap.put("date",txt_date.getText().toString());
                    stringMap.put("desc",et_desc.getText().toString());
                    stringMap.put("type",et_name.getText().toString());

                    return stringMap;
                }
            };
            RequestHandler.getInstance(AddFoodActivity.this).addToRequestQueue(stringRequest);
        }
    }
}
