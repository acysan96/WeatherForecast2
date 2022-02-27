package vn.edu.tdc.webservice_weather.data_models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherWrapper<T> {
    @SerializedName("list")
    public ArrayList<T> items;
}
