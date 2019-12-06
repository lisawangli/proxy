package com.example.switchnetwork;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {

    public static RequestQueue queue;
    @Override
    public void onCreate() {
        super.onCreate();
        HttpProxy.getInstance().init(new OkHttp());
    }
}
