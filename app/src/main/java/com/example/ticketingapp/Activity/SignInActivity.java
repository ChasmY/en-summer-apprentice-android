package com.example.ticketingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticketingapp.Model.Dto.CustomerPost;
import com.example.ticketingapp.R;
import com.example.ticketingapp.Service.SigninService;
import com.google.android.material.button.MaterialButton;


public class SignInActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        TextView username = findViewById(R.id.customer_name);
        TextView email = findViewById(R.id.customer_email);
        TextView password = findViewById(R.id.customer_password);

        SigninService signinService = new SigninService();

        MaterialButton signinbtn = (MaterialButton) findViewById(R.id.signinbtn);


        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerPost customerPost = new CustomerPost(username.getText().toString(), email.getText().toString(), password.getText().toString());

                Log.d("Customer Post activity", "Customer is" + customerPost.toString());

                Log.d("Customer Post", "Customer is" + customerPost.toString());
                signinService.addCustomer(customerPost, new SigninService.AddCustomerCallback() {
                    @Override
                    public void onSuccess() {
                        // Customer added successfully
                        Intent intent = new Intent(SignInActivity.this, LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(SignInActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        // Customer addition failed
                        Toast.makeText(SignInActivity.this, "Sign in failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        TextView loginbtn = findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the login activity
                Intent intent = new Intent(SignInActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
}
}
