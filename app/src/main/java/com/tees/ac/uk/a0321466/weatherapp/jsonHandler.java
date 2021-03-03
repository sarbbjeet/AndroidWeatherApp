package com.tees.ac.uk.a0321466.weatherapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class jsonHandler {
    private Double temp;
    private String cityName;
    private String weatherCondition;
    private String Country;

    //constructor
    public jsonHandler() {


    }


    //apply json filter and update variables
    public void updateData(JSONObject jsonObject){
        try {
            cityName=jsonObject.getString("name");
            JSONObject mainObject= jsonObject.getJSONObject("main"); //get "main" JsonObject
            JSONArray weatherObject= jsonObject.getJSONArray("weather");  //get weather key objects
            JSONObject sysObject= jsonObject.getJSONObject("sys"); //get "sys" JsonObject(details about country/sunset)
            Double tempValue= mainObject.getDouble("temp");
            setTemp(tempValue);
            JSONObject index1= (JSONObject) weatherObject.get(0);
            weatherCondition= index1.getString("main");
            setWeatherCondition(weatherCondition); //set weather cond. //Haze, Clouds, Clear, Mist
            Country= sysObject.getString("country");
            setCountry(Country);
            setCityName(cityName);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }
}
