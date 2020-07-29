package com.apple.ippb;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class verifyotp extends AppCompatActivity implements TextWatcher {
    EditText t1, t2, t3, t4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyotp);

        t1 = findViewById(R.id.txt1);
        t2 = findViewById(R.id.txt2);
        t3 = findViewById(R.id.txt3);
        t4 = findViewById(R.id.txt4);

        t1.addTextChangedListener(this);
        t2.addTextChangedListener(this);
        t3.addTextChangedListener(this);
        t4.addTextChangedListener(this);


        String email = ForgetPassword.Email();

        Button VerifyButton = findViewById(R.id.verify_button);
        VerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    // External Methods
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.length() == 1) {
            if (t1.length() == 1) {
                t2.requestFocus();
            }
            if (t2.length() == 1) {
                t3.requestFocus();
            }
            if (t3.length() == 1) {
                t4.requestFocus();
            }
        } else if (editable.length() == 0) {
            if (t4.length() == 0) {
                t3.requestFocus();
            }
            if (t3.length() == 0) {
                t2.requestFocus();
            }
            if (t2.length() == 0) {
                t1.requestFocus();
            }
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}