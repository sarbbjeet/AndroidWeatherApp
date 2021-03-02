package com.tees.ac.uk.a0321466.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class cityFinder extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_finder);

        //back button event
        ((ImageView)findViewById(R.id.goBack)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleActivity(""); //go back without data (pass ""  to mainActivity)
            }
        });

        //cancel button
        ((Button)findViewById(R.id.btnCancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleActivity("");  //send empty string data
            }
        });
            //ok button
        ((Button)findViewById(R.id.btnOk)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edCity=findViewById(R.id.searchCity);
                handleActivity(edCity.getText().toString());  //pass enterted city name
            }
        });
    }

//    pass data from activity_city_finder to main activity
     private void handleActivity(String s){
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        if(s !=""){
            i.putExtra("city",s);
        }
        else {
            i.putExtra("city", "");
        }
        startActivity(i);
    }
}
