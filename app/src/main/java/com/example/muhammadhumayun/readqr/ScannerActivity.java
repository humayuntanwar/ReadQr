package com.example.muhammadhumayun.readqr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.zxing.Result;

import java.util.HashMap;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends AppCompatActivity  implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;
    String resultString, tag,length,value;
    String TLV;
    public HashMap<String,String> lengthMap= new HashMap<String, String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView); //set to scanner cam view

        lengthMap.put("00","PAYLOAD FORMAT INDICATOR");
        lengthMap.put("01","POINT INITIATION METHOD");


        /*
        02 to 51 Merchant Account Information
         */
        lengthMap.put("02","PAYEE IDENTIFIER ACCOUNT NUM");
        lengthMap.put("04","MERCHANT IDENTIFIER MASTER CARD");
        lengthMap.put("05","MERCHANT IDENTIFIER MASTER CARD");
        lengthMap.put("06","");
        lengthMap.put("07","");
        lengthMap.put("08","");
        lengthMap.put("09","");




        lengthMap.put("52","MERCHANT CATEGORY CODE");
        lengthMap.put("53","CURRENCY CODE");
        lengthMap.put("54","TRANSACTIONAL AMOUNT");
        lengthMap.put("58","COUNTRY CODE");
        lengthMap.put("59","MERCHANT NAME");
        lengthMap.put("60","MERCHANT CITY");




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

        resultString =  result.toString(); //this string is the resulting string of scanned QR
       // showData(resultString); // pass the string to show data

        //Handling string result tag one
        int startlen =0;
        tag = resultString.substring(startlen, 2);  //tag first two tag
        length = resultString.substring(2,4);
        int lenthof1 = length.length();
        String tagmap = lengthMap.get(tag);
        String value = resultString.substring(4,lenthof1 +4);// is either 01 or 02 , type 1 qr or type 2 qr
        int vallen1 = value.length() + tag.length() +lenthof1;


        //handling string result tag two
        int startlen2 =  vallen1 ; // 2 BELONGS POINT INITIATION METHOD
        String tag2 = resultString.substring(startlen2, startlen2+2);
        String len2 = resultString.substring(startlen2+2,startlen2+4);
        int lenof2 = len2.length();
        String tagmap2 = lengthMap.get(tag2);
        String val2 = resultString.substring(startlen2+4,lenof2+startlen2+4);// is either 12 dynamic QR , 11 static QR
        int vallen2 = tag2.length() +lenof2 + val2.length();


        //handling starting result tag three
        int startlen3 =  vallen2 +startlen2; //3 BELONGS TO MASTERS
        String tag3 = resultString.substring(startlen3, startlen3+2);
        String len3 = resultString.substring(startlen3+2,startlen3+4);
        int lenof3 = Integer.parseInt(len3);
        String tagmap3 = lengthMap.get(tag3);
        String val3= resultString.substring(startlen3+4,lenof3+startlen3+4);// is either 12 dynamic QR , 11 static QR
        int vallen3= tag3.length() +len3.length() + val3.length();
       // String view = tag3+val3+len3;



        //handling starting result tag four
        int startlen4 =  vallen3 + startlen3; // 4 BELONGS TO MASTERS
        String tag4 = resultString.substring(startlen4, startlen4+2);
        String len4 = resultString.substring(startlen4+2,startlen4+4);
        int lenof4 = Integer.parseInt(len4);
        String tagmap4 = lengthMap.get(tag4);
        String val4= resultString.substring(startlen4+4,lenof4+startlen4+4);// mercchant account number
        int vallen4= tag4.length() +len4.length() + val4.length();


        //lets now skip the merchant tags and go directly to handle 52

        int startlen52 = startlen4 + vallen4+76; // 52 IS MCC // 76 chars from string skipped to reach 52
        String tag52 = resultString.substring(startlen52, startlen52+2);
        String len52= resultString.substring(startlen52+2,startlen52+4);
        int lenof52 = Integer.parseInt(len52);
        String tagmap52 = lengthMap.get(tag52);
        String val52= resultString.substring(startlen52+4,lenof52+startlen52+4);// amount
        int vallen52= tag52.length() +len52.length() + val52.length();

        //handling 53
        int startlen53 = startlen52 + vallen52; // 53 IS CURRENCY CODE
        String tag53 = resultString.substring(startlen53, startlen53+2);
        String len53= resultString.substring(startlen53+2,startlen53+4);
        int lenof53 = Integer.parseInt(len53);
        String tagmap53 = lengthMap.get(tag53);
        String val53= resultString.substring(startlen53+4,lenof53+startlen53+4);// amount
        int vallen53= tag53.length() +len53.length() + val53.length();










        MainActivity.tvCardText.setText(tag+ "\nindicates:     "  +tagmap +"\nvalue is "+ value+"\n"+

                                       tag2+ "\n indicates    "+tagmap2 + "\nvalue is : "+val2
                                       + "\n" + tag3 +"\n indicates  "+ tagmap3 + "\nvalue is : "+val3
                                        +"\n"+tag4 + "\n indicates    "+tagmap4 +"\n value is : " + val4
                                        +"\n"+tag52 + "\n indicates  "+tagmap52 + "\n value is : "+val52
                                        + "\n" + tag53+ "\nindicates  "+tagmap53+"\n value is : "+ val53) ;
        onBackPressed();


    }


}
