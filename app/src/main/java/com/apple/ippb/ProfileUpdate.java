package com.apple.ippb;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileUpdate extends AppCompatActivity {
    final FirebaseUser userProfile = FirebaseAuth.getInstance().getCurrentUser();
    CircleImageView ppu;
    int time = 1000;
    StorageReference REFFERENCE;
    ProgressBar pgrbb;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

        REFFERENCE = FirebaseStorage.getInstance().getReference();
        assert userProfile != null;
        final EditText updatename = findViewById(R.id.name_update);
        final Button emailupdate, updatepassword, NameUpdate, verifyb;
        final TextView status = findViewById(R.id.notice);

        pgrbb = findViewById(R.id.updateBar);
        ppu = findViewById(R.id.upadteprofilepic);
        String name = userProfile.getDisplayName();
        updatename.setText(name);

        Glide.with(ProfileUpdate.this)
                .load(Objects.requireNonNull(userProfile.getPhotoUrl()).toString())
                .error(R.drawable.account)
                .centerCrop()
                .into(ppu);

        NameUpdate = findViewById(R.id.Update_NAME);
        NameUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newOfName = updatename.getText().toString().trim();
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(newOfName).build();
                userProfile.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    pgrbb.setVisibility(View.VISIBLE);
                                    Toast.makeText(ProfileUpdate.this, "Successfully Updated Your Name", Toast.LENGTH_SHORT).show();
                                    pgrbb.setVisibility(View.INVISIBLE);
                                } else if (task.isCanceled()) {
                                    Toast.makeText(ProfileUpdate.this, "Error !! , Try Again", Toast.LENGTH_LONG).show();
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
                builder.setTitle("Email Update");
                final EditText text = new EditText(ProfileUpdate.this);
                text.setHint("Enter Your New Email");
                text.setEms(10);
                builder.setView(text);
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        assert user != null;
                        user.updateEmail(text.getText().toString().trim())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    pgrbb.setVisibility(View.VISIBLE);
                                                    Toast.makeText(ProfileUpdate.this, "Email Verification Link Sended", Toast.LENGTH_LONG).show();
                                                }
                                            });
                                            Toast.makeText(ProfileUpdate.this, "Email Updated Successfully", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ProfileUpdate.this, "Email Updating Failed", Toast.LENGTH_LONG).show();
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
                builder.setTitle("Update Password");
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
                                            pgrbb.setVisibility(View.VISIBLE);
                                            Toast.makeText(ProfileUpdate.this, "Password Updated Successfully", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                Toast.makeText(ProfileUpdate.this, "Password Updating Failed", Toast.LENGTH_SHORT).show();
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


        verifyb = findViewById(R.id.verifyemailbutton);
        final FirebaseUser usr = FirebaseAuth.getInstance().getCurrentUser();
        assert usr != null;
        if (usr.isEmailVerified()) {
            status.setTextColor(Color.parseColor("#FF007802"));
            status.setText("Email is verified!!");
            verifyb.setVisibility(View.INVISIBLE);
        } else if (usr.isEmailVerified()) {
            status.setTextColor(Color.parseColor("#930000"));
            status.setText("Verify Your Email");
            verifyb.setVisibility(View.VISIBLE);
        }

        verifyb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usr.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pgrbb.setVisibility(View.VISIBLE);
                        Toast.makeText(ProfileUpdate.this, "Email Sended Successfully", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ProfileUpdate.this, "Email Sending Failed ", Toast.LENGTH_SHORT).show();
                    }
                });
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


        // Saved Instances Ended Here //
    }

    // Don't Disturb This Other Wise It it will be Costlier


    public void handlerImage(View view) {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(i, time);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == time) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                final Uri uri = data.getData();

                String a = userProfile.getUid();
                StorageReference reff = REFFERENCE.child("Profile Pictures").child("Profile Pic" + a);
                reff.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        Toast.makeText(ProfileUpdate.this, "Image Uploaded Successfully!!", Toast.LENGTH_SHORT).show();
                    }
                });
                //ppu.setImageURI(uri);
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                reff.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(final Uri uri) {
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setPhotoUri(uri)
                                .build();

                        assert user != null;
                        user.updateProfile(profileUpdates)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            pgrbb.setVisibility(View.VISIBLE);
                                            Toast.makeText(ProfileUpdate.this, "Image Uploaded Successfully!!", Toast.LENGTH_LONG).show();
                                            pgrbb.setVisibility(View.INVISIBLE);
                                            Glide.with(ProfileUpdate.this).load(uri).into(ppu);
                                        }
                                    }
                                });
                    }
                });


            }

        }

    }


// Ends Of Danger Zone And Free From Here

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ProfileUpdate.this, MainActivity.class));
        this.finish();
    }

}