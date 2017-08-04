package com.yysha.weather.model;

import com.yysha.weather.model.entity.web.WeatherEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitService {
    String HOST = "https://free-api.heweather.com/v5/";

    @GET("weather")
    Observable<WeatherEntity> getWeather(@Query("city") String city, @Query("key") String key);
}