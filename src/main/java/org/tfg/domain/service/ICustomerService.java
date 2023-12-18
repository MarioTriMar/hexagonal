package org.tfg.domain.service;

import org.springframework.web.multipart.MultipartFile;
import org.tfg.domain.model.Customer;

public interface ICustomerService {
    void register(String name, String email, String pass1, String pass2, MultipartFile image);
    Customer login(String email, String password);
    Customer getCustomerById(String id);

}
