package org.tfg.infrastracture.repository;

import jakarta.persistence.*;
import org.tfg.domain.model.Car;

import java.io.Serializable;

@Entity
@Table(
        name="cars",
        indexes = {
                @Index(columnList="name",unique=true)
        }
)
public class CarEntity implements Serializable {

    @Id
    private String id;

    @Column(length=100)
    private String name;

    public CarEntity(){}

    public CarEntity(Car car){
        this.id=car.getId();
        this.name=car.getName();
    }

    public Car toCar(){
        return new Car(this.id,this.name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
