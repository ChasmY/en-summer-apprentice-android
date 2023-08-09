package com.example.ticketingapp.Service;

import com.example.ticketingapp.Model.EventDto;
import com.example.ticketingapp.Model.OrderDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EventService {
        @GET("Event/GetAllEvents")
        Call<List<EventDto>> getAllEvents();


}
