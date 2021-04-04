package com.bitvilltecnologies.savicom.AUTH;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bitvilltecnologies.savicom.HomeActivity;
import com.bitvilltecnologies.savicom.MODELS.User_Model;
import com.bitvilltecnologies.savicom.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText name,email,phone,address,password,password2,nin;
    Button signupbtn;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = (EditText)findViewById(R.id.signup_name);
        email = (EditText)findViewById(R.id.signup_email_edittext);
        phone = (EditText)findViewById(R.id.signupphone);
        address = (EditText)findViewById(R.id.signupaddress);
        password = (EditText)findViewById(R.id.signupeditextpassword);
        password2 = (EditText)findViewById(R.id.confirmpasswordeditext);
        nin = (EditText)findViewById(R.id.nin);
        mAuth = FirebaseAuth.getInstance();
        signupbtn=(Button)findViewById(R.id.signupAbtn);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUser();
            }
        });

    }

    private void RegisterUser() {
      final String Name = name.getText().toString().trim();
       final String Email = email.getText().toString().trim();
       final String Address = address.getText().toString().trim();
     String Password = password.getText().toString().trim();
      String Password2 = password2.getText().toString().trim();
       final String Phone = phone.getText().toString().trim();
       final String Nin = nin.getText().toString().trim();


        if (TextUtils.isEmpty(Name)){
            Toast.makeText(SignupActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(Email)){
            Toast.makeText(SignupActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(Password)){
            Toast.makeText(SignupActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }else if (Password.length()<6){
            Toast.makeText(SignupActivity.this,"Password must be greater then 6 digit",Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User_Model user_model=new User_Model(Name,Email,Phone,Address,Nin);
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance()
                            .getCurrentUser().getUid())
                            .setValue(user_model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                //Toast.makeText(SignupActivity.this,"WELCOME PROGRAMMER ",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    finish();
                    Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                    startActivity(intent);
                    Toast.makeText(SignupActivity.this,"REGISTERED",Toast.LENGTH_LONG).show();
                }else {

                    if (task.getException()instanceof FirebaseAuthException){

                        Toast.makeText(getApplicationContext(),"YOU ARE ALREADY REGISTERED",Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });


    }
}