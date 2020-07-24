package com.apple.ippb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Replace_device extends AppCompatActivity {
private Spinner reSp , sepS , sepE ,degn;
private String rcarrier_name , mobile_model, Biometric_model , degn_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_replace);


        ImageButton btn = findViewById(R.id.R_back_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sec = new Intent(getApplicationContext(), HomeP.class);
                finish();
                sec.putExtra("name_vale",MainActivity.ID());
                startActivity(sec);
            }
        });
        //DropDown Selector

        reSp = findViewById(R.id.spinner5);
        ArrayAdapter<String> myAdapter7 = new ArrayAdapter<String>(Replace_device.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.mane));
        myAdapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reSp.setAdapter(myAdapter7);

        reSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1) {
                    rcarrier_name = "Vodafone";
                    // startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else if (i == 2) {
                    rcarrier_name = "Airtel";
                    // startActivity(new Intent(MainActivity.this, WorkActivity.class));
                }else if (i == 3) {
                    rcarrier_name = "Reliance Jio";
                    // startActivity(new Intent(MainActivity.this, OtherActivity.class));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sepS = findViewById(R.id.All_replace_M_Model);
        ArrayAdapter<String> myAdapter6 = new ArrayAdapter<String>(Replace_device.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Mobile_Model));
        myAdapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sepS.setAdapter(myAdapter6);

        sepS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1) {
                    mobile_model = "Samsung J2";
                    // startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else if (i == 2) {
                    mobile_model = "Samsung J4";
                    // startActivity(new Intent(MainActivity.this, WorkActivity.class));
                }else if (i == 3) {
                    mobile_model = "Panasonic eluga";
                    // startActivity(new Intent(MainActivity.this, OtherActivity.class));
                }else if (i == 4){
                    mobile_model = "Moto C";
                }else if (i == 5){
                    mobile_model = "iVoomi";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        sepE = findViewById(R.id.All_replace_BioMedel);
        ArrayAdapter<String> myAdapter10 = new ArrayAdapter<String>(Replace_device.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Bio_model));
        myAdapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sepE.setAdapter(myAdapter10);

        sepE.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 2) {
                    Biometric_model = "Mantra";
                    // startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else if (i == 3) {
                    Biometric_model = "Starek";
                    // startActivity(new Intent(MainActivity.this, WorkActivity.class));
                }else if (i == 4) {
                    Biometric_model = "Morpho";
                    // startActivity(new Intent(MainActivity.this, OtherActivity.class));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        degn = findViewById(R.id.Replace_all_designation);
        ArrayAdapter<String> myAdapter9 = new ArrayAdapter<String>(Replace_device.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Designation));
        myAdapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        degn.setAdapter(myAdapter9);

        degn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1) {
                    degn_name = "G.O.S";
                    // startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else if (i == 2) {
                    degn_name = "Post Man";
                    // startActivity(new Intent(MainActivity.this, WorkActivity.class));
                }else if (i == 3) {
                    degn_name = "PA";
                    // startActivity(new Intent(MainActivity.this, OtherActivity.class));
                }else if(i == 4){
                    degn_name = "IPPB";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        //DropDown Selector



    }
}