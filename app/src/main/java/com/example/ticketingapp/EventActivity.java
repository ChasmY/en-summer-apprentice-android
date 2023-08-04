package com.example.ticketingapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketingapp.Adapter.EventAdapter;
import com.example.ticketingapp.Model.Event;
import com.example.ticketingapp.Model.EventDto;
import com.example.ticketingapp.Service.EventApi;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        setEvents();

        recyclerView = findViewById(R.id.myRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        eventAdapter = new EventAdapter(this, events);
        recyclerView.setAdapter(eventAdapter);

    }

    public void setEvents(){
        events.add(new EventDto("Festival 1", "Descriere Festival",
                "21/02/2023", "21-03-2023"));
        events.add(new EventDto("Festival 2", "Descriere Festival",
                "21/02/2023", "21-03-2023"));
        events.add(new EventDto("Festival 3", "Descriere Festival",
                "21/02/2023", "21-03-2023"));
        events.add(new EventDto("Festival 4", "Descriere Festival",
                "21/02/2023", "21-03-2023"));
        events.add(new EventDto("Festival 5", "Descriere Festival",
                "21/02/2023", "21-03-2023"));
        events.add(new EventDto("Festival 6", "Descriere Festival",
                "21/02/2023", "21-03-2023"));
    }

}


