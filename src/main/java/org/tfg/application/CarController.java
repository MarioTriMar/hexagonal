package org.tfg.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.tfg.domain.model.Car;
import org.tfg.domain.service.ICarService;

@RestController
@RequestMapping("car")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class CarController {
    @Autowired
    private ICarService carService;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    private Logger logger = LoggerFactory.getLogger(CarController.class);


    @GetMapping("/getCarById/{id}")
    public Car getCarById(@PathVariable String id) throws JsonProcessingException {
        return this.carService.findById(id);
    }

    @PostMapping("/register/{name}")
    public Car register(@PathVariable String name){
        this.kafkaTemplate.send("prueba-channel","Yepa: " +name);
        this.logger.info("Evento enviado");
        return this.carService.save(name);
    }

    @GetMapping("/funcion2")
    public String funcion2(){
        return "funcion2";
    }
}
