package com.apple.ippb;

import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Isue_dev extends AppCompatActivity {

   private EditText a1,a2,a4,b2,b3,b4,c1,c2,d2;
   private Spinner isdSp , isdp ,idsp , degnita;
    private String icarrier_name , iModel ,iBio_model ,degn_name;
    long Count;
    DatabaseReference reffe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isue_dev);
        a1 = (EditText)findViewById(R.id.name_of_cand);
        a2 = (EditText)findViewById(R.id.post_cand);
        a4 = (EditText)findViewById(R.id.cont_cand);
        //Issue Mobile
        b2 = (EditText)findViewById(R.id.m_serial);
        b3 = (EditText)findViewById(R.id.m_im1);
        b4 = (EditText)findViewById(R.id.m_im2);
        //Issue Sim
        c1 = (EditText)findViewById(R.id.s_mobno);
        c2 = (EditText)findViewById(R.id.s_serial);
        //issue Biometric
        d2 = (EditText)findViewById(R.id.b_serial);


        //DropDown Menu Code

        reffe = FirebaseDatabase.getInstance().getReference().child("Issue_details");
        reffe.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    Count = snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                error.toException().printStackTrace();
            }
        });



        //Update Details


        isdSp = findViewById(R.id.spinner4);
        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<String>(Isue_dev.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.mane));
        myAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        isdSp.setAdapter(myAdapter3);

        isdSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 2) {
                    icarrier_name = "Vodafone";
                    // startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else if (i == 3) {
                    icarrier_name = "Airtel";
                    // startActivity(new Intent(MainActivity.this, WorkActivity.class));
                }else if (i == 4) {
                    icarrier_name = "Reliance Jio";
                    // startActivity(new Intent(MainActivity.this, OtherActivity.class));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        isdp = findViewById(R.id.issue_Mobile_model);
        ArrayAdapter<String> myAdapter4 = new ArrayAdapter<String>(Isue_dev.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Mobile_Model));
        myAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        isdp.setAdapter(myAdapter4);

        isdp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 2) {
                    iModel = "Samsung J2";
                    // startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else if (i == 3) {
                    iModel = "Samsung J4";
                    // startActivity(new Intent(MainActivity.this, WorkActivity.class));
                }else if (i == 4) {
                    iModel = "Panasonic eluga";
                    // startActivity(new Intent(MainActivity.this, OtherActivity.class));
                }else if (i==5){
                    iModel = "Moto C";
                }else if (i==6){
                    iModel = "iVoomi";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        idsp = findViewById(R.id.issue_Bio_model);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(Isue_dev.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Bio_model));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        idsp.setAdapter(myAdapter2);

        idsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 2) {
                    iBio_model = "Mantra";
                    // startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else if (i == 3) {
                    iBio_model = "Starek";
                    // startActivity(new Intent(MainActivity.this, WorkActivity.class));
                }else if (i == 4) {
                    iBio_model = "Morpho";
                    // startActivity(new Intent(MainActivity.this, OtherActivity.class));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        degnita = findViewById(R.id.issue_designation);
        ArrayAdapter<String> degni = new ArrayAdapter<String>(Isue_dev.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Designation));
        degni.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        degnita.setAdapter(degni);

        degnita.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1) {
                    degn_name = "G.D.S";
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


        //DropDown Menu Ends

        //Home Button
        ImageButton btn = (ImageButton) findViewById(R.id.Is_bac_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sec = new Intent(getApplicationContext(), HomeP.class);
                finish();
                sec.putExtra("name_vale",MainActivity.ID());
                startActivity(sec);
            }
        });

        //Barcode reader




        //Barcode reader ends

        Button sb = (Button)findViewById(R.id.Submit_button);
        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Event action

                String ia = a1.getText().toString().trim();
                String ib = a2.getText().toString().trim();
                String ic = a4.getText().toString().trim();
                String id = b2.getText().toString().trim();
                String ie = b3.getText().toString().trim();
                String ig = b4.getText().toString().trim();
                String ih = c1.getText().toString().trim();
                String ij = c2.getText().toString().trim();
                String ik = d2.getText().toString().trim();
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                String strDate = formatter.format(date);
                formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                strDate = formatter.format(date);
                Issue_details isd = new Issue_details();
                isd.setUser_name(ia);
                isd.setEmployeeID(ib);
                isd.setDesig_nation(degn_name);
                isd.setAccesspoint(ic);
                isd.setDevice_Model(iModel);
                isd.setDevice_serial(id);
                isd.setDevice_ime1(ie);
                isd.setDevice_ime2(ig);
                isd.setSim_serial(ij);
                isd.setSim_mobile(ih);
                isd.setSim_operator(icarrier_name);
                isd.setBio_model(iBio_model);
                isd.setBio_serial(ik);
                reffe.child(String.valueOf(Count+1)+"- -"+strDate).setValue(isd);
                Toast.makeText(Isue_dev.this,"Susccessfully Inserted",Toast.LENGTH_LONG).show();




                //Submission Action
            }
        });

        Button ccl = (Button)findViewById(R.id.Cancel_button);
        ccl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // JOptionPane Starts here
                AlertDialog.Builder abc = new AlertDialog.Builder(Isue_dev.this);
                abc.setMessage("Do You really wants to cancel");
                abc.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        try {
                            ClearAll();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
                abc.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog amc = abc.create();
                amc.show();
                //JoptionPane Ends
            }
        });



    }

    private void ClearAll() {
        d2.setText(null);
        c1.setText(null);
        c2.setText(null);
        b2.setText(null);
        b3.setText(null);
        b4.setText(null);
        a1.setText(null);
        a2.setText(null);
        a4.setText(null);
    }
}