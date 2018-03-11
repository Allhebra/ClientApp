package com.bereg.clientapp.presentation.view;

import com.arellomobile.mvp.MvpView;

/**
 * Created by 1 on 11.03.2018.
 */

public interface WeatherView extends MvpView {

    void showWeatherInfo(String weatherInfo);
}
