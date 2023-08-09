package com.example.ticketingapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketingapp.Adapter.EventAdapter;
import com.example.ticketingapp.Model.EventDto;
import com.example.ticketingapp.Service.EventService;
import com.example.ticketingapp.Service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventActivity extends AppCompatActivity {

    private ArrayList<EventDto> events = new ArrayList<>();
    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;
    private EventService eventService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Log.d("call", "Inainte de events:");
        this.setTitle("Events");
        getEvents();


    }

    public void getEvents(){
        eventService = RetrofitService.getEventApi().create(EventService.class);
        Call<List<EventDto>> call = eventService.getAllEvents();
        call.enqueue(new Callback<List<EventDto>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<EventDto>> call, Response<List<EventDto>> response) {
                Log.d("call", "Status code: " + response.code());
                events.clear();
                if(response.body() != null){
                    events.addAll(response.body());
                }
                setRecyclerView();
                eventAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<EventDto>> call, Throwable t) {
                Log.d("call", "Failed: " + t.toString());
            }
        });
    }
    public void setRecyclerView(){
        recyclerView = findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        eventAdapter = new EventAdapter(this, events);
        recyclerView.setAdapter(eventAdapter);
    }


}


