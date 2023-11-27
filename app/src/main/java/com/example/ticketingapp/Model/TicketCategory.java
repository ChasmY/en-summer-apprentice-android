package com.example.ticketingapp.Model;

public class TicketCategory {
    private Integer ticketCategoryId;
    private String description;
    private int price;

    public Integer getTicketCategoryId() {
        return ticketCategoryId;
    }

    public void setTicketCategoryId(Integer ticketCategoryId) {
        this.ticketCategoryId = ticketCategoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public TicketCategory(Integer ticketCategoryId, String description, int price) {
        this.ticketCategoryId = ticketCategoryId;
        this.description = description;
        this.price = price;
    }
}
