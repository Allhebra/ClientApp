package com.bereg.clientapp.presentation.presenter;

import android.util.Log;

import com.bereg.clientapp.Utils.Screens;
import com.bereg.clientapp.domain.ConnectionInteractor;

import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 09.03.2018.
 */

public class MainPresenter implements ConnectionInteractor.WeatherResultListener {

    private static final String TAG = MainPresenter.class.getSimpleName();
    private ConnectionInteractor mConnectionInteractor;
    private Router mRouter;

    public MainPresenter(ConnectionInteractor connectionInteractor, Router router) {
        mConnectionInteractor = connectionInteractor;
        mConnectionInteractor.setWeatherResultListener(this);
        mRouter = router;
    }

    public void onComeInClicked() {

        try {
            mRouter.showSystemMessage("onComeInClicked");
            new Thread(mConnectionInteractor).start();
            mRouter.navigateTo(Screens.WEATHER_SCREEN);
        }catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public void onWeatherResultReady(String weatherResult) {


    }
}
