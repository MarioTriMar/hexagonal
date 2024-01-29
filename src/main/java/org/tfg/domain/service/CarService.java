package org.tfg.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.tfg.domain.model.Car;
import org.tfg.domain.repository.ICarRepository;

import java.time.Duration;

@Service
public class CarService implements ICarService{

    @Autowired
    private ICarRepository carRepository;


    private final ObjectMapper objectMapper=new ObjectMapper();

    @Override
    public void save(String name) {
        Car car=new Car();
        car.setName(name);
        this.carRepository.save(car);
    }


    @Override
    @Cacheable(cacheNames = "car", key = "#id")
    public Car findById(String id) throws JsonProcessingException {
        return this.carRepository.findById(id);
    }
}

