package org.tfg.infrastracture.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {
    private Logger logger= LoggerFactory.getLogger(Listener.class);

    @KafkaListener(topics = "prueba-channel")
    public void handler(String message){
        logger.info("mensaje recibido: "+message);
    }

}
