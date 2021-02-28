package com.tees.ac.uk.a0321466.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class cityFinder extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_finder);

        ((ImageView)findViewById(R.id.goBack)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                EditText city= findViewById(R.id.searchCity);
                String _sCity= city.getText().toString();
                i.putExtra("city", _sCity);
                startActivity(i);
            }
        });


    }
}
