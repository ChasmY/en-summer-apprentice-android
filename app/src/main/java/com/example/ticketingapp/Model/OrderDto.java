package com.example.ticketingapp.Model;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

public class OrderDto {
    private String clientName;
    private String eventName;
    private int numberOfTickets;
    private double totalPrice;
    private String orderedAt;
    private String ticketCategory;


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

    public String getTicketCategory() {
        return ticketCategory;
    }

    public void setTicketCategory(String ticketCategory) {
        this.ticketCategory = ticketCategory;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String event) {
        this.eventName = event;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public OrderDto(){}

    public OrderDto(String eventName, int numberOfTickets, int totalPrice, String orderedAt, String ticketCategory) {
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
                ", orderEventName='" + eventName + '\'' +
                ", nrTickets=" + numberOfTickets +
                ", price=" + totalPrice +
                ", ordered at=" + orderedAt +
                '}';
    }
}
