package com.example.ticketingapp.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ticketingapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the ImageButton views

        TextView button1 = findViewById(R.id.button1);
        TextView button2 = findViewById(R.id.button2);
        TextView button3 = findViewById(R.id.button3);
        TextView button4 = findViewById(R.id.button4);

        // Set click listeners for the ImageButtons

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Eve ntActivity when imageButton2 is clicked
                Intent intent =new Intent(MainActivity.this, EventActivity.class);
                int customerId = getIntent().getIntExtra("customerId", -1);
                intent.putExtra("customerId", customerId);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the OrderActivity when imageButton3 is clicked
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                int customerId = getIntent().getIntExtra("customerId", -1);
                intent.putExtra("customerId", customerId);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, GoogleMapsActivity.class);
                startActivity(intent);}
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the OrderActivity when imageButton3 is clicked
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


}
