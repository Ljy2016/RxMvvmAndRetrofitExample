package com.example.admin.rxmvvmandretrofitexample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.admin.rxmvvmandretrofitexample.R;

/**
 * Created by Admin on 2016/11/3.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mian);
    }

    public void run1(View view) {
        startActivity(new Intent(MainActivity.this, FuLiActivityMvvM.class));
    }

    public void run2(View view) {
        startActivity(new Intent(MainActivity.this, FuLiActivityMvp.class));
    }

    public void run3(View view) {
        startActivity(new Intent(MainActivity.this, FuLiActivityMvpDagger2S.class));
    }

    public void run4(View view) {
        startActivity(new Intent(MainActivity.this, FuLiActivityMvpDagger2M.class));
    }
}
