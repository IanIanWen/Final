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

public class CollectionActivity_travel extends AppCompatActivity {

    ListViewAdapter_travel_collect adapter;
    private static final int LIST_Tour = 1;

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
        setContentView(R.layout.activity_collection_travel);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("收藏名單");

        ListView lvTour = (ListView) findViewById(R.id.list_collect);

        adapter = new ListViewAdapter_travel_collect(this, new ArrayList<Travel>());
        lvTour.setAdapter(adapter);

        getTourFromFirebase();
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

    private void getTourFromFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("collect_travel");
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
