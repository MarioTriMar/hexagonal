package org.tfg.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.tfg.domain.model.Car;
import org.tfg.domain.service.ICarService;

@RestController
@RequestMapping("car")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class CarController {
    @Autowired
    private ICarService carService;


    @GetMapping("/getCarById/{id}")
    public Car getCarById(@PathVariable String id) throws JsonProcessingException {
        return this.carService.findById(id);
    }

    @PostMapping("/register/{name}")
    public void register(@PathVariable String name){
        this.carService.save(name);
    }
}
