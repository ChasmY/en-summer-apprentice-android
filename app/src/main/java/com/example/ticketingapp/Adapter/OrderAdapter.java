package com.example.ticketingapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketingapp.Model.EventDto;
import com.example.ticketingapp.Model.OrderDto;
import com.example.ticketingapp.R;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{

    Context context;

    List<OrderDto> orders;

    public OrderAdapter(Context context, List<OrderDto> orders) {
        this.context = context;
        this.orders = orders;
    }
    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_recycler, parent, false);
        return new OrderAdapter.OrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {
        OrderDto order = orders.get(position);

        // Set the data to the views in the order_recycler.xml layout
        holder.orderId.setText(String.valueOf(order.getOrderId()));
        holder.ticketType.setText(order.getTicketType());
        holder.numberOfTickets.setText(String.valueOf(order.getNumberOfTickets()));
        holder.totalPrice.setText(String.valueOf(order.getTotalPrice()));
        holder.orderedAt.setText(order.getOrderedAt());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    delete the order
            }
        });
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                edit the order
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView orderId, numberOfTickets, totalPrice, orderedAt, ticketType;
        public Button deleteButton, editButton;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.textView3);
            numberOfTickets = itemView.findViewById(R.id.textView7);
            totalPrice = itemView.findViewById(R.id.textView8);
            orderedAt = itemView.findViewById(R.id.textView9);
            ticketType = itemView.findViewById(R.id.textView6);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            editButton = itemView.findViewById(R.id.editButton);
        }
    }
}