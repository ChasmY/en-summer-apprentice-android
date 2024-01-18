package com.example.ticketingapp.Model.Dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VenueDto implements Serializable {

    private Integer venueId;
    private String location;
    private String type;
    private Integer capacity;
    private BigDecimal latitudine;
    private BigDecimal longitudine;

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVenueType() {
        return type;
    }

    public void setVenueType(String venueType) {
        this.type = venueType;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
    public void setLatitude(BigDecimal latitude){ this.latitudine = latitude;}
    public BigDecimal getLatitude() { return this.latitudine; }
    public void setLongitude(BigDecimal longitude){ this.longitudine = longitude; }
    public BigDecimal getLongitude(){ return this.longitudine;}

    @Override
    public String toString(){
        return "Venue{" +
                ", venue='" + venueId + '\'' +
                ", Location='" + location + '\'' +
                ", lat=" + latitudine +
                ", long=" + longitudine +
                ", venue Type=" + type +
                '}';
    }

}
