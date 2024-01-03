package org.tfg.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.tfg.domain.model.Car;

public interface ICarService {
    void save(String name);
    Car findById(String id) throws JsonProcessingException;
}
