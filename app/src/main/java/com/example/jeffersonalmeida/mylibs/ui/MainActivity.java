package com.example.jeffersonalmeida.mylibs.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.jeffersonalmeida.mylibs.R;
import com.example.jeffersonalmeida.mylibs.adapter.MyAdapter;
import com.example.jeffersonalmeida.mylibs.application.App;
import com.example.jeffersonalmeida.mylibs.model.Video;
import com.example.jeffersonalmeida.mylibs.rest.RestInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    @Bind(R.id.my_recycler_view) RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RestInterface apiService = App.getRestApi().getApiService();
        Call<List<Video>> videos = apiService.videos();
        videos.enqueue(videosCallBack);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter( this, new ArrayList<Video>());
        mRecyclerView.setAdapter(mAdapter);

    }

    private Callback<List<Video>> videosCallBack = new Callback<List<Video>>() {
        @Override
        public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
            if (response.isSuccess()) {
                List<Video> videos = response.body();
                showVideosList(videos);
            } else {
                int statusCode = response.code();
                // handle request errors yourself
                ResponseBody errorBody = response.errorBody();
            }
        }

        @Override
        public void onFailure(Call<List<Video>> call, Throwable t) {
            // handle execution failures like no internet connectivity
            Log.d("FAILURE", "FAILURE");
        }
    };

    private void showVideosList(List<Video> videos) {
        mAdapter.addMoreVideos(videos);
    }

}
