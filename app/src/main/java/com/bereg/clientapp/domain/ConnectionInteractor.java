package com.bereg.clientapp.domain;

import android.util.Log;

import com.bereg.clientapp.data.InMemoryCacheManager;
import com.bereg.clientapp.data.SharedPreferencesManager;
import com.bereg.clientapp.data.client.Client;
import com.bereg.clientapp.data.core.Message;
import com.bereg.clientapp.models.MessageModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 1 on 09.03.2018.
 */

public class ConnectionInteractor implements Runnable {

    private static final String TAG = ConnectionInteractor.class.getSimpleName();

    private WeatherResultListener mWeatherResultListener;
    private SharedPreferencesManager mSharedPreferencesManager;
    private InMemoryCacheManager mInMemoryCacheManager;
    private Client mClient;

    public ConnectionInteractor(SharedPreferencesManager sharedPreferencesManager, InMemoryCacheManager inMemoryCacheManager) {

        mSharedPreferencesManager = sharedPreferencesManager;
        mInMemoryCacheManager = inMemoryCacheManager;
        Log.e(TAG, "Constructor");
    }

    @Override
    public void run() {

        try {
            //Client client = new Client("192.168.1.4"/*"10.0.2.15"*/, 8080);
            mClient = new Client(mSharedPreferencesManager.readServerAddress(), mSharedPreferencesManager.readServerPort());
            mClient.getMessagesObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<MessageModel>() {
                        @Override
                        public void accept(MessageModel model) throws Exception {
                            Log.e(TAG, "accept:   " + model);
                            mInMemoryCacheManager.addMessageToMemoryCache(model);
                            if (model.getMessage().contains(Message.TEMPLATE)) {  //TODO: bad place, need to change. See Message class.
                                mWeatherResultListener.onWeatherResultReady(model);
                            }
                        }
                    });
            mClient.start();
        }catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    public void setWeatherResultListener(WeatherResultListener resultListener) {
        mWeatherResultListener = resultListener;
    }

    public interface WeatherResultListener {
        void onWeatherResultReady(MessageModel weatherResult);
    }
}
