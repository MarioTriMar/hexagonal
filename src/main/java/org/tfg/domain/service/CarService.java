package org.tfg.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.tfg.domain.model.Car;
import org.tfg.domain.repository.ICarRepository;



@Service
public class CarService implements ICarService{

    @Autowired
    private ICarRepository carRepository;



    @Override
    public Car save(String name) {
        Car car=new Car();
        car.setName(name);
        return this.carRepository.save(car);
    }


    @Override
    public Car findById(String id) throws JsonProcessingException {
        return this.carRepository.findById(id);
    }
}

