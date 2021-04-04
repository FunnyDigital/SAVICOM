package com.bitvilltecnologies.savicom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.bitvilltecnologies.savicom.AUTH.Profile;
import com.bitvilltecnologies.savicom.HOMEFUNCTION.LostItem;
import com.bitvilltecnologies.savicom.HOMEFUNCTION.Wanted;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    ImageButton wantedbtn,lostbtn;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:

                    return true;

                case R.id.newsfeed:
                    Intent intent =new Intent(HomeActivity.this,Feed.class);
                    finish();
                    startActivity(intent);
                    return true;

                case R.id.profile:
                    Intent intent2 = new Intent(HomeActivity.this, Profile.class);
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
        setContentView(R.layout.activity_home);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        wantedbtn=(ImageButton)findViewById(R.id.wantedbtn);
        lostbtn=(ImageButton)findViewById(R.id.lostbtn);

        wantedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(HomeActivity.this, Wanted.class);
                startActivity(intent1);
                finish();
            }
        });


      lostbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(HomeActivity.this, LostItem.class);
                startActivity(intent1);
                finish();
            }
        });
    }
}
