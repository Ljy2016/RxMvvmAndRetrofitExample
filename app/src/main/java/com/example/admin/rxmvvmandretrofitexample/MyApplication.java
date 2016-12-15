package com.example.admin.rxmvvmandretrofitexample;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Admin on 2016/10/29.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
