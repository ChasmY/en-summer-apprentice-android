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
import com.example.ticketingapp.Model.Dto.OrderDto;
import com.example.ticketingapp.Model.Dto.OrderPostDto;
import com.example.ticketingapp.Model.TicketCategory;
import com.example.ticketingapp.R;
import com.example.ticketingapp.Service.ApiService;
import com.example.ticketingapp.Service.RetrofitService;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventPopUp extends DialogFragment {

    ApiService apiService;
    EventAdapter eventAdapter;
    Spinner dropdownSpinner;
    String ticketDescription;

    List<EventDto> eventList;

    int ticketCategoryId;
    int eventId;

    public EventPopUp(EventAdapter eventAdapter, int eventId, List<EventDto> eventList){
        this.eventAdapter = eventAdapter;
        this.eventId = eventId;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.event_dialog_box, null);

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

                        OrderPostDto orderPostDto = new OrderPostDto(3, ticketCategoryId, nrTickets);
                        Log.d("Order Post", "order is: " + orderPostDto.toString() + eventId    );
                        getTicketId(ticketDescription, eventId);
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
                Log.d("call", "Status code: " + response.code());
                Log.d("call", "Post sucesfull");
                eventAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("call", "Failed: " + t.toString());
            }

        });
    }

    public int getTicketId(String ticketDescription, int eventId){
        for(int i = 0; i<eventAdapter.getItemCount(); i ++){
            if(eventList.get(i).getEventId().equals(eventId)){
                eventList.get(i).getTicketCategoryList();
                Log.d("Ticket List" , "tickets list" + eventList.get(i).getTicketCategoryList());
            }
        }
        return 0;
    }

}
