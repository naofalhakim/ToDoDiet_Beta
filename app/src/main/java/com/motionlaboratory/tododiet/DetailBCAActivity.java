package com.motionlaboratory.tododiet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DetailBCAActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bca);

        ((TextView) findViewById(R.id.txt_date_bca)).setText(getIntent().getStringExtra("date"));

        ((TextView) findViewById(R.id.txt_serial_number )).setText(getIntent().getStringExtra("serial_number"));

        ((TextView) findViewById(R.id.txt_bca_weight  )).setText(getIntent().getStringExtra("bca_weight"));

        ((TextView) findViewById(R.id.txt_fat_percent)).setText(getIntent().getStringExtra("fat_percent"));

        ((TextView) findViewById(R.id.fat_mass)).setText(getIntent().getStringExtra("fat_mass"));

        ((TextView) findViewById(R.id.txt_ffm )).setText(getIntent().getStringExtra("ffm"));

        ((TextView) findViewById(R.id.txt_muscle_mass )).setText(getIntent().getStringExtra("muscle_mass"));

        ((TextView) findViewById(R.id.txt_tbw )).setText(getIntent().getStringExtra("tbw"));

        ((TextView) findViewById(R.id.txt_tbw_percent )).setText(getIntent().getStringExtra("tbw_percent"));

        ((TextView) findViewById(R.id.txt_bone_mass )).setText(getIntent().getStringExtra("bone_mass"));

        ((TextView) findViewById(R.id.txt_bmr )).setText(getIntent().getStringExtra("bmr"));

        ((TextView) findViewById(R.id.txt_metabolic_age )).setText(getIntent().getStringExtra("metabolic_age"));

        ((TextView) findViewById(R.id.txt_visceral_fat_rating )).setText(getIntent().getStringExtra("visceral_fat_rating"));

        ((TextView) findViewById(R.id.txt_bca_bmi )).setText(getIntent().getStringExtra("bca_bmi"));

        ((TextView) findViewById(R.id.txt_ideal_body_weight )).setText(getIntent().getStringExtra("ideal_body_weight"));

        ((TextView) findViewById(R.id.txt_degree_of_obesity_percent )).setText(getIntent().getStringExtra("degree_of_obesity_percent"));

        ((TextView) findViewById(R.id.txt_fat_bot_range_percent )).setText(getIntent().getStringExtra("fat_bot_range_percent"));

        ((TextView) findViewById(R.id.txt_fat_top_range_percent )).setText(getIntent().getStringExtra("fat_top_range_percent"));

        ((TextView) findViewById(R.id.txt_fat_mass_bot_range_percent )).setText(getIntent().getStringExtra("fat_mass_bot_range_percent"));

        ((TextView) findViewById(R.id.txt_fat_mass_top_range_percent )).setText(getIntent().getStringExtra("fat_mass_top_range_percent"));    }
}
