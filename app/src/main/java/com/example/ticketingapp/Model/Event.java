package com.example.ticketingapp.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Event {

    private String eventName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private String image = "@drawable/banner";

    public String getEventName() {
        return eventName;
    }
    public LocalDateTime getStartDate() {
        return startDate;
    }
    public LocalDateTime getEndDate() {
        return endDate;
    }
    public String getDescription() {
        return description;
    }


    public String getImage() {
        return image;
    }

    public Event(String eventName, String description){

        this.eventName = eventName;
        this.description =description;
        this.image = "@drawable/banner";
    }

}
