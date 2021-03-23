package com.bitvilltecnologies.savicom.AUTH;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bitvilltecnologies.savicom.HomeActivity;
import com.bitvilltecnologies.savicom.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
Button loginbtn;
EditText emailtxt,passwordtxt;

    private FirebaseAuth.AuthStateListener mAuthlistener;
    final  String TAG ="ViewDatabase";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginbtn=(Button)findViewById(R.id.loginbtn);
        emailtxt=(EditText)findViewById(R.id.emaileditext);
        passwordtxt=(EditText)findViewById(R.id.passwordeditext);


        mAuth = FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginMethod();
            }
        });

        mAuthlistener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Log.d(TAG,"onAuthStateChanged:signed_in:"+user.getUid());
                    toastmessage("Welcome");
                }else {

                }
            }
        };


    }


    private void LoginMethod() {
        String email = emailtxt.getText().toString().trim();
        String password = passwordtxt.getText().toString().trim();

        if (email.isEmpty()) {
            emailtxt.setHint("Email cant be empty");
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailtxt.setText("Type a valid Email");
            emailtxt.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordtxt.setHint("Password cant be empty");
            passwordtxt.requestFocus();
            return;
        }
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Signing in.....");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful())  {
                  progressDialog.dismiss();
                  Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                  Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_SHORT).show();
                  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                  startActivity(intent);
              }else {
                  progressDialog.dismiss();
                  Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
              }
            }
        });

    }

    @Override
    protected  void  onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthlistener);
        if (mAuth.getCurrentUser()!=null){
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
        }
    }

    public void signup (View view){
        Intent signup =new Intent(LoginActivity.this , SignupActivity.class);
        startActivity(signup);
    }

    private void toastmessage(String message){
        Toast.makeText(LoginActivity.this,message,Toast.LENGTH_LONG).show();
    }
}

