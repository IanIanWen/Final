package com.example.admin.final1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class ListView_change_food_collect extends AppCompatActivity {

    private ViewPager pager;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_change_food_collect);

        Bundle bundle=getIntent().getExtras();
        int[] image = bundle.getIntArray("pic");

        pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setOffscreenPageLimit(1);
        pager.setAdapter(new InfiniteAdapter(this, image));

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("Pager", String.valueOf(pager.getCurrentItem() + 1));
                pager.setCurrentItem(pager.getCurrentItem() + 1);
                handler.postDelayed(this, 2000);
            }
        }, 2000);

        ActionBar actionBar = getSupportActionBar();
        TextView mDetailTv = findViewById(R.id.text_cotent);  //介紹文

        Intent intent = getIntent();

        Food2 food = (Food2)intent.getSerializableExtra("item");
        //set actionbar title
        actionBar.setTitle(food.getTitle());
        //set text in textview
        mDetailTv.setText("地址: " + food.getAddress() + "\n" + "介紹: " + food.getIntro());
    }
}
