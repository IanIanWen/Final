package com.example.admin.final1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {

    ListViewAdapter_food adapter;
    private static final int LIST_Food = 1;


    private ViewPager pager;
    private int[] image = {R.drawable.food_1, R.drawable.food_2, R.drawable.food_3, R.drawable.food_4, R.drawable.food_5};
    private ImageButton item_new;
    private ImageButton item_wallet;
    private ImageButton item_collect;
    private ImageButton item_chat;
    ListView lvFood;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LIST_Food: {
                    List<Food> foods = (List<Food>)msg.obj;
                    refreshFoodList(foods);
                    break;
                }
            }
        }
    };

    private void refreshFoodList(List<Food> foods) {
        adapter.clear();
        adapter.addAll(foods);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("私房美食");

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

        //近期開幕按鈕
        item_new = (ImageButton) this.findViewById(R.id.New);
        item_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FoodActivity.this, NewActivity.class);
                startActivity(intent);
            }
        });

        //Wallet按鈕
        item_wallet = (ImageButton) this.findViewById(R.id.Wallet);
        item_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FoodActivity.this, WalletActivity_food.class);
                startActivity(intent);
            }
        });

        //Collection 按鈕
        item_collect = (ImageButton) this.findViewById(R.id.Collect);
        item_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FoodActivity.this, CollectionActivity_food.class);
                startActivity(intent);
            }
        });

        //ChatRoom 按鈕
        item_chat = (ImageButton) this.findViewById(R.id.Chat);
        item_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FoodActivity.this, Food_Board.class);
                startActivity(intent);
            }
        });

        //ListView美食
        lvFood = (ListView) findViewById(R.id.list_food);

        adapter = new ListViewAdapter_food(this, new ArrayList<Food>());
        lvFood.setAdapter(adapter);

        getFoodFromFirebase();
    }

    class FirebaseThread extends Thread {

        private DataSnapshot dataSnapshot;

        public FirebaseThread(DataSnapshot dataSnapshot) {
            this.dataSnapshot = dataSnapshot;
        }

        @Override
        public void run() {
            List<Food> lsFood = new ArrayList<>();
            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                DataSnapshot dsSTitle = ds.child("title");
                DataSnapshot dsADD = ds.child("address");
                DataSnapshot dsIntro = ds.child("intro");

                String FoodTitle = (String)dsSTitle.getValue();
                String add = (String)dsADD.getValue();
                String Intro = (String)dsIntro.getValue();
                DataSnapshot dsImg = ds.child("img");
                String imgUrl = (String) dsImg.getValue();
                Bitmap foodImg = getImgBitmap(imgUrl);

                Food aFood = new Food();
                aFood.setTitle(FoodTitle);
                aFood.setAddress(add);
                aFood.setIntro(Intro);

                aFood.setImgUrl(foodImg);
                aFood.setImgLink(imgUrl);
                lsFood.add(aFood);
                Log.v("Food", FoodTitle + ";" + add);
            }
            Message msg = new Message();
            msg.what = LIST_Food;
            msg.obj = lsFood;
            handler.sendMessage(msg);
        }
    }

    private void getFoodFromFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("food");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                new FirebaseThread(dataSnapshot).start();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("Food", databaseError.getMessage());
            }
        });
    }

    private Bitmap getImgBitmap(String imgUrl) {
        try {
            URL url = new URL(imgUrl);
            Bitmap bm = BitmapFactory.decodeStream(url.openConnection()
                    .getInputStream());
            return bm;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
