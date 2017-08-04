package com.yysha.weather.model.entity;



import com.yysha.weather.model.entity.web.WeatherEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/17.
 */

public class CurrentCityWeather {
    private String currentCity;
    private String weatherTemp;
    private String weatherInfo;
    private String tempRange;


    public CurrentCityWeather(WeatherEntity entity) {
        this.currentCity = entity.getHeWeather5().get(0).getBasic().getCity();
        this.weatherTemp = entity.getHeWeather5().get(0).getHourly_forecast().get(0).getTmp()+"°";
        this.weatherInfo = entity.getHeWeather5().get(0).getHourly_forecast().get(0).getCond().getTxt();
        this.tempRange = entity.getHeWeather5().get(0).getDaily_forecast().get(0).getTmp().getMin()+"°"+"~"
        + entity.getHeWeather5().get(0).getDaily_forecast().get(0).getTmp().getMax()+"°";
          }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getWeatherTemp() {
        return weatherTemp;
    }

    public void setWeatherTemp(String weatherTemp) {
        this.weatherTemp = weatherTemp;
    }

    public String getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(String weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    public String getTempRange() {
        return tempRange;
    }

    public void setTempRange(String tempRange) {
        this.tempRange = tempRange;
    }

}
