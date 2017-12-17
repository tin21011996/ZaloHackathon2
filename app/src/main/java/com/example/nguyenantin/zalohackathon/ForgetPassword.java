package com.example.nguyenantin.zalohackathon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ForgetPassword extends AppCompatActivity {

    ImageButton btn_back_track1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        btn_back_track1 = (ImageButton)findViewById(R.id.btn_back1);
        btn_back_track1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(ForgetPassword.this ,
                        MainActivity.class);
                ForgetPassword.this.startActivity(intentMain);
            }
        });
    }
}
