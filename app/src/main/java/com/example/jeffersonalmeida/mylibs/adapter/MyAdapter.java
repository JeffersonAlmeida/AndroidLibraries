package com.example.jeffersonalmeida.mylibs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jeffersonalmeida.mylibs.R;
import com.example.jeffersonalmeida.mylibs.model.Video;

import java.util.List;

/**
 * Created by jeffersonalmeida on 3/4/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<Video> videos;

    public MyAdapter(Context context, List<Video> videos) {
        this.context = context;
        this.videos = videos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View screen = LayoutInflater.from(context)
                .inflate(R.layout.recyclerview_layout, viewGroup, false);

        MyViewHolder myViewHolder = new MyViewHolder(screen);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Video video = videos.get(position);
        String imagemURL = video.getImagem();
        ImageView imageVideo = holder.getImageVideo();
        Glide
                .with(context)
                .load(imagemURL)
                .centerCrop()
                .crossFade()
                .into(imageVideo);

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public void addMoreVideos( List<Video> videos ){
        this.videos.addAll(videos);
        notifyDataSetChanged();
    }
}
