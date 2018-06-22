package com.example.admin.final1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Listview_change_food extends AppCompatActivity {

    private ViewPager pager;
    private Handler handler = new Handler();
    Button collect;


    //a list to store all the artist from firebase database
    List<Artist> artists;

    //our database reference object
    DatabaseReference databaseArtists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_change_food);

        collect = findViewById(R.id.btn_collect);

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

        databaseArtists = FirebaseDatabase.getInstance().getReference("collect_food");

        artists = new ArrayList<>();
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArtist();
            }
        });
    }

    private void addArtist() {
        Intent intent = getIntent();

        Food2 food = (Food2)intent.getSerializableExtra("item");

        //checking if the value is provided
        if (!TextUtils.isEmpty(food.getTitle())) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = databaseArtists.push().getKey();

            //creating an Artist Object
            Artist artist = new Artist(id, food.getAddress(), food.getImgLink(),
                    food.getIntro(), food.getTitle());

            //Saving the Artist
            databaseArtists.child(id).setValue(artist);

            food.setId(id);

            //displaying a success toast
            Toast.makeText(this, "Add to collection list !!!", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please try again!", Toast.LENGTH_LONG).show();
        }
    }
}
