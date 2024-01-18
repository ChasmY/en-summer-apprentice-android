package com.example.ticketingapp.Model.Dto;

import androidx.annotation.NonNull;

public class CustomerPost {

    private String name;
    private String email;
    private String password;

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

    public CustomerPost(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @NonNull
    @Override
    public String toString(){
        return "Customer{" +
                " name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password=" + password +
                '}';
    }
}
