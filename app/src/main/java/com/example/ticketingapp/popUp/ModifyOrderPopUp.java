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
import com.example.ticketingapp.Model.Dto.OrderDto;
import com.example.ticketingapp.Model.Dto.OrderPatchDto;
import com.example.ticketingapp.Model.Dto.OrderPostDto;
import com.example.ticketingapp.Model.TicketCategory;
import com.example.ticketingapp.R;
import com.example.ticketingapp.Service.ApiService;
import com.example.ticketingapp.Service.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyOrderPopUp extends DialogFragment {

    List<OrderDto> orderList;
    ApiService apiService;
    OrderAdapter orderAdapter;
    Spinner dropdownSpinner;
    String ticketDescription;
    int orderId;

    int ticketCategoryId;

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

        dropdownSpinner = view.findViewById(R.id.dialogDropDownSpinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.spinnerItems, android.R.layout.simple_spinner_dropdown_item);
        dropdownSpinner.setAdapter(spinnerAdapter);
        dropdownSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ticketDescription = dropdownSpinner.getSelectedItem().toString();
                ticketCategoryId = findTicketID(orderList, ticketDescription);

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
                        OrderPatchDto orderPatchDto = new OrderPatchDto(orderId, ticketCategoryId, numberOfTickets);
                        Log.d("Order status", "" + orderPatchDto);
                        modifyOrder(orderPatchDto);
                    }
                });
        return builder.create();
    }

    public void modifyOrder(OrderPatchDto orderPatchDto){
        apiService = RetrofitService.getEventApi().create(ApiService.class);
        Call<Void> call = apiService.orderPatch(orderPatchDto);

        call.enqueue(new Callback<Void>() {

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("call", "Status code: " + response.code());
                Log.d("call", "Patch succesfull");
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("call", "Patch failed " + t.toString());
            }
        });
    }


        public int findTicketID(List<OrderDto> orders, String description){
        for(OrderDto o : orders)
            if(o.getTicketCategory().getDescription().equals(description))
                return o.getTicketCategory().getTicketCategoryId();
        return -1;
    }

}
