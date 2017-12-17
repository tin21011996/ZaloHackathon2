package com.example.nguyenantin.zalohackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Profile_Setting extends AppCompatActivity {

    ImageButton btn_back3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__setting);
        btn_back3 = (ImageButton)findViewById(R.id.btn_back2);
        btn_back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(Profile_Setting.this ,
                        MainActivity.class);
                Profile_Setting.this.startActivity(intentMain);
            }
        });
    }
}
