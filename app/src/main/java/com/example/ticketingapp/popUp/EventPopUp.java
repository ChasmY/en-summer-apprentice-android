package com.example.ticketingapp.popUp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ticketingapp.Adapter.EventAdapter;
import com.example.ticketingapp.Model.Dto.EventDto;
import com.example.ticketingapp.Model.Dto.OrderPostDto;
import com.example.ticketingapp.Model.TicketCategory;
import com.example.ticketingapp.R;
import com.example.ticketingapp.Service.ApiService;
import com.example.ticketingapp.Service.RetrofitService;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventPopUp extends DialogFragment {

    private ApiService apiService = RetrofitService.getEventApi().create(ApiService.class);;
    private EventAdapter eventAdapter;
    private Spinner dropdownSpinner;
    private String ticketDescription;

    private List<EventDto> eventList;

    private ArrayList<TicketCategory> ticketCategoryList = new ArrayList<>();

    private int ticketCategoryId;
    private int eventId;
    private int customerId;

    public EventPopUp(EventAdapter eventAdapter, int eventId, List<EventDto> eventList, int customerId){
        this.eventAdapter = eventAdapter;
        this.eventId = eventId;
        this.eventList = eventList;
        this.customerId = customerId;
    }

    public void getTickets(){
        Call<List<TicketCategory>> callTickets = apiService.getAllTickets();
        callTickets.enqueue(new Callback<List<TicketCategory>>() {
            @Override
            public void onResponse(Call<List<TicketCategory>> call, Response<List<TicketCategory>> response) {
                if(response.isSuccessful()){
                    Log.d("GetTickets", "Status code: " + response.code());
                    if(response.body() != null) {
                        ticketCategoryList.addAll(response.body());
                    }
                }
                else{
                    Log.d("call", "Status code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<List<TicketCategory>> call, Throwable t) {
                Log.d("GetTickets", "Patch failed " + t.toString());
            }
        });
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.event_dialog_box, null);
        getTickets();

        dropdownSpinner = view.findViewById(R.id.dialogDropDownSpinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.spinnerItems, android.R.layout.simple_spinner_dropdown_item);
        dropdownSpinner.setAdapter(spinnerAdapter);
        dropdownSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ticketDescription = dropdownSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        EditText nrTick = view.findViewById(R.id.dialogNumberInput);
        builder.setView(view)
                .setTitle("Place order")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Place order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int nrTickets = Integer.parseInt(nrTick.getText().toString());

                        TicketCategory ticket = getTicket(ticketDescription, eventId);
                        Log.d("Ticket obtinut", "Ticket" + ticket.toString());
                        ticketCategoryId = ticket.getTicketCategoryId();
                        OrderPostDto orderPostDto = new OrderPostDto(customerId, ticketCategoryId, nrTickets);
                        Log.d("Order Post", "order is: " + orderPostDto.toString() + eventId    );
                        placeOrder(orderPostDto);
                    }
                });
        return builder.create();
    }

    public void placeOrder(OrderPostDto orderPostDto){
        apiService = RetrofitService.getEventApi().create(ApiService.class);
        Log.d("api call", "" + apiService.toString());

        Call<Void> call = apiService.orderPost(orderPostDto);

        call.enqueue(new Callback<Void>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("EventCall", "Status code: " + response.code());
                Log.d("EventCcall", "Post sucesfull");
                eventAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("call", "Failed: " + t.toString());
            }

        });
    }

    public TicketCategory getTicket(String ticketDescription, int eventId){

        for(TicketCategory ticket: ticketCategoryList){
            if(ticket.getEvent().getEventId() == eventId && ticket.getDescription().equals(ticketDescription)) {
                Log.d("getTicket", "Ticket: " + ticket.toString() + " " + eventId + " "+ ticket.getTicketCategoryId());
                return ticket;
            }
        }
        return null;
    }

}
