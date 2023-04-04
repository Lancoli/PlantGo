package com.example.myapplication.business.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class ToastMaker {

    public static boolean EmptyToastValidator(Context context, String valueToTest, String message) {
        boolean hasError = false;
        if (TextUtils.isEmpty(valueToTest) || valueToTest.equals(Constants.EMPTY_OPTION)) {
            hasError = true;
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
        return hasError;
    }
}
