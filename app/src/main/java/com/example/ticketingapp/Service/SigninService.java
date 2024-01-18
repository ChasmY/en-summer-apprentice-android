package com.example.ticketingapp.Service;

import android.util.Log;

import com.example.ticketingapp.Model.Customer;
import com.example.ticketingapp.Model.Dto.CustomerPost;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninService {
    private List<Customer> customerList;
    private ApiService apiService;


    public SigninService() {
        this.customerList = new ArrayList<>();
        this.apiService = RetrofitService.getEventApi().create(ApiService.class);
    }
    public CustomerPost getCustomer(CustomerPost customerPost){
        Call<List<Customer>> callCustomers = apiService.getAllCustomers();

        Log.d("Request", "Request URL: " + callCustomers.request().url());


        callCustomers.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                if(response.isSuccessful() && response.body()!= null){
                    customerList.addAll(response.body());
                    Log.d("call GetCustomer", "Status code: " + response.code());

                }
                else{
                    Log.d("call GetCustomer", "Status code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                Log.d("GetCustomers", "Get failed: " + t.toString());

            }
        });
        for(Customer c: customerList){
            if(c.getClientEmail().equals(customerPost.getEmail()) && c.getClientName().equals(customerPost.getName()) && c.getPassword().equals(customerPost.getPassword())){
                return customerPost;
            }
        }
        return null;
    }

    public interface AddCustomerCallback {
        void onSuccess();
        void onFailure(String errorMessage);
    }

    public void addCustomer(CustomerPost customerPost, AddCustomerCallback callback){
        if(getCustomer(customerPost) == null){
            customerPost.setPassword(PasswordEncoderService.encodePassword(customerPost.getPassword()));
            Call<Void> callCustomer = apiService.customerPost(customerPost);
            callCustomer.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Log.d("Customer Post", "Status code: " + response.code());
                        Log.d("Customer Post", "Customer is" + customerPost.toString());
                        callback.onSuccess();
                    } else {
                        Log.d("CustomerPost", "Unsuccessful response: " + response.code());
                        callback.onFailure("Unsuccessful response: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("CustomerPost", "Patch failed " + t.toString());
                    callback.onFailure("Patch failed: " + t.toString());
                }
            });
        }
    }

}
