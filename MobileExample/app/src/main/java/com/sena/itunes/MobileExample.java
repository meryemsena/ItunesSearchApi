package com.sena.itunes;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.sena.itunes.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by Sena KILIÇ on 1/10/2022.
 */

public class MobileExample extends Application implements HasActivityInjector {

    private static Context context;
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initializeInjector();
    }

    private void initializeInjector() {
        DaggerAppComponent.builder().application(this).applicationContext(this.getApplicationContext()).build().inject(this);
    }

    @BindingAdapter({"imageUrl"})
    public static void image(ImageView imageView, String url) {
        Glide
             .with(context.getApplicationContext())
             .load(url)
             .centerCrop()
             .placeholder(R.mipmap.ic_launcher)
             .into(imageView);
    }

    @BindingAdapter("onTabSelectedListener")
    public static void setOnTabSelectedListener(TabLayout viewPager, TabLayout.OnTabSelectedListener listener) {
        viewPager.clearOnTabSelectedListeners();
        viewPager.addOnTabSelectedListener(listener);
    }

    @BindingAdapter("tabText1")
    public static void setTabText1(TabLayout tabLayout, ObservableField<String> text){
        if (text != null && text.get() != null) {
            tabLayout.removeAllTabs();
            tabLayout.addTab(tabLayout.newTab().setText(text.get()));
        }
    }

    @BindingAdapter("tabText2")
    public static void setTabText2(TabLayout tabLayout, ObservableField<String> text){
        if (text != null && text.get() != null) {
            tabLayout.addTab(tabLayout.newTab().setText(text.get()));
        }
    }

    @BindingAdapter("tabText3")
    public static void setTabText3(TabLayout tabLayout, ObservableField<String> text){
        if (text != null && text.get() != null) {
            tabLayout.addTab(tabLayout.newTab().setText(text.get()));
        }
    }

    @BindingAdapter("videoPlayer")
    public static void setVideoPlayer(VideoView videoView, String url){
        if (!url.equals("") && videoView.getContext() != null) {
            MediaController mediaController = new MediaController(videoView.getContext());
            mediaController.setAnchorView(videoView);
            Uri video = Uri.parse(url);
            videoView.setMediaController(mediaController);
            videoView.requestFocus();
            videoView.setVideoURI(video);
            videoView.start();
        }
    }
}
