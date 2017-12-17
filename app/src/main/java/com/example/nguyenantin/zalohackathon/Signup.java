package com.example.nguyenantin.zalohackathon;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    EditText txt_login;
    EditText txt_pass;
    Button btn_login1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        txt_login = (EditText)findViewById(R.id.txt_login);
        txt_pass = (EditText)findViewById(R.id.txt_pass);
        btn_login1  = (Button) findViewById(R.id.btn_login);
        btn_login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String us = txt_login.getText().toString();
                String ps = txt_login.getText().toString();
                if(us.toString()=="admin".toString() && ps.toString()=="123456".toString()){
                            Intent intentMain = new Intent(Signup.this ,
                                    HealthTracking.class);

                            Signup.this.startActivity(intentMain);
                }
                else {
                    Intent intentMain = new Intent(Signup.this ,
                            HealthTracking.class);

                    Signup.this.startActivity(intentMain);
                }
            }
        });
    }
}
