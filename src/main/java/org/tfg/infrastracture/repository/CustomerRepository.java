package org.tfg.infrastracture.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.tfg.domain.model.Customer;
import org.tfg.domain.repository.ICustomerRepository;

import java.util.Optional;

@Component
public class CustomerRepository implements ICustomerRepository {

    @Autowired
    private JpaCustomerRepository customerRepository;

    @Override
    public void save(Customer customer) {
        customerRepository.save(new CustomerEntity(customer));
    }

    @Override
    public Customer findById(String id) {
        Optional<CustomerEntity> customerEntity = this.customerRepository.findById(id);
        if(customerEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer doesn´t exist");
        }
        return customerEntity.get().toCustomer();
    }
}
