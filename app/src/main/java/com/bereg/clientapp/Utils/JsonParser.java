package com.bereg.clientapp.Utils;

import android.util.Log;

import com.bereg.clientapp.models.MessageModel;
import com.bereg.clientapp.models.WeatherResultModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 1 on 02.06.2018.
 */

public class JsonParser {

    private static final String TAG = JsonParser.class.getSimpleName();

    public static WeatherResultModel fromJson(MessageModel messageModel) {

        Gson gson = new Gson();
        WeatherResultModel weatherResultModel = new WeatherResultModel();

        weatherResultModel.setSender(messageModel.getSender());
        weatherResultModel.setTimestamp(messageModel.getTimestamp());
        //Map<String, String> map = gson.fromJson(messageModel.getMessage(), new TypeToken<WeatherResultModel>() {}.getType());
        Map map = gson.fromJson(messageModel.getMessage(), HashMap.class);
        if (map instanceof Map<String,String>) {
            weatherResultModel.setTemp((Map<String, String>) map);
        }

        Log.e(TAG, "1212121212" + weatherResultModel);
        Log.e(TAG, "fromJson:   ");

        return weatherResultModel;
    }
}
