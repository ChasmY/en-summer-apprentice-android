package com.example.ticketingapp.Service;

import com.example.ticketingapp.Model.Dto.EventDto;
import com.example.ticketingapp.Model.Dto.OrderDto;
import com.example.ticketingapp.Model.Dto.OrderPatchDto;
import com.example.ticketingapp.Model.Dto.OrderPostDto;
import com.example.ticketingapp.Model.TicketCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("Event/GetAllEvents")
    Call<List<EventDto>> getAllEvents();

    @GET("TicketCategory/GetAllTickets")
    Call<List<TicketCategory>> getAllTickets();

    @GET("Order/GetAllOrders")
    Call<List<OrderDto>> getAllOrders();

    @DELETE("Order/Delete/{orderId}")
    Call<Void> deleteOrder(@Path("orderId") Integer orderId);

    @PATCH("Order/OrderPatch")
    Call<Void> orderPatch(@Body OrderPatchDto orderDto);

    @POST("Order/OrderPost")
    Call<Void> orderPost(@Body OrderPostDto orderPostDto);
}
