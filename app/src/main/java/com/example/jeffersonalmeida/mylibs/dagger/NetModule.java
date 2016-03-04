package com.example.jeffersonalmeida.mylibs.dagger;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.jeffersonalmeida.mylibs.rest.RchloVideosRestInterface;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jeffersonalmeida on 3/1/16.
 */

@Module
public class NetModule {

    private static final String BASE_URL = "http://www.riachuelo.com.br/";

    private Application application;

    public NetModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return this.application;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    RchloVideosRestInterface provideRchloVideosRestInterface(Retrofit retrofit) {
        RchloVideosRestInterface rchloVideosRestInterface = retrofit.create(RchloVideosRestInterface.class);
        return rchloVideosRestInterface;
    }

}
