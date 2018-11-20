package com.example.muhammadhumayun.readqr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    public static TextView tvCardText;
    Button btStartScan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCardText =findViewById(R.id.tv_code_text);
        btStartScan =findViewById(R.id.btn_scan);

        btStartScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScannerActivity.class);
                startActivity(intent);
            }
        });

        MerchantQRData merchantQRData = new MerchantQRData(9,11,344,34,"pkr","testQR");
        String data = MerchantEncoder.GeneratePayload(merchantQRData);
        Log.e("oncreate", data );
    }






}
