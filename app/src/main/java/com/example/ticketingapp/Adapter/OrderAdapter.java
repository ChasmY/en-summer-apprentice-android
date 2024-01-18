package com.example.ticketingapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketingapp.Model.Dto.OrderDto;
import com.example.ticketingapp.R;
import com.example.ticketingapp.ViewHolder.OrderViewHolder;
import com.example.ticketingapp.popUp.DeleteOrder;
import com.example.ticketingapp.popUp.ModifyOrderPopUp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder>{

    Context context;

    List<OrderDto> orders;

    Integer orderId = 0;

    public OrderAdapter(Context context, List<OrderDto> orders) {
        this.context = context;
        this.orders = orders;
    }
    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, @SuppressLint("RecyclerView") int position) {
        OrderDto order = orders.get(position);
        Log.d("client", "Order Id " + order.getOrderId());
        Log.d("client", "Client Id " + order.getClient().getClientId());

        Log.d("client", "Client Name " + order.getClient().getClientName());
        Log.d("client", "Client Email " + order.getClient().getClientEmail());

        Log.d("breakpoint", "breakpoint");


        holder.clientName.setText(order.getClient().getClientName());
        holder.eventName.setText(String.valueOf(order.getEventName()));
        holder.ticketType.setText(order.getTicketCategory().getDescription());
        holder.numberOfTickets.setText(String.valueOf(order.getNumberOfTickets()));
        holder.totalPrice.setText(String.valueOf(order.getTotalPrice()));
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try{
            Date orderDate = dateFormat.parse(order.getOrderedAt());

            SimpleDateFormat displayFormat = new SimpleDateFormat("dd/MM/yyy");
            String formattedOrderDate = displayFormat.format(orderDate);
            holder.orderedAt.setText(formattedOrderDate);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        holder.modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderId = orders.get(position).getOrderId();
                Log.d("orderId", "order id: " + orderId + " " + position);
                popUpModify();
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderId = orders.get(position).getOrderId();
                popUpDelete();
            }
        });

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void popUpDelete(){
        DeleteOrder deleteOrder = new DeleteOrder(this, orderId , orders);
        deleteOrder.show(((AppCompatActivity)context).getSupportFragmentManager(), "Dialog");
    }

    public void popUpModify(){
        ModifyOrderPopUp modifyOrderPopUp = new ModifyOrderPopUp(this, orderId, orders);
        modifyOrderPopUp.show(((AppCompatActivity)context).getSupportFragmentManager(), "Dialog");    }
}