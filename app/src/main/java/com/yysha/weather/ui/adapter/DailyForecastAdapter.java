package com.yysha.weather.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yysha.weather.R;
import com.yysha.weather.model.entity.DailyForecast;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */

public class DailyForecastAdapter extends RecyclerView.Adapter<DailyForecastAdapter.MyViewHolder> {
    private Context mContext;
    private List<DailyForecast> mDailyForecastList;

    public DailyForecastAdapter(List<DailyForecast> dailyForecastList, Context context) {
        mContext = context;
        mDailyForecastList = dailyForecastList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.date.setText(mDailyForecastList.get(position).getDate());
        holder.weatherinfo.setText(mDailyForecastList.get(position).getWeatherInfo());
        holder.weatherrange.setText(mDailyForecastList.get(position).getWeatherRange());
    }

    @Override
    public int getItemCount() {
        return mDailyForecastList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView weatherinfo;
        TextView weatherrange;

        MyViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.date);
            weatherinfo = (TextView) view.findViewById(R.id.weather_information);
            weatherrange = (TextView) view.findViewById(R.id.weather_range);
        }
    }

}
