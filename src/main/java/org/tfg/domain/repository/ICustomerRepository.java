package org.tfg.domain.repository;

import org.tfg.domain.model.Customer;

public interface ICustomerRepository {
    void save(Customer customer);
    Customer findById(String id);
}
