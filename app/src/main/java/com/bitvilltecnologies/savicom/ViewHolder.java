package com.bitvilltecnologies.savicom;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView ;
    public ViewHolder(View itemView) {
        super(itemView);

        mView =  itemView;

    }
    public void  setDetails( String title,String image, String description){


        TextView mTiltleView = mView.findViewById(R.id.titlez);
        ImageView mimageView = mView.findViewById(R.id.rimageview);
        TextView mdiscription = mView.findViewById(R.id.rdiscriptionv);


        mTiltleView.setText(title);
        mdiscription.setText(description);
        Picasso.get().load(image).into(mimageView);

    }

}