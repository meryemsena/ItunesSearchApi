package com.sena.itunes.di;

import android.app.Application;
import android.content.Context;

import com.sena.itunes.MobileExample;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Sena KILIÇ on 1/10/2022.
 */

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        FragmentModule.class,
        ActivityModule.class,
        ViewModelModule.class,
        NetModule.class})
public interface AppComponent {

    void inject(MobileExample mobileExample);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder applicationContext(@Named("ApplicationContext") Context context);

        AppComponent build();
    }

}
