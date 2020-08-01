package com.apple.ippb;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import java.io.ByteArrayOutputStream;
import java.util.Objects;


public class ProfileUpdate extends AppCompatActivity {
    final FirebaseUser userProfile = FirebaseAuth.getInstance().getCurrentUser();
    ImageView ppu;
    String displayname = null, profileurl = null;
    int time = 1000;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);


        assert userProfile != null;
        final EditText updatename = findViewById(R.id.name_update);
        final Button emailupdate, updatepassword, NameUpdate, verifyb;
        final TextView status = findViewById(R.id.notice);
        ppu = findViewById(R.id.upadteprofile);
        String name = userProfile.getDisplayName();
        updatename.setText(name);

        Uri reference = Uri.parse(Objects.requireNonNull(userProfile.getPhotoUrl()).toString());
        ppu.setImageURI(reference);

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
                                    Toast.makeText(ProfileUpdate.this, "Successfully Updated Your Name", Toast.LENGTH_SHORT).show();
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

    public void handlerImage(View view) {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(i, time);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == time) {
            switch (resultCode) {
                case RESULT_OK:
                    Bitmap bmp = (Bitmap) data.getExtras().get("Data");
                    ppu.setImageBitmap(bmp);
                    uploadActivity(bmp);
            }

        }

    }

    private void uploadActivity(Bitmap bmp) {
        ByteArrayOutputStream by = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, by);
        String uid = userProfile.getUid();
        final StorageReference reff = FirebaseStorage.getInstance().getReference().child("Profile Pictures").child("PP" + uid + "jpeg");
        reff.putBytes(by.toByteArray()).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                getDownload(reff);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private void getDownload(StorageReference reference) {
        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d(null, "onSucess" + uri);
                setUerProfile(uri);
            }
        });
    }

    private void setUerProfile(Uri uri) {
        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder().setPhotoUri(uri).build();

        userProfile.updateProfile(userProfileChangeRequest).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ProfileUpdate.this, "Updated Suceesfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProfileUpdate.this, "Updating Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
// Uploading Methods


    // Uploading Method


    /// New Methods Created Here


}