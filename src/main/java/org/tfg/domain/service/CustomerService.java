package org.tfg.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.tfg.domain.model.Customer;
import org.tfg.domain.repository.ICustomerRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void register(String name, String email, String pass1, String pass2, MultipartFile image) {
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        if(!email.toLowerCase().matches(regex.toLowerCase())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Email incorrecto");
        }
        if(!pass1.equals(pass2)){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Las contraseñas no coinciden");
        }
        PasswordEncoder ncoder=new BCryptPasswordEncoder();
        Customer customer=new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(ncoder.encode(pass1));
        try{
            customer.setImage(compressBytes(image.getBytes()));
            this.customerRepository.save(customer);
        }catch (IOException e){
            throw new RuntimeException("Error al procesar imagen");
        }
    }

    @Override
    public Customer login(String email, String password) {
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        if(!email.toLowerCase().matches(regex.toLowerCase())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Email incorrecto");
        }
        Customer customer = this.customerRepository.findByEmail(email);
        PasswordEncoder dcoder=new BCryptPasswordEncoder();
        if(!dcoder.matches(password, customer.getPassword())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Login incorrecto");
        }

        return customer;
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    @Override
    public Customer getCustomerById(String id) {

        Customer customer = this.customerRepository.findById(id);
        redisTemplate.opsForValue().set(customer.getId(), customer);
        return customer;
    }
}
