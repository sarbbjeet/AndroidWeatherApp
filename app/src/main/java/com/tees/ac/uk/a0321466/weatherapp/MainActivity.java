package com.tees.ac.uk.a0321466.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    LinearLayout city_finder;   //button to move to second Activity(page)
    private static String weatherKey = "68a550b753c3784accaed012c1828338"; //key to access api
    private static String weatherUrl = "https://api.openweathermap.org/data/2.5/weather?q=";
    private String cityName = "";
    WeatherDataService _service;  //using volley library to GET request
    jsonHandler jsonData; // class to handle or filter json data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pass MainActivity instance to WeatherDataService constructor
        _service = new WeatherDataService(MainActivity.this);
         defaultDisplayData(); //display  data on startup
        //Log.d("check",_service.getHttpData("india"));

        city_finder = findViewById(R.id.cityFinder);
        city_finder.setOnClickListener(new View.OnClickListener() {  //listen click event
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), cityFinder.class);  //move to second activity(cityFinder)
                startActivity(i);
                //Toast.makeText(MainActivity.this,"button press",Toast.LENGTH_SHORT).show();
            }
        });

        //get data to second Activity(cityFinder)
        Intent g = getIntent();
        final String city = g.getStringExtra("city");  //"name" is key(get value by name key)
        if (city != null) {
            String finalUrl = weatherUrl + city + "&appid=" + weatherKey;
            _service.getHttpData(finalUrl, new WeatherDataService.volleyResponseListener() {
                @Override
                public void onError(String errorMsg) {

                }

                @Override
                public void onResponse(JSONObject object) {
                    jsonData = new jsonHandler(); //class call
                    jsonData.updateData(object);   //pass json data to function to filter data
                    //get double value and convert to string
                    String _temp = Double.toString(jsonData.getTemp());
                    String wConditions=jsonData.getWeatherCondition();

                    //pass data to view (main Activity)
                    ((TextView) findViewById(R.id.temperature)).setText(_temp); //display data to textView
                    ((TextView) findViewById(R.id.city)).setText((jsonData.getCityName())+"," + (jsonData.getCountry())); //city,country name display
                    ((TextView) findViewById(R.id.weatherConditions)).setText(wConditions); //print weather conditions

                    //now change image according to weather conditions
                    changeImage(jsonData.getWeatherCondition());


                }
            });

            //Toast.makeText(MainActivity.this, "search :" + "yo get ",Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(MainActivity.this, "sorry you entered empty value", Toast.LENGTH_SHORT).show();
        }

    }




    //display data on the app when app is start first time
    //so I will display london weather data
    private void defaultDisplayData() {
         String finalUrl = weatherUrl + "london" + "&appid=" + weatherKey;
        _service.getHttpData(finalUrl, new WeatherDataService.volleyResponseListener() {
            @Override
            public void onError(String errorMsg) {
            Toast.makeText(MainActivity.this, "error to get data from server", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(JSONObject object) {
                jsonData = new jsonHandler();
                jsonData.updateData(object);
                //get double value and convert to string
                String _temp = Double.toString(jsonData.getTemp());

                //pass data to app screen(main Activity)
                ((TextView) findViewById(R.id.temperature)).setText(_temp); //display data to textView
                ((TextView) findViewById(R.id.city)).setText((jsonData.getCityName())+"," + (jsonData.getCountry())); //city,country name display
                ((TextView) findViewById(R.id.weatherConditions)).setText(jsonData.getWeatherCondition()); //print weather conditions
                //now change image according to weather conditions
                changeImage(jsonData.getWeatherCondition());

            }
        });

    }

    private void changeImage(String wCondtions){
        switch (wCondtions){
            case "Mist":
                ((ImageView)findViewById(R.id.weatherIcon)).setImageResource(R.drawable.fog);
                break;
            case "Clouds":
                ((ImageView)findViewById(R.id.weatherIcon)).setImageResource(R.drawable.cloudy);
                break;
            case "Clear":
                ((ImageView)findViewById(R.id.weatherIcon)).setImageResource(R.drawable.sunny);
                break;
            case "Haze":
                ((ImageView)findViewById(R.id.weatherIcon)).setImageResource(R.drawable.overcast);
                break;
                default:
                    ((ImageView)findViewById(R.id.weatherIcon)).setImageResource(R.drawable.finding);
                    break;

        }


    }
}

