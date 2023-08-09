package com.example.ticketingapp.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Event {

    private String eventName;
    private String startDate;
    private String endDate;
    private String description;
    private String image = "@drawable/banner";

    public String getEventName() {
        return eventName;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public String getDescription() {
        return description;
    }


    public String getImage() {
        return image;
    }

}
