package com.apple.ippb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

public class Sim_replace extends AppCompatActivity {
    private String carrier_name , degn_name;
    DatabaseReference reffernce1  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sim_replace);

        ImageButton h_b = findViewById(R.id.R_home_b);
        h_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),HomeP.class);
                finish();
                i.putExtra("name_vale",MainActivity.ID());
                startActivity(i);
            }
        });

        //ListBOX Setting
        Spinner spm = findViewById(R.id.Spimmer);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Sim_replace.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.mane));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spm.setAdapter(myAdapter);

        spm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 2) {
                    carrier_name = "Vodafone";
                    // startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else if (i == 3) {
                    carrier_name = "Airtel";
                    // startActivity(new Intent(MainActivity.this, WorkActivity.class));
                }else if (i == 4) {
                    carrier_name = "Reliance Jio";
                    // startActivity(new Intent(MainActivity.this, OtherActivity.class));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner spm2 = findViewById(R.id.Spimmer2);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(Sim_replace.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.mane));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spm2.setAdapter(myAdapter2);

        spm2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 2) {
                    carrier_name = "Vodafone";
                    // startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else if (i == 3) {
                    carrier_name = "Airtel";
                    // startActivity(new Intent(MainActivity.this, WorkActivity.class));
                }else if (i == 4) {
                    carrier_name = "Reliance Jio";
                    // startActivity(new Intent(MainActivity.this, OtherActivity.class));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        Spinner deg = findViewById(R.id.Sim_designation);
        ArrayAdapter<String> degn = new ArrayAdapter<String>(Sim_replace.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Designation));
        degn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deg.setAdapter(degn);

        deg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        // ListBox Ended




    }
}