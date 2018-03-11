package com.bereg.clientapp.data.client;

import android.util.Log;

import com.bereg.clientapp.data.core.Message;
import com.bereg.clientapp.data.core.communication.MessageReader;
import com.bereg.clientapp.data.core.communication.MessageWriter;

import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by 1 on 09.03.2018.
 */

public class Client {

    private static final String TAG = Client.class.getSimpleName();

    private final InetAddress host;
    private final int port;
    private MessageReader reader;
    private MessageWriter writer;
    private WeatherResultListener mWeatherResultListener;

    public Client(String host, int port) throws Exception{

        this.host = InetAddress.getByName(host);
        this.port = port;
    }

    public void start() {

        try {
            Log.e(TAG, "try");

            Socket socket = new Socket(this.host, this.port);
            reader = new MessageReader(socket.getInputStream());
            writer = new MessageWriter(socket.getOutputStream());

            //Запуск логики приложения
            this.logicStart();
            //socket.close();
        }catch (Exception e) {
            Log.e(TAG, "start:   " + e.toString());
        }
    }

    private void logicStart() {
        //Логика приложения
        try {
            writer.writeMessage(Message.HELLO);
            Log.e(TAG, "writer:HELLO");

            String msg = reader.readMessage();
            Log.e(TAG, "reader:   " + msg);
            if (msg.equals(Message.HELLO)) {
                Log.e(TAG, "if");
                writer.writeMessage(Message.READY_REQUEST);
                Log.e(TAG, "writer:READY_REQUEST");
                msg = reader.readMessage();
                Log.e(TAG, "reader:   " + msg);
                if (msg.equals(Message.READY_RESPONSE)) {
                    writer.writeMessage(Message.GET_INFORMATION);
                    Log.e(TAG, "writer:GET_INFORMATION");
                    String weatherResult = reader.readMessage();
                    mWeatherResultListener.onWeatherResultReady(weatherResult);
                }
            }
        }catch (Exception e) {
            Log.e(TAG, "logicStart:   " + e.toString());
        }
    }

    public void setWeatherResultListener(WeatherResultListener resultListener) {
        mWeatherResultListener = resultListener;
    }

    public interface WeatherResultListener {
        void onWeatherResultReady(String weatherResult);
    }
}
