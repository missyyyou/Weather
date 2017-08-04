package com.yysha.weather.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.yysha.weather.R;
import com.yysha.weather.model.entity.CurrentCityWeather;
import com.yysha.weather.model.entity.DailyForecast;
import com.yysha.weather.model.entity.web.WeatherEntity;
import com.yysha.weather.presenter.WeatherPresenter;
import com.yysha.weather.ui.adapter.DailyForecastAdapter;
import com.yysha.weather.ui.view.WeatherView;
import com.yysha.weather.utils.Settings;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yysha.weather.utils.Settings.CITY_NAME;

public class MainActivity
        extends AppCompatActivity
        implements WeatherView {
    @BindView(R.id.action_back)
    ImageButton mActionBack;
    @BindView(R.id.searchTextView)
    EditText mSearchTextView;
    @BindView(R.id.action_search)
    ImageButton mActionSearch;
    @BindView(R.id.temp_range)
    TextView mTempRange;
    @BindView(R.id.weather_info)
    TextView mWeatherInfo;
    @BindView(R.id.weather_temp)
    TextView mWeatherTemp;
    @BindView(R.id.current_city)
    TextView mCurrentCity;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh)
    FloatingActionButton mRefresh;
    @BindView(R.id.publish_time)
    TextView mPublishTime;

    private Settings setting;
    // 返回键两次点击退出
    private long mPressedTime = 0;
    private List<DailyForecast> mDailyForecastList;
    private WeatherPresenter mWeatherPresenter = new WeatherPresenter(this);
    private DailyForecastAdapter mForecastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setting = Settings.getInstance();

        mWeatherPresenter.onCreate();
        mWeatherPresenter.attachView(this);
        mWeatherPresenter.getSearchWeather();

        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDailyForecastList = new ArrayList<>();
        mForecastAdapter = new DailyForecastAdapter(mDailyForecastList, this);
        mRecyclerView.setAdapter(mForecastAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWeatherPresenter.onStop();
    }

    @OnClick({R.id.action_back, R.id.searchTextView, R.id.action_search, R.id.refresh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.action_back:
                long mNowTime = System.currentTimeMillis();//获取第一次按键时间
                if ((mNowTime - mPressedTime) > 1000) {//比较两次按键时间差
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    mPressedTime = mNowTime;
                } else {
                    this.finish();
                    System.exit(0);
                }
                break;
            case R.id.city_search:
                mSearchTextView.requestFocus();
                InputMethodManager imm = (InputMethodManager) mSearchTextView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
                break;
            case R.id.action_search:
                String city_name = mSearchTextView.getText().toString();
                setting.putString(CITY_NAME, city_name);
                mWeatherPresenter.getSearchWeather();
                break;
            case R.id.refresh:
                mWeatherPresenter.getSearchWeather();
                break;
        }
    }

    @Override
    public void onSuccess(WeatherEntity mWeatherEntity) {
        SimpleDateFormat formatter=new SimpleDateFormat("HH:mm:ss");
        String refreshTime=formatter.format(new Date(System.currentTimeMillis()))+"更新";
        CurrentCityWeather currentCityWeather = new CurrentCityWeather(mWeatherEntity);
        mCurrentCity.setText(currentCityWeather.getCurrentCity());
        mWeatherTemp.setText(currentCityWeather.getWeatherTemp());
        mWeatherInfo.setText(currentCityWeather.getWeatherInfo());
        mTempRange.setText(currentCityWeather.getTempRange());
        mPublishTime.setText(refreshTime);
        List<WeatherEntity.HeWeather5Bean.DailyForecastBean> beanList =
                mWeatherEntity.getHeWeather5().get(0).getDaily_forecast();
        mDailyForecastList.clear();
        for (int i = 0; i < 3; i++) {
            DailyForecast dailyForecast = new DailyForecast(beanList.get(i));
            mDailyForecastList.add(dailyForecast);
        }
        mForecastAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }
}