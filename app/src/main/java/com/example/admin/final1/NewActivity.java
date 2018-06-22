package com.example.admin.final1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class NewActivity extends AppCompatActivity {
 
    ListViewAdapter_food adapter;
    private static final int LIST_Food = 1;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LIST_Food: {
                    List<Food> foods = (List<Food>)msg.obj;
                    refreshTourList(foods);
                    break;
                }
            }
        }
    };

    private void refreshTourList(List<Food> foods) {
        adapter.clear();
        adapter.addAll(foods);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("近期開幕");

        ListView lvFood = (ListView) findViewById(R.id.list_newfood);

        adapter = new ListViewAdapter_food(this, new ArrayList<Food>());
        lvFood.setAdapter(adapter);

        getTourFromFirebase();
    }

    class FirebaseThread extends Thread {

        private DataSnapshot dataSnapshot;

        public FirebaseThread(DataSnapshot dataSnapshot) {
            this.dataSnapshot = dataSnapshot;
        }

        @Override
        public void run() {
            List<Food> lsTour = new ArrayList<>();
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
                lsTour.add(aFood);
                Log.v("Food", FoodTitle + ";" + add);
            }
            Message msg = new Message();
            msg.what = LIST_Food;
            msg.obj = lsTour;
            handler.sendMessage(msg);
        }
    }

    private void getTourFromFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("new_food");
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
