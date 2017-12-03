package com.motionlaboratory.tododiet;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.motionlaboratory.tododiet.Config.SessionPatient;
import com.motionlaboratory.tododiet.DatabaseConfig.ConnectionDb;
import com.motionlaboratory.tododiet.DatabaseConfig.RequestHandler;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class AddBCAActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener{

    private EditText et_date, et_serial_number, et_bca_weight , et_fat_percent, fat_mass, et_ffm, et_muscle_mass,
            et_tbw, et_tbw_percent, et_bone_mass, et_bmr, et_metabolic_age, et_visceral_fat_rating, et_bca_bmi,
            et_ideal_body_weight, et_degree_of_obesity_percent, et_fat_bot_range_percent, et_fat_top_range_percent,
            et_fat_mass_bot_range_percent, et_fat_mass_top_range_percent;

    private Button btn_add;
    private SessionPatient sessionPatient;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bca);

        sessionPatient = new SessionPatient(this);
        progressDialog = new ProgressDialog(this);

        btn_add = (Button) findViewById(R.id.btn_add_bca);
        btn_add.setOnClickListener(this);

        et_date = (EditText) findViewById(R.id.et_date_bca);

        et_serial_number = (EditText) findViewById(R.id.et_serial_number );

        et_bca_weight  = (EditText) findViewById(R.id.et_bca_weight  );

        et_fat_percent = (EditText) findViewById(R.id.et_fat_percent);

        fat_mass = (EditText) findViewById(R.id.et_fat_mass );

        et_ffm = (EditText) findViewById(R.id.et_ffm );

        et_muscle_mass = (EditText) findViewById(R.id.et_muscle_mass );

        et_tbw = (EditText) findViewById(R.id.et_tbw );

        et_tbw_percent = (EditText) findViewById(R.id.et_tbw_percent );

        et_bone_mass = (EditText) findViewById(R.id.et_bone_mass );

        et_bmr = (EditText) findViewById(R.id.et_bmr );

        et_metabolic_age = (EditText) findViewById(R.id.et_metabolic_age );

        et_visceral_fat_rating = (EditText) findViewById(R.id.et_visceral_fat_rating );

        et_bca_bmi = (EditText) findViewById(R.id.et_bca_bmi );

        et_ideal_body_weight = (EditText) findViewById(R.id.et_ideal_body_weight );

        et_degree_of_obesity_percent = (EditText) findViewById(R.id.et_degree_of_obesity_percent );

        et_fat_bot_range_percent = (EditText) findViewById(R.id.et_fat_bot_range_percent );

        et_fat_top_range_percent = (EditText) findViewById(R.id.et_fat_top_range_percent );

        et_fat_mass_bot_range_percent = (EditText) findViewById(R.id.et_fat_mass_bot_range_percent );

        et_fat_mass_top_range_percent = (EditText) findViewById(R.id.et_fat_mass_top_range_percent );
    }

    @Override
    public void onClick(View v) {
            if(btn_add == v) {

            final String id = sessionPatient.getPatientId().get(sessionPatient.KEY_ID);

            progressDialog.setMessage("Saving BCA ");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, ConnectionDb.INSERT_BCA,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            Toast.makeText(AddBCAActivity.this, "Created", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(AddBCAActivity.this,BCAActivity.class));
                            finish();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(AddBCAActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> stringMap = new HashMap<>();
                    stringMap.put("patient_id", id);
                    stringMap.put("date", et_date.getText().toString());
                    stringMap.put("serial_number", et_serial_number.getText().toString());
                    stringMap.put("bca_weight", et_bca_weight.getText().toString());
                    stringMap.put("fat_percent", et_fat_percent.getText().toString());
                    stringMap.put("fat_mass", fat_mass.getText().toString());
                    stringMap.put("ffm", et_ffm.getText().toString());
                    stringMap.put("muscle_mass", et_muscle_mass.getText().toString());
                    stringMap.put("tbw", et_tbw.getText().toString());
                    stringMap.put("tbw_percent", et_tbw_percent.getText().toString());
                    stringMap.put("bone_mass", et_bone_mass.getText().toString());
                    stringMap.put("bmr", et_bmr.getText().toString());
                    stringMap.put("metabolic_age", et_metabolic_age.getText().toString());
                    stringMap.put("visceral_fat_rating", et_visceral_fat_rating.getText().toString());
                    stringMap.put("bca_bmi", et_bca_bmi.getText().toString());
                    stringMap.put("ideal_body_weight", et_ideal_body_weight.getText().toString());
                    stringMap.put("degree_of_obesity_percent", et_degree_of_obesity_percent.getText().toString());
                    stringMap.put("fat_bot_range_percent", et_fat_bot_range_percent.getText().toString());
                    stringMap.put("fat_top_range_percent", et_fat_top_range_percent.getText().toString());
                    stringMap.put("fat_mass_bot_range_percent", et_fat_mass_bot_range_percent.getText().toString());
                    stringMap.put("fat_mass_top_range_percent", et_fat_mass_top_range_percent.getText().toString());
                    return stringMap;
                }
            };

            RequestHandler.getInstance(AddBCAActivity.this).addToRequestQueue(stringRequest);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar cal = new GregorianCalendar(year,monthOfYear,dayOfMonth);
        setDate(cal);
    }

    private void setDate(final Calendar cal) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        et_date.setText(dateFormat.format(cal.getTime()));
    }

    public void datePicker(View v){
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(),"date");
    }

    public static class DatePickerFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int tanggal = c.get(Calendar.DAY_OF_MONTH);
            int bulan = c.get(Calendar.MONTH);
            int tahun = c.get(Calendar.YEAR);

            return new DatePickerDialog(
                    getActivity(),
                    (DatePickerDialog.OnDateSetListener) getActivity(),
                    tahun,bulan,tanggal
            );
        }
    }
}
