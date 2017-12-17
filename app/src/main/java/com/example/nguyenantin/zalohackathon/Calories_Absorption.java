package com.example.nguyenantin.zalohackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Calories_Absorption extends AppCompatActivity {

    Button btn_back_track;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories__absorption);
        btn_back_track = (Button)findViewById(R.id.btn_Login);
        btn_back_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(Calories_Absorption.this ,
                        MainActivity.class);
                Calories_Absorption.this.startActivity(intentMain);
            }
        });
    }
}
