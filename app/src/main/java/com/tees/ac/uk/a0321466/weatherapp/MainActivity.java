package com.tees.ac.uk.a0321466.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    LinearLayout city_finder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city_finder= findViewById(R.id.cityFinder);
        city_finder.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                Intent i = new Intent(getApplicationContext(),cityFinder.class);
                startActivity(i);
                //Toast.makeText(MainActivity.this,"button press",Toast.LENGTH_SHORT).show();
            }
        });

        Intent g= getIntent();
        String city= g.getStringExtra("city");
        if(city !=null){
            Toast.makeText(MainActivity.this, "search :" + city,Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(MainActivity.this, "sorry you entered empty value", Toast.LENGTH_SHORT).show();
        }

    }
}
