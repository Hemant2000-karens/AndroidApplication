package com.apple.ippb;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Isue_dev extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener navlist = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == 1) {
                Fragment selected = new MobileIssue();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_hoem, selected).commit();
            } else if (item.getItemId() == 2) {
                Fragment selected = new SimIssue();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_hoem, selected).commit();
            } else if (item.getItemId() == 3) {
                Fragment selected = new SimIssue();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_hoem, selected).commit();
            }

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isue_dev);

        BottomNavigationView navigationView = findViewById(R.id.navbar);
        navigationView.setOnNavigationItemSelectedListener(navlist);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_hoem, new MobileIssue()).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Isue_dev.this, HomeP.class));

    }
}