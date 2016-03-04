package com.example.jeffersonalmeida.mylibs.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.NotificationTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.jeffersonalmeida.mylibs.R;
import com.example.jeffersonalmeida.mylibs.model.Video;

import org.michaelevans.colorart.library.ColorArt;
import org.michaelevans.colorart.library.FadingImageView;

import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

import static org.michaelevans.colorart.library.FadingImageView.*;

public class ShowImageWithDetails extends Activity {

    @Bind(R.id.descricao) TextView descricao;
    @Bind(R.id.zoomImage) ImageView zoomImage;
    @Bind(R.id.layout) View layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image_with_details);
        ButterKnife.bind(this);

        Video video = (Video) getIntent().getSerializableExtra("video");

        String titulo = video.getTitulo();
        descricao.setText(titulo);

        final String imagemURL = video.getImagem();
        Glide
                .with(this)
                .load(imagemURL)
                .crossFade()
                .into(zoomImage);

        new PhotoViewAttacher(zoomImage);

        Glide.with(this)
                .load(imagemURL)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Palette.generateAsync(resource, new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {
                                // Here's your generated palette
                                int def = 0x000000;
                                int vibrant = palette.getVibrantColor(def);
                                int vibrantLight = palette.getLightVibrantColor(def);
                                int vibrantDark = palette.getDarkVibrantColor(def);
                                int muted = palette.getMutedColor(def);
                                int mutedLight = palette.getLightMutedColor(def);
                                int mutedDark = palette.getDarkMutedColor(def);
                                descricao.setTextColor(vibrantDark);
                                descricao.setBackgroundColor(vibrantLight);
                            }
                        });
                    }
                });

    }


}
