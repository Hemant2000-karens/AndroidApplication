package com.apple.ippb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Mobile_Replace extends AppCompatActivity {

    Spinner old_Mobile , new_Mobile , degination; String rModel , degn_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile__replace);

        ImageButton m_b = findViewById(R.id.M_back_buttin);
        m_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nue = new Intent(getApplicationContext(), HomeP.class);
                finish();
                nue.putExtra("name_vale",MainActivity.ID());
                startActivity(nue);
            }
        });

        old_Mobile = findViewById(R.id.Mobile_issue_old);
        ArrayAdapter<String> resp = new ArrayAdapter<String>(Mobile_Replace.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Mobile_Model));
        resp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        old_Mobile.setAdapter(resp);

        old_Mobile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 2) {
                    rModel = "Samsung J2";
                    // startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else if (i == 3) {
                    rModel = "Samsung J4";
                    // startActivity(new Intent(MainActivity.this, WorkActivity.class));
                }else if (i == 4) {
                    rModel = "Panasonic eluga";
                    // startActivity(new Intent(MainActivity.this, OtherActivity.class));
                }else if (i == 5){
                    rModel = "Moto C";
                }else if (i == 6){
                    rModel = "iVoomi";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        new_Mobile = findViewById(R.id.issue_new_Device);
        ArrayAdapter<String> repst = new ArrayAdapter<String>(Mobile_Replace.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Mobile_Model));
        repst.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        new_Mobile.setAdapter(repst);

        new_Mobile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 2) {
                    rModel = "Samsung J2";
                    // startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else if (i == 3) {
                    rModel = "Samsung J4";
                    // startActivity(new Intent(MainActivity.this, WorkActivity.class));
                }else if (i == 4) {
                    rModel = "Panasonic eluga";
                    // startActivity(new Intent(MainActivity.this, OtherActivity.class));
                }else if (i==4){
                    rModel = "Moto C";
                }else if (i==5){
                    rModel = "iVoomi";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        degination = findViewById(R.id.Mobile_Designation);
        ArrayAdapter<String> degnita = new ArrayAdapter<String>(Mobile_Replace.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Designation));
        degnita.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        degination.setAdapter(degnita);

        degination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    }
}