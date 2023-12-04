package com.example.ticketingapp.Model.Dto;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.ticketingapp.Model.TicketCategory;

import java.io.Serializable;
import java.util.List;

public class EventDto implements Serializable {
    private Integer eventId;
    private VenueDto venue;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private List<TicketCategory> ticketCategoryList;
    private Integer eventImage;


    public VenueDto getVenue() {
        return venue;
    }

    public void setVenue(VenueDto venue) {
        this.venue = venue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    @SuppressLint("DiscouragedApi")
    public Integer getEventImage(Context context, String eventName) {
        // Assuming that the image resource IDs in res/drawable have names corresponding to event names
        String imageName = name.toLowerCase().replace(" ", "_"); // Replace spaces with underscores
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }

    public void setEventImage(Integer eventImage) {
        this.eventImage = eventImage;
    }

    public EventDto(Integer eventId, VenueDto venue, String name, String description, String startDate, String endDate, List<TicketCategory> ticketCategoryList) {
        this.eventId = eventId;
        this.venue = venue;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ticketCategoryList = ticketCategoryList;
    }
}
