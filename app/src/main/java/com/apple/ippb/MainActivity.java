package com.apple.ippb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    public static String usernameq;
    EditText usrn, pswd;
    FirebaseAuth mAuth;


    public static String ID() {
        return usernameq;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button lBut;
        TextView forget;
        usrn = findViewById(R.id.t1);
        pswd = findViewById(R.id.t2);


// ..
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();


        forget = findViewById(R.id.forgot);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ForgetPassword.class);
                startActivity(i);
                finish();
            }
        });

        lBut = findViewById(R.id.log_btn);
        lBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = usrn.getText().toString().trim();
                String b = pswd.getText().toString().trim();

                if (TextUtils.isEmpty(a) && TextUtils.isEmpty(b)) {
                    usrn.setError("Empty Email !!");
                    pswd.setError("Empty Password !!");
                } else {
                    mAuth.signInWithEmailAndPassword(a, b).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @SuppressLint("ShowToast")
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Successfully Login!!", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(getApplicationContext(), HomeP.class);
                                ProgressBar prb = findViewById(R.id.progressBar);
                                prb.setVisibility(View.VISIBLE);
                                startActivity(i);
                                finish();
                            } else
                                Toast.makeText(getApplicationContext(), "Check Error And Try Again!!", Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });


        ImageButton gsi = findViewById(R.id.Google_signIN);
        gsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Now nothing
            }
        });


        TextView signup = findViewById(R.id.newAccount);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SignUp.class);
                startActivity(i);
                finish();
            }
        });


    }

    private FirebaseUser updateUI(FirebaseUser currentUser) {
        return currentUser;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

}
