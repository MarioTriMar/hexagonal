package org.tfg.infrastracture.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    @KafkaListener(topics = "prueba-channel", groupId = "prueba-hex")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}
