package com.bitvilltecnologies.savicom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feed extends AppCompatActivity {
    ImageView imageViewno;
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    LinearLayoutManager mLayoutManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    Intent intent =new Intent(Feed.this,HomeActivity.class);
                    finish();

                    startActivity(intent);
                    return true;

                case R.id.newsfeed:

                    return true;

                case R.id.profile:
                    Intent intent2 = new Intent(Feed.this,Profile.class);
                    finish();
                    startActivity(intent2);
                    return true;

            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.newsfeed);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mLayoutManager = new LinearLayoutManager(Feed.this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);


        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(mLayoutManager);



        firebaseDatabase =FirebaseDatabase.getInstance();
        reference= firebaseDatabase.getReference("Users/POSTS");

        //imageViewno=findViewById(R.id.imageViewno);
        if (!TextUtils.isEmpty(Config.imageLink))
            Picasso.get().load(Config.imageLink).into(imageViewno);
    }


}
