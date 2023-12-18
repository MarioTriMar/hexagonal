package org.tfg.domain.model;



import java.io.Serializable;
import java.util.UUID;


public class Customer implements Serializable {


    private String id;
    private String name;
    private String email;
    private String password;
    private byte[] image;

    public Customer() {
        this.id= UUID.randomUUID().toString();
    }

    public Customer(String id, String name, String email, String password, byte[] image){
        this.id=id;
        this.name=name;
        this.email=email;
        this.password=password;
        this.image=image;
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
}
