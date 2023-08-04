package com.example.ticketingapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketingapp.Adapter.EventAdapter;
import com.example.ticketingapp.Adapter.OrderAdapter;
import com.example.ticketingapp.Model.OrderDto;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    private ArrayList<OrderDto> orders = new ArrayList<>();
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setOrders();

        recyclerView = findViewById(R.id.order_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        orderAdapter = new OrderAdapter(this, orders);
        recyclerView.setAdapter(orderAdapter);
    }

    public void setOrders(){
        orders.add(new OrderDto(1, 2, 1000, "21/04/2023", "Standard"));
        orders.add(new OrderDto(2, 3, 2500, "21/04/2023", "Standard"));
        orders.add(new OrderDto(3, 7, 7700, "21/04/2023", "Standard"));
        orders.add(new OrderDto(4, 12, 25000, "21/04/2023", "Standard"));
        orders.add(new OrderDto(5, 1, 500, "21/04/2023", "Standard"));
        orders.add(new OrderDto(6, 1, 1000, "21/04/2023", "Standard"));
        orders.add(new OrderDto(7, 4, 1000, "21/04/2023", "Standard"));        orders.add(new OrderDto(1, 2, 1000, "21/04/2023", "Standard"));        orders.add(new OrderDto(1, 2, 1000, "21/04/2023", "Standard"));
        orders.add(new OrderDto(8, 2, 1000, "21/04/2023", "Standard"));


    }
}
