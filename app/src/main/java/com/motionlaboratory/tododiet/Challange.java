package com.motionlaboratory.tododiet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

public class Challange extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout rel_exe, rel_food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_challange);

        rel_exe = (RelativeLayout) findViewById(R.id.rel_exercise);
        rel_food = (RelativeLayout) findViewById(R.id.rel_food);

        rel_exe.setOnClickListener(this);
        rel_food.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == rel_exe){
            startActivity(new Intent(Challange.this,ExerciseActivity.class));
        }else if(rel_food == v){
            startActivity(new Intent(Challange.this,FoodControlActivity.class));
        }
    }
}
