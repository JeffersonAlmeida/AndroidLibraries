package com.example.jeffersonalmeida.mylibs.rest;

import com.example.jeffersonalmeida.mylibs.model.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jefferson on 23/02/16.
 */
public interface RchloVideosRestInterface {

    @GET("app/json/json-tv-riachuelo.aspx")
    Call<List<Video>> downloadVideos();

}
