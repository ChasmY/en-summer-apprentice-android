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

import com.example.ticketingapp.Model.Event;
import com.example.ticketingapp.Model.EventDto;
import com.example.ticketingapp.R;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    Context context;
    private List<EventDto> eventList;

    public EventAdapter(Context context, List<EventDto> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_row, parent, false);
        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventDto event = eventList.get(position);
        Log.d("EventAdapterAdded", "Event name: " + event.getName());

        // Set the data to the views in the recycler_row_event.xml layout
        holder.eventNameTextView.setText(event.getName());
        holder.eventDescriptionTextView.setText(event.getDescription());
        holder.startDateTextView.setText(event.getStartDate());
        holder.endDateTextView.setText(event.getEndDate());
        holder.buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBuyDialog(event);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    private void showBuyDialog(EventDto event) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_box, null);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();

        // Get references to views inside the dialog
        Spinner dropdownSpinner = dialogView.findViewById(R.id.dialogDropDownSpinner);
        EditText quantityInput = dialogView.findViewById(R.id.dialogNumberInput);
        Button buyButton = dialogView.findViewById(R.id.buyButton);




        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quantity = quantityInput.getText().toString();
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        public TextView eventNameTextView, eventDescriptionTextView, startDateTextView, endDateTextView, venueTextView;
        public Button buyButton;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventNameTextView = itemView.findViewById(R.id.textView);
            eventDescriptionTextView = itemView.findViewById(R.id.textView4);
            startDateTextView = itemView.findViewById(R.id.textView20);
            endDateTextView = itemView.findViewById(R.id.textView23);
            buyButton = itemView.findViewById(R.id.buyButton);
        }
    }
}
