package com.bereg.clientapp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bereg.clientapp.Utils.JsonParser;
import com.bereg.clientapp.domain.ConnectionInteractor;
import com.bereg.clientapp.models.MessageModel;
import com.bereg.clientapp.models.WeatherResultModel;
import com.bereg.clientapp.presentation.view.WeatherView;

import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 11.03.2018.
 */

@InjectViewState
public class WeatherPresenter extends MvpPresenter<WeatherView> implements ConnectionInteractor.WeatherResultListener{

    private static final String TAG = WeatherPresenter.class.getSimpleName();

    private ConnectionInteractor mConnectionInteractor;
    private Router mRouter;

    public WeatherPresenter(ConnectionInteractor connectionInteractor, Router router) {
        mConnectionInteractor = connectionInteractor;
        mConnectionInteractor.setWeatherResultListener(this);
        mRouter = router;
    }

    @Override
    public void onWeatherResultReady(MessageModel weatherResult) {

        getViewState().showWeatherInfo(JsonParser.fromJson(weatherResult));
        mRouter.showSystemMessage(weatherResult.getMessage());
    }

    public void openScreen(String tag) {
        mRouter.navigateTo(tag);
    }
}
