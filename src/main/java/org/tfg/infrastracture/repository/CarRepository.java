package org.tfg.infrastracture.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.tfg.domain.model.Car;
import org.tfg.domain.repository.ICarRepository;

import java.util.Optional;

@Component
public class CarRepository implements ICarRepository {

    @Autowired
    private JpaCarRepository carRepository;

    @Override
    public Car findById(String id) {
        Optional<CarEntity> carEntity=this.carRepository.findById(id);
        if(carEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe");
        }
        return carEntity.get().toCar();
    }

    @Override
    public Car save(Car car) {
        return this.carRepository.save(new CarEntity(car)).toCar();
    }
}
