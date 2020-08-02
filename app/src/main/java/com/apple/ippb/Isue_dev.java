package com.apple.ippb;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Isue_dev extends AppCompatActivity {

    MobileIssue mobileIssue = new MobileIssue();
    SimIssue simIssue = new SimIssue();
    Biometric biometric = new Biometric();


    private BottomNavigationView.OnNavigationItemSelectedListener navlist = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.mobile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_hoem, mobileIssue).commit();
                    return true;

                case R.id.sim:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_hoem, simIssue).commit();
                    return true;

                case R.id.fingerprint:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_hoem, biometric).commit();
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isue_dev);


        BottomNavigationView navigationView = findViewById(R.id.navbar);
        navigationView.setOnNavigationItemSelectedListener(navlist);
        navigationView.setSelectedItemId(R.id.mobile);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Isue_dev.this, HomeP.class));

    }
}