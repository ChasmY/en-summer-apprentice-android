package com.example.ticketingapp.Service;

import com.example.ticketingapp.Adapter.DateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static Retrofit retrofit;

    public static EventApi getEventApi() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new DateTimeAdapter())
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://172.16.98.65:8080")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit.create(EventApi.class);
    }
}
