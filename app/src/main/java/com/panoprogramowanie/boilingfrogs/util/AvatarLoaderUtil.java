package com.panoprogramowanie.boilingfrogs.util;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.panoprogramowanie.boilingfrogs.R;

/**
 * Created by Wojciech on 11.01.2016.
 */
public class AvatarLoaderUtil {
    public static void loadAvatar(Context context, String from, ImageView into, int placeholder) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .showImageOnLoading(placeholder)
                .showImageOnFail(placeholder)
                .build();

        ImageLoader.getInstance().displayImage(from, into, options);
    }
}
