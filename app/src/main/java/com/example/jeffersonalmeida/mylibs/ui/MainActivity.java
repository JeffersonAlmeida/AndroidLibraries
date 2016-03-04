package com.example.jeffersonalmeida.mylibs.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.jeffersonalmeida.mylibs.R;
import com.example.jeffersonalmeida.mylibs.adapter.MyAdapter;
import com.example.jeffersonalmeida.mylibs.application.App;
import com.example.jeffersonalmeida.mylibs.model.Video;
import com.example.jeffersonalmeida.mylibs.rest.RchloVideosRestInterface;
import com.example.jeffersonalmeida.mylibs.util.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    @Inject RchloVideosRestInterface restInterface;
    @Bind(R.id.my_recycler_view) RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Video> videos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getNetComponent().inject(this);

        final Call<List<Video>> videos = restInterface.downloadVideos();
        videos.enqueue(videosCallBack);

        mRecyclerView.setHasFixedSize(true);


        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        onItemClicked(position);
                    }
                })
        );

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        this.videos = new ArrayList<Video>();
        mAdapter = new MyAdapter( this, this.videos);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void onItemClicked(int position) {
        this.videos = mAdapter.getVideos();
        Video video = this.videos.get(position);
        Intent i = new Intent(MainActivity.this, ShowImageWithDetails.class);
        i.putExtra("video", video);
        startActivity(i);
    }

    private Callback<List<Video>> videosCallBack = new Callback<List<Video>>() {
        @Override
        public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
            if (response.isSuccess()) {
                List<Video> videos = response.body();
                showVideosList(videos);
            } else {
                int statusCode = response.code();
                ResponseBody errorBody = response.errorBody();
            }
        }

        @Override
        public void onFailure(Call<List<Video>> call, Throwable t) {
            Log.d("FAILURE", "FAILURE");
        }
    };

    private void showVideosList(List<Video> videos) {
        mAdapter.addMoreVideos(videos);
    }

}
