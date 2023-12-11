package org.tfg.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tfg.domain.model.Customer;

import org.tfg.domain.service.ICustomerService;

import java.util.Map;

@RestController
@RequestMapping("customers")
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @PostMapping("/saveCustomer")
    public void saveCustomer(@RequestBody Map<String,Object> info){
        String name=info.get("name").toString();
        String email=info.get("email").toString();
        this.customerService.createCustomer(name,email);
    }

    @GetMapping("/getCustomerById/{id}")
    public Customer getCustomerById(@PathVariable String id){
        return this.customerService.getCustomerById(id);
    }
}
