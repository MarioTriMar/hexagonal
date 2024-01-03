package org.tfg.domain.repository;

import org.tfg.domain.model.Car;

public interface ICarRepository {
    Car findById(String id);
    void save(Car car);
}
