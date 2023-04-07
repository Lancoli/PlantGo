package com.example.myapplication.logic.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Validator {

    public static boolean EmptyValidator(String valueToTest) {
        return (TextUtils.isEmpty(valueToTest) || valueToTest.equals(Constants.EMPTY_OPTION));
    }

    public static void setEmptySpinnerError(Spinner spinner, String message) {
        TextView errorText = (TextView)spinner.getSelectedView();
        errorText.setError("");
        errorText.setTextColor(Color.RED);//just to highlight that this is an error
        errorText.setText(message);
    }
}
