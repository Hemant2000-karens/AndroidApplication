package com.apple.ippb;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    ProgressBar prgbr;
    Button signupbutton;
    FirebaseAuth firebaseAuth;
    EditText person, emailAdderss, password, confirm_password, phonenumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        person = findViewById(R.id.FirstName);
        emailAdderss = findViewById(R.id.Userid);
        password = findViewById(R.id.userpassword);
        confirm_password = findViewById(R.id.confirmpassword);
        phonenumber = findViewById(R.id.userContact);
        signupbutton = findViewById(R.id.Signupnext);
        prgbr = findViewById(R.id.progressBar1);
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();


        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String newUser = person.getText().toString().trim();
                String newEmail = emailAdderss.getText().toString().trim();
                String newPassword = password.getText().toString().trim();
                String newC_password = confirm_password.getText().toString().trim();
                String newPhone = phonenumber.getText().toString().trim();


                if (TextUtils.isEmpty(newUser) && TextUtils.isEmpty(newEmail) && TextUtils.isEmpty(newC_password) && TextUtils.isEmpty(newPhone)) {
                    person.setError("Name Cannot be Empty");
                    emailAdderss.setError("Name Cannot be Empty");
                    password.setError("Name Cannot be Empty");
                    confirm_password.setError("Name Cannot be Empty");
                    phonenumber.setError("Name Cannot be Empty");
                    return;
                }


                firebaseAuth.createUserWithEmailAndPassword(newEmail, newC_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Successfully Verified  !!", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    } else
                                        Toast.makeText(getApplicationContext(), "Error Try Again", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }
                });
                prgbr.setVisibility(View.VISIBLE);
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private FirebaseUser updateUI(FirebaseUser currentUser) {
        return currentUser;
    }
}