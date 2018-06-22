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

public class ListViewAdapter_food extends ArrayAdapter<Food> {

    Context context;

    public ListViewAdapter_food(Context context, List<Food> items) {
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
        final Food item = (Food) getItem(position);
        TextView tvTitle = (TextView) itemlayout.findViewById(R.id.Title);
        tvTitle.setText(item.getTitle());
        TextView tvAdd = (TextView) itemlayout.findViewById(R.id.Add);
        tvAdd.setText(item.getAddress());
        ImageView ivFood = (ImageView) itemlayout.findViewById(R.id.Icon);
        ivFood.setImageBitmap(item.getImgUrl());

        itemlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.getTitle().equals("Mr.Bar風味炭燒")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_food.class);

                    Food2 f2 = new Food2();
                    f2.setAddress(item.getAddress());
                    f2.setIntro(item.getIntro());
                    f2.setTitle(item.getTitle());
                    f2.setImgLink(item.getImgLink());
                    intent.putExtra("item", f2);

                    int[] image = {R.drawable.a1, R.drawable.a2, R.drawable.a3,  R.drawable.a4};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("Dody Duke馬鈴薯專門店")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_food.class);

                    Food2 f2 = new Food2();
                    f2.setAddress(item.getAddress());
                    f2.setIntro(item.getIntro());
                    f2.setTitle(item.getTitle());
                    f2.setImgLink(item.getImgLink());
                    intent.putExtra("item", f2);

                    int[] image = {R.drawable.b1_resized, R.drawable.b2_resized, R.drawable.b3_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("三清洞摩西韓式年糕鍋")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_food.class);

                    Food2 f2 = new Food2();
                    f2.setAddress(item.getAddress());
                    f2.setIntro(item.getIntro());
                    f2.setTitle(item.getTitle());
                    f2.setImgLink(item.getImgLink());
                    intent.putExtra("item", f2);

                    int[] image = {R.drawable.c1_resized, R.drawable.c2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("許生煎包")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_food.class);

                    Food2 f2 = new Food2();
                    f2.setAddress(item.getAddress());
                    f2.setIntro(item.getIntro());
                    f2.setTitle(item.getTitle());
                    f2.setImgLink(item.getImgLink());
                    intent.putExtra("item", f2);

                    int[] image = {R.drawable.d1_resized, R.drawable.d2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("老虎堂（逢甲店）")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_food.class);

                    Food2 f2 = new Food2();
                    f2.setAddress(item.getAddress());
                    f2.setIntro(item.getIntro());
                    f2.setTitle(item.getTitle());
                    f2.setImgLink(item.getImgLink());
                    intent.putExtra("item", f2);

                    int[] image = {R.drawable.e1_resized, R.drawable.e2_resized, R.drawable.e3_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("養鍋Yang Guo石頭涮涮鍋")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_food.class);

                    Food2 f2 = new Food2();
                    f2.setAddress(item.getAddress());
                    f2.setIntro(item.getIntro());
                    f2.setTitle(item.getTitle());
                    f2.setImgLink(item.getImgLink());
                    intent.putExtra("item", f2);

                    int[] image = {R.drawable.f1_resized, R.drawable.f2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("雞排本色 – 逢甲店")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_food.class);

                    Food2 f2 = new Food2();
                    f2.setAddress(item.getAddress());
                    f2.setIntro(item.getIntro());
                    f2.setTitle(item.getTitle());
                    f2.setImgLink(item.getImgLink());
                    intent.putExtra("item", f2);

                    int[] image = {R.drawable.g1_resized, R.drawable.g2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("朴大哥韓式炸雞")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_food.class);

                    Food2 f2 = new Food2();
                    f2.setAddress(item.getAddress());
                    f2.setIntro(item.getIntro());
                    f2.setTitle(item.getTitle());
                    f2.setImgLink(item.getImgLink());
                    intent.putExtra("item", f2);

                    int[] image = {R.drawable.h1_resized, R.drawable.h2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("菜田開門 捲心蔬")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_food.class);

                    Food2 f2 = new Food2();
                    f2.setAddress(item.getAddress());
                    f2.setIntro(item.getIntro());
                    f2.setTitle(item.getTitle());
                    f2.setImgLink(item.getImgLink());
                    intent.putExtra("item", f2);

                    int[] image = {R.drawable.i1_resized, R.drawable.i2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("櫻桃計畫 cherry")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_food.class);

                    Food2 f2 = new Food2();
                    f2.setAddress(item.getAddress());
                    f2.setIntro(item.getIntro());
                    f2.setTitle(item.getTitle());
                    f2.setImgLink(item.getImgLink());
                    intent.putExtra("item", f2);

                    int[] image = {R.drawable.j1_resized, R.drawable.j2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("返樸朝食")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_food.class);

                    Food2 f2 = new Food2();
                    f2.setAddress(item.getAddress());
                    f2.setIntro(item.getIntro());
                    f2.setTitle(item.getTitle());
                    f2.setImgLink(item.getImgLink());
                    intent.putExtra("item", f2);

                    int[] image = {R.drawable.k1_resized, R.drawable.k2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("鎮新記")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_food.class);

                    Food2 f2 = new Food2();
                    f2.setAddress(item.getAddress());
                    f2.setIntro(item.getIntro());
                    f2.setTitle(item.getTitle());
                    f2.setImgLink(item.getImgLink());
                    intent.putExtra("item", f2);

                    int[] image = {R.drawable.k1_resized, R.drawable.k2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("H:Cafe")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_food.class);

                    Food2 f2 = new Food2();
                    f2.setAddress(item.getAddress());
                    f2.setIntro(item.getIntro());
                    f2.setTitle(item.getTitle());
                    f2.setImgLink(item.getImgLink());
                    intent.putExtra("item", f2);

                    int[] image = {R.drawable.k1_resized, R.drawable.k2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("拾陌 Shihmo")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_food.class);

                    Food2 f2 = new Food2();
                    f2.setAddress(item.getAddress());
                    f2.setIntro(item.getIntro());
                    f2.setTitle(item.getTitle());
                    f2.setImgLink(item.getImgLink());
                    intent.putExtra("item", f2);

                    int[] image = {R.drawable.k1_resized, R.drawable.k2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("我流精緻烤物")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_food.class);

                    Food2 f2 = new Food2();
                    f2.setAddress(item.getAddress());
                    f2.setIntro(item.getIntro());
                    f2.setTitle(item.getTitle());
                    f2.setImgLink(item.getImgLink());
                    intent.putExtra("item", f2);

                    int[] image = {R.drawable.k1_resized, R.drawable.k2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("墨村食所 MoCun")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_food.class);

                    Food2 f2 = new Food2();
                    f2.setAddress(item.getAddress());
                    f2.setIntro(item.getIntro());
                    f2.setTitle(item.getTitle());
                    f2.setImgLink(item.getImgLink());
                    intent.putExtra("item", f2);

                    int[] image = {R.drawable.k1_resized, R.drawable.k2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("隨主飡黎明店")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_food.class);

                    Food2 f2 = new Food2();
                    f2.setAddress(item.getAddress());
                    f2.setIntro(item.getIntro());
                    f2.setTitle(item.getTitle());
                    f2.setImgLink(item.getImgLink());
                    intent.putExtra("item", f2);

                    int[] image = {R.drawable.k1_resized, R.drawable.k2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("KoDo和牛燒肉")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_food.class);

                    Food2 f2 = new Food2();
                    f2.setAddress(item.getAddress());
                    f2.setIntro(item.getIntro());
                    f2.setTitle(item.getTitle());
                    f2.setImgLink(item.getImgLink());
                    intent.putExtra("item", f2);

                    int[] image = {R.drawable.k1_resized, R.drawable.k2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("貳捌麵魂")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_food.class);

                    Food2 f2 = new Food2();
                    f2.setAddress(item.getAddress());
                    f2.setIntro(item.getIntro());
                    f2.setTitle(item.getTitle());
                    f2.setImgLink(item.getImgLink());
                    intent.putExtra("item", f2);

                    int[] image = {R.drawable.k1_resized, R.drawable.k2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("夏爾 Shire")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_food.class);

                    Food2 f2 = new Food2();
                    f2.setAddress(item.getAddress());
                    f2.setIntro(item.getIntro());
                    f2.setTitle(item.getTitle());
                    f2.setImgLink(item.getImgLink());
                    intent.putExtra("item", f2);

                    int[] image = {R.drawable.k1_resized, R.drawable.k2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }

                if (item.getTitle().equals("瞞著爹台中精誠丼飯")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(context, Listview_change_food.class);

                    Food2 f2 = new Food2();
                    f2.setAddress(item.getAddress());
                    f2.setIntro(item.getIntro());
                    f2.setTitle(item.getTitle());
                    f2.setImgLink(item.getImgLink());
                    intent.putExtra("item", f2);

                    int[] image = {R.drawable.k1_resized, R.drawable.k2_resized};
                    bundle.putIntArray("pic",image);//加入圖片的id
                    intent.putExtras(bundle);// 將Bundle物件加入Intent物件中

                    context.startActivity(intent);// 轉換至下一個Activity
                }
            }
        });
        return itemlayout;
    }
}