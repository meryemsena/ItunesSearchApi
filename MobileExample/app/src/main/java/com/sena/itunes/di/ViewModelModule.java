package com.sena.itunes.di;

import androidx.lifecycle.ViewModel;

import com.sena.itunes.ui.main.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    protected abstract ViewModel mainViewModel(MainViewModel menuViewModel);
}
