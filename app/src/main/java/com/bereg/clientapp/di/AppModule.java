package com.bereg.clientapp.di;

import android.content.Context;

import com.bereg.clientapp.App;
import com.bereg.clientapp.data.InMemoryCacheManager;
import com.bereg.clientapp.data.SharedPreferencesManager;
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
    ConnectionInteractor provideConnectionInteractor(SharedPreferencesManager sharedPreferencesManager, InMemoryCacheManager inMemoryCacheManager) {
        return new ConnectionInteractor(sharedPreferencesManager, inMemoryCacheManager);
    }

    @Provides
    @Singleton
    Router provideRouter() {
        return App.getInstance().getRouter();
    }

    @Provides
    @Singleton
    SharedPreferencesManager provideSharedPreferencesManager() {
        return new SharedPreferencesManager();
    }

    @Provides
    @Singleton
    InMemoryCacheManager provideInMemoryCacheManager() {
        return new InMemoryCacheManager();
    }
}
