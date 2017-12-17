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

public class CustomList extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;
    private final String [] ten;
    private final String [] diem;
    public CustomList(Activity context,
                      String[] web, Integer[] imageId, String [] ten, String [] diem) {
        super(context,R.layout.item_consumption);
        this.context = context;
        this.ten = ten;
        this.web = web;
        this.diem = diem;
        this.imageId = imageId;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.item_consumption, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt_con1);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img_con);
        txtTitle.setText(web[position]);
        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
