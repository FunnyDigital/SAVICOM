package com.bitvilltecnologies.savicom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void signup (View view){
        Intent signup =new Intent(LoginActivity.this , SignupActivity.class);
        startActivity(signup);
    }
}
