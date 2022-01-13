package com.sena.itunes.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.sena.itunes.BR;
import com.sena.itunes.R;
import com.sena.itunes.databinding.MainActivityBinding;
import com.sena.itunes.ui.base.BaseActivity;
import com.sena.itunes.ui.base.BaseNavigator;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity<MainActivityBinding, MainViewModel> implements HasSupportFragmentInjector, BaseNavigator {
    private ItemViewAdapter itemViewAdapter;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Inject
    MainViewModel mainViewModel;
    private MainActivityBinding mainActivityBinding;
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    public MainViewModel getViewModel() {
        return mainViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityBinding = getViewDataBinding();
        mainViewModel.setNavigator(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new SearchFragment())
                .commitNow();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }


}
