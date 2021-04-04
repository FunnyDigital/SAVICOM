package com.bitvilltecnologies.savicom.MODELS;


public class User_Model {
    String name, email, phone, address,  nin;

    public User_Model() {
    }

    public User_Model(String name, String email, String phone, String address, String nin) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.nin = nin;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }
}

