package com.example.ticketingapp.Adapter;

import android.annotation.SuppressLint;
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

import com.example.ticketingapp.Model.Dto.EventDto;
import com.example.ticketingapp.R;
import com.example.ticketingapp.ViewHolder.EventViewHolder;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {
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
    public void onBindViewHolder(@NonNull EventViewHolder holder, @SuppressLint("ReciclerView") int position) {
        EventDto event = eventList.get(position);
        Log.d("EventAdapterAdded", "Event name: " + event.getName());

        // Set the data to the views in the recycler_row_event.xml layout
        holder.eventNameTextView.setText(event.getName());
        holder.eventDescriptionTextView.setText(event.getDescription());
        holder.startDateTextView.setText(event.getStartDate());
        holder.endDateTextView.setText(event.getEndDate());

        int imageResourceId = event.getEventImage(context, event.getName());
        holder.eventImage.setImageResource(imageResourceId);

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


}
