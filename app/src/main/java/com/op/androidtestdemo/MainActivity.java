package com.op.androidtestdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.op.androidtestdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
    }

    private void initView() {
        binding.btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvContent.setVisibility(View.VISIBLE);
                binding.tvContent.setText("hello espresso");
            }
        });
        binding.btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvContent.setVisibility(View.VISIBLE);
                binding.tvContent.setText("success");
                binding.et01.setText("");
            }
        });
    }


}