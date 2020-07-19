package com.apple.ippb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddRecords extends AppCompatActivity {

    EditText at2, at3 , at4, at5 , at6 , at8 ;
    Button add_r , cancel ;
    Spinner spm3 , add_mob , bio_model ;
    String acarrier_name,mobile_model,Biometric_model ;
    DatabaseReference reff ;
    long countID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_records);

        at2 = findViewById(R.id.add_m_serial);
        at3 = findViewById(R.id.add_imei1);
        at4 = findViewById(R.id.add_m_imei2);
        at5 = findViewById(R.id.Add_sim_serial);
        at6 = findViewById(R.id.add_Sim_mob);
        at8 = findViewById(R.id.Add_bio_serial);
        reff = FirebaseDatabase.getInstance().getReference().child("Rec_ords");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                     countID = snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ImageButton img = findViewById(R.id.Add_home);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),HomeP.class);
                i.putExtra("name_vale",MainActivity.ID());
                startActivity(i);
            }
        });

         spm3 = findViewById(R.id.Spinner3);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(AddRecords.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.mane));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spm3.setAdapter(myAdapter2);

        spm3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1) {
                    acarrier_name = "Vodafone";
                    // startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else if (i == 2) {
                   acarrier_name = "Airtel";
                    // startActivity(new Intent(MainActivity.this, WorkActivity.class));
                }else if (i == 3) {
                    acarrier_name = "Reliance Jio";
                    // startActivity(new Intent(MainActivity.this, OtherActivity.class));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        add_mob = findViewById(R.id.Add_mobile_Model);
        ArrayAdapter<String> abcd = new ArrayAdapter<String>(AddRecords.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Mobile_Model));
        abcd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        add_mob.setAdapter(abcd);

        add_mob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        bio_model = findViewById(R.id.Add_Biom_Model);
        ArrayAdapter<String> abdc = new ArrayAdapter<String>(AddRecords.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Bio_model));
        abdc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bio_model.setAdapter(abdc);

        bio_model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1) {
                    Biometric_model = "Mantra";
                    // startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else if (i == 2) {
                    Biometric_model = "Starek";
                    // startActivity(new Intent(MainActivity.this, WorkActivity.class));
                }else if (i == 3) {
                    Biometric_model = "Morpho";
                    // startActivity(new Intent(MainActivity.this, OtherActivity.class));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        add_r = findViewById(R.id.Add_record_button);
        add_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ab = at2.getText().toString().trim();
                String ac = at3.getText().toString().trim();
                String ad = at4.getText().toString().trim();
                String ae = at5.getText().toString().trim();
                String af = at6.getText().toString().trim();
                String ah = at8.getText().toString().trim();
                Rec_ords rec_ords = new Rec_ords();
                rec_ords.setMob_model(mobile_model);
                rec_ords.setMob_serial(ab);
                rec_ords.setMob_imei1(ac);
                rec_ords.setMob_imei2(ad);
                rec_ords.setSim_serial(ae);
                rec_ords.setSim_mob(af);
                rec_ords.setSim_operator(acarrier_name);
                rec_ords.setBiom_model(Biometric_model);
                rec_ords.setBiom_serial(ah);
                reff.child(String.valueOf(countID+1)).setValue(rec_ords);
                Toast.makeText(AddRecords.this,"Susccessfully Inserted",Toast.LENGTH_LONG).show();
            }
        });


        cancel = findViewById(R.id.Cance_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spm3.setSelection(0);
                bio_model.setSelection(0);
                add_mob.setSelection(0);
                at2.setText("");
                at3.setText("");
                at4.setText("");
                at5.setText("");
                at6.setText("");
                at8.setText("");
            }
        });

    }
}