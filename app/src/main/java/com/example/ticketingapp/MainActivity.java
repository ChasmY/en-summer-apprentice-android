package com.example.ticketingapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the ImageButton views

        TextView button1 = findViewById(R.id.button1);
        TextView button2 = findViewById(R.id.button2);

        // Set click listeners for the ImageButtons

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Eve ntActivity when imageButton2 is clicked
                Intent intent =new Intent(MainActivity.this, EventActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the OrderActivity when imageButton3 is clicked
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });
    }


}
