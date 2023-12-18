package org.tfg.domain.repository;

import org.tfg.domain.model.Customer;

public interface ICustomerRepository {
    void save(Customer customer);
    Customer findByEmail(String email);
    Customer findById(String id);
}
