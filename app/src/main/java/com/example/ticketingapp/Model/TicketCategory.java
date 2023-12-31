package com.example.ticketingapp.Model;

import androidx.annotation.NonNull;

import com.example.ticketingapp.Model.Dto.EventDto;

public class TicketCategory {
    private Integer ticketCategoryId;
    private String description;
    private int price;

    private EventDto event;

    public EventDto getEvent() {
        return event;
    }

    public void setEvent(EventDto event) {
        this.event = event;
    }

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

    public TicketCategory(Integer ticketCategoryId, int price, String description) {
        this.ticketCategoryId = ticketCategoryId;
        this.price = price;
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return "TicketCategory{" +
                ", ticketId='" + ticketCategoryId + '\'' +
                ", price='" + price + '\'' +
                ", description=" + description +

                '}';
    }
}
