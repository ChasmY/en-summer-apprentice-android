package com.example.ticketingapp.Service;

import com.example.ticketingapp.Model.EventDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EventApi {
        @GET("api/Event/GetAllEvents")
        Call<List<EventDto>> getAllEvents();
}
