package com.example.muhammadhumayun.readqr.Validation;

import android.content.Context;
import android.widget.Toast;

public class ShowableException extends Exception {

    public void notifyUserWithToast(Context context) {
        Toast.makeText(context, toString(), Toast.LENGTH_SHORT).show();
    }
}
