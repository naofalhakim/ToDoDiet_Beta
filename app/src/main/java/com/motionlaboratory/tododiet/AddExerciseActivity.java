package com.motionlaboratory.tododiet;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
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

public class AddExerciseActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private Spinner sp_exercise;
    private EditText et_tanggal,et_repetition;
    private Button btn_add_exe;
    private int tanggal, bulan, tahun;
    private SessionPatient sessionPatient;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        sessionPatient = new SessionPatient(AddExerciseActivity.this);

        progressDialog =  new ProgressDialog(this);

        sp_exercise = (Spinner) findViewById(R.id.sp_exercise);
        et_tanggal = (EditText) findViewById(R.id.et_date_exercise);
        et_repetition = (EditText) findViewById(R.id.et_date_repetition);
        btn_add_exe = (Button) findViewById(R.id.btn_add_exercise);

        String sp_exe[] = {"run","jump"};
        ArrayAdapter exe_adapter = new ArrayAdapter(AddExerciseActivity.this,android.R.layout.simple_spinner_dropdown_item,sp_exe);
        sp_exercise.setAdapter(exe_adapter);

        btn_add_exe.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(btn_add_exe==v){
            HashMap<String,String> integerHashMap = sessionPatient.getPatientId();
            insertExercise(integerHashMap.get(sessionPatient.KEY_ID));
        }
    }

    private void insertExercise(final String id) {
        progressDialog.setMessage("Creating Exercise");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConnectionDb.INSERT_EXERCISE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Toast.makeText(AddExerciseActivity.this,"Created",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(AddExerciseActivity.this,ExerciseActivity.class));
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(AddExerciseActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> stringMap = new HashMap<>();
                stringMap.put("patient_id",id);
                stringMap.put("type",sp_exercise.getSelectedItem().toString());
                stringMap.put("due_date",et_tanggal.getText().toString());
                stringMap.put("time",et_repetition.getText().toString());
                return stringMap;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = new GregorianCalendar(year,month,dayOfMonth);
        setDate(cal);
    }

    private void setDate(final Calendar cal) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        et_tanggal.setText(dateFormat.format(cal.getTime()));
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
