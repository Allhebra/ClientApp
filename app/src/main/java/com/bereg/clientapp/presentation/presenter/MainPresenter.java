package com.bereg.clientapp.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bereg.clientapp.Utils.Screens;
import com.bereg.clientapp.domain.ConnectionInteractor;
import com.bereg.clientapp.models.MessageModel;
import com.bereg.clientapp.presentation.view.MainView;

import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 09.03.2018.
 */

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> implements ConnectionInteractor.WeatherResultListener {

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
            getViewState().hideWidgets();
        }catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public void onWeatherResultReady(MessageModel weatherResult) {


    }
}
