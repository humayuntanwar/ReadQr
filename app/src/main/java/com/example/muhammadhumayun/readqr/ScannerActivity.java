package com.example.muhammadhumayun.readqr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.Result;

import java.util.HashMap;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends AppCompatActivity  implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    String resultString;
    emvQRReader emvQRReader = new emvQRReader(); //emvreader class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView); //set to scanner cam view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result result) {
        //handle the result here

        resultString = result.toString(); //this string is the resulting string of scanned QR

        /*
       check if QR is emv or not
         */
        if (emvQRReader.firstTwo(resultString).equals("00")) {
            //IS EMV
            emvQRReader.readEMVQR(resultString);
            //readEMVQR(resultString);
            onBackPressed();

        }

        else {
            //NOT EMV
            MainActivity.tvCardText.setText(resultString);
            onBackPressed();
        }
    }



}
