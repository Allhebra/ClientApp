package com.bereg.clientapp.models;

import org.joda.time.DateTime;

import java.util.Map;

/**
 * Created by 1 on 02.06.2018.
 */

public class WeatherResultModel {

    private String sender;
    private DateTime timestamp;
    private Map<String, String> temp;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, String> getTemp() {
        return temp;
    }

    public void setTemp(Map<String, String> temp) {
        this.temp = temp;
    }
}
