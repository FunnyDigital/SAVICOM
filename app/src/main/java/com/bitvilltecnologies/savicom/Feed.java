package com.bitvilltecnologies.savicom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

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
       // if (!TextUtils.isEmpty(Config.imageLink))
         //   Picasso.get().load(Config.imageLink).into(imageViewno);

        FeedaAdapter();
    }

    private void FeedaAdapter() {
        Query query = FirebaseDatabase.getInstance().getReference().child("feed").limitToLast(50);
        FirebaseRecyclerOptions<Model>options=new FirebaseRecyclerOptions.Builder<Model>().setQuery(query,Model.class).build();
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Model,ViewHolder>(options) {


            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_custom_rom,parent,false);
                return  new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int position, @NonNull Model model) {
                viewHolder.setDetails(model.getTitle(),model.getImage(),model.getDescription());


            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }


}
