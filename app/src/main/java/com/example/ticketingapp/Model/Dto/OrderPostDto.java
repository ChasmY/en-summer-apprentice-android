package com.example.ticketingapp.Model.Dto;

public class OrderPostDto {
    int CustomerId;

    int ticketCategoryId;
    int numberOfTickets;

    public OrderPostDto(int customerId,  int ticketCategoryId, int numberOfTickets) {
        this.CustomerId = customerId;
        this.ticketCategoryId = ticketCategoryId;
        this.numberOfTickets = numberOfTickets;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public int getTicketCategoryId() {
        return ticketCategoryId;
    }

    public void setTicketCategoryId(int ticketCategoryId) {
        this.ticketCategoryId = ticketCategoryId;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    @Override
    public String toString() {
        return "OrderPostDTO{" +
                ", customerId=" + CustomerId+
                ", ticketCategoryId='" + ticketCategoryId + '\'' +
                ", numberOfTickets=" + numberOfTickets +
                '}';
    }
}
