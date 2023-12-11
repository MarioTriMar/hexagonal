package org.tfg.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tfg.domain.model.Customer;
import org.tfg.domain.repository.ICustomerRepository;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public void createCustomer(String name, String email) {
        Customer customer=new Customer();
        customer.setName(name);
        customer.setEmail(email);
        this.customerRepository.save(customer);
    }

    @Override
    @Cacheable(cacheNames = "customers", key="#id")
    public Customer getCustomerById(String id) {
        return this.customerRepository.findById(id);
    }
}
