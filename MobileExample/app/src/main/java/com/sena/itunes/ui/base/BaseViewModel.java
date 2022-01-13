package com.sena.itunes.ui.base;

import androidx.lifecycle.ViewModel;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Sena KILIÇ on 1/10/2022.
 */

public abstract class BaseViewModel <N> extends ViewModel {
    private WeakReference<N> mNavigator;
    private CompositeDisposable mCompositeDisposable;


    public BaseViewModel() {
        this.mCompositeDisposable = new CompositeDisposable();
    }

    public N getNavigator() {
        return mNavigator.get();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }


}
