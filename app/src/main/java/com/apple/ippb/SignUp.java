package com.apple.ippb;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    ProgressBar prgbr;
    FirebaseAuth firebaseAuth;
    private EditText person, emailAdderss, password, confirm_password, phonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        person = findViewById(R.id.FirstName);
        emailAdderss = findViewById(R.id.Userid);
        password = findViewById(R.id.userpassword);
        confirm_password = findViewById(R.id.confirmpassword);
        phonenumber = findViewById(R.id.userContact);
        prgbr = findViewById(R.id.progressBar1);
        firebaseAuth = FirebaseAuth.getInstance();


    }
}