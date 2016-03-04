package com.example.jeffersonalmeida.mylibs.application;

import android.app.Application;

import com.example.jeffersonalmeida.mylibs.rest.RestClient;

/**
 * Created by jeffersonalmeida on 3/4/16.
 */
public class App extends Application {

    private static RestClient restClient;

    @Override
    public void onCreate() {
        super.onCreate();

        restClient = new RestClient();

    }

    public static RestClient getRestApi(){
        return restClient;
    }
}
