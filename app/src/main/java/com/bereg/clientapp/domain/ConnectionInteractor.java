package com.bereg.clientapp.domain;

import android.util.Log;

import com.bereg.clientapp.data.client.Client;

import java.lang.ref.PhantomReference;
import java.net.InetAddress;

/**
 * Created by 1 on 09.03.2018.
 */

public class ConnectionInteractor implements Runnable, Client.WeatherResultListener {

    private static final String TAG = ConnectionInteractor.class.getSimpleName();

    private WeatherResultListener mWeatherResultListener;

    @Override
    public void run() {

        try {
            Client client = new Client("192.168.1.2", 8080);
            client.setWeatherResultListener(this);
            Log.e(TAG, "new Thread");
            client.start();
        }catch (Exception e) {
            Log.e(TAG, e.toString());

        }
    }

    @Override
    public void onWeatherResultReady(String weatherResult) {

        mWeatherResultListener.onWeatherResultReady(weatherResult);
    }

    public void setWeatherResultListener(WeatherResultListener resultListener) {
        mWeatherResultListener = resultListener;
    }

    public interface WeatherResultListener {
        void onWeatherResultReady(String weatherResult);
    }
}
