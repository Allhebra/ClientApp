package com.bereg.clientapp.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.bereg.clientapp.models.MessageModel;
import com.bereg.clientapp.models.WeatherResultModel;

/**
 * Created by 1 on 11.03.2018.
 */

public interface WeatherView extends MvpView {

    void showWeatherInfo(WeatherResultModel weatherResultModel);
}
