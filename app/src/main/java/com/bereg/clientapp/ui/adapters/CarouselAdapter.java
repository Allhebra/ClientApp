package com.bereg.clientapp.ui.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bereg.clientapp.R;
import com.bereg.clientapp.models.WeatherResultModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 02.06.2018.
 */

public class CarouselAdapter extends PagerAdapter {

    private static final String TAG = CarouselAdapter.class.getSimpleName();

    private List<WeatherResultModel> weatherResultModels = new ArrayList<>();
    private Context mContext;

    public CarouselAdapter(Context context, List<WeatherResultModel> weatherResultModels) {
        this.weatherResultModels = weatherResultModels;
        mContext = context;
        Log.e(TAG, "Constructor:   " + weatherResultModels);
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.weather_carousel_item_layout, collection, false);

        TextView date = layout.findViewById(R.id.tv_date);
        TextView dayTemp = layout.findViewById(R.id.tv_day_temp);
        TextView minTemp = layout.findViewById(R.id.tv_min_temp);
        TextView maxTemp = layout.findViewById(R.id.tv_max_temp);
        TextView mornTemp = layout.findViewById(R.id.tv_morn_temp);
        TextView eveTemp = layout.findViewById(R.id.tv_eve_temp);
        TextView nightTemp = layout.findViewById(R.id.tv_night_temp);
        TextView pressure = layout.findViewById(R.id.tv_pressure);
        TextView humidity = layout.findViewById(R.id.tv_humidity);

        date.setText(weatherResultModels.get(position).getTimestamp().toString());
        dayTemp.setText(weatherResultModels.get(position).getTemp().get("day"));
        minTemp.setText(weatherResultModels.get(position).getTemp().get("min"));
        maxTemp.setText(weatherResultModels.get(position).getTemp().get("max"));
        mornTemp.setText(weatherResultModels.get(position).getTemp().get("morn"));
        eveTemp.setText(weatherResultModels.get(position).getTemp().get("eve"));
        nightTemp.setText(weatherResultModels.get(position).getTemp().get("night"));
        pressure.setText(weatherResultModels.get(position).getTemp().get("pressure"));
        humidity.setText(weatherResultModels.get(position).getTemp().get("humidity"));

        collection.addView(layout);
        Log.e(TAG, "instantiateItem:   " + collection + position + weatherResultModels + weatherResultModels.get(position).getTimestamp());
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
        Log.e(TAG, "destroyItem:   " + collection + position + view + weatherResultModels);
    }

    @Override
    public int getCount() {
        Log.e(TAG, "getCount:   " + weatherResultModels + weatherResultModels.size());
        if (weatherResultModels.size()>0) {
            Log.e(TAG, "getCount:   " + weatherResultModels.get(0));
        }
        return weatherResultModels.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        Log.e(TAG, "isViewFromObject:   " + view + object + weatherResultModels);
        return object.getClass()==view.getClass();
    }
}
