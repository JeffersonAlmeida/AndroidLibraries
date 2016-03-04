package com.example.jeffersonalmeida.mylibs.application;

import android.app.Application;

import com.example.jeffersonalmeida.mylibs.dagger.DaggerNetComponent;
import com.example.jeffersonalmeida.mylibs.dagger.NetComponent;
import com.example.jeffersonalmeida.mylibs.dagger.NetModule;

/**
 * Created by jeffersonalmeida on 3/4/16.
 */
public class App extends Application {

    private static NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        netComponent = DaggerNetComponent
                .builder()
                .netModule(new NetModule(this))
                .build();
    }

    public static NetComponent getNetComponent() {
        return netComponent;
    }
}
