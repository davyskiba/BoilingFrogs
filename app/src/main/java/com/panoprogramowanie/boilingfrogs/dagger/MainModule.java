package com.panoprogramowanie.boilingfrogs.dagger;

import android.content.Context;

import com.google.gson.Gson;
import com.panoprogramowanie.boilingfrogs.BuildConfig;
import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.api.ScheduleService;
import com.panoprogramowanie.boilingfrogs.suppliers.NavigationSupplier;
import com.panoprogramowanie.boilingfrogs.suppliers.NotificationSupplier;
import com.panoprogramowanie.boilingfrogs.suppliers.ScheduleSupplier;
import com.panoprogramowanie.boilingfrogs.suppliers.implementation.GreenDaoScheduleSupplier;
import com.panoprogramowanie.boilingfrogs.suppliers.implementation.NavigationSupplierImpl;
import com.panoprogramowanie.boilingfrogs.suppliers.implementation.NotificationSupplierImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wdawi on 24.01.2016.
 */
@Module
public class MainModule {

    private Context applicationContext;

    public MainModule(Context context) {
        this.applicationContext=context;
    }

    @Singleton
    @Provides
    ScheduleSupplier provideScheduleSupplier() {
        return  new GreenDaoScheduleSupplier(applicationContext);
    }

    @Singleton
    @Provides
    NavigationSupplier provideNavigationSupplier() {
        return new NavigationSupplierImpl();
    }

    @Provides
    NotificationSupplier provideNotificationSupplier() {
        return new NotificationSupplierImpl();
    }

    @Provides
    ScheduleService provideScheduleService(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if(BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        }

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(applicationContext.getString(R.string.schedule_server_url))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(client)
                .build();

        return retrofit.create(ScheduleService.class);
    }
}
