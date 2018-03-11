package com.bereg.clientapp.di;

import android.content.Context;

import com.bereg.clientapp.App;
import com.bereg.clientapp.domain.ConnectionInteractor;
import com.bereg.clientapp.presentation.presenter.MainPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 08.03.2018.
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    MainPresenter provideMainPresenter(ConnectionInteractor connectionInteractor, Router router) {
        return new MainPresenter(connectionInteractor, router);
    }
    @Provides
    @Singleton
    ConnectionInteractor provideConnectionInteractor() {
        return new ConnectionInteractor();
    }

    @Provides
    @Singleton
    Router provideRouter() {
        return App.getInstance().getRouter();
    }
}
