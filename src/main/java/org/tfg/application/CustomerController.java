package org.tfg.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tfg.domain.model.Customer;

import org.tfg.domain.service.ICustomerService;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("customers")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @PostMapping("/register")
    public void register(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("pass1") String pass1,
            @RequestParam("pass2") String pass2,
            @RequestParam("imageFile") MultipartFile image
            ){

        this.customerService.register(name,email, pass1, pass2, image);
    }

    @PostMapping("/login")
    public Customer login(@RequestBody Map<String,Object> info){
        String email=info.get("email").toString();
        String password=info.get("password").toString();
        return this.customerService.login(email,password);
    }

    @GetMapping("/getCustomerById/{id}")
    public Customer getCustomerById(@PathVariable String id){
        return this.customerService.getCustomerById(id);
    }

}
