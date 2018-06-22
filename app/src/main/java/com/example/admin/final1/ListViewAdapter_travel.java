package com.example.admin.final1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter_travel extends ArrayAdapter<Travel> {

    Context context;

    public ListViewAdapter_travel(Context context, List<Travel> items) {
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
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_travel.class);

                    Travel2 t2 = new Travel2();
                    t2.setAddress(item.getAddress());
                    t2.setIntro(item.getIntro());
                    t2.setTitle(item.getTitle());
                    t2.setImgLink(item.getImgLink());
                    intent.putExtra("item", t2);

                    int[] image = {R.drawable.l1_resized, R.drawable.l2_resized, R.drawable.l3_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("彩虹眷村")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_travel.class);

                    Travel2 t2 = new Travel2();
                    t2.setAddress(item.getAddress());
                    t2.setIntro(item.getIntro());
                    t2.setTitle(item.getTitle());
                    t2.setImgLink(item.getImgLink());
                    intent.putExtra("item", t2);

                    int[] image = {R.drawable.m1_resized, R.drawable.m2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("東海藝術街")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_travel.class);

                    Travel2 t2 = new Travel2();
                    t2.setAddress(item.getAddress());
                    t2.setIntro(item.getIntro());
                    t2.setTitle(item.getTitle());
                    t2.setImgLink(item.getImgLink());
                    intent.putExtra("item", t2);

                    int[] image = {R.drawable.n1_resized, R.drawable.n2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("知高圳步道")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_travel.class);

                    Travel2 t2 = new Travel2();
                    t2.setAddress(item.getAddress());
                    t2.setIntro(item.getIntro());
                    t2.setTitle(item.getTitle());
                    t2.setImgLink(item.getImgLink());
                    intent.putExtra("item", t2);

                    int[] image = {R.drawable.o1_resized, R.drawable.o2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("高美濕地")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_travel.class);

                    Travel2 t2 = new Travel2();
                    t2.setAddress(item.getAddress());
                    t2.setIntro(item.getIntro());
                    t2.setTitle(item.getTitle());
                    t2.setImgLink(item.getImgLink());
                    intent.putExtra("item", t2);

                    int[] image = {R.drawable.p1_resized, R.drawable.p2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("勤美術館")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_travel.class);

                    Travel2 t2 = new Travel2();
                    t2.setAddress(item.getAddress());
                    t2.setIntro(item.getIntro());
                    t2.setTitle(item.getTitle());
                    t2.setImgLink(item.getImgLink());
                    intent.putExtra("item", t2);

                    int[] image = {R.drawable.q1_resized, R.drawable.q2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("九天黑森林")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_travel.class);

                    Travel2 t2 = new Travel2();
                    t2.setAddress(item.getAddress());
                    t2.setIntro(item.getIntro());
                    t2.setTitle(item.getTitle());
                    t2.setImgLink(item.getImgLink());
                    intent.putExtra("item", t2);

                    int[] image = {R.drawable.q1_resized, R.drawable.q2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("太平區公所3D彩繪牆")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_travel.class);

                    Travel2 t2 = new Travel2();
                    t2.setAddress(item.getAddress());
                    t2.setIntro(item.getIntro());
                    t2.setTitle(item.getTitle());
                    t2.setImgLink(item.getImgLink());
                    intent.putExtra("item", t2);

                    int[] image = {R.drawable.q1_resized, R.drawable.q2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }
            }
        });
        return itemlayout;
    }
}
