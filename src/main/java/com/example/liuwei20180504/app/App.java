package com.example.liuwei20180504.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Administrator on 2018/5/4.
 */

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        Fresco.initialize(this);
    }
}
