package com.example.angeo.newproject.App;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClientTest {

    public static final String BASE_URL = "http://campuswallet.chillarcards.com/parent_app/api_2.5.1/";
    public static final String BASE_URL_Local = "http://192.168.0.223/campuswallettest/parent_app/api_2.1/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create( new GsonBuilder()
                            .setLenient()
                            .create()))
                    .build();
        }
        return retrofit;
    }
}
