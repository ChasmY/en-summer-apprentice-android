package com.example.ticketingapp.Adapter;

import static android.content.Intent.getIntent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketingapp.Model.Dto.EventDto;
import com.example.ticketingapp.R;
import com.example.ticketingapp.ViewHolder.EventViewHolder;
import com.example.ticketingapp.popUp.EventPopUp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {
    private Context context;
    private List<EventDto> eventList;

    private Integer eventId;
    private int customerId;

    public EventAdapter(Context context, List<EventDto> eventList, int customerId) {
        this.context = context;
        this.eventList = eventList;
        this.customerId = customerId;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_row, parent, false);
        return new EventViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, @SuppressLint({"ReciclerView", "RecyclerView"}) int position) {
        EventDto event = eventList.get(position);
        Log.d("EventAdapterAdded", "Event name: " + event.getName());

        // Set the data to the views in the recycler_row_event.xml layout
        holder.eventNameTextView.setText(event.getName());
        holder.eventDescriptionTextView.setText(event.getDescription());

        int imageResourceId = event.getEventImage(context, event.getName());
        holder.eventImage.setImageResource(imageResourceId);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try{
            Date startDate = dateFormat.parse(event.getStartDate());
            Date endDate = dateFormat.parse(event.getEndDate());

            SimpleDateFormat displayFormat = new SimpleDateFormat("dd/MM/yyy");
            String formattedStartDate = displayFormat.format(startDate);
            String formattedEndDate = displayFormat.format(endDate);
            holder.startDateTextView.setText(formattedStartDate);
            holder.endDateTextView.setText(formattedEndDate);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        holder.buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "" + eventList.get(position).getEventId(), Toast.LENGTH_SHORT).show();
                eventId = eventList.get(position).getEventId();
                popUp();
            }
        });

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }


    public void popUp(){
            EventPopUp eventPopUp = new EventPopUp(this, eventId, eventList, customerId);
            eventPopUp.show(((AppCompatActivity)context).getSupportFragmentManager(), "Dialog");
        }
    }

