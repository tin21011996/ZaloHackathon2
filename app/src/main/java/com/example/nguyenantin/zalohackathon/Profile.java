package com.example.nguyenantin.zalohackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Profile extends AppCompatActivity {

    ImageButton btn_back_track;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btn_back_track = (ImageButton)findViewById(R.id.btn_back2);
        btn_back_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(Profile.this ,
                        MainActivity.class);
                Profile.this.startActivity(intentMain);
            }
        });
    }
}
