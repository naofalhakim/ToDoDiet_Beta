package com.motionlaboratory.tododiet;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.motionlaboratory.tododiet.Config.SessionManager;
import com.motionlaboratory.tododiet.Model.Patient;

public class SplashActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sessionManager = new SessionManager(SplashActivity.this);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                if(sessionManager.isLoggedIn()) {
                    startActivity(new Intent(getApplicationContext(),PatientActivity.class));
                }else{
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                }
                    finish();
            }
        },3000);
    }
}
