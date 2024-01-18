package com.example.ticketingapp.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketingapp.Adapter.OrderAdapter;
import com.example.ticketingapp.Model.Dto.OrderDto;
import com.example.ticketingapp.R;
import com.example.ticketingapp.Service.ApiService;
import com.example.ticketingapp.Service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {

    private ArrayList<OrderDto> orders = new ArrayList<>();
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private ApiService apiService;
    private int customerId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        this.setTitle("Orders");
        getOrders();

    }

    public void getOrders(){

        apiService = RetrofitService.getEventApi().create(ApiService.class);
        customerId = getIntent().getIntExtra("customerId", -1);

        Call<List<OrderDto>> call = apiService.getAllOrders();
        call.enqueue(new Callback<List<OrderDto>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<OrderDto>> call, Response<List<OrderDto>> response) {
                Log.d("call", "Status code: " + response.code());

                orders.clear();
                if(response.body() != null){
                    orders.addAll(response.body());
                    orders.removeIf(order -> order.getClient().getClientId() != customerId);
                }
                setRecyclerView();
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<OrderDto>> call, Throwable t) {
                Log.d("Orders call", "Failed: " + t.toString());
            }
        });
    }
    public void setRecyclerView(){
        recyclerView = findViewById(R.id.order_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        orderAdapter = new OrderAdapter(this, orders);
        recyclerView.setAdapter(orderAdapter);
    }

}