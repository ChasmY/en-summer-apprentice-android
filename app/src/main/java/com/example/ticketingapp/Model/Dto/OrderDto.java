package com.example.ticketingapp.Model.Dto;

import androidx.annotation.NonNull;

import com.example.ticketingapp.Model.Customer;
import com.example.ticketingapp.Model.TicketCategory;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OrderDto implements Serializable {
    private Customer customer;
    private String eventName;
    private int numberOfTickets;
    private int totalPrice;
    private String orderedAt;
    private TicketCategory ticketCategory;
    private int orderId;
    private int eventId;


    public OrderDto(Customer customer, String eventName, int numberOfTickets, int totalPrice, String orderedAt, TicketCategory ticketCategory, int orderId, int eventId) {
        this.customer = customer;
        this.eventName = eventName;
        this.numberOfTickets = numberOfTickets;
        this.totalPrice = totalPrice;
        this.orderedAt = orderedAt;
        this.ticketCategory = ticketCategory;
        this.orderId = orderId;
        this.eventId = eventId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
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

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
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
