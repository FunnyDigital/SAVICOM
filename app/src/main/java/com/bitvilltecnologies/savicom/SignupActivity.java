package com.bitvilltecnologies.savicom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText TextEmail,Textpassword2,Textpassword,phonetxt,addresstxt,nametxt;
    Button Signupbtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Textpassword2=(EditText)findViewById(R.id.confirmpasswordeditext);
        Textpassword =(EditText)findViewById(R.id.signupeditextpassword);
        TextEmail=(EditText)findViewById(R.id.signupemaileditext);
        phonetxt=(EditText)findViewById(R.id.signupphone);
        addresstxt=(EditText)findViewById(R.id.signupaddress);
        Signupbtn=(Button)findViewById(R.id.signupAbtn);
        nametxt=(EditText)findViewById(R.id.signupname);
        mAuth=FirebaseAuth.getInstance();

        Signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterUser();
            }
        });

    }

    private void RegisterUser() {
        final String email =TextEmail.getText().toString().trim();
       final String password= Textpassword2.getText().toString().trim();
       final String password1= Textpassword.getText().toString().trim();
        final String phone=phonetxt.getText().toString().trim();
        final String address=addresstxt.getText().toString().trim();
        final  String name=nametxt.getText().toString().trim();

        if (email.isEmpty()){

            TextEmail.setHint("Email cant be empty");
            TextEmail.requestFocus();
            return;
        }

        if (name.isEmpty()){

            nametxt.setHint("Type in your full name");
            nametxt.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            TextEmail.setHint("Type a valid email");
            TextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            Textpassword2.setHint("password is empty");
            Textpassword2.requestFocus();
            return;
        }

        if (password1.isEmpty()){
            Textpassword.setHint("password is empty");
            Textpassword.requestFocus();
            return;
        }

        if (password.matches(password1) && name !=null && phone !=null && address !=null  && email !=null){



            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                   // progressBar.setVisibility(View.GONE);
                    if(task.isSuccessful()){





                        User user = new User(

                               name,
                                phone,
                                address,
                                email

                        );
                        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance()
                                .getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(SignupActivity.this,"Welcome",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        finish();

                        Intent intent = new Intent(SignupActivity.this,HomeActivity.class);
                        startActivity(intent);


                        Toast.makeText(getApplicationContext(),"You are already registered",Toast.LENGTH_SHORT).show();

                    }else {

                        if (task.getException()instanceof FirebaseAuthException){

                            Toast.makeText(getApplicationContext(),"You are already registered",Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        }else {

            Toast.makeText(getApplicationContext(),"PASSWORD DON'T MATCH", Toast.LENGTH_LONG).show();
        }


    }
}
