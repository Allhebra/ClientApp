package com.bereg.clientapp.data.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by 1 on 09.03.2018.
 */

public class Message {

    public static final String HELLO = "hello";
    public static final String READY_REQUEST = "ready?";
    public static final String READY_RESPONSE = "ready!";
    public static final String GET_INFORMATION = "getInformation";
    //TODO: make template random or gotten from real source;
    public static final String TEMPLATE = "{\"temp\":{\"day\":15,\"min\":5,\"pressure\":1013,\"humidity\":44,\"max\":18,\"night\":5,\"eve\":7,\"morn\":10}}";

    private String message;

    public void readExternal(DataInputStream dis) throws IOException {
        message = dis.readUTF();
    }

    public void writeExternal(DataOutputStream dos) throws IOException {
        dos.writeUTF(message);
    }
}
