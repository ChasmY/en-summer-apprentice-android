package com.example.ticketingapp.popUp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ticketingapp.Adapter.EventAdapter;
import com.example.ticketingapp.Adapter.OrderAdapter;
import com.example.ticketingapp.Model.Dto.OrderDto;
import com.example.ticketingapp.Model.Dto.OrderPostDto;
import com.example.ticketingapp.Model.Event;
import com.example.ticketingapp.R;
import com.example.ticketingapp.Service.ApiService;
import com.example.ticketingapp.Service.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DeleteOrder extends DialogFragment {
    ApiService apiService;
    Integer orderId;
    OrderAdapter orderAdapter;

    List<OrderDto> orderList;

    public DeleteOrder(OrderAdapter orderAdapter, Integer orderId, List<OrderDto> orderList){
        this.orderAdapter = orderAdapter;
        this.orderId = orderId;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.delete_dialog_box, null);

        builder.setView(view)
                .setMessage("Are you sure?")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteOrder();
                    }
                });
        return builder.create();
    }
    public void deleteOrder(){
        apiService = RetrofitService.getEventApi().create(ApiService.class);
        Call<Void> call = apiService.deleteOrder(orderId);

        call.enqueue(new Callback<Void>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("call", "Status code: " + response.code());
                for(int i = 0; i<orderList.size(); i++){
                    if(orderList.get(i).getOrderId() == orderId){
                        orderList.remove(i);
                        break;
                    }
                }
                Log.d("call", "Delete succesfull");
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("call", "Failed: " + t.toString());
            }
        });
    }
}
