package com.example.ticketingapp.Model.Dto;

import androidx.annotation.NonNull;

import com.example.ticketingapp.Model.Customer;
import com.example.ticketingapp.Model.TicketCategory;

import java.time.LocalDateTime;

public class OrderDto {
    private Customer customer;
    private String eventName;
    private int numberOfTickets;
    private double totalPrice;
    private String orderedAt;
    private TicketCategory ticketCategory;


    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(String orderedAt) {
        this.orderedAt = orderedAt;
    }

    public TicketCategory getTicketCategory() {
        return ticketCategory;
    }

    public void setTicketCategory(TicketCategory ticketCategory) {
        this.ticketCategory = ticketCategory;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String event) {
        this.eventName = event;
    }

    public Customer getClient() {
        return customer;
    }

    public void setClientName(Customer clientName) {
        this.customer = clientName;
    }

    public OrderDto(){}

    public OrderDto(Customer customer, String eventName, int numberOfTickets, int totalPrice, String orderedAt, TicketCategory ticketCategory) {
        this.customer = customer;
        this.eventName = eventName;
        this.numberOfTickets = numberOfTickets;
        this.totalPrice = totalPrice;
        this.orderedAt = orderedAt;
        this.ticketCategory = ticketCategory;
    }

    @NonNull
    @Override
    public String toString() {
        return "Order{" +
                ", customerName='" + customer + '\'' +
                ", orderEventName='" + eventName + '\'' +
                ", nrTickets=" + numberOfTickets +
                ", price=" + totalPrice +
                ", ordered at=" + orderedAt +
                '}';
    }
}
