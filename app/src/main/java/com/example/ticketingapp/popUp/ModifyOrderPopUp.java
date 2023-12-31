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
import com.example.ticketingapp.Adapter.OrderAdapter;
import com.example.ticketingapp.Model.Dto.EventDto;
import com.example.ticketingapp.Model.Dto.OrderDto;
import com.example.ticketingapp.Model.Dto.OrderPatchDto;
import com.example.ticketingapp.Model.Dto.OrderPostDto;
import com.example.ticketingapp.Model.Event;
import com.example.ticketingapp.Model.TicketCategory;
import com.example.ticketingapp.R;
import com.example.ticketingapp.Service.ApiService;
import com.example.ticketingapp.Service.RetrofitService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyOrderPopUp extends DialogFragment {

    List<OrderDto> orderList;
    ApiService apiService = RetrofitService.getEventApi().create(ApiService.class);

    OrderAdapter orderAdapter;
    Spinner dropdownSpinner;
    String ticketDescription;
    OrderPatchDto orderPatchDto;
    int orderId;
    int ticketId;

    TicketCategory ticketCategory;

    ArrayList<TicketCategory> ticketCategoryList = new ArrayList<>();

    public ModifyOrderPopUp(OrderAdapter orderAdapter, Integer orderId, List<OrderDto> orderList){
        this.orderAdapter = orderAdapter;
        this.orderId = orderId;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.order_modify_dialog_box, null);
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
                .setTitle("Modify  order")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Modify order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int numberOfTickets = Integer.parseInt(nrTick.getText().toString());
                        ticketCategory = findTicket(orderList, orderId, ticketDescription);
                        Log.d("b4Patch", "ticketDescription " + ticketDescription);
                        ticketId = ticketCategory.getTicketCategoryId();
                        orderPatchDto = new OrderPatchDto(orderId, ticketId, numberOfTickets);
                        Log.d("Order status", "" + orderPatchDto);
                        modifyOrder(orderPatchDto);
                    }
                });
        return builder.create();
    }

    public void getTickets(){
        Call<List<TicketCategory>> callTickets = apiService.getAllTickets();

        callTickets.enqueue(new Callback<List<TicketCategory>>() {
            @Override
            public void onResponse(Call<List<TicketCategory>> call, Response<List<TicketCategory>> response) {
                if(response.isSuccessful()){
                    Log.d("GetTickets", "Status code: " + response.code());
                    if(response.body() != null) {

                        Log.d("breakpoint", "breakpoint");
                        Log.d("TicketCategory", "ticketCategory" + response.body());
                        Log.d("breakpoint", "breakpoint");
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
    public void modifyOrder(OrderPatchDto orderPatchDto){
        apiService = RetrofitService.getEventApi().create(ApiService.class);
        Call<Void> call = apiService.orderPatch(orderPatchDto);
        call.enqueue(new Callback<Void>() {

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    Log.d("ModifyOrder", "Status code: " + response.code());
                    Log.d("ModifyOrder", "Patch succesfull");
                    updateOrder(orderPatchDto);
                    Log.d("BiletCumparat", "Bilet " + orderPatchDto.getticketCategoryId()
                            + " " + ticketCategory.getTicketCategoryId());
                    Log.d("breakpoint", "breakpoint");
                    orderAdapter.notifyDataSetChanged();
                }
                else {
                    Log.d("ModifyOrder", "Status code: " + response.code());
                    Log.d("ModifyOrder", "Patch failed");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("call", "Patch failed " + t.toString());
            }
        });
    }

        public TicketCategory findTicket(List<OrderDto> orderList, int orderId, String description){
        for(OrderDto o: orderList) {
            if(o.getOrderId() == orderId) {
                for(int i=0; i<= ticketCategoryList.size(); i++) {
                    if (o.getEventId() == ticketCategoryList.get(i).getEvent().getEventId() &&
                            ticketCategoryList.get(i).getDescription().equals(description)){
                        Log.d("ticket in while", "Ticket" + ticketCategoryList.get(i).toString() + i + " "+ description);
                            return ticketCategoryList.get(i);
                    }
                }
            }
        }
        return null;
        }


        public void updateOrder(OrderPatchDto orderPatchDto){
            for(OrderDto o: orderList){
                if(o.getOrderId() == orderPatchDto.getOrderId()){
                    Log.d("Bilet updated", "Ticket" + ticketCategory +
                            ticketCategory.getTicketCategoryId() + " " + o.getNumberOfTickets());
                    o.setTicketCategory(ticketCategory);
                    o.setTotalPrice(ticketCategory.getPrice() * orderPatchDto.getnumberOfTickets());
                    o.setNumberOfTickets(orderPatchDto.getnumberOfTickets());
                }
            }
        }
}
