package com.example.admin.final1;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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

public class MapActivity extends AppCompatActivity {

    ListViewAdapter_map adapter;
    static final int REQUEST_LOCATION = 1;
    private LocationManager locationManager;
    private static final int LIST_Tour = 1;

    private double latti;
    private double longi;

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
        setContentView(R.layout.activity_map);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("地圖導覽");

        //取的現在位置
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getLocation();

        ListView lvTour = (ListView) findViewById(R.id.list_map);

        adapter = new ListViewAdapter_map(this, new ArrayList<Travel>());
        lvTour.setAdapter(adapter);

        getTourFromFirebase();
    }

    void getLocation() {
        if (ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MapActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);

            if(location != null) {
                latti = location.getLatitude();
                longi = location.getLongitude();
            } else {

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_LOCATION:
                getLocation();
                break;
        }
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
                DataSnapshot dsImg = ds.child("img");
                DataSnapshot dspx = ds.child("Px");
                DataSnapshot dspy = ds.child("Py");

                String TravelTitle = (String)dsSTitle.getValue();
                String add = (String)dsADD.getValue();
                String Intro = (String)dsIntro.getValue();
                String imgUrl = (String) dsImg.getValue();
                Bitmap foodImg = getImgBitmap(imgUrl);
                double px = (double)dspx.getValue();
                double py = (double)dspy.getValue();

                Travel aTravel = new Travel();
                aTravel.setTitle(TravelTitle);
                aTravel.setAddress(add);
                aTravel.setIntro(Intro);
                aTravel.setLatti(latti);
                aTravel.setLongi(longi);
                aTravel.setPx(px);
                aTravel.setPy(py);

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
