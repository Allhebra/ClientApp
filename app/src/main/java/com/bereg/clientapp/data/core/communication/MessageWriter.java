package com.bereg.clientapp.data.core.communication;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by 1 on 09.03.2018.
 */

public class MessageWriter {

    private final DataOutputStream out;

    public MessageWriter(OutputStream os) {
        this.out = new DataOutputStream(os);
    }

    public void writeMessage(String message) throws IOException {

        out.writeUTF(message);
        out.flush();
    }
}
