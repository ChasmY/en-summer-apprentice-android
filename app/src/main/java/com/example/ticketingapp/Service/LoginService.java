package com.example.ticketingapp.Service;

import android.util.Log;

import com.example.ticketingapp.Model.Customer;
import com.example.ticketingapp.Model.Dto.CustomerPost;
import com.example.ticketingapp.Service.ApiService;
import com.example.ticketingapp.Service.PasswordEncoderService;
import com.example.ticketingapp.Service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginService {

    List<Customer> customerList;
    ApiService apiService;

    public LoginService() {
        this.customerList = new ArrayList<>();
        this.apiService = RetrofitService.getEventApi().create(ApiService.class);
    }

    public interface LoginCallback {
        void onSuccess(Customer customer);
        void onFailure(String errorMessage);
    }


    public void getCustomer(String email, String password, LoginCallback loginCallback){

        Call<List<Customer>> call = apiService.getAllCustomers();

        Log.d("Request", "Request URL: " + call.request().url());

        call.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                Log.d("Response", "response is " + response.errorBody() + " " + response.isSuccessful());
                if(response.isSuccessful()){
                    Log.d("call GetCustomer", "Status code: " + response.code());
                    if(response.body() != null) {
                        customerList.addAll(response.body());
                    }
                    for(Customer c: customerList){
                        if(c.getClientEmail().equals(email) && PasswordEncoderService.verifyPassword(password, c.getPassword())){
                            loginCallback.onSuccess(c);
                            return;
                        }
                    }
                    loginCallback.onFailure("Customer not found");
                }
                else{
                    String errorBody = response.errorBody() != null ? response.errorBody().toString() : "Unknown Error";
                    Log.d("call GetCustomer failed ", "Status code: " + response.code() + " " + errorBody);
//                    Log.d("call GetCustomer failed ", "Status code: " + response.code() + response.body());
                    loginCallback.onFailure("API call failed with status code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                Log.d("GetCustomers", "Get failed: " + t.toString());
                loginCallback.onFailure("API call failed: " + t.toString());
            }
        });
    }


    public void login(String email, String password, LoginCallback loginCallback){
        getCustomer(email, password, new LoginCallback() {
            @Override
            public void onSuccess(Customer customer) {
                loginCallback.onSuccess(customer);
            }

            @Override
            public void onFailure(String errorMessage) {
                loginCallback.onFailure(errorMessage);
            }
        });
    }
}
