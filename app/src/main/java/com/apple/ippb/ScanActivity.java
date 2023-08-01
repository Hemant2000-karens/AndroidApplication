package com.apple.ippb;


import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    int a = 10, b = 20, c = 30, d = 40, e = 50, f = 60;
    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view

        int currentApiVersion = Build.VERSION.SDK_INT;

        if (currentApiVersion >= Build.VERSION_CODES.M) {
            if (checkPermission()) {
                Toast.makeText(getApplicationContext(), "Permission already granted!", Toast.LENGTH_LONG).show();
            } else {
                requestPermission();
            }
        }
    }

    private boolean checkPermission() {
        return (ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, 1);
    }


    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);// Register ourselves as a handler for scan results.
        mScannerView.startCamera();// Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        // Log.v("tag", rawResult.getText()); // Prints scan results
        // Log.v("tag", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        if (AddRecords.a == a) {
            AddRecords.at2.setText(rawResult.getText());
        } else if (AddRecords.a == b) {
            AddRecords.at3.setText(rawResult.getText());
        } else if (AddRecords.a == c) {
            AddRecords.at4.setText(rawResult.getText());
        } else if (AddRecords.a == d) {
            AddRecords.at5.setText(rawResult.getText());
        } else if (AddRecords.a == e) {
            AddRecords.at6.setText(rawResult.getText());
        } else if (AddRecords.a == f) {
            AddRecords.at8.setText(rawResult.getText());
        }
        onBackPressed();
        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }
}