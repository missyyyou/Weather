package com.yysha.weather.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.yysha.weather.app.WeatherApp;

public class Settings {
    public static final String XML_NAME = "settings";
    public static final String CITY_NAME = "city_name";
    private static Settings sInstance;
    private SharedPreferences mPrefs;

    public static Settings getInstance() {
        if (sInstance == null) {
            sInstance = new Settings(WeatherApp.mContext);
        }
        return sInstance;
    }

    private Settings(Context context) {
        mPrefs = context.getSharedPreferences(XML_NAME, Context.MODE_PRIVATE);
    }


    public Settings putInt(String key, int value) {
        mPrefs.edit().putInt(key, value).apply();
        return this;
    }

    public int getInt(String key, int defValue) {
        return mPrefs.getInt(key, defValue);
    }

    public Settings putString(String key, String value) {
        mPrefs.edit().putString(key, value).apply();
        return this;
    }

    public String getString(String key, String defValue) {
        return mPrefs.getString(key, defValue);
    }
}
