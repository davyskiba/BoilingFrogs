package com.panoprogramowanie.boilingfrogs.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by wdawi on 24.01.2016.
 */
public class BrowserLaunchingUtil {

    public static void launchBrowser(Context context, int urlId)
    {
        String url=context.getString(urlId);
        launchBrowser(context,url);
    }

    public static void launchBrowser(Context context, String url)
    {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse(url));
        context.startActivity(intent);
    }
}
