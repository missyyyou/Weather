package com.yysha.weather.ui.view;

import com.yysha.weather.model.entity.web.WeatherEntity;

public interface WeatherView extends View {
    void onSuccess(WeatherEntity mWeatherEntity);

    void onError(String result);
}