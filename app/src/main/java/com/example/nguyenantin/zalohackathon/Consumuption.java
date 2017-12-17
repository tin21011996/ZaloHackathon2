package com.example.nguyenantin.zalohackathon;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Consumuption extends AppCompatActivity implements SensorEventListener, TextToSpeech.OnInitListener {

    private static final int N_SAMPLES = 200;
    private static List<Float> x;
    private static List<Float> y;
    private static List<Float> z;

    private TextToSpeech textToSpeech;
    private float[] results = {0,0,0,0,0,0};
    private TensorFlowClassifier classifier;
    ListView lv;
    float Sumtext = 0;
    ListAdapter adapter;
    TextView textSum;

    String []ten2={ "120 cal","120 cal",
            "80 cal","50 cal","100 cal","75 cal"
    };

    int []hinh={ R.drawable.healthy1,R.drawable.healthy2,
            R.drawable.healthy3,R.drawable.healthy4, R.drawable.healthy3,R.drawable.healthy4
    };
    private String[] labels = {"Downstairs", "Jogging", "Sitting", "Standing", "Upstairs", "Walking"};
    ImageButton back_cons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumuption);
        textSum = (TextView) findViewById(R.id.txtSum);
        lv=(ListView)findViewById(R.id.lst_con);
        back_cons = (ImageButton)findViewById(R.id.back_cons);
        back_cons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(Consumuption.this ,
                        HealthTracking.class);
                Consumuption.this.startActivity(intentMain);
            }
        });
        x = new ArrayList<>();
        y = new ArrayList<>();
        z = new ArrayList<>();


        classifier = new TensorFlowClassifier(getApplicationContext());



        if(results.length==6) {
            adapter = new Consumuption.myadapter_con(this, labels);
            //lv.setAdapter(new myadapter1(this));
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {

                    TextView tv = (TextView) arg1.findViewById(R.id.txt_con1);
                    Toast.makeText(getApplicationContext(),
                            "ban chon " + tv.getText().toString(), Toast.LENGTH_SHORT).show();
                }

            });
        }
    }

    class myadapter_con extends ArrayAdapter {
        Context context;


        public myadapter_con(Context context,String [] ten)
        {
            super(context,R.layout.item_consumption,ten);
            this.context=context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // dung layoutinflater lấy đọc cấu trúc và nội dung của từng hàng listview
            LayoutInflater inf=(LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View rowview=inf.inflate(R.layout.item_consumption,parent, false);
            // Inflate only once
            if(convertView == null) {
                convertView = getLayoutInflater()
                        .inflate(R.layout.item_consumption, null, false);
            }
            //ánh xạ từng hàng listview , cập nhật thông tin
            TextView textview=(TextView)convertView.findViewById(R.id.txt_con1);
            TextView textview2=(TextView)convertView.findViewById(R.id.txt_con2);
            TextView textview3=(TextView)convertView.findViewById(R.id.txt_con3);

            ImageView imageview=(ImageView)convertView.findViewById(R.id.img_con);

            textview.setText(labels[position]);
            textview2.setText(ten2[position]);
            textview3.setText(Float.toString(round(results[position],2)));
            imageview.setImageResource(hinh[position]);

            return convertView;
        }

    }
    @Override
    public void onInit(int status) {
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                if (results == null || results.length == 0) {
//                    return;
//                }
//                float max = -1;
//                int idx = -1;
////                for (int i = 0; i < results.length; i++) {
////                    if (results[i] > max) {
////                        idx = i;
////                        max = results[i];
////                    }
////                }
//            }
//        }, 100, 500);
    }

    protected void onPause() {
        getSensorManager().unregisterListener(this);
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        getSensorManager().registerListener(this, getSensorManager().getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        activityPrediction();
        x.add(event.values[0]);
        y.add(event.values[1]);
        z.add(event.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void activityPrediction() {
        if (x.size() == N_SAMPLES && y.size() == N_SAMPLES && z.size() == N_SAMPLES) {
            List<Float> data = new ArrayList<>();
            data.addAll(x);
            data.addAll(y);
            data.addAll(z);

            results = classifier.predictProbabilities(toFloatArray(data));
            Sumtext = (float) (635 + results[0]*0.0297 + results[1]*0.0503 + results[2]*0.0087 + results[3]*0.011 + results[4]*0.034 + results[5]*0.029);
            textSum.setText(Float.toString(round(Sumtext,5)));
            x.clear();
            y.clear();
            z.clear();
            lv.setAdapter(adapter);
        }
    }

    private float[] toFloatArray(List<Float> list) {
        int i = 0;
        float[] array = new float[list.size()];

        for (Float f : list) {
            array[i++] = (f != null ? f : Float.NaN);
        }
        return array;
    }

    private static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    private SensorManager getSensorManager() {
        return (SensorManager) getSystemService(SENSOR_SERVICE);
    }

}
