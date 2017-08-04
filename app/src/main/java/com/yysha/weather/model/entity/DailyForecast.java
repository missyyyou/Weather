package com.yysha.weather.model.entity;

import com.yysha.weather.model.entity.web.WeatherEntity;

/**
 * Created by Administrator on 2017/7/12.
 */

public class DailyForecast {
    private String mDate;
    private String mWeatherInfo;
    private String mWeatherRange;

    public DailyForecast(WeatherEntity.HeWeather5Bean.DailyForecastBean bean) {
        mDate = bean.getDate();
        mWeatherInfo = bean.getCond().getTxt_d();
        String min = bean.getTmp().getMin();
        String max = bean.getTmp().getMax();
        mWeatherRange = min+"°"+ "~" + max+"°";
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public String getWeatherInfo() {
        return mWeatherInfo;
    }

    public void setWeatherInfo(String weatherInfo) {
        this.mWeatherInfo = weatherInfo;
    }

    public String getWeatherRange() {
        return mWeatherRange;
    }

    public void setWeatherRange(String weatherRange) {
        this.mWeatherRange = weatherRange;
    }
}
