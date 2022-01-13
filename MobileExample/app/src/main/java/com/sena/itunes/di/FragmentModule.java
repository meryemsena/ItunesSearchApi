package com.sena.itunes.di;


import com.sena.itunes.ui.main.DetailFragment;
import com.sena.itunes.ui.main.SearchFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract DetailFragment contributeDetailFragment();

    @ContributesAndroidInjector
    abstract SearchFragment contributeSearchFragment();
}
