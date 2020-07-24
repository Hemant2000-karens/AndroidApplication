package com.apple.ippb;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static String usernameq , password;
    public static String ID()
    {
        return usernameq;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText usrn, pswd;
        Button lBut, rBut;
        usrn = findViewById(R.id.t1);
        pswd = findViewById(R.id.t2);

        rBut = findViewById(R.id.res_but);
        rBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usrn.setText("");
                pswd.setText("");
            }
        });

        lBut = findViewById(R.id.log_btn);
        lBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String a = usrn.getText().toString();
                String b = pswd.getText().toString();

                //USER LIST WITH PASSWORD
                if (a.equalsIgnoreCase("Admin") && b.equals("#234_@gmail")) {
                     usernameq = "Administrator" ; password = "#234_@gmail";
                }
                else if(a.equalsIgnoreCase("Ravi_4431")&& b.equals("990_abc"))
                {
                    usernameq = "Ravi Prakash" ; password = "990_abc";
                }
                else if(a.equalsIgnoreCase("Hemant_2002")&& b.equals("hemant"))
                {
                    usernameq = "HEMANT KUMAR" ; password = "hemant";
                }

                //LIST OVER AND CASE VERIFICATION STARTS
                if (a.equalsIgnoreCase(a) && b.equals(password))
                {
                    Intent ab = new Intent(getApplicationContext(), HomeP.class);
                    //NAME OF THE USER IN HOME PAGE TO BE SHOWN
                    if(a.equalsIgnoreCase("Admin"))
                    {
                        ab.putExtra("name_vale",usernameq);
                    }
                    else if(a.equalsIgnoreCase("Ravi_4431"))
                    {
                        ab.putExtra("name_vale",usernameq);
                    }
                    else if(a.equalsIgnoreCase("Hemant_2002"))
                    {
                        ab.putExtra("name_vale",usernameq);
                    }
                    // FORWARDED NAME
                    finish();
                    startActivity(ab);
                }
                else
                {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setIcon(R.drawable.eroor);
                    builder.setMessage("Invalid Uername or Password , Try Again");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog buld = builder.create();
                    buld.show();
                }
            }
        });




    }
}