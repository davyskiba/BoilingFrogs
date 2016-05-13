package com.panoprogramowanie.boilingfrogs.dagger;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.content.Context;

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

    private ScheduleSupplier scheduleSupplier;
    private Context applicationContext;

    public MainModule(Context context) {
        scheduleSupplier = new GreenDaoScheduleSupplier(context);
        this.applicationContext=context;
    }

    @Provides
    ScheduleSupplier provideScheduleSupplier() {
        return scheduleSupplier;
    }

    @Singleton
    @Provides
    NavigationSupplier provideNavigationSupplier(ScheduleSupplier scheduleSupplier) {
        return new NavigationSupplierImpl(scheduleSupplier);
    }

    @Provides
    NotificationSupplier provideNotificationSupplier() {
        return new NotificationSupplierImpl();
    }

    @Provides
    @Singleton
    ScheduleService provideScheduleService(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson=new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(applicationContext.getString(R.string.schedule_server_url))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit.create(ScheduleService.class);
    }
}
