package com.example.muhammadhumayun.readqr;

import android.util.Log;

public class MerchantEncoder {
    public static String TAG;

    public static String GeneratePayload(MerchantQRData merchantQRData){
        StringBuilder qrData = new StringBuilder();

        qrData.append(merchantQRData.payloadFormatIndicator);
        qrData.append(merchantQRData.pointOfInitializationMethod);
        qrData.append(merchantQRData.merchantCategoryCode);
        Log.e("generated payload",qrData.toString());

        return qrData.toString();
    }

}
