package com.bitvilltecnologies.savicom.HOMEFUNCTION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitvilltecnologies.savicom.MODELS.Lost_item_Model;
import com.bitvilltecnologies.savicom.R;
import com.bitvilltecnologies.savicom.ViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class LostItem extends AppCompatActivity {
    GridLayoutManager mLayoutManager;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_item);

        mLayoutManager = new GridLayoutManager(LostItem.this,2);
        mLayoutManager.getReverseLayout();

        recyclerView = findViewById(R.id.lostRycycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);

        LostItemAdapter();
    }

    private void LostItemAdapter() {
        Query query = FirebaseDatabase.getInstance().getReference().child("LostItem").limitToLast(50);
        FirebaseRecyclerOptions<Lost_item_Model>options = new
                FirebaseRecyclerOptions.Builder<Lost_item_Model>().setQuery(query,Lost_item_Model.class).build();
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Lost_item_Model, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Lost_item_Model lost_item_model) {
                viewHolder.setLost(lost_item_model.getLostimage(),lost_item_model.getLostdate(),lost_item_model.getLostdes());
            }



            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lost_cardview,parent,false);
                return  new ViewHolder(view);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    public void addLost(View view){

        Intent intent = new Intent(LostItem.this, AddLost.class);
        startActivity(intent);

    }
}