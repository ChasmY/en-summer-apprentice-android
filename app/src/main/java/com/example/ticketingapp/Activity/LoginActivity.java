package com.example.ticketingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticketingapp.Model.Customer;
import com.example.ticketingapp.R;
import com.example.ticketingapp.Service.LoginService;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView email = findViewById(R.id.customer_email);
        TextView password = findViewById(R.id.customer_password);

        LoginService loginService = new LoginService();

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                loginService.login(email.getText().toString(), password.getText().toString(), new LoginService.LoginCallback() {
                    @Override
                    public void onSuccess(Customer customer) {
                        Toast.makeText(LoginActivity.this, "Log in successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("customerId", customer.getClientId());
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Toast.makeText(LoginActivity.this, "Log in failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        TextView signinbtn = findViewById(R.id.signinbtn);
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }
}
