package com.yysha.weather.model.manager;

import android.content.Context;

import com.yysha.weather.model.RetrofitHelper;
import com.yysha.weather.model.RetrofitService;
import com.yysha.weather.model.entity.web.WeatherEntity;

import rx.Observable;

public class DataManager {

    private RetrofitService mRetrofitService;

    public DataManager(Context context) {
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }

    public Observable<WeatherEntity> getWeather(String city) {
        return mRetrofitService.getWeather(city, "a09cb5f487db451589356ba7280b0084");
    }
}