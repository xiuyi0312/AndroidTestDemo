package com.op.androidtestdemo.util;

import android.text.TextUtils;

public class PasswordValidator {
    public boolean validatePassword(String input) {
        if (input == null || input.length() < 6) {
            return false;
        }
        return true;
    }
}
