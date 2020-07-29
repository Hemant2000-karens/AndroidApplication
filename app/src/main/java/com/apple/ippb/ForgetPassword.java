package com.apple.ippb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ForgetPassword extends AppCompatActivity {
    public static String emailid;

    public static String Email() {
        return emailid;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_activity);

        EditText emailh = findViewById(R.id.emailtxt);
        emailid = emailh.getText().toString();

        Button SendButton = findViewById(R.id.sendbutton);
        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), verifyotp.class);
                startActivity(i);
            }
        });

    }


}