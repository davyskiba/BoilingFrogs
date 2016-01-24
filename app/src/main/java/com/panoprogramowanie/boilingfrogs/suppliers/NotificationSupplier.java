package com.panoprogramowanie.boilingfrogs.suppliers;

import android.view.View;

/**
 * Created by Wojciech on 20.01.2016.
 */
public interface NotificationSupplier {
    void showShortLastingSnackbar(View view,String text);
    void showShortLastingSnackbar(View view,int textResource);

    void showLongLastingSnackbar(View view,String text);
    void showLongLastingSnackbar(View view,int textResource);
}
