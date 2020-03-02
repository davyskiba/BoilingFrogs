package com.panoprogramowanie.boilingfrogs.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Wojciech on 11.01.2016.
 */
public class AvatarLoaderUtil {

    @BindingAdapter({"bind:imageUrl", "bind:placeholder"})
    public static void setImageSrc(ImageView view, String url, Drawable placeholder) {
        loadAvatar(url, view, placeholder);
    }

    public static void loadAvatar(String from, ImageView into, Drawable placeholder) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .showImageOnLoading(placeholder)
                .showImageOnFail(placeholder)
                .build();

        ImageLoader.getInstance().displayImage(from, into, options);
    }

    public static void loadAvatar(String from, ImageView into, int placeholder) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .showImageOnLoading(placeholder)
                .showImageOnFail(placeholder)
                .build();

        ImageLoader.getInstance().displayImage(from, into, options);
    }
}
