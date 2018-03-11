package com.bereg.clientapp.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bereg.clientapp.App;
import com.bereg.clientapp.R;
import com.bereg.clientapp.Utils.Screens;
import com.bereg.clientapp.presentation.presenter.MainPresenter;
import com.bereg.clientapp.ui.fragment.WeatherFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    MainPresenter mMainPresenter;

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.fragment_container) {

        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            switch (screenKey) {
                case Screens.WEATHER_SCREEN:
                    Log.e(TAG, "WEATHER_SCREEN");
                    return WeatherFragment.getInstance();
            }
            Log.e(TAG, "NULL");
            return null;
        }

        @Override
        protected void showSystemMessage(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void exit() {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainPresenter = App.getAppComponent().getMainPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.getInstance().getNavigatorHolder().setNavigator(navigator);
        Log.e(TAG, "onResume");
        Toast.makeText(MainActivity.this, "ActivityOnResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {

        App.getInstance().getNavigatorHolder().removeNavigator();
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @OnClick(R.id.btn_come_in)
    public void onComeInButtonClick() {
        mMainPresenter.onComeInClicked();
    }
}
