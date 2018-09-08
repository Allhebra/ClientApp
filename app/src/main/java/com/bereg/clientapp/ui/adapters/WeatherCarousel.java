package com.bereg.clientapp.ui.adapters;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;

import com.bereg.clientapp.presentation.presenter.WeatherPresenter;

/**
 * Created by 1 on 02.06.2018.
 */

public class WeatherCarousel extends ViewPager {

    private static final String TAG = WeatherCarousel.class.getSimpleName();

    public WeatherCarousel(Context context) {
        super(context);
        Log.e(TAG, "Constructor");
    }

    public WeatherCarousel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
