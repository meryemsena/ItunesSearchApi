package com.sena.itunes.ui.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sena.itunes.BR;
import com.sena.itunes.R;
import com.sena.itunes.databinding.DetailFragmentBinding;
import com.sena.itunes.model.ResultContent;
import com.sena.itunes.ui.base.ViewModelFactory;
import com.sena.itunes.ui.base.BaseFragment;
import com.sena.itunes.ui.base.BaseNavigator;

import javax.inject.Inject;

public class DetailFragment extends BaseFragment<DetailFragmentBinding, MainViewModel> implements BaseNavigator {
    @Inject
    ViewModelFactory viewModelFactory;
    private MainViewModel mainViewModel;
    private DetailFragmentBinding detailFragmentBinding;
    private ResultContent.ItemContent itemContent;

    public DetailFragment(ResultContent.ItemContent itemContent) {
        this.itemContent = itemContent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mainViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(MainViewModel.class);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        detailFragmentBinding = getViewDataBinding();
        mainViewModel.setNavigator(this);
        return detailFragmentBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.detail_fragment;
    }

    @Override
    public MainViewModel getViewModel() {
        return mainViewModel;
    }
}
