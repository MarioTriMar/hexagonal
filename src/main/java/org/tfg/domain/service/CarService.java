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
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    private final ObjectMapper objectMapper=new ObjectMapper();

    @Override
    public void save(String name) {
        Car car=new Car();
        car.setName(name);
        this.carRepository.save(car);
    }
    /*
    @Override
    public Car findById(String id) throws JsonProcessingException {
        String key="car::"+id;
        String carRedis=(String) redisTemplate.opsForValue().get(key);
        if(carRedis==null){
            Car car=this.carRepository.findById(id);
            String companyJson = objectMapper.writeValueAsString(car);
            redisTemplate.opsForValue().set(key,companyJson, Duration.ofMinutes(22220));
            return car;
        }else{
            return objectMapper.readValue(carRedis, new TypeReference<Car>(){});
        }

    */

    @Override
    @Cacheable(cacheNames = "car", key = "#id")
    public Car findById(String id) throws JsonProcessingException {
        return this.carRepository.findById(id);
    }
}

