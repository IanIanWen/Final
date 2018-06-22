package com.example.admin.final1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter_map extends ArrayAdapter<Travel> {

    Context context;

    public ListViewAdapter_map(Context context, List<Travel> items) {
        super(context, 0, items);
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Bundle bundle=new Bundle();
        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout itemlayout = null;
        if (convertView == null) {
            itemlayout = (LinearLayout) inflater.inflate(R.layout.row, null);
        } else {
            itemlayout = (LinearLayout) convertView;
        }
        final Travel item = (Travel) getItem(position);
        TextView tvTitle = (TextView) itemlayout.findViewById(R.id.Title);
        tvTitle.setText(item.getTitle());
        TextView tvAdd = (TextView) itemlayout.findViewById(R.id.Add);
        tvAdd.setText(item.getAddress());
        ImageView ivFood = (ImageView) itemlayout.findViewById(R.id.Icon);
        ivFood.setImageBitmap(item.getImgUrl());

        itemlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.getTitle().equals("鰲峰山觀景平台")){
                    gps(item);
                }

                if (item.getTitle().equals("彩虹眷村")){
                    gps(item);
                }

                if (item.getTitle().equals("東海藝術街")){
                    gps(item);
                }

                if (item.getTitle().equals("知高圳步道")){
                    gps(item);
                }

                if (item.getTitle().equals("高美濕地")){
                    gps(item);
                }

                if (item.getTitle().equals("勤美術館")){
                    gps(item);
                }

                if (item.getTitle().equals("九天黑森林")){
                    gps(item);
                }
                if (item.getTitle().equals("太平區公所3D彩繪牆")){
                    gps(item);
                }
            }
        });
        return itemlayout;
    }

    public void gps(Travel item){
        double startLatitude = item.getLatti();
        double startLongitude = item.getLongi();

        double endLatitude = item.getPx();
        double endLongitude = item.getPy();

        String saddr = "saddr=" + startLatitude + "," + startLongitude;
        String daddr = "daddr=" + endLatitude + "," + endLongitude;
        String uriString = "http://maps.google.com/maps?" + saddr + "&" + daddr;

        Uri uri = Uri.parse(uriString);

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");

        context.startActivity(intent);
    }
}
