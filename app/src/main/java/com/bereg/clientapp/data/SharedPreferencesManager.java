package com.bereg.clientapp.data;

import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import com.bereg.clientapp.App;

/**
 * Created by 1 on 19.05.2018.
 */

public class SharedPreferencesManager {

    private static final String TAG = SharedPreferencesManager.class.getSimpleName();
    private SharedPreferences mPrefs;

    public SharedPreferencesManager() {

        mPrefs = PreferenceManager
                .getDefaultSharedPreferences(App.getInstance().getApplicationContext());
    }

    public String readServerAddress() {

        return mPrefs.getString("server_address", "address not specified");
    }

    public int readServerPort() {

        return Integer.valueOf(mPrefs.getString("server_port", " port not specified"));
    }
}
