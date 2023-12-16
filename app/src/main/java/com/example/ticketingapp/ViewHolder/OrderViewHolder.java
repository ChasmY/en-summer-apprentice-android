package com.example.ticketingapp.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketingapp.R;

public class OrderViewHolder extends  RecyclerView.ViewHolder {
    public TextView clientName, eventName, ticketType, numberOfTickets, totalPrice, orderedAt;

    public Button deleteButton, modifyButton;

    public OrderViewHolder(@NonNull View itemView){
        super(itemView);
        clientName = itemView.findViewById(R.id.textView3);
        eventName = itemView.findViewById(R.id.textView15);
        ticketType = itemView.findViewById(R.id.textView6);
        numberOfTickets = itemView.findViewById(R.id.textView7);
        totalPrice = itemView.findViewById(R.id.textView8);
        orderedAt = itemView.findViewById(R.id.textView9);

        deleteButton = itemView.findViewById(R.id.deleteButton);
        modifyButton = itemView.findViewById(R.id.editButton);
    }
}
