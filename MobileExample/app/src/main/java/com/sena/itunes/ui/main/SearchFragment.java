package com.sena.itunes.ui.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sena.itunes.BR;
import com.sena.itunes.R;
import com.sena.itunes.databinding.SearchFragmentBinding;
import com.sena.itunes.ui.base.BaseFragment;
import com.sena.itunes.ui.base.BaseNavigator;
import com.sena.itunes.ui.base.ViewModelFactory;

import javax.inject.Inject;

public class SearchFragment extends BaseFragment<SearchFragmentBinding, MainViewModel> implements BaseNavigator {
    @Inject
    ViewModelFactory viewModelFactory;
    private MainViewModel mainViewModel;
    private SearchFragmentBinding searchFragmentBinding;
    private ItemViewAdapter itemViewAdapter;

    public SearchFragment() {
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
        searchFragmentBinding = getViewDataBinding();
        mainViewModel.setNavigator(this);
        setObservers();
        return searchFragmentBinding.getRoot();
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
        return R.layout.search_fragment;
    }

    @Override
    public MainViewModel getViewModel() {
        return mainViewModel;
    }

    private void setObservers() {
        mainViewModel.getContentMutableLiveData().observe(this, data -> {
            if (data != null) {
                itemViewAdapter = new ItemViewAdapter(getContext(), data);
                itemViewAdapter.setOnItemClickListener((view, item) -> {
                    mainViewModel.setDetailContent(item);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, new DetailFragment(item))
                            .addToBackStack(null).commit();
                });
                searchFragmentBinding.list.setAdapter(itemViewAdapter);
            }
        });

    }
}
