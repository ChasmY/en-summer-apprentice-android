package com.example.ticketingapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketingapp.Model.Dto.OrderDto;
import com.example.ticketingapp.R;
import com.example.ticketingapp.ViewHolder.OrderViewHolder;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder>{

    Context context;

    List<OrderDto> orders;

    public OrderAdapter(Context context, List<OrderDto> orders) {
        this.context = context;
        this.orders = orders;
    }
    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_recycler, parent, false);
        return new OrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderDto order = orders.get(position);
        Log.d("client", "Client Id " + order.getClient().getClientId());
        Log.d("client", "Client Name " + order.getClient().getClientName());
        Log.d("client", "Client Email " + order.getClient().getClientEmail());

        Log.d("breakpoint", "breakpoint");

        holder.clientName.setText(order.getClient().getClientName());
        holder.eventName.setText(String.valueOf(order.getEventName()));
        holder.ticketType.setText(order.getTicketCategory().getDescription());
        holder.numberOfTickets.setText(String.valueOf(order.getNumberOfTickets()));
        holder.totalPrice.setText(String.valueOf(order.getTotalPrice()));
        holder.orderedAt.setText(order.getOrderedAt());

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

}