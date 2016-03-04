package com.example.jeffersonalmeida.mylibs.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.jeffersonalmeida.mylibs.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jeffersonalmeida on 3/4/16.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.videoImage) ImageView imageVideo;

    public MyViewHolder(View screen) {
        super(screen);
        ButterKnife.bind(this, screen);
    }

    public ImageView getImageVideo() {
        return imageVideo;
    }

}
