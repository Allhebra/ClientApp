package com.bereg.clientapp.di;

import com.bereg.clientapp.data.InMemoryCacheManager;
import com.bereg.clientapp.domain.ConnectionInteractor;
import com.bereg.clientapp.presentation.presenter.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 1 on 08.03.2018.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    ConnectionInteractor getConnectionInteractor();
    InMemoryCacheManager getInMemoryCacheManager();
}
