package org.tfg.application;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class Receiver implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println(message);
    }
}
