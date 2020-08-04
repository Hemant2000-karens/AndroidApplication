package com.apple.ippb;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_p);
        FirebaseUser fbauth = FirebaseAuth.getInstance().getCurrentUser();
        final TextView Nme_usr = findViewById(R.id.usr_name);
        assert fbauth != null;
        String user = fbauth.getDisplayName();
        Nme_usr.setText(user);

        CircleImageView pp = findViewById(R.id.profilepic);
        Glide.with(HomeP.this)
                .load((Objects.requireNonNull(fbauth.getPhotoUrl())).toString())
                .error(R.drawable.account)
                .centerCrop()
                .into(pp);

        pp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //All the Functions with DialogBox
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeP.this);
                LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialogbox, null);
                builder.setCancelable(true);
                builder.setView(dialogView);
                Button update = dialogView.findViewById(R.id.updateProfile);
                Button logout = dialogView.findViewById(R.id.Logout_button);

                AlertDialog dialog = builder.create();
                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // JOptionPane Starts here
                        final AlertDialog.Builder abc = new AlertDialog.Builder(HomeP.this);
                        LayoutInflater inflater1 = getLayoutInflater();
                        View logoutView = inflater1.inflate(R.layout.logoutbox, null);
                        abc.setView(logoutView);
                        Button Logout = logoutView.findViewById(R.id.logot);
                        Logout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                try {
                                    LogOut();
                                    FirebaseAuth.getInstance().signOut();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        Button cancel = logoutView.findViewById(R.id.cancelButton);
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Cancel Button Function
                                abc.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                    @Override
                                    public void onDismiss(DialogInterface dialogInterface) {
                                        dialogInterface.dismiss();
                                    }
                                });
                            }
                        });
                        abc.show();
                        //JoptionPane Ends

                    }
                });

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(HomeP.this, ProfileUpdate.class));
                        finish();
                    }
                });

                dialog.show();

            }
        });


        ImageButton plu = findViewById(R.id.add_buttin);
        plu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HomeP.this, AddRecords.class);
                finish();
                startActivity(i);
            }
        });

        ImageButton usi = findViewById(R.id.issue_but);
        usi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(HomeP.this, Isue_dev.class));
            }
        });
/*
        ImageButton rpd = findViewById(R.id.repall_but);
        rpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Replace_device.class);
                finish();
                startActivity(i);
            }
        });

        ImageButton m_m = findViewById(R.id.m_isrep);
   m_m.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           Intent i = new Intent(getApplicationContext(),Mobile_Replace.class);
           finish();
           startActivity(i);
       }
   });


        ImageButton s_m = findViewById(R.id.Sim_repel);
   s_m.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           Intent i = new Intent(getApplicationContext(),Sim_replace.class);
           finish();
           startActivity(i);
       }
   });


        ImageButton b_m = findViewById(R.id.Biomet_repel);
        b_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Biometrics_replace.class);
                finish();
                startActivity(i);
            }
        });


    }

    @Override
    public void onBackPressed() {

    }
*/

        //External Save Instances
    }
    private void LogOut() {
        Intent activity2Intent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivity(activity2Intent);


    }
}
