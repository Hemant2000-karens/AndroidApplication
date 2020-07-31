package com.apple.ippb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {

    EditText emailh = findViewById(R.id.emailtxt);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_activity);

        Button SendButton = findViewById(R.id.sendbutton);
        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.setLanguageCode("en");
                auth.sendPasswordResetEmail(emailh.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Email Reset Link Send Successfully", Toast.LENGTH_LONG).show();
                                } else
                                    Toast.makeText(getApplicationContext(), "Sorry! , Try Again", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });


        ImageButton bc = findViewById(R.id.login_back);
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ForgetPassword.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }


}