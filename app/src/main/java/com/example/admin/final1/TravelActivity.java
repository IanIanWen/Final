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

public class TravelActivity extends AppCompatActivity {

    ListViewAdapter_travel adapter;
    private static final int LIST_Tour = 1;


    private ImageButton item_map;
    private ImageButton item_wallet;
    private ImageButton item_collect;
    private ImageButton item_chat;
    private ViewPager pager;
    private int[] image = {R.drawable.travel_1_resized, R.drawable.travel_2_resized, R.drawable.travel_3_resized
            , R.drawable.travel_4_resized, R.drawable.travel_5_resized};
    ListView lvTravel;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LIST_Tour: {
                    List<Travel> tours = (List<Travel>)msg.obj;
                    refreshFoodList(tours);
                    break;
                }
            }
        }
    };

    private void refreshFoodList(List<Travel> tours) {
        adapter.clear();
        adapter.addAll(tours);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("熱門景點");

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

        //Map 按鈕
        item_map = (ImageButton) this.findViewById(R.id.Map);
        item_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TravelActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        //Wallet按鈕
        item_wallet = (ImageButton) this.findViewById(R.id.Wallet);
        item_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TravelActivity.this, WalletActivity_food.class);
                startActivity(intent);
            }
        });

        //Collection 按鈕
        item_collect = (ImageButton) this.findViewById(R.id.Collect);
        item_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TravelActivity.this, CollectionActivity_travel.class);
                startActivity(intent);
            }
        });

        //ChatRoom 按鈕
        item_chat = (ImageButton) this.findViewById(R.id.Chat);
        item_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TravelActivity.this, Travel_Board.class);
                startActivity(intent);
            }
        });

        //ListView美食
        lvTravel = (ListView) findViewById(R.id.list_travel);

        adapter = new ListViewAdapter_travel(this, new ArrayList<Travel>());
        lvTravel.setAdapter(adapter);

        getFoodFromFirebase();
    }

    class FirebaseThread extends Thread {

        private DataSnapshot dataSnapshot;

        public FirebaseThread(DataSnapshot dataSnapshot) {
            this.dataSnapshot = dataSnapshot;
        }

        @Override
        public void run() {
            List<Travel> lsTravel = new ArrayList<>();
            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                DataSnapshot dsSTitle = ds.child("title");
                DataSnapshot dsADD = ds.child("address");
                DataSnapshot dsIntro = ds.child("intro");

                String TravelTitle = (String)dsSTitle.getValue();
                String add = (String)dsADD.getValue();
                String Intro = (String)dsIntro.getValue();
                DataSnapshot dsImg = ds.child("img");
                String imgUrl = (String) dsImg.getValue();
                Bitmap foodImg = getImgBitmap(imgUrl);

                Travel aTravel = new Travel();
                aTravel.setTitle(TravelTitle);
                aTravel.setAddress(add);
                aTravel.setIntro(Intro);

                aTravel.setImgUrl(foodImg);
                aTravel.setImgLink(imgUrl);
                lsTravel.add(aTravel);
                Log.v("Travel", TravelTitle + ";" + add);
            }
            Message msg = new Message();
            msg.what = LIST_Tour;
            msg.obj = lsTravel;
            handler.sendMessage(msg);
        }
    }

    private void getFoodFromFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("travel");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                new FirebaseThread(dataSnapshot).start();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("Travel", databaseError.getMessage());
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
