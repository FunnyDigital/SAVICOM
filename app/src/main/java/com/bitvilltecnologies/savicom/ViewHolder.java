package com.bitvilltecnologies.savicom;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

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


    public  void  setWanted(String wantedname, String wantedcrime, String wanteddescription,String wantedimg){
        TextView Wantednametxt = mView.findViewById(R.id.wantedname);
        TextView Wantedcrimetxt = mView.findViewById(R.id.wantedcrime);
        TextView Wanteddescription = mView.findViewById(R.id.wanteddescription);
        ImageView WimageView = mView.findViewById(R.id.wantedimg);

        Wantednametxt.setText(wantedname);
    Wantedcrimetxt.setText(wantedcrime);
    Wanteddescription.setText(wanteddescription);
    Picasso.get().load(wantedimg).into(WimageView);


    }

    public void setLost(String lostimage, String lostdate, String lostdes) {

        ImageView Lostimage = mView.findViewById(R.id.lostImage);
        TextView Lostdate = mView.findViewById(R.id.date);
        TextView Lostdes = mView.findViewById(R.id.description_of_lostItem);

        Picasso.get().load(lostimage).into(Lostimage);
        Lostdate.setText(lostdate);
        Lostdes.setText(lostdes);

    }


}