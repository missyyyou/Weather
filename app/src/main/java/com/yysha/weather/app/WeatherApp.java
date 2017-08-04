package com.yysha.weather.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/7/12.
 */

public class WeatherApp extends Application {
    @SuppressLint("StaticFieldLeak")
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
