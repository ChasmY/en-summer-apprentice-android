package com.example.ticketingapp.Model;

public class Customer {

    private int customerId;
    private String name;
    private String email;

    private String password;

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getClientId(){
        return customerId;
    }

    public String getClientName(){
        return name;
    }
    public String getClientEmail(){
        return email;
    }

    public String getPassword() { return password;}


    public Customer(int customerId, String name, String clientEmail, String password){
        this.customerId = customerId;
        this.name = name;
        this.email = clientEmail;
        this.password = password;
    }


}
