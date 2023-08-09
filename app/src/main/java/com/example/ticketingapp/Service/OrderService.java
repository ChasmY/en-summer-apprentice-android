package com.example.ticketingapp.Service;

import com.example.ticketingapp.Model.OrderDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderService {
    @GET("Order/GetAllOrders")
    Call<List<OrderDto>> getAllOrders();

    @DELETE("Order/Delete")
    Call<List<OrderDto>> deleteOrder(@Path("orderId") int orderId);

//    @PATCH("Order/OrderPatch")
//    Call<Void> orderPatch(@Body OrderPatchDto orderDto);
//
//    @POST("Order/OrderPost")
//    Call<Void> orderPost(@Body OrderPostDto orderPostDto);
}
