package com.bereg.clientapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bereg.clientapp.App;
import com.bereg.clientapp.R;
import com.bereg.clientapp.Utils.Screens;
import com.bereg.clientapp.domain.ConnectionInteractor;
import com.bereg.clientapp.models.MessageModel;
import com.bereg.clientapp.presentation.presenter.WeatherPresenter;
import com.bereg.clientapp.presentation.view.WeatherView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.Router;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeatherFragment#getInstance} factory method to
 * create an instance of this fragment.
 */

public class WeatherFragment extends MvpAppCompatFragment implements WeatherView {

    @BindView(R.id.tv_weather_result)
    TextView weatherResult;

    @InjectPresenter
    WeatherPresenter mWeatherPresenter;

    @ProvidePresenter
    WeatherPresenter provideInfoPresenter() {

        ConnectionInteractor mConnectionInteractor = App.getAppComponent().getConnectionInteractor();
        Router router = App.getInstance().getRouter();
        return new WeatherPresenter(mConnectionInteractor, router);
    }

    public WeatherFragment() {
    }

    public static WeatherFragment getInstance() {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_update:
                                break;
                            case R.id.action_settings:
                                mWeatherPresenter.openScreen(Screens.SETTINGS_SCREEN);
                                break;
                            case R.id.action_logs:
                                mWeatherPresenter.openScreen(Screens.LOGS_SCREEN);
                                break;
                        }
                        return true;
                    }
                });
    }

    @Override
    public void showWeatherInfo(MessageModel weatherInfo) {

        weatherResult.setText(weatherInfo.getMessage());
    }
}
