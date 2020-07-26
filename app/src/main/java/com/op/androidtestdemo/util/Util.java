package com.op.androidtestdemo.util;

import android.content.Context;
import android.content.Intent;

import com.op.androidtestdemo.MainActivity;

public class Util {
    public static Intent createQuery(Context context, String query, String value) {
        // 简单起见，重用MainActivity
        Intent i = new Intent(context, MainActivity.class);
        i.putExtra("QUERY", query);
        i.putExtra("VALUE", value);
        return i;
    }
}
