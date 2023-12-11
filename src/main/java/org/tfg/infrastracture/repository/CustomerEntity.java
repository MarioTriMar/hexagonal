package org.tfg.infrastracture.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.tfg.domain.model.Customer;

import java.io.Serializable;

@Entity
@Table(
        name="customers"
)
public class CustomerEntity implements Serializable {
    @Id
    private String id;
    @Column(length = 100)
    private String name;
    @Column(length = 140)
    private String email;

    public CustomerEntity() {
    }

    public CustomerEntity(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmail();
    }

    public Customer toCustomer(){
        return new Customer(this.id, this.name, this.email);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
