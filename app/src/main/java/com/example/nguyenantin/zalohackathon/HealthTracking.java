package com.example.nguyenantin.zalohackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HealthTracking extends AppCompatActivity {

    ListView lv;

    String []ten={ "Burning Calories" , "Getting Calories" , "Today Challenge" , "Profile Setting"
    };
    String [] cal= {"Calculate current burned calories","Estimate the number of calories into body","Become fitness through interesting tasks","Changing your profile to be served better"};
    int [] hinh={ R.drawable.healthy1, R.drawable.healthy2,
            R.drawable.healthy3, R.drawable.healthy4
    };
    ImageButton btn_back4;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tracking);
        lv=(ListView)findViewById(R.id.list_healthy);
        btn_back4 = (ImageButton)findViewById(R.id.btn_back4);
        btn_back4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(HealthTracking.this ,
                        MainActivity.class);
                HealthTracking.this.startActivity(intentMain);
            }
        });
        lv.setAdapter(new myadapter(this,ten));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent intentMain = new Intent(HealthTracking.this ,
                        Consumuption.class);
                HealthTracking.this.startActivity(intentMain);
            }
//            public void onItemClick(AdapterView<?> parent, View view, 3, long id) {
//                Intent intentMain = new Intent(HealthTracking.this ,
//                        Profile_Setting.class);
//                HealthTracking.this.startActivity(intentMain);
//            }

        });
    }
    //cach 1

    class myadapter extends ArrayAdapter{
        Context context;


        public myadapter(Context context,String [] ten)
        {
            super(context,R.layout.item_healthy_tracking,ten);
            this.context=context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // dung layoutinflater lấy đọc cấu trúc và nội dung của từng hàng listview
            LayoutInflater inf=(LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowview=inf.inflate(R.layout.item_healthy_tracking,parent, false);

            //ánh xạ từng hàng listview , cập nhật thông tin
            TextView textview=(TextView)rowview.findViewById(R.id.txt_healthy1);
            TextView textview2=(TextView)rowview.findViewById(R.id.txt_healthy2);
            ImageView imageview=(ImageView)rowview.findViewById(R.id.image_health);

            textview.setText(ten[position]);
            textview2.setText(cal[position]);
            imageview.setImageResource(hinh[position]);

            return rowview;
        }

    }

}
