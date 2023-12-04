package com.example.ticketingapp.Model;

public class Customer {

    private int customerId;
    private String name;
    private String email;

    public int getClientId(){
        return customerId;
    }

    public String getClientName(){
        return name;
    }
    public String getClientEmail(){
        return email;
    }

    public Customer(int customerId, String name, String clientEmail){
        this.customerId = customerId;
        this.name = name;
        this.email = clientEmail;
    }


}
