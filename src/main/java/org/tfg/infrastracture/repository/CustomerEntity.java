package org.tfg.infrastracture.repository;

import jakarta.persistence.*;
import org.tfg.domain.model.Customer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@Entity
@Table(
        name="customers",
        indexes = {
                @Index(columnList="email",unique=true)
        }
)
public class CustomerEntity implements Serializable {
    @Id
    private String id;
    @Column(length = 100)
    private String name;
    @Column(length = 140)
    private String email;
    private String password;
    @Column(length = 4096)
    private byte[] image;

    public CustomerEntity() {
    }

    public CustomerEntity(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.password= customer.getPassword();
        this.image = customer.getImage();
    }

    public Customer toCustomer(){
        byte[] image=decompressBytes(this.image);
        return new Customer(this.id, this.name, this.email,this.password, image);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }

}
