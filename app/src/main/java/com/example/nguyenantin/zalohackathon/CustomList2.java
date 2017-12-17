package com.example.nguyenantin.zalohackathon;

/**
 * Created by nguyenantin on 12/17/17.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomList2 extends ArrayAdapter<String>{
    private final Activity context;
    private final Integer[] imageId;
    private final String[] web;
    private final String[] ten;
    public CustomList2(Activity context,
                      String[] web, Integer[] imageId, String [] ten) {
        super(context, R.layout.item_consumption, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
        this.ten = ten;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.item_consumption, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt_healthy1);
        TextView txtTitle2 = (TextView) rowView.findViewById(R.id.txt_healthy2);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.image_health);
        txtTitle.setText(web[position]);
        txtTitle2.setText(ten[position]);
        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
