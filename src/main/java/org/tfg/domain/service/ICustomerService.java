package org.tfg.domain.service;

import org.tfg.domain.model.Customer;

public interface ICustomerService {
    void createCustomer(String name, String email);
    Customer getCustomerById(String id);
}
