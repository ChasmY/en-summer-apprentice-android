package com.example.ticketingapp.Model.Dto;

public class OrderPatchDto {

    int orderId;
    int ticketCategoryId;
    int numberOfTickets;

    public OrderPatchDto(int orderId, int ticketCategoryId, int numberOfTickets) {
        this.orderId = orderId;
        this.ticketCategoryId = ticketCategoryId;
        this.numberOfTickets = numberOfTickets;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getticketCategoryId() {
        return ticketCategoryId;
    }

    public void setticketCategoryId(int ticketCategoryId) {
        this.ticketCategoryId = ticketCategoryId;
    }

    public int getnumberOfTickets() {
        return numberOfTickets;
    }

    public void setnumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    @Override
    public String toString() {
        return "OrderPatchDTO{" +
                "orderID=" + orderId +
                ", ticketCategoryID=" + ticketCategoryId +
                ", numberOfTickets=" + numberOfTickets +
                '}';
    }
}
