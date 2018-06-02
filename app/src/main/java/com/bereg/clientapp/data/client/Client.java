package com.bereg.clientapp.data.client;

import android.util.Log;

import com.bereg.clientapp.data.core.Message;
import com.bereg.clientapp.data.core.SenderType;
import com.bereg.clientapp.data.core.communication.MessageReader;
import com.bereg.clientapp.data.core.communication.MessageWriter;
import com.bereg.clientapp.models.MessageModel;

import org.joda.time.DateTime;

import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by 1 on 09.03.2018.
 */

public class Client {

    private static final String TAG = Client.class.getSimpleName();

    private final InetAddress host;
    private final int port;
    private MessageReader reader;
    private MessageWriter writer;
    private Observable<MessageModel> messagesObservable;
    private MessageModel message;
    //private boolean sessionRunningStatus = false;
    private boolean sessionEndedStatus = false;
    private boolean newMessageReady =false;

    public Client(String host, int port) throws Exception{

        this.host = InetAddress.getByName(host);
        this.port = port;
    }

    public Observable<MessageModel> getMessagesObservable() {

        messagesObservable = Observable.create(new ObservableOnSubscribe<MessageModel>() {
            @Override
            public void subscribe(ObservableEmitter<MessageModel> e) throws Exception {
                Log.e(TAG, "subscribe:   ");
                while (/*sessionRunningStatus && */!sessionEndedStatus) {
                    if (newMessageReady/* && !message.equals(lastMsg)*/) {
                        e.onNext(message);
                        newMessageReady = false;
                        Log.e(TAG, "observable.create:   " + message);
                    }
                }
                e.onComplete();
            }
        });
        return messagesObservable;
    }

    //TODO: check why throws "connection timed out" exception
    public void start() {

        //sessionRunningStatus = true;
        try {
            Socket socket = new Socket(this.host, this.port);
            reader = new MessageReader(socket.getInputStream());
            writer = new MessageWriter(socket.getOutputStream());
            //start of communication logic
            communicationProtocol();
            socket.close();
        }catch (Exception e) {
            Log.e(TAG, "start:   " + e.toString());
        }
    }

    private void communicationProtocol() {

        //logic of communication
        try {
            writer.writeMessage(Message.HELLO);
            message = new MessageModel(SenderType.CLIENT, Message.HELLO, DateTime.now());
            Log.e(TAG, "writer:HELLO");
            waitTillMessageEmitted();

            String msg = reader.readMessage();
            message = new MessageModel(SenderType.SERVER, msg, DateTime.now());
            Log.e(TAG, "reader:   " + message);
            waitTillMessageEmitted();

            if (msg.equals(Message.HELLO)) {
                Log.e(TAG, "if");
                writer.writeMessage(Message.READY_REQUEST);
                message = new MessageModel(SenderType.CLIENT, Message.READY_REQUEST, DateTime.now());
                Log.e(TAG, "writer:READY_REQUEST");
                waitTillMessageEmitted();

                msg = reader.readMessage();
                message = new MessageModel(SenderType.SERVER, msg, DateTime.now());
                Log.e(TAG, "reader:   " + message);
                waitTillMessageEmitted();

                if (msg.equals(Message.READY_RESPONSE)) {
                    writer.writeMessage(Message.GET_INFORMATION);
                    message = new MessageModel(SenderType.CLIENT, Message.GET_INFORMATION, DateTime.now());
                    Log.e(TAG, "writer:GET_INFORMATION");
                    waitTillMessageEmitted();

                    msg = reader.readMessage();
                    message = new MessageModel(SenderType.SERVER, msg, DateTime.now());
                    Log.e(TAG, "reader:   " + message);
                    waitTillMessageEmitted();
                }
            }
            sessionEndedStatus = true;
        }catch (Exception e) {
            Log.e(TAG, "communicationProtocol:   " + e.toString());
        }
    }

    private void waitTillMessageEmitted() {

        newMessageReady = true;
        while (newMessageReady) {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
                Log.e(TAG, "waitTillMessageEmitted");
            } catch (Exception e) {
                Log.e(TAG, "waitTillMessageEmitted:   " + e.toString());
            }
        }
    }
}
