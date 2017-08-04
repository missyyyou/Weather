package com.yysha.weather.presenter;

import android.content.Context;
import android.content.Intent;


import com.yysha.weather.utils.Settings;
import com.yysha.weather.model.entity.web.WeatherEntity;
import com.yysha.weather.model.manager.DataManager;
import com.yysha.weather.ui.view.View;
import com.yysha.weather.ui.view.WeatherView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class WeatherPresenter implements Presenter {
    private Settings setting;
    //private String city_name;
    private DataManager manager;

    private Context mContext;
    private WeatherView mWeatherView;
    private WeatherEntity mWeatherEntity;
    private CompositeSubscription mCompositeSubscription;

    public WeatherPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate() {
        manager = new DataManager(mContext);
        mCompositeSubscription = new CompositeSubscription();
        setting = Settings.getInstance();
       // city_name = setting.getString(Settings.CITY_NAME, "成都");
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {
    }


    @Override
    public void attachView(View view) {
        mWeatherView = (WeatherView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {
    }

    public void getSearchWeather() {
        mCompositeSubscription.add(manager.getWeather(setting.getString(Settings.CITY_NAME, "成都"))
                //用city_name代替的话没有初始化
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherEntity>() {
                    @Override
                    public void onCompleted() {
                        if (mWeatherEntity != null) {
                            mWeatherView.onSuccess(mWeatherEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mWeatherView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(WeatherEntity weatherEntity) {
                        mWeatherEntity = weatherEntity;
                    }
                })
        );
    }
}