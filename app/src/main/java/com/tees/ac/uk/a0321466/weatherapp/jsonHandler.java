package com.tees.ac.uk.a0321466.weatherapp;

import org.json.JSONException;
import org.json.JSONObject;

public class jsonHandler {
    private Double temp;
    private String cityName;

    public jsonHandler() {


    }


    //apply json filter and update variables
    public void updateData(JSONObject jsonObject){
        try {
            JSONObject mainObject= jsonObject.getJSONObject("main"); //get "main" JsonObject
            Double tempValue= mainObject.getDouble("temp");
            setTemp(tempValue);
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
}
