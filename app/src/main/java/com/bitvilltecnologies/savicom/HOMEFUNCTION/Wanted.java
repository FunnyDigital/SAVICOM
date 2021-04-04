package com.bitvilltecnologies.savicom.HOMEFUNCTION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bitvilltecnologies.savicom.Feed;
import com.bitvilltecnologies.savicom.MODELS.News_feed_Model;
import com.bitvilltecnologies.savicom.MODELS.Wanted_model;
import com.bitvilltecnologies.savicom.R;
import com.bitvilltecnologies.savicom.ViewHolder;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Wanted extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
   // LinearLayoutManager mLayoutManager;
    GridLayoutManager mLayoutManager;
    ImageButton searchbtn;
    EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wanted);



       // mLayoutManager = new LinearLayoutManager(Wanted.this);
        mLayoutManager = new GridLayoutManager(Wanted.this,2);
       //mLayoutManager.setReverseLayout(true);
       //mLayoutManager.setStackFromEnd(true);
        mLayoutManager.getReverseLayout();



        recyclerView = findViewById(R.id.recycler_wanted);
        recyclerView.setHasFixedSize(true);
       //recyclerView.setLayoutManager(new  GridLayoutManager(this));
        recyclerView.setLayoutManager(mLayoutManager);

        searchbtn=(ImageButton)findViewById(R.id.searchbtn);
        searchEditText=(EditText)findViewById(R.id.searchtextedittext);

       // firebaseDatabase =FirebaseDatabase.getInstance();
        //reference= firebaseDatabase.getReference("WantedCriminals");

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                firebaseSearch();

            }
        });

        FeedaAdapter();
    }

    private void firebaseSearch() {
   //databaseReference = firebaseDatabase.getReference().child("WantedCriminals/");
    DatabaseReference mRef =FirebaseDatabase.getInstance().getReferenceFromUrl("https://savicom-888e1-default-rtdb.firebaseio.com/WantedCriminals");
        mRef.child("WantedCriminals");
        String searchItem = searchEditText.getText().toString().toUpperCase();
        String searchItemL = searchEditText.getText().toString().toLowerCase();
        Toast.makeText(Wanted.this,"Searching...",Toast.LENGTH_LONG).show();

        Query query = databaseReference.orderByChild("wantedname").startAt(searchItem).endAt(searchItemL+"\uf8ff");
        FirebaseRecyclerOptions<Wanted_model>options = new FirebaseRecyclerOptions.Builder<Wanted_model>().setQuery(query,Wanted_model.class).build();
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Wanted_model, ViewHolder>(options){

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wanted_cardview,parent,false);
                return  new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Wanted_model wanted_model) {
                viewHolder.setWanted(wanted_model.getWantedname(),wanted_model.getWantedcrime(),wanted_model.getWanteddescription(),wanted_model.getWantedimg());

            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void FeedaAdapter() {

        Query query = FirebaseDatabase.getInstance().getReference().child("WantedCriminals").limitToLast(50);
        FirebaseRecyclerOptions<Wanted_model> options=new FirebaseRecyclerOptions.Builder<Wanted_model>().setQuery(query, Wanted_model.class).build();
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Wanted_model, ViewHolder>(options) {


            @Override
            protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Wanted_model wanted_model) {
                viewHolder.setWanted(wanted_model.getWantedname(),wanted_model.getWantedcrime(),wanted_model.getWanteddescription(),wanted_model.getWantedimg());
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wanted_cardview,parent,false);
                return  new ViewHolder(view);
            }

        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}