package com.example.ticketingapp.Model;

import java.time.LocalDateTime;

public class OrderDto {
    private int orderId;
    private int numberOfTickets;
    private double totalPrice;
    private String orderedAt;
    private String ticketType;

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

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public OrderDto(int orderId, int numberOfTickets, int totalPrice, String orderedAt, String ticketType) {
        this.orderId = orderId;
        this.numberOfTickets = numberOfTickets;
        this.totalPrice = totalPrice;
        this.orderedAt = orderedAt;
        this.ticketType = ticketType;
    }
}
