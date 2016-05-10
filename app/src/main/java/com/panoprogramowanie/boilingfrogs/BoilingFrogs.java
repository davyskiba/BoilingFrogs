package com.panoprogramowanie.boilingfrogs;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.panoprogramowanie.boilingfrogs.dagger.DaggerMainComponent;
import com.panoprogramowanie.boilingfrogs.dagger.MainComponent;
import com.panoprogramowanie.boilingfrogs.dagger.MainModule;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Wojciech on 30.12.2015.
 */
public class BoilingFrogs extends Application {

    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (!BuildConfig.DEBUG) {
            Fabric.with(this, new Crashlytics());
        }

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());

        initializeMainComponent();
    }

    private void initializeMainComponent() {
        mainComponent = DaggerMainComponent.builder().mainModule(new MainModule(this)).build();
    }

    public static MainComponent getMainComponent(Context context) {
        return ((BoilingFrogs) context.getApplicationContext()).mainComponent;
    }
}
