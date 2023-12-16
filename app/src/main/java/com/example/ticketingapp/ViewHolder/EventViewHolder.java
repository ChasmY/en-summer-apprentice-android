package com.example.ticketingapp.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketingapp.R;

public class EventViewHolder extends RecyclerView.ViewHolder {
    public TextView eventNameTextView, eventDescriptionTextView, startDateTextView, endDateTextView, venueTextView;
    public Button buyButton;
    public ImageView eventImage;

    public static CardView cardView;


    public EventViewHolder(@NonNull View itemView) {
        super(itemView);

        eventNameTextView = itemView.findViewById(R.id.textView);
        eventDescriptionTextView = itemView.findViewById(R.id.textView4);
        startDateTextView = itemView.findViewById(R.id.textView20);
        endDateTextView = itemView.findViewById(R.id.textView23);
        buyButton = itemView.findViewById(R.id.buyButton);
        eventImage = itemView.findViewById(R.id.imageView);
        cardView = itemView.findViewById(R.id.cardView);
    }
}