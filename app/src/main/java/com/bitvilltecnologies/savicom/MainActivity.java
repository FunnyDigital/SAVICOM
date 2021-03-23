package com.bitvilltecnologies.savicom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.bitvilltecnologies.savicom.AUTH.LoginActivity;

public class MainActivity extends AppCompatActivity {
public static  int SPLASH_TIME =5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splash = new Intent(MainActivity.this , LoginActivity.class);
                startActivity(splash);
            }
        },SPLASH_TIME);
    }
}
