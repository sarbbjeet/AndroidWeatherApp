package com.tees.ac.uk.a0321466.weatherapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class WeatherDataService {

    private static Context cx;
    JSONObject _response;
    public WeatherDataService(Context context) {
           cx= context;
    }

     //callback function
    public interface volleyResponseListener{
      void onError(String errorMsg);
      void onResponse(JSONObject object);
    }

    public void getHttpData(String url, final volleyResponseListener callback){

        RequestQueue queue = Volley.newRequestQueue(cx);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                       callback.onResponse(response);
                       // _response=response;
                        //Toast.makeText(cx,"yooo",Toast.LENGTH_SHORT).show();
                        //textView.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                         //_response=null;
                       callback.onError("data error! please enter correct city name");
                        //Toast.makeText(cx,"Sorry error",Toast.LENGTH_SHORT).show();

                    }
                });



// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }

}
