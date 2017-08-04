package com.yysha.weather.presenter;

import android.content.Intent;

import com.yysha.weather.ui.view.View;

public interface Presenter {
    void onCreate();

    void onStart();

    void onStop();

    void pause();

    void attachView(View view);

    void attachIncomingIntent(Intent intent);
}