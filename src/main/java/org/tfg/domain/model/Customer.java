package org.tfg.domain.model;



import java.io.Serializable;
import java.util.UUID;


public class Customer implements Serializable {


    private String id;
    private String name;
    private String email;

    public Customer() {
        this.id= UUID.randomUUID().toString();
    }

    public Customer(String id, String name, String email){
        this.id=id;
        this.name=name;
        this.email=email;
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
}
