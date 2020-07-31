package com.apple.ippb;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ProfileUpdate extends AppCompatActivity {
    FirebaseUser fbauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);


        final EditText updatename = findViewById(R.id.name_update);
        final Button emailupdate, updatepassword, NameUpdate;

        FirebaseUser userProfile = FirebaseAuth.getInstance().getCurrentUser();
        String name = userProfile.getDisplayName();

        updatename.setText(name);

        NameUpdate = findViewById(R.id.Update_NAME);
        NameUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newOfName = updatename.getText().toString().trim();

                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(newOfName).build();
                fbauth.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ProfileUpdate.this, "Successfully Updated Your Name", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });


        emailupdate = findViewById(R.id.emailUpdate);
        emailupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ProfileUpdate.this);
                builder.setCancelable(true);
                builder.setTitle("Edit Profile");
                final EditText text = new EditText(ProfileUpdate.this);
                text.setHint("Enter Your Display Name");
                text.setEms(10);
                builder.setView(text);
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        assert user != null;
                        user.updateEmail(text.getText().toString().trim())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(ProfileUpdate.this, "Email Updated Successfully", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Function Of the Negative Button


                    }
                });
                AlertDialog buld = builder.create();
                buld.show();
            }
        });


        updatepassword = findViewById(R.id.passwordUpdate);
        updatepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ProfileUpdate.this);
                builder.setCancelable(true);
                builder.setTitle("Edit Profile");
                final EditText newpass = new EditText(ProfileUpdate.this);
                newpass.setHint("Enter New Password");
                newpass.setEms(10);
                builder.setView(newpass);
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        assert user != null;
                        user.updatePassword(newpass.getText().toString().trim())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(ProfileUpdate.this, "Password Updated Successfully", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Function Of the Negative Button
                    }
                });
                AlertDialog buld = builder.create();
                buld.show();
            }
        });


        ImageButton bc = findViewById(R.id.home_back);
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileUpdate.this, HomeP.class);
                startActivity(i);
                finish();
            }
        });


    }
}