package com.example.jeffersonalmeida.mylibs.dagger;


import com.example.jeffersonalmeida.mylibs.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jeffersonalmeida on 3/1/16.
 */

@Singleton
@Component( modules = NetModule.class )
public interface NetComponent {

    public void inject (MainActivity mainActivity);

}
